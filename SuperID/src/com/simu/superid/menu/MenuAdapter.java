package com.simu.superid.menu;

import java.util.List;

import com.simu.superid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	Context mContext;
	List<Item> items;

	public MenuAdapter(Context context, List<Item> items) {
		mContext = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Item item = (Item) getItem(position);

		if (v == null) {
			v = LayoutInflater.from(mContext).inflate(R.layout.menu_row_item,
					parent, false);
		}

		TextView tv = (TextView) v;
		tv.setText(item.mTitle);
		tv.setCompoundDrawablesWithIntrinsicBounds(item.mIconRes, 0,
				0, 0);

		return v;
	}
}
