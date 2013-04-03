package yl.demo.pathHelper;

import java.util.Timer;
import java.util.TimerTask;
import yl.demo.pathHelper.util.FileUtil;
import yl.demo.pathHelper.util.PreferenceUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FlashActivity extends Activity {
	private Context mContext;
	private Timer mTimer;
	private boolean mHasCopyDbFinished;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flash);
		
		initComponment();
		initDb();
		autoToMainActivity();
	}

	private void initComponment() {
		// TODO Auto-generated method stub
		mContext = FlashActivity.this;
		mHasCopyDbFinished = false;
		FileUtil.createDirs();
	}

	private void autoToMainActivity() {
		// TODO Auto-generated method stub
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				while(!mHasCopyDbFinished);
				Intent intent = new Intent(FlashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}
	
	private void initDb() {
		PreferenceUtils.initPreference(mContext);
		if(!PreferenceUtils.getBooleanValue(mContext, PreferenceUtils.KEY_HAS_DB)) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					FileUtil.initDatabase(getApplicationContext(), "ap_data.db");
					mHasCopyDbFinished = true;
				}
			}).start();
			PreferenceUtils.saveBooleanValue(mContext, PreferenceUtils.KEY_HAS_DB, true);
		} else {
			mHasCopyDbFinished = true;
		}
	}

}
