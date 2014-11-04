package com.marvik.apps.simplehealthcare;

import android.content.Context;
import android.util.Log;

public class Recorder implements Runnable {
	Context context;
	Utils utils;
	Runnable mRunner;
	
	public Recorder() {
		// TODO Auto-generated constructor stub

	}

	public Recorder(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		utils = new Utils(context);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				utils.recordCall();
			}
		}).start();
		
	}

	public void stop() {
		utils.stopRecorder();
	}
}
