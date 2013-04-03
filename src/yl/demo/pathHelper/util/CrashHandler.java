/**
 * CrashHandler.java 	Version <1.00>	2013-1-16
 *
 * Copyright(C) 2009-2012  All rights reserved. 
 * Lu Zhiyong is a student majoring in Software Engineering (Communication Software), 
 * from the School of Software, SUN YAT-SEN UNIVERSITY, GZ 510006, P. R. China.
 *
 * Blog: http://www.gzayong.info
 */
package yl.demo.pathHelper.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * UncaughtException������,��������Uncaught�쳣��ʱ��,�ɸ������ӹܳ���,����¼���ʹ��󱨸�.
 * 
 * @author YONG
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {
	private static final String TAG = "CrashHandler";
	
	/**
	 * ϵͳĬ�ϵ�UncaughtException������
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	
	private static CrashHandler INSTANCE = new CrashHandler();
	private Context mContext;
	private String url;
	private String logFileSavePath = "/tmp";
	
	/**
	 * �����洢�豸��Ϣ���쳣��Ϣ
	 */
	private Map<String, String> infos = new HashMap<String, String>();
	
	/**
	 * ���ڸ�ʽ������,��Ϊ��־�ļ�����һ����
	 */
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	private CrashHandler() {

	}

	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	public void init(Context context, String errCollectUrl) {
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();		// ��ȡϵͳĬ�ϵ�UncaughtException������
		Thread.setDefaultUncaughtExceptionHandler(this);								// ���ø�CrashHandlerΪ�����Ĭ�ϴ�����
		url = errCollectUrl;
	}

	/**
	 * ��UncaughtException����ʱ��ת�����д�ķ���������
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// ����Զ����û�д�������ϵͳĬ�ϵ��쳣������������
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(3000);		// ��������ˣ��ó����������3�����˳�����֤�ļ����沢�ϴ���������
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		}
	}

	/**
	 * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.
	 * 
	 * @param ex		�쳣��Ϣ
	 * @return true 	��������˸��쳣��Ϣ;���򷵻�false.
	 */
	public boolean handleException(final Throwable ex) {
		if (ex == null)
			return false;
		new Thread() {
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, "�ܱ�Ǹ,��������쳣,�����˳�", 0).show();
				Looper.loop();
			}
		}.start();
		
		collectDeviceInfo(mContext);
		
		new Thread() {
			public void run() {
				String filename = saveCrashInfo2File(ex);
				send2Server(filename);
			};
		}.start();
		return true;
	}

	/**
	 * �ռ��豸������Ϣ
	 * 
	 * @param context
	 */
	public void collectDeviceInfo(Context context) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo info = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES); 
			if (info != null) {
				infos.put("appName", info.applicationInfo.name);
				infos.put("versionName", info.versionName == null ? "null" : info.versionName);
				infos.put("versionCode", info.versionCode + "");
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get("").toString());
				Log.d(TAG, field.getName() + ":" + field.get(""));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private StringBuffer sb;
	private String saveCrashInfo2File(Throwable ex) {
		sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\r\n");
		}
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		
		long timetamp = System.currentTimeMillis();
		String time = format.format(new Date());
		String fileName = "crash-" + time + "-" + timetamp + ".log";
		try {
			File dir = new File(logFileSavePath + File.separator + "crash");
			Log.i(TAG, dir.toString());
			if (!dir.exists())
				dir.mkdir();
			FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
			fos.write(sb.toString().getBytes());
			fos.close();
			return fileName;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void send2Server(String filename) {
		if(url == null || url.length() == 0)
			return ;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			JSONObject object = new JSONObject();
			object.put(mContext.getPackageName(), sb.toString());
			BasicNameValuePair pair = new BasicNameValuePair("errorContent", object.toString());
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(pair);
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode() == 200) {
				Log.e(TAG, "Upload Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLogFileSavePath() {
		return logFileSavePath;
	}
}
