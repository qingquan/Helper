package yl.demo.pathHelper.Network;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import yl.demo.pathHelper.util.FileUtil;

public class HttpClientHandler {
	private final static String BASE_URL = "";
	private String mBaseUrl;
	private String mFileName;
	private OnGetFinishedListener mOnFinishedListener;
	
	public HttpClientHandler(String fileName) {
		// TODO Auto-generated constructor stub
		mBaseUrl = BASE_URL;
		mFileName = fileName;
	}
	
	public HttpClientHandler(String baseUrl, String fileName) {
		mBaseUrl = baseUrl;
		mFileName = fileName;
	}
	
	public void execute() {
		HttpGet httpRequest = new HttpGet(mBaseUrl+mFileName);  
        //取得HttpClient对象  
		HttpClient httpclient = new DefaultHttpClient();  
        //请求HttpClient，取得HttpResponse  
        HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //请求成功  
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)  
           {  
               //取得返回的字符串  
               try {
				String result = EntityUtils.toString(httpResponse.getEntity());
				FileUtil.saveFile(mFileName, result.getBytes());
				if ( mOnFinishedListener != null )
					mOnFinishedListener.onGetFinished();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
                 
           }   
	}
	
	public void setOnGetFinishedListener(OnGetFinishedListener onGetFinishedListener) {
		mOnFinishedListener = onGetFinishedListener;
	}
	
	public interface OnGetFinishedListener {
		public void onGetFinished();
	};

};
