package yl.demo.pathHelper;

import yl.demo.pathHelper.Adapter.LayersAdapter;
import yl.demo.pathHelper.util.LanguageAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;

public class LayerActivity extends Activity {
	public static final int MSG_UPDATA_LISTVIEW = 0;
	public static final int MSG_NOT_SELECT_ITEM = 1;
	
	private ExpandableListView mLayerListView;
	private ImageView mOKButton;
	private LayersAdapter mLayersAdapter;
	private LanguageAdapter mLanguageAdapter;
	private Context mContext;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_NOT_SELECT_ITEM:
				int id = mLanguageAdapter.getIdWithLanguageAdaptation("string", "not_select_item");
				Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
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
		setContentView(R.layout.layer_layout);
		
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
				int floorId = mLayersAdapter.getFloorId();
				if ( floorId != -1 ) {
					Intent intent = new Intent();
					intent.putExtra("floorId", floorId);
					LayerActivity.this.setResult(RESULT_OK, intent);
					LayerActivity.this.finish();
				} else {
					mHandler.sendEmptyMessage(MSG_NOT_SELECT_ITEM);
				}
			}
		});
		
		mLayerListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				mLayersAdapter.setClicked(groupPosition, childPosition);
				mLayersAdapter.notifyDataSetChanged();
				return false;
			}
		});
		
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mLayerListView = (ExpandableListView) findViewById(R.id.layer_listview);
		mOKButton = (ImageView) findViewById(R.id.layer_choose_btn);
		
		mOKButton.setImageResource(mLanguageAdapter.getIdWithLanguageAdaptation("drawable", "choose_houjilou_button"));
		
		mLayerListView.setAdapter(mLayersAdapter);
		mLayerListView.setDivider(null);
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		mContext = LayerActivity.this;
		mLayersAdapter = new LayersAdapter(LayerActivity.this);
		mLanguageAdapter = LanguageAdapter.getInstance(LayerActivity.this);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent();
		LayerActivity.this.setResult(RESULT_CANCELED, intent);
		LayerActivity.this.finish();
	}

}
