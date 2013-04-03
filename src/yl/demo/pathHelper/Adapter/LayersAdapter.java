package yl.demo.pathHelper.Adapter;

import yl.demo.pathHelper.R;
import yl.demo.pathHelper.util.LanguageAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LayersAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	private String[] mBuilding;
	private LayerInfo[][] mFloors;
	private int mGroupPosition;
	private int mChildPosition;
	
	public LayersAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		LanguageAdapter languageAdapter = LanguageAdapter.getInstance(mContext);
		mBuilding = mContext.getResources().getStringArray(languageAdapter.getIdWithLanguageAdaptation("array", "building"));
		mFloors = new LayerInfo[mBuilding.length][];
		for ( int i = 0; i < mFloors.length; i++ ) {
			String[] info = mContext.getResources().getStringArray(languageAdapter.getIdWithLanguageAdaptation("array", "building"+(i+1)));
			mFloors[i] = new LayerInfo[info.length/2];
			for ( int j = 0; j < info.length; j = j + 2 )
				mFloors[i][j/2] = new LayerInfo(info[j], Integer.parseInt(info[j+1]));
		}	
		mGroupPosition = mChildPosition = -1;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mBuilding.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return mFloors[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout relativeLayout = null;
		if ( convertView == null )
			relativeLayout = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.layer_building_item, null);
		else 
			relativeLayout = (RelativeLayout)convertView;
		
		relativeLayout.setBackgroundResource(R.drawable.facilities_bg);
		ImageView logo = (ImageView) relativeLayout.findViewById(R.id.layer_image);
		logo.setImageResource(getResourcesId(groupPosition));
		TextView text = (TextView) relativeLayout.findViewById(R.id.layer_text);
		text.setText(mBuilding[groupPosition]);
	
		return relativeLayout;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout relativeLayout = null;
		if ( convertView == null )
			relativeLayout = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.layer_floor_item, null);
		else 
			relativeLayout = (RelativeLayout)convertView;
		
		relativeLayout.setBackgroundResource(R.drawable.facilities_bg);
		ImageView logo = (ImageView) relativeLayout.findViewById(R.id.layer_image);
		logo.setImageResource(getResourcesId(childPosition));
		TextView text = (TextView) relativeLayout.findViewById(R.id.layer_text);
		text.setText(mFloors[groupPosition][childPosition].mName);
		ImageView click = (ImageView) relativeLayout.findViewById(R.id.layer_click);
		if ( mFloors[groupPosition][childPosition].mIsClicked ) {
			click.setVisibility(View.VISIBLE);
			relativeLayout.setBackgroundResource(R.drawable.facilities_chosen_bg);
		} else {
			click.setVisibility(View.INVISIBLE);
			relativeLayout.setBackgroundResource(R.drawable.facilities_bg);
		}
	
		return relativeLayout;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public int getFloorId() {
		if ( mGroupPosition != -1 )
			return mFloors[mGroupPosition][mChildPosition].mFloorId;
		return -1;
	}
	
	public void setClicked(int groupPosition, int childPosition ) {
		if ( mGroupPosition != -1 )
			mFloors[mGroupPosition][mChildPosition].mIsClicked = false;
		mGroupPosition = groupPosition;
		mChildPosition = childPosition;
		mFloors[mGroupPosition][mChildPosition].mIsClicked = true;
	}
	
	private int getResourcesId(int position) {
		int id = 0;
		switch (position) {
		case 0:
			id = R.drawable.floor_1;
			break;
		case 1:
			id = R.drawable.floor_2;
			break;
		case 2:
			id = R.drawable.floor_3;
			break;
		case 3:
			id = R.drawable.floor_4;
			break;
		case 4:
			id = R.drawable.floor_5;
			break;
		case 5:
			id = R.drawable.floor_6;
			break;
		case 6:
			id = R.drawable.floor_7;
			break;

		default:
			break;
		}
		
		return id;
	}
	
	public class LayerInfo {
		public String mName;
		public int mFloorId;
		public boolean mIsClicked;
		
		public LayerInfo(String name, int floor) {
			// TODO Auto-generated constructor stub
			mName = name;
			mFloorId = floor;
			mIsClicked = false;
		}
		
	}
	
}
