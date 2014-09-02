package com.simu.superid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.search, menu);
		// 获取SearchView对象
		MenuItem searchItem = menu.findItem(R.id.menu_action_search);
		SearchView searchView = (SearchView) searchItem.getActionView();
		searchView.setIconifiedByDefault(true); //
		// 缺省值就是true，可能不专门进行设置，false和true的效果图如下，true的输入框更大
		searchItem.expandActionView();
		return super.onCreateOptionsMenu(menu);
	}

	private void createSearchItem(Menu menu) {
		// 获取SearchView对象
		// SearchView searchView = (SearchView)
		// menu.findItem(R.id.menu_search).getActionView();
		MenuItem searchItem = menu.findItem(R.id.menu_action_search);
		// SearchView searchView = (SearchView) searchItem.getActionView();
		// searchView.setIconifiedByDefault(true); //
		// 缺省值就是true，可能不专门进行设置，false和true的效果图如下，true的输入框更大
		searchItem.expandActionView();
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
}
