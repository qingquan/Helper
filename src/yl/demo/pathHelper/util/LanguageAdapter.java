package yl.demo.pathHelper.util;

import yl.demo.pathHelper.db.model.Facility;
import yl.demo.pathHelper.db.model.Model;
import android.content.Context;

public class LanguageAdapter {
	public final static int LANGUAGE_ZH = 0;
	public final static int LANGUAGE_EN = 1;
	private Context mContext;
	private int mCurrentLanguage;
	private static LanguageAdapter mInstance;
	
	private LanguageAdapter(Context context) {
		// TODO Auto-generated constructor stub
		PreferenceUtils.initPreference(context);
		mCurrentLanguage = PreferenceUtils.getIntValue(context, PreferenceUtils.KEY_LANGUAGE);
		mContext = context;
	}
	
	public static LanguageAdapter getInstance(Context context) {
		if ( mInstance == null )
			mInstance = new LanguageAdapter(context);
		return mInstance;
	}
	
	public void setCurrentLanguage(int language) {
		PreferenceUtils.saveIntValue(mContext, PreferenceUtils.KEY_LANGUAGE, language);
		mCurrentLanguage = language;
	}
	
	public String getFileNameWithLanguageAdaptationFromAssets(String fileName) {
	
		return getFullFileName(fileName);
	}
	
	public int getIdWithLanguageAdaptation(String src, String fileName) {
		String fullFileName = getFullFileName(fileName);
		return mContext.getResources().getIdentifier(fullFileName, src, mContext.getPackageName());
	}
	
	public String getStringFromDbFacility(Facility facility) {
		if ( mCurrentLanguage == LANGUAGE_ZH ) 
			return facility.getName();
		else if ( mCurrentLanguage == LANGUAGE_EN )
			return facility.getNameEn();
		return null;
		
	}
	
	public int getLanguage() {
		return mCurrentLanguage;
	}
	
	private String getFullFileName(String fileName) {
		int point = fileName.indexOf('.');
		String suffix = null;
		if ( mCurrentLanguage == LANGUAGE_ZH ) {
			suffix = "_zh";
		} else if ( mCurrentLanguage == LANGUAGE_EN ) {
			suffix = "_en";
		}
		if ( point == -1 ) {
			return fileName + suffix;
		} else {
			suffix += ".";
			return fileName.replace(".", suffix);
		}
	}

}
