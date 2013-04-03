package yl.demo.pathHelper.Adapter;

import java.util.ArrayList;
import java.util.List;
import yl.demo.pathHelper.R;
import yl.demo.pathHelper.algrorithm.MapConstraint;
import yl.demo.pathHelper.db.model.Facility;
import yl.demo.pathHelper.db.model.Model;
import yl.demo.pathHelper.model.Location;
import yl.demo.pathHelper.util.LanguageAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NearFacilityAdapter extends BaseAdapter {
	private Context mContext;
	private List<Model> mFacilities;
	private LanguageAdapter mLanguageAdapter;
	private Location mLocation;
	private int mPosition;
	private boolean isSelected[];
	private int mDistance[];
	
	public NearFacilityAdapter(Context context, List<Model> facilities, Location location) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mFacilities = facilities;
		mLanguageAdapter = LanguageAdapter.getInstance(mContext);
		mLocation = location;
		mPosition = -1;
		isSelected = new boolean[mFacilities.size()];
		mDistance = new int[mFacilities.size()];
		
		for ( int i = 0; i < isSelected.length; i++ ) {
			Facility facility = (Facility)mFacilities.get(i);
			isSelected[i] = false;
			mDistance[i] = (int)Math.sqrt((mLocation.x-facility.getX())*(mLocation.x-facility.getX())+(mLocation.y-facility.getY())*(mLocation.y-facility.getY()))/MapConstraint.PIXEL_PER_METER;
		}
		sortFacilitiesByDistance();		
	}

	private void sortFacilitiesByDistance() {
		// TODO Auto-generated method stub
		int[] distance = mDistance.clone();
		List<Model> facilities = new ArrayList<Model>();
		for ( int i = 0; i < distance.length; i++ ) {
			int position = findMIN(mDistance);
			facilities.add(mFacilities.get(position));
			distance[i] = mDistance[position];
			mDistance[position] = Integer.MAX_VALUE;
		}
		mFacilities = facilities;
		mDistance = distance;
	}

	private int findMIN(int[] distance) {
		// TODO Auto-generated method stub
		int min = Integer.MAX_VALUE;
		int position = -1;
		for ( int i = 0; i < distance.length; i++ ) {
			if ( distance[i] < min ) {
				min = distance[i];
				position = i;
			}
		}
		return position;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFacilities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Facility facility = (Facility)mFacilities.get(position);
		RelativeLayout relativeLayout = null;
		if ( convertView == null )
			relativeLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);
		else 
			relativeLayout = (RelativeLayout) convertView;
		
		ImageView logo = (ImageView) relativeLayout.findViewById(R.id.facility_image);
		logo.setImageResource(getResourcesId(facility.getType()));
		TextView name = (TextView) relativeLayout.findViewById(R.id.facility_text);
		name.setText(mLanguageAdapter.getStringFromDbFacility(facility));
		TextView info = (TextView) relativeLayout.findViewById(R.id.facility_info);
		info.setText(getInfoString(facility,position));
		if ( isSelected[position] ) 
			relativeLayout.setBackgroundColor(Color.rgb(202, 237, 228));
		else 
			relativeLayout.setBackgroundColor(Color.rgb(255, 255, 255));
		
		
		return relativeLayout;
	}
	
	private String getInfoString(Facility facility, int position) {
		// TODO Auto-generated method stub
		String text = null;
		if (facility.getFloorId() == mLocation.floorId) {
			text = mContext.getResources().getString(mLanguageAdapter.getIdWithLanguageAdaptation("string", "distance"));
			text = text.replace("_", mDistance[position]+"");
		} else {
			text = mContext.getResources().getString(mLanguageAdapter.getIdWithLanguageAdaptation("string", "not_in_same_floor"));
		}

		return text;
	}

	private int getResourcesId(int type) {
		int id = 0;
		switch (type) {
		case 0:
			id = R.drawable.facilities_restaurant;
			break;
		case 1:
			id = R.drawable.facilities_store;
			break;
		case 2:
			id = R.drawable.facilities_male_toilet;
			break;
		case 3:
			id = R.drawable.facilities_female_toilet;
			break;
		case 4:
			id = R.drawable.facilities_elevator;
			break;
		case 5:
			id = R.drawable.facilities_escalator;
			break;
		case 6:
			id = R.drawable.facilities_stair;
			break;	
		case 7:
			id = R.drawable.facilities_atm;
			break;
		case 8:
			id = R.drawable.facilities_room;
			break;
		case 9:
			id = R.drawable.facilities_guide;
			break;
		case 10:
			id = R.drawable.facilities_guitai;
			break;
		case 11:
			id = R.drawable.facilities_restroom;
			break;
		case 12:
			id = R.drawable.facilities_child;
			break;
		default:
			break;
		}
		
		return id;
	}
	
	public PointF getSelectedFacilityPosition(int position) {
		Facility facility = (Facility)mFacilities.get(position);
		return new PointF(facility.getX().floatValue(), facility.getY().floatValue());
	}
	
	public void setClicked(int position) {
		if ( mPosition != -1 ) 
			isSelected[mPosition] = false;
		mPosition = position;
		isSelected[mPosition] = true;
	}
	
}
