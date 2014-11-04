package com.marvik.apps.simplehealthcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Activities extends Activity {
	
	ListView lvActivites;
	String [] lActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities);
		  
		lActivity = new String []{"AboutUs","BugReport","CallStats","ContactUs","Help","HowToUse","Notifications","Register",
        		  "Settings","Splash","Statistics", "Test","StatisticsMore"};
		lvActivites = (ListView)findViewById(R.id.activities_listView_activites);
		lvActivites.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,  lActivity));
		lvActivites.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				try {
					startActivity(new Intent(getApplicationContext(), Class.forName(getPackageName()+"."+lActivity[position])));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Oops,\nThe class "
							+lActivity[position]+" \nin the package \n"+getPackageName()
							+"\n was not found", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
