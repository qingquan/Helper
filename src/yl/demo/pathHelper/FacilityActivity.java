package yl.demo.pathHelper;

import yl.demo.pathHelper.Adapter.FacilitiesAdapter;
import yl.demo.pathHelper.util.LanguageAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class FacilityActivity extends Activity {
	public static final int MSG_UPDATA_LISTVIEW = 0;
	
	private ListView mFacilitiesListView;
	private ImageView mOKButton;
	private FacilitiesAdapter mFacilitiesAdapter;
	private LanguageAdapter mLanguageAdapter;
	private Context mContext;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATA_LISTVIEW:
				mFacilitiesAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.facility_layout);
		
		initComponent();
		initViews();
		setListeners();
	}

	private void setListeners() {
		// TODO Auto-generated method stub
		mOKButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("facility", mFacilitiesAdapter.getFacilityTypes());
				FacilityActivity.this.setResult(RESULT_OK, intent);
				FacilityActivity.this.finish();
			}
		});
		
		mFacilitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mFacilitiesAdapter.setClicked(position);
				mHandler.sendEmptyMessage(MSG_UPDATA_LISTVIEW);
			}
		});
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mFacilitiesListView = (ListView) findViewById(R.id.facility_listview);
		mOKButton = (ImageView) findViewById(R.id.facility_show_btn);
		mOKButton.setImageResource(mLanguageAdapter.getIdWithLanguageAdaptation("drawable", "show_it_button"));
		
		mFacilitiesListView.setAdapter(mFacilitiesAdapter);
		mFacilitiesListView.setDivider(null);
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		mContext = FacilityActivity.this;
		mFacilitiesAdapter = new FacilitiesAdapter(mContext);
		mLanguageAdapter = LanguageAdapter.getInstance(mContext);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent();
		FacilityActivity.this.setResult(RESULT_CANCELED, intent);
		FacilityActivity.this.finish();
	}

}
