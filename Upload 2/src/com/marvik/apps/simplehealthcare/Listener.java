package com.marvik.apps.simplehealthcare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class Listener extends BroadcastReceiver {
	/*HANDLE CHECKING IF THIS APP IS TURNED ON OR OFF*/
	TelephonyManager manager;
	Utils utils;
	Recorder mRecorder;
	boolean started;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		utils = new Utils(context);
		
		String action = intent.getAction();
		if (action.equalsIgnoreCase(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
			String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
			
			String idle = TelephonyManager.EXTRA_STATE_IDLE;
			String ringing = TelephonyManager.EXTRA_STATE_RINGING;
			String offHook = TelephonyManager.EXTRA_STATE_OFFHOOK;
			
			
			if (state.equalsIgnoreCase(idle)) {
				
				if (started) {
					mRecorder.stop();
					mRecorder = null;
					utils.houseKeepOnCallEnd();
				}

			}
			if (state.equalsIgnoreCase(ringing)) {
				
			}
			if (state.equalsIgnoreCase(offHook)) {
				if (utils.enableSHC()) {
					mRecorder = new Recorder(context);
					mRecorder.run();
					started = true;
				}
			}
		}

	}

}
