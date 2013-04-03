package yl.demo.pathHelper.view;

import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import yl.demo.pathHelper.MainActivity;
import yl.demo.pathHelper.R;
import yl.demo.pathHelper.Adapter.SelectDialogAdapter;
import yl.demo.pathHelper.Network.SocketClientHandler;
import yl.demo.pathHelper.db.model.Model;
import yl.demo.pathHelper.model.Location;
import yl.demo.pathHelper.util.LanguageAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.ListView;

public class DialogFactory {
	private static DialogFactory mInstance;
	private Context mContext;

	public static DialogFactory getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DialogFactory(context);
		}
		mInstance.setContext(context);
		return mInstance;
	}

	private DialogFactory(Context context) {
		mContext = context;
	}
	
	private void setContext(Context context) {
		mContext = context;
	}
	
	public Dialog getSelectDialog(AdapterView.OnItemClickListener lsn, List<Model> facilities, Location location) {
		ListView listView = new ListView(mContext);
		listView.setAdapter(new SelectDialogAdapter(mContext, facilities, location));
		listView.setOnItemClickListener(lsn);
		Dialog dialog = new AlertDialog.Builder(mContext).setView(listView).setTitle("��ѡ��").setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).create();
		
		return dialog;
	}
	
	public Dialog getLanguageChoiceDialog(final Handler handler) {
		final LanguageAdapter languageAdapter = LanguageAdapter.getInstance(mContext);
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);    
        builder.setTitle(languageAdapter.getIdWithLanguageAdaptation("string", "title_select_language"));  
        final DialogInterface.OnClickListener choiceListener =   
            new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					languageAdapter.setCurrentLanguage(which);
				}};  
        builder.setSingleChoiceItems(R.array.language, languageAdapter.getLanguage(), choiceListener);  
          
        DialogInterface.OnClickListener btnListener =   
            new DialogInterface.OnClickListener() {  
                @Override  
                public void onClick(DialogInterface dialogInterface, int which) {  
                	handler.sendEmptyMessage(MainActivity.MSG_UPDATE_LANGUAGE);
                }  
            };  
        builder.setPositiveButton(languageAdapter.getIdWithLanguageAdaptation("string", "dialog_ok"), btnListener);  
        return builder.create();  
	}
	
	public Dialog getUploadPositionDialog(final HashMap<String, Double> rssVec, Location location) {
		LanguageAdapter languageAdapter = LanguageAdapter.getInstance(mContext);
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(languageAdapter.getIdWithLanguageAdaptation("string", "title_alert"));
		builder.setMessage(languageAdapter.getIdWithLanguageAdaptation("string", "dialog_info_upload"));
		builder.setPositiveButton(languageAdapter.getIdWithLanguageAdaptation("string", "dialog_ok"), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				try {
					JSONObject jsonObject = new JSONObject("{}");
					jsonObject.put("rssVec", rssVec);
					SocketClientHandler.getInstance("", 8888).sendMessage(jsonObject.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		builder.setNegativeButton(languageAdapter.getIdWithLanguageAdaptation("string", "dialog_cancel"), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return builder.create();
	}
}
