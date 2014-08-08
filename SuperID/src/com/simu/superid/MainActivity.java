package com.simu.superid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {
	private SlidingMenu mSlidingMenu;
	protected ListFragment mFrag;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new MenuListFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}
        
        initSlidingMenu();
        setContentView(R.layout.activity_main);
        
    }

	private void initSlidingMenu() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int mScreenWidth = dm.widthPixels;// ��ȡ��Ļ�ֱ��ʿ��
		// customize the SlidingMenu
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setShadowWidth(mScreenWidth / 40);// ������Ӱ���
		mSlidingMenu.setBehindOffset(mScreenWidth / 4);// ���ò˵����
		mSlidingMenu.setFadeDegree(0.35f);// ���õ��뵭���ı���
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);// ������˵���ӰͼƬ
		mSlidingMenu.setFadeEnabled(true);// ���û���ʱ�˵����Ƿ��뵭��
		mSlidingMenu.setBehindScrollScale(0.333f);// ���û���ʱ��קЧ��
		
		setSlidingActionBarEnabled(false);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		case R.id.action_search:
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SearchActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
