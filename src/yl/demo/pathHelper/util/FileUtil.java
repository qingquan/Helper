/**
 * FileUtil.java 	Version <1.00>	2012-8-18
 *
 * Copyright(C) 2009-2012  All rights reserved. 
 * Lu Zhiyong is a student majoring in Software Engineering (Communication Software), 
 * from the School of Software, SUN YAT-SEN UNIVERSITY, GZ 510006, P. R. China.
 *
 * Blog: http://www.gzayong.info
 */
package yl.demo.pathHelper.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.media.audiofx.EnvironmentalReverb;
import android.os.Environment;


public class FileUtil {
	public static final String BASE_PATH = "/sdcard/pathHelper/"; 
	
	public final static String FILE_CONFIG = "Sysconfig.xml";
	public final static String FILE_DB_PATH = "path.txt";
	public final static String FILE_DB_BUILDING = "building.txt";
	public final static String FILE_DB_FLOOR = "floor.txt";
	public final static String FILE_DB_MAP = "map.txt";
	public final static String FILE_DB_CORNER = "corner.txt";
	public final static String FILE_DB_FACILITY = "facility.txt";
	
	public final static String FILE_DATA_HISTORY = "queryHistory.txt";
	
	public static boolean initDatabase(Context context, String assetsFileName) {
		File databaseFolder = new File(context.getFilesDir().getParentFile().getAbsolutePath() + "/databases/");
		if(!databaseFolder.exists() || !databaseFolder.isDirectory()) {
			databaseFolder.mkdirs();
		}
		try {
			InputStream inputStream = context.getAssets().open(assetsFileName);
			FileOutputStream output = new FileOutputStream(databaseFolder.getAbsolutePath() + "/" + assetsFileName);
			byte[] buf = new byte[10240];
			int count = 0;
			while ((count = inputStream.read(buf)) > 0) {
				output.write(buf, 0, count);
			}
			output.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static void saveFile(String fileName, byte[] buffer ) {
		File file = new File(BASE_PATH + fileName);
		if ( !file.exists() ) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(file);
			outStream.write(buffer);
			outStream.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
         
	}
	
	public static InputStream getInstreamFromFile(String fileName ) {
		File file = new File(BASE_PATH+fileName);
		InputStream inputStream = null;
		if ( file.exists() ) {
			try {
				inputStream = new FileInputStream(file);
				inputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		return inputStream;
	}
	
	public static String getStringFromFile(String fileName) {
		File file = new File(BASE_PATH+fileName);
		String pathString = "";
		String tempLine = null;
		BufferedReader bufferedReader = null;
		if ( file.exists() ) {
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				try {
					while ( ( tempLine = bufferedReader.readLine() ) != null ) {
						pathString += tempLine;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return pathString;
	}
	
	public static String[] getQueryHistory(Context context) {
		String res = "";
		String inputHistorys[];
		String reversedInputHistorys[];
		try {
			FileInputStream fin = context.openFileInput(FILE_DATA_HISTORY);
			int length = fin.available(); 
			byte [] buffer = new byte[length];   
	        fin.read(buffer);       
	        res = EncodingUtils.getString(buffer, "UTF-8");   
	        fin.close();   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		inputHistorys = res.split(" ");
		reversedInputHistorys = new String[inputHistorys.length];
		for ( int i = 0 ; i < inputHistorys.length; i++ ) {
			reversedInputHistorys[i] = inputHistorys[inputHistorys.length-i-1];
		}
		return reversedInputHistorys;
	}
	
	public static void saveQueryHistory(Context context, String text) {
		if ( isQueryHistoryExist(context, text) )
			return;
		try {
			FileOutputStream fout =context.openFileOutput(FILE_DATA_HISTORY, context.MODE_APPEND);
			text += " ";
			fout.write(text.getBytes());
			fout.flush();
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static boolean isQueryHistoryExist(Context context, String text) {
		String[] queryHistorys = getQueryHistory(context);
		for ( int i = 0; i < queryHistorys.length; i++ ) {
			if ( queryHistorys[i].equals(text) )
				return true;
		}
		return false;
	}
	
	public static void createDirs() {
		File file = new File(Environment.getExternalStorageDirectory() + "/PathHelper/fingerPrinting/");
		if ( !file.exists() ) 
			file.mkdirs();
	}
	
	public static String getFingerPrintingDirectoryPath() {
		return Environment.getExternalStorageDirectory() + "/PathHelper/fingerPrinting/";
	}
	
}
