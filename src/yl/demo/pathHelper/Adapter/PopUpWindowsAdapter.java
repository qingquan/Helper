package yl.demo.pathHelper.Adapter;

import java.util.ArrayList;
import java.util.List;
import yl.demo.pathHelper.R;
import yl.demo.pathHelper.util.FileUtil;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopUpWindowsAdapter extends BaseAdapter {
	private String[] mWholeHistoryInputStrings;
	private List<String> mFilteredInputStringList;
	private Context mContext;
	
	public PopUpWindowsAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		updateWholeQueryHistory();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFilteredInputStringList.size();
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
		RelativeLayout relativeLayout;
		if ( convertView == null )
			relativeLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_up_list_item, null);
		else 
			relativeLayout = (RelativeLayout) convertView;
		TextView textView = (TextView) relativeLayout.findViewById(R.id.pop_up_item_text);
		textView.setText(mFilteredInputStringList.get(position));
		return relativeLayout;
	}
	
	public String getSelectedText(int position) {
		return mFilteredInputStringList.get(position);
	}
	
	public void filterInfoWithPrefix(String prefix) {
		mFilteredInputStringList.clear();
		for ( int i = 0; i < mWholeHistoryInputStrings.length; i++ ) {
			if ( mWholeHistoryInputStrings[i].startsWith(prefix) ) 
				mFilteredInputStringList.add(mWholeHistoryInputStrings[i]);
		}
		notifyDataSetChanged();
	}
	
	public void updateWholeQueryHistory() {
		mWholeHistoryInputStrings = FileUtil.getQueryHistory(mContext);
		mFilteredInputStringList = new ArrayList<String>();
		for(int i = 0; i < mWholeHistoryInputStrings.length; i++) {
			mFilteredInputStringList.add(mWholeHistoryInputStrings[i]);
		}
		notifyDataSetChanged();
	}

}
