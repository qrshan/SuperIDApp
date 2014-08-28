package com.simu.superid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.simu.superid.adapter.Schedule;
import com.simu.superid.adapter.ScheduleAdapter;
import com.simu.superid.wiget.ListViewForScrollView;
import com.squareup.timessquare.CalendarView;
import com.squareup.timessquare.CalendarView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarView.OnMonthChangedListener;

public class ScheduleActivity extends Activity {
	ScrollView sView;
	ListViewForScrollView mListView;
	CalendarView calendar;
	Date chooseDate;
	TextView scheduleDate;
	ImageView scheduleAdd, scheduleEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		sView = (ScrollView) findViewById(R.id.schedule_sv);
		calendar = (CalendarView) findViewById(R.id.calendar);
		scheduleDate = (TextView) findViewById(R.id.schedule_date);
		scheduleAdd = (ImageView) findViewById(R.id.schedule_add);
		scheduleEdit = (ImageView) findViewById(R.id.schedule_edit);
		mListView = (ListViewForScrollView) findViewById(R.id.schedule_list);
		initView();
		initList();
	}

	private void initList() {
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(new Schedule("18:00", "17:50", "下午六点与王总会面，商讨下周合作事宜，并共进晚餐", true));
		schedules.add(new Schedule("19:00", "18:50", "发货100吨", false));
		schedules.add(new Schedule("20:00", "19:50", "老爸过生日，回家吃晚饭", true));
		schedules.add(new Schedule("18:00", "17:50", "下午六点与王总会面，商讨下周合作事宜，并共进晚餐", true));
		schedules.add(new Schedule("19:00", "18:50", "发货100吨", false));
		schedules.add(new Schedule("20:00", "19:50", "老爸过生日，回家吃晚饭", true));
		ScheduleAdapter adapter = new ScheduleAdapter(ScheduleActivity.this, schedules);
		mListView.setAdapter(adapter);
	}

	private void initView() {
		sView.smoothScrollTo(0, 0);
		chooseDate = new Date();
		scheduleDate.setText(DateFormat.format(getString(R.string.date_format),
				chooseDate) + getString(R.string.schedule));
		// 设置日期点击监听器
		calendar.setOnDateSelectedListener(new OnDateSelectedListener() {

			@Override
			public void onDateUnselected(Date date) {
			}

			@Override
			public void onDateSelected(Date date) {
				chooseDate = date;
				scheduleDate.setText(DateFormat.format(
						getString(R.string.date_format), chooseDate)
						+ getString(R.string.schedule));
			}
		});

		calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
			@Override
			public void onChangedToPreMonth(Date dateOfMonth) {
				new GetCalendarsOfMonthTask(dateOfMonth).execute();
			}

			@Override
			public void onChangedToNextMonth(Date dateOfMonth) {
				new GetCalendarsOfMonthTask(dateOfMonth).execute();
			}
		});
		new GetCalendarsOfMonthTask(Calendar.getInstance().getTime()).execute();
		
		scheduleAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(ScheduleActivity.this, CreateScheduleActivity.class);
				intent.putExtra("local_date", DateFormat.format(getString(R.string.date_format), chooseDate));
				startActivity(intent);
			}
		});
		
		scheduleEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class GetCalendarsOfMonthTask extends AsyncTask<Object, Object, String> {
		Date dateOfMonth;
		List<List<Calendar>> calsList;

		public GetCalendarsOfMonthTask(Date dateOfMonth) {
			this.dateOfMonth = dateOfMonth;
		}

		@Override
		protected String doInBackground(Object... params) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateOfMonth);
			calsList = getCalendarsOfMonth(cal.get(Calendar.YEAR) + "",
					(cal.get(Calendar.MONTH) + 1) + "");
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (calsList != null && calsList.size() > 1) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateOfMonth);
				calendar.markDatesOfMonth(cal.get(Calendar.YEAR),
						cal.get(Calendar.MONTH), false, true, calsList.get(0));
				// calendar.markDatesOfMonth(cal.get(Calendar.YEAR),
				// cal.get(Calendar.MONTH), true, false, calsList.get(1));
			}
		}

	}

	private List<List<Calendar>> getCalendarsOfMonth(String year, String month) {

		List<List<Calendar>> data = new ArrayList<List<Calendar>>();

		int yearI = Integer.parseInt(year);
		int monthI = Integer.parseInt(month) - 1;

		List<Calendar> feeds = new ArrayList<Calendar>();
		for (int i = 0; i < 15; i++) {
			int day = new Random().nextInt(30) + 1;
			Calendar cal = Calendar.getInstance();
			cal.set(yearI, monthI, day, 0, 0, 0);
			feeds.add(cal);
		}
		data.add(feeds);

		List<Calendar> calendars = new ArrayList<Calendar>();
		for (int i = 0; i < 21; i++) {
			int day = new Random().nextInt(30) + 1;
			Calendar cal = Calendar.getInstance();
			cal.set(yearI, monthI, day, 0, 0, 0);
			calendars.add(cal);
		}
		data.add(calendars);

		return data;
	}
}
