package com.simu.superid;

import java.util.ArrayList;
import java.util.List;

import com.simu.superid.menu.Item;
import com.simu.superid.menu.MenuAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MenuListFragment extends ListFragment {

	private MenuAdapter mMenuAdapter;
	List<Item> items;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.behind_slidingmenu, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		items = new ArrayList<Item>();
		items.add(new Item(0, "我的超级账户", R.drawable.icon_my));
		items.add(new Item(1, "通知", R.drawable.icon_inform));
		items.add(new Item(2, "日程管理", R.drawable.icon_schedule));
		items.add(new Item(3, "相关服务", R.drawable.icon_service));
		mMenuAdapter = new MenuAdapter(this.getActivity(), items);
		setListAdapter(mMenuAdapter);
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 1:
			
			break;
		case 2:
			Intent intent = new Intent();
			intent.setClass(getActivity(), ScheduleActivity.class);
			getActivity().startActivity(intent);
			break;
		case 3:
			
			break;

		default:
			break;
		}
		super.onListItemClick(l, v, position, id);
	}

}
