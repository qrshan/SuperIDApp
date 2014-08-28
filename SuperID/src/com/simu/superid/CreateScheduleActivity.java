package com.simu.superid;

import java.util.Calendar;
import java.util.Date;

import android.R.anim;
import android.R.integer;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateScheduleActivity extends FragmentActivity {
	TextView dateEdit;
	TextView timeEdit;
	TextView alarmEdit;
	Button createBtn;
	private final static int DATE_DIALOG = 0;
	private final static int TIME_DIALOG = 1;
	private final static int ALARM_DIALOG = 2;
	private Calendar c = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_schedule);
		dateEdit = (TextView) findViewById(R.id.create_sche_date_edit);
		timeEdit = (TextView) findViewById(R.id.create_sche_time_edit);
		alarmEdit = (TextView) findViewById(R.id.create_sche_alarm_edit);
		createBtn = (Button) findViewById(R.id.creat_schedule);

		dateEdit.setText(getIntent().getStringExtra("local_date"));
		alarmEdit.setText(DateFormat.format("hh:mm", new Date()));
		timeEdit.setText(DateFormat.format("hh:mm", new Date()));
		dateEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DialogFragment datePicker = new DateTimePicker(DATE_DIALOG);
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				datePicker.show(ft, "Date");
			}
		});
		timeEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DialogFragment timePicker = new DateTimePicker(TIME_DIALOG);
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				timePicker.show(ft, "Time");
			}
		});
		alarmEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DialogFragment timePicker = new DateTimePicker(ALARM_DIALOG);
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				timePicker.show(ft, "Time");
			}
		});

		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(CreateScheduleActivity.this, getString(R.string.create_schedule_success), Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_schedule, menu);
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

	/**
	 * 创建日期及时间选择对话框
	 */
	public class DateTimePicker extends DialogFragment {

		public DateTimePicker(int title) {
			Bundle args = new Bundle();
			args.putInt("title", title);
			setArguments(args);
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			int title = getArguments().getInt("title");
			Dialog dialog = null;
			switch (title) {
			case DATE_DIALOG:
				c = Calendar.getInstance();
				dialog = new DatePickerDialog(CreateScheduleActivity.this,
						new DatePickerDialog.OnDateSetListener() {
							public void onDateSet(DatePicker dp, int year,
									int month, int dayOfMonth) {
								dateEdit.setText(year + "/" + (month + 1) + "/"
										+ dayOfMonth);
							}
						}, c.get(Calendar.YEAR), // 传入年份
						c.get(Calendar.MONTH), // 传入月份
						c.get(Calendar.DAY_OF_MONTH) // 传入天数
				);
				break;
			case TIME_DIALOG:
				c = Calendar.getInstance();
				dialog = new TimePickerDialog(CreateScheduleActivity.this,
						new TimePickerDialog.OnTimeSetListener() {
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								timeEdit.setText(hourOfDay + ":" + minute);
							}
						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
						false);
				break;
			case ALARM_DIALOG:
				c = Calendar.getInstance();
				dialog = new TimePickerDialog(CreateScheduleActivity.this,
						new TimePickerDialog.OnTimeSetListener() {
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								alarmEdit.setText(hourOfDay + ":" + minute);
							}
						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
						false);
				break;
			}
			return dialog;
		}

	}
}
