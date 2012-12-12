package de.goldmann.android.applicationviewer;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.goldmann.android.applicationviewer.adapters.AppInfoAdapter;
import de.goldmann.android.applicationviewer.utilities.Utilities;

public class MainActivity extends Activity {

	private ListView mListAppInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// load list application
		mListAppInfo = (ListView) findViewById(R.id.lvApps);

		// create new Adapter
		AppInfoAdapter adapter = new AppInfoAdapter(this,
				Utilities.getInstalledApplication(this), getPackageManager());

		// set adapter to listview
		mListAppInfo.setAdapter(adapter);

		// implement event when item on list is clicked
		mListAppInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {

				// get List adapter
				AppInfoAdapter adapter = (AppInfoAdapter) parent.getAdapter();

				// get selected item on list
				ApplicationInfo appInfo = (ApplicationInfo) adapter
						.getItem(pos);

				// launch selected application
				Utilities.launchApp(parent.getContext(), getPackageManager(),
						appInfo.packageName);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
