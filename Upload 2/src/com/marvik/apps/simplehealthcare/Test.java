package com.marvik.apps.simplehealthcare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Test extends Activity {
	Recorder mRecorder;
	Utils utils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		utils = new Utils(Test.this);
		mRecorder = new Recorder(Test.this);

	}

	public void start(View view) {
		mRecorder = new Recorder(Test.this);
		mRecorder.run();
	}

	public void stop(View view) {
		mRecorder.stop();
		mRecorder = null;
		utils.houseKeepOnCallEnd();
	}

	public void showHighBpNotification(View view) {
		utils.notifyTestHighBp(true);
	}

	public void showNotification(View view) {
		utils.sendNotification();
	}

	public void cancelNotification(View view) {
		utils.cancelSentNotification();
	}
}
