package com.marvik.apps.simplehealthcare;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Notifications extends Activity {
	Utils utils;
	TextView tvCallNo,tvLowestAmplitudeAmplitudes,tvLowestAmplitudeDecibels,tvHighestAmplitudeAmplitudes,tvHighestAmplitudeDecibels,
	tvLastCallAverageAmplitudesAmplitudes,tvLastCallAverageAmplitudesDecibels,tvUserAmplitudeRange,tvAllowedAmplitudeRange,tvLastCallAverageAmplitude
	,tvAmplitudeIncrease;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		init(savedInstanceState);
	}

	private void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		utils = new Utils(this);
		
		tvCallNo = (TextView)findViewById(R.id.notification_textView_call_no);
		tvLowestAmplitudeAmplitudes = (TextView)findViewById(R.id.notification_textView_lowest_amplitude_amplitudes);
		tvLowestAmplitudeDecibels = (TextView)findViewById(R.id.notification_textView_lowest_amplitude_decibels);
		tvHighestAmplitudeAmplitudes = (TextView)findViewById(R.id.notification_textView_highest_amplitude_amplitudes);
		tvHighestAmplitudeDecibels = (TextView)findViewById(R.id.notification_textView_highest_amplitude_decibels);
		tvLastCallAverageAmplitudesAmplitudes = (TextView)findViewById(R.id.notification_textView_last_call_amplitude_average_amplitudes);
		tvLastCallAverageAmplitudesDecibels = (TextView)findViewById(R.id.notification_textView_last_call_amplitude_average_decibels);
		
		tvUserAmplitudeRange = (TextView)findViewById(R.id.notification_more_textView_average_amplitude_amplitude);
		tvAllowedAmplitudeRange = (TextView)findViewById(R.id.notification_more_textView_allowed_amplitude_range_value);
		tvLastCallAverageAmplitude = (TextView)findViewById(R.id.notification_more_textView_last_calls_average_amplitude_value);
		tvAmplitudeIncrease = (TextView)findViewById(R.id.notification_more_textView_amplitude_increase_value);
		
		
		
		savedInstanceState = getIntent().getExtras();
		if(savedInstanceState!=null){
			tvCallNo.setText(""+savedInstanceState.getInt(Utils.EXTRA_CALL_NO,utils.getCallCount() ));
			tvLowestAmplitudeAmplitudes.setText(""+savedInstanceState.getInt(Utils.EXTRA_LOWEST_AMPLITUDE,utils.getMinAllowedAmplitude() ));
			tvLowestAmplitudeDecibels.setText(""+savedInstanceState.getInt(Utils.EXTRA_LOWEST_AMPLITUDE,((int)(utils.getMinAllowedAmplitude()*1.5))));
			tvHighestAmplitudeAmplitudes.setText(""+savedInstanceState.getInt(Utils.EXTRA_HIGHEST_AMPLITUDE,utils.getMaxAllowedAmplitude() ));
			tvHighestAmplitudeDecibels.setText(""+savedInstanceState.getInt(Utils.EXTRA_HIGHEST_AMPLITUDE,(int)(utils.getMaxAllowedAmplitude()*1.5) ));
			tvUserAmplitudeRange.setText(""+savedInstanceState.getInt(Utils.EXTRA_USER_AVERAGE_AMPLITUDE,0 ));
			tvLastCallAverageAmplitudesAmplitudes.setText(""+savedInstanceState.getInt(Utils.EXTRA_LAST_CALL_AVERAGE_AMPLITUDE,0 ));
			tvLastCallAverageAmplitudesDecibels.setText(""+(savedInstanceState.getInt(Utils.EXTRA_LAST_CALL_AVERAGE_AMPLITUDE,0 )*1.5));
			
			tvAllowedAmplitudeRange.setText(""+savedInstanceState.getInt(Utils.EXTRA_ALLOWED_AMPLITUDE_RANGE,utils.getAmplitudeRange() ));
			tvLastCallAverageAmplitude.setText(""+savedInstanceState.getInt(Utils.EXTRA_LAST_CALL_AVERAGE_AMPLITUDE,0 ));
			tvAmplitudeIncrease.setText(""+savedInstanceState.getInt(Utils.EXTRA_INCREASE_IN_AMPLITUDES, 0));
			cancelNotification();
		}
	}

	private void cancelNotification() {
		// TODO Auto-generated method stub
		Bundle extras = new Bundle();
		//context.startActivity(new Intent(context,Notifications.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtras(extras));
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,Notifications.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtras(extras), PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationManager nm = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.green_button,"Health Monitoring",System.currentTimeMillis());
		notification.setLatestEventInfo(this, "Simple Health Care", "Click to view your last calls statisctics", pendingIntent);
		notification = new Notification(R.drawable.red_bp,"Health Monitoring",System.currentTimeMillis());
		String [] color= new String []{"aabbcc", "0000FF","A52A2A","808080","008000","4B0082","F0E68C","FFB6C1","FFFFE0","00FF00","FF00FF","FFE4E1",
		         "FFA500","FFC0CB","800080","C0C0C0","00FF7F","4682B4"};
			//notification.ledARGB=Color.parseColor("#"+color[utils.getBlinkColor()]);
		if(true){
			 notification = new Notification(R.drawable.red_bp,"Health Monitoring",System.currentTimeMillis());
			 notification.setLatestEventInfo(this, "Health Monitoring", "You seem to have a high bp. Click here for more info", pendingIntent);
			 notification.ledARGB=Color.RED;
			 notification.ledOffMS=0;
		}
		
		if(utils.isEnableVibrations()){
			notification.flags=Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE;
		}
		
		nm.notify(1, notification);
		nm.cancelAll();
	}
}
