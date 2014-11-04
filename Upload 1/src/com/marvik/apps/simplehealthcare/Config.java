package com.marvik.apps.simplehealthcare;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Config {
	Context context;
	SharedPreferences prefs;
	Editor editor;
	// /////////////////////BASIC SETTINGS/////////////////////////////////////
	boolean enableSHC, alertOnHighBp, saveRecordedCalls;
	int quickAddLocation, averageAmplitude;
	int amplitudeRange;

	// /////////////////////////ADVANCED SETTINGS////////////////////////////
	int generateProfileAfter, intervalLengths, consecutiveAmplitudes,
			lowestAmplitude, highestAmplitude;

	// ////////////////////////NOTIFICATION SETTINGS//////////////////////
	boolean enableNotification, enableVibration;
	boolean notifyAfterCall;
	int blinkColor;

	public Config(Context context) {
		this.context = context;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		editor = prefs.edit();
	}

	// ////////////////// BASIC SETTINGS
	// //////////////////////////////////////////////
	/**
	 * @return the enableSHC
	 */
	public boolean isEnableSHC() {
		return prefs.getBoolean(Utils.ENABLE_SIMPLEHEALTHCARE, false);
	}

	/**
	 * @param enableSHC
	 *            the enableSHC to set
	 */
	public void setEnableSHC(boolean enableSHC) {
		this.enableSHC = enableSHC;
		editor.putBoolean(Utils.ENABLE_SIMPLEHEALTHCARE, enableSHC);
		editor.commit();
	}

	/**
	 * @return the alertOnHighBp
	 */
	public boolean isAlertOnHighBp() {
		return prefs.getBoolean(Utils.ALERT_ON_HIGH_BP, false);
	}

	/**
	 * @param alertOnHighBp
	 *            the alertOnHighBp to set
	 */
	public void setAlertOnHighBp(boolean alertOnHighBp) {
		this.alertOnHighBp = alertOnHighBp;
		editor.putBoolean(Utils.ALERT_ON_HIGH_BP, alertOnHighBp);
		editor.commit();
	}

	/**
	 * @return the saveRecordedCalls
	 */
	public boolean isSaveRecordedCalls() {
		return prefs.getBoolean(Utils.SAVE_RECORDED_CALLS, true);
	}

	/**
	 * @param saveRecordedCalls
	 *            the saveRecordedCalls to set
	 */
	public void setSaveRecordedCalls(boolean saveRecordedCalls) {
		this.saveRecordedCalls = saveRecordedCalls;
		editor.putBoolean(Utils.SAVE_RECORDED_CALLS, saveRecordedCalls);
		editor.commit();
	}

	/**
	 * @return the quickAddLocation
	 */
	public int getQuickAddLocation() {
		return prefs.getInt(Utils.QUICK_ADD_LOCATION, 0);
	}

	/**
	 * @param quickAddLocation
	 *            the quickAddLocation to set
	 */
	public void setQuickAddLocation(int quickAddLocation) {
		this.quickAddLocation = quickAddLocation;
		editor.putInt(Utils.QUICK_ADD_LOCATION, quickAddLocation);
		editor.commit();
	}

	/**
	 * @return the averageAmplitude
	 */
	public int getAverageAmplitude() {
		return prefs.getInt(Utils.AVERAGE_AMPLITUDE, 0);
	}

	/**
	 * @param averageAmplitude
	 *            the averageAmplitude to set
	 */
	public void setAverageAmplitude(int averageAmplitude) {
		this.averageAmplitude = averageAmplitude;
		editor.putInt(Utils.AVERAGE_AMPLITUDE, averageAmplitude);
		editor.commit();
	}

	int getAmplitudeRange() {
		return prefs.getInt(Utils.AMPLITUDE_RANGE, 0);
	}

	void setAmplitudeRange(int amplitudeRange) {
		this.amplitudeRange = amplitudeRange;
		editor.putInt(Utils.AMPLITUDE_RANGE, amplitudeRange);
		editor.commit();
	}

	// ////////////////// ADVANCED SETTINGS
	// //////////////////////////////////////////////
	/**
	 * @return the generateProfileAfter
	 */
	public int getGenerateProfileAfter() {
		return prefs.getInt(Utils.GENERATE_PROFILE, 2);
	}

	/**
	 * @param generateProfileAfter
	 *            the generateProfileAfter to set
	 */
	public void setGenerateProfileAfter(int generateProfileAfter) {
		this.generateProfileAfter = generateProfileAfter;
		editor.putInt(Utils.GENERATE_PROFILE, generateProfileAfter);
		editor.commit();
	}

	/**
	 * @return the intervalLengths
	 */
	public int getIntervalLengths() {
		return prefs.getInt(Utils.INTERVAL_LENGTH, 4);
	}

	/**
	 * @param intervalLengths
	 *            the intervalLengths to set
	 */
	public void setIntervalLengths(int intervalLengths) {
		this.intervalLengths = intervalLengths;
		editor.putInt(Utils.INTERVAL_LENGTH, intervalLengths);
		editor.commit();
	}

	/**
	 * @return the consecutiveAmplitudes
	 */
	public int getConsecutiveAmplitudes() {
		return prefs.getInt(Utils.CONSECUTIVE_AMPLITUDES, 6);

	}

	/**
	 * @param consecutiveAmplitudes
	 *            the consecutiveAmplitudes to set
	 */
	public void setConsecutiveAmplitudes(int consecutiveAmplitudes) {
		this.consecutiveAmplitudes = consecutiveAmplitudes;
		editor.putInt(Utils.CONSECUTIVE_AMPLITUDES, consecutiveAmplitudes);
		editor.commit();
	}

	/**
	 * @return the lowestAmplitude
	 */
	public int getLowestAmplitude() {
		return prefs.getInt(Utils.AMPLITUDE_MIN_ALLOWED, 0);
	}

	/**
	 * @param lowestAmplitude
	 *            the lowestAmplitude to set
	 */
	public void setLowestAmplitude(int lowestAmplitude) {
		this.lowestAmplitude = lowestAmplitude;
		editor.putInt(Utils.AMPLITUDE_MIN_ALLOWED, lowestAmplitude);
		editor.commit();
	}

	/**
	 * @return the highestAmplitude
	 */
	public int getHighestAmplitude() {
		return prefs.getInt(Utils.AMPLITUDE_MAX_ALLOWED, 0);
	}

	/**
	 * @param highestAmplitude
	 *            the highestAmplitude to set
	 */
	public void setHighestAmplitude(int highestAmplitude) {
		this.highestAmplitude = highestAmplitude;
		editor.putInt(Utils.AMPLITUDE_MAX_ALLOWED, highestAmplitude);
		editor.commit();
	}

	public void resetPreferences() {
		// TODO Auto-generated method stub
		editor.clear();
		editor.commit();
		Toast.makeText(context, "Your current settings have been reset.",
				Toast.LENGTH_SHORT).show();
	}

	public void setDefaultPreferences() {
		// TODO Auto-generated method stub

		setAlertOnHighBp(true);
		setAmplitudeRange(1500);
		// setAverageAmplitude(0);
		setConsecutiveAmplitudes(5);
		setEnableSHC(true);
		setGenerateProfileAfter(1);
		setHighestAmplitude(8);
		setIntervalLengths(5);
		setLowestAmplitude(7);
		setQuickAddLocation(0);
		setSaveRecordedCalls(true);
		setEnableNotification(true);
		setEnableVibration(false);
		setBlinkColor(0);
		setNotifyAfterCall(true);

		Toast.makeText(
				context,
				"Your current settings have been reset to factory preferences.",
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * @return the enableNotification
	 */
	public boolean isEnableNotification() {
		return prefs.getBoolean(Utils.ENABLE_NOTIFICATIONS, true);
	}

	/**
	 * @param enableNotification
	 *            the enableNotification to set
	 */
	public void setEnableNotification(boolean enableNotification) {
		this.enableNotification = enableNotification;
		editor.putBoolean(Utils.ENABLE_NOTIFICATIONS, enableNotification);
		editor.commit();
	}

	/**
	 * @return the enableVibration
	 */
	public boolean isEnableVibration() {
		return prefs.getBoolean(Utils.VIBRATE, false);
	}

	/**
	 * @param enableVibration
	 *            the enableVibration to set
	 */
	public void setEnableVibration(boolean enableVibration) {
		this.enableVibration = enableVibration;
		editor.putBoolean(Utils.VIBRATE, enableVibration);
		editor.commit();
	}

	/**
	 * @return the blinkColor
	 */
	public int getBlinkColor() {
		return prefs.getInt(Utils.BLINK_LIGHT_COLOR, 0);
	}

	/**
	 * @param blinkColor
	 *            the blinkColor to set
	 */
	public void setBlinkColor(int blinkColor) {
		this.blinkColor = blinkColor;
		editor.putInt(Utils.BLINK_LIGHT_COLOR, blinkColor);
		editor.commit();
	}

	public void setNotifyAfterCall(boolean notifyAfterCall) {
		// TODO Auto-generated method stub
		this.notifyAfterCall = notifyAfterCall;
		editor.putBoolean(Utils.NOTIFY_AFTER_CALL, notifyAfterCall);
		editor.commit();
	}

	boolean isNotifyAfterCall() {
		return prefs.getBoolean(Utils.NOTIFY_AFTER_CALL, true);
	}

}
