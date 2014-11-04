package com.marvik.apps.simplehealthcare;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CallStats extends Activity {
	TextView tvLastestAmplitude,tvHighestAllowed,tvLowestAllowed;
	LinearLayout llParent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callstats);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		llParent = (LinearLayout)findViewById(R.id.callstats_linearLayout_parent);
		llParent.setVisibility(LinearLayout.GONE);
		
		tvLastestAmplitude = (TextView) findViewById(R.id.callstats_textView_latest_amplitude_on_call);
		tvLastestAmplitude.setVisibility(TextView.GONE);
		tvHighestAllowed = (TextView) findViewById(R.id.callstats_textView_highest_allowed_amplitude);
		tvHighestAllowed.setVisibility(TextView.GONE);
		tvLowestAllowed = (TextView) findViewById(R.id.callstats_textView_lowest_allowed_amplitude);
		tvLowestAllowed.setVisibility(TextView.GONE);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//unregisterReceiver(new CallStatsReceiver());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver(new CallStatsReceiver(), new IntentFilter(Utils.EXTRA_PARSED_AMPLITUDE));
		registerReceiver(new CallStatsReceiver(), new IntentFilter(Utils.CALL_ENDED));
	}
	

	class CallStatsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Utils utils = new Utils(context);
			if(intent.getAction().equals(Utils.EXTRA_PARSED_AMPLITUDE)){
				if(!llParent.isShown()){llParent.setVisibility(LinearLayout.VISIBLE);}
				if (!tvLastestAmplitude.isShown()) {tvLastestAmplitude.setVisibility(TextView.VISIBLE);}
				if (!tvHighestAllowed.isShown()) {tvHighestAllowed.setVisibility(TextView.VISIBLE);}
				if (!tvLowestAllowed.isShown()) {tvLowestAllowed.setVisibility(TextView.VISIBLE);}
				
				int lastAmplitude = intent.getExtras().getInt(Utils.PARSED_AMPLITUDE, 0);
				tvLowestAllowed.setText("Lowest Allowed Amplitude : "+utils.getMinAllowedAmplitude());
				tvLastestAmplitude.setText("Last Amplitude : "+lastAmplitude);
				tvHighestAllowed.setText("Highest Allowed Amplitude : "+utils.getMaxAllowedAmplitude());
			}
			if(intent.getAction().equals(Utils.CALL_ENDED)){
				llParent.setVisibility(LinearLayout.GONE);
				finish();
			}
		}
	}
}
