package com.simu.superid.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.simu.superid.R;

public class ScheduleAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<Schedule> schedules;
	
	public ScheduleAdapter(Context context, ArrayList<Schedule> schedules) {
		this.mContext = context;
		this.schedules = schedules;
	}
	
	@Override
	public int getCount() {
		return schedules.size();
	}

	@Override
	public Object getItem(int arg0) {
		return schedules.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null
				|| convertView.getTag() == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.schedule_item, parent, false);
			holder.actionTime = (TextView) convertView.findViewById(R.id.schedule_item_action_time);
			holder.content = (TextView) convertView.findViewById(R.id.schedule_item_content);
			holder.alarmButton = (ToggleButton) convertView.findViewById(R.id.schedule_item_alarm_icon);
			holder.alarmTime = (TextView) convertView.findViewById(R.id.schedule_item_alarm_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Schedule schedule = schedules.get(position);
		holder.actionTime.setText(schedule.getActionTime());
		holder.content.setText(schedule.getContent());
		holder.alarmTime.setText(schedule.getAlarmTime());
		if (schedule.isAlarmOn()) {
			holder.alarmButton.toggle();
			holder.alarmTime.setTextColor(0xff0086db);
		}
		holder.alarmButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					holder.alarmTime.setTextColor(0xff0086db);
				} else {
					holder.alarmTime.setTextColor(0xffd3d1d1);
				}
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView actionTime;
		TextView content;
		ToggleButton alarmButton;
		TextView alarmTime;
	}
}
