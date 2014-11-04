package com.marvik.apps.simplehealthcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity implements OnCheckedChangeListener, OnClickListener, OnItemSelectedListener {
	Config config;String [] quickAdd;
	Spinner spSettingsType;
	LinearLayout llBasic,llAdvanced,llNotification;
	
	///////////////////////////////// Basic Settings //////////////////////////////////////////////////////
	// TextViews
	TextView tvSettings, tvEnableSHC, tvAlertOnHighBp, tvSaveRecordedFiles,
			tvAmplitudeRange, tvAverageAmplitudeLabel, tvAverageAmplitudeVaue,
			tvQuickAdd;

	// CheckBoxes
	CheckBox cbEnableSHC, cbAlertOnHighBp, cbSaveRecordedFiles;

	// Buttons
	Button btPlus, btMinus;

	// EditText
	EditText etAmplitudeRange;

	// Spinners
	Spinner spQuickAdd;
/////////////////////////////////////// ADVANCED SETTIINGS ///////////////////////////////////////////////////
	//Buttons
	Button btResetAll,btSetDefaults;
	
	//TextView
	TextView tvGenerateProfileAfter,tvIntervalLengths,tvConsecutiveAmplitudes,tvLowestAmplitude,tvHighestAmplitude;
	
	//Spinners
	Spinner spGenerateProfileAfter,spIntervalLengths,spConsecutiveAmplitudes,spLowestAmplitude,spHighestAmplitude;
	
/////////////////////////////////////// NOTIFICATION SETTINGS ////////////////////////////////////////////////
	//Checkbox
	CheckBox cbEnableNotifications,cbEnableVibration,cbNotifyAfterCall;
	
	//Spinner
	Spinner spBlinkColor;
	
	//TextView
	TextView tvBlinkColor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);
		
		init();
		
		
		initSettings();
		initAdvanced();
		initNotifications();

		readSettingsPrefs();
		readAdvancedPrefs();
		readNotificationsPrefs();

	}

	private void init() {
	// TODO Auto-generated method stub
		config = new Config(this);
		quickAdd = getResources().getStringArray(R.array.quick_add);
		spSettingsType = (Spinner)findViewById(R.id.config_spinner_settings_type);
		spSettingsType.setOnItemSelectedListener(this);
		
		llBasic= (LinearLayout)findViewById(R.id.config_linearLayout_basics);
		llAdvanced= (LinearLayout)findViewById(R.id.config_linearLayout_advanced);
		llNotification = (LinearLayout)findViewById(R.id.config_linearLayout_notifications);
	}

	private void initSettings() {
		// TODO Auto-generated method stub
		tvSettings = (TextView) findViewById(R.id.config_textView_settings);
		tvEnableSHC = (TextView) findViewById(R.id.config_textView_enable_app);
		tvAlertOnHighBp = (TextView) findViewById(R.id.config_textView_alert_high_bp);
		tvSaveRecordedFiles = (TextView) findViewById(R.id.config_textView_save_call_records);

		tvAmplitudeRange = (TextView) findViewById(R.id.config_textView_amplitude_range);
		tvAverageAmplitudeLabel = (TextView) findViewById(R.id.config_textView_average_amplitude_label);
		tvAverageAmplitudeVaue = (TextView) findViewById(R.id.config_textView_average_amplitude_value);
		tvQuickAdd = (TextView) findViewById(R.id.config_textView_quick_add);

		// CheckBoxes
		cbEnableSHC = (CheckBox) findViewById(R.id.config_checkBox_enable_app);
		cbEnableSHC.setOnCheckedChangeListener(this);
		
		cbAlertOnHighBp = (CheckBox) findViewById(R.id.config_checkBox_alert_high_bp);
		cbAlertOnHighBp.setOnCheckedChangeListener(this);
		
		cbSaveRecordedFiles = (CheckBox) findViewById(R.id.config_checkBox_save_call_records);
		cbSaveRecordedFiles.setOnCheckedChangeListener(this);

		// Buttons
		btPlus = (Button) findViewById(R.id.config_button_plus);
		btPlus.setOnClickListener(this);
		
		btMinus = (Button) findViewById(R.id.config_button_minus);
		btMinus.setOnClickListener(this);

		// EditText
		etAmplitudeRange = (EditText) findViewById(R.id.config_editText_amplitude);

		// Spinners
		spQuickAdd = (Spinner) findViewById(R.id.config_spinner_quick_add);
		spQuickAdd.setOnItemSelectedListener(this);
	}

	private void readSettingsPrefs() {
		// TODO Auto-generated method stub

		tvAverageAmplitudeVaue.setText(""+config.getAverageAmplitude());
		cbEnableSHC.setChecked(config.isEnableSHC());
		cbAlertOnHighBp.setChecked(config.isAlertOnHighBp());
		cbSaveRecordedFiles.setChecked(config.isSaveRecordedCalls());

		 etAmplitudeRange.setText(""+(config.getAmplitudeRange()));

		// Spinners
		spQuickAdd.setSelection(config.getQuickAddLocation());
	}

	private void initAdvanced() {
		// TODO Auto-generated method stub
		btResetAll = (Button) findViewById(R.id.config_button_reset_all);
		btSetDefaults = (Button) findViewById(R.id.config_button_set_defaults);
		
		btResetAll.setOnClickListener(this);
		btSetDefaults.setOnClickListener(this);
		
		//TextView
		tvGenerateProfileAfter = (TextView) findViewById(R.id.config_textView_generate_profile);
		tvIntervalLengths = (TextView) findViewById(R.id.config_textView_amplitude_intervals);
		tvConsecutiveAmplitudes = (TextView) findViewById(R.id.config_textView_consecutive_amplitudes);
		tvLowestAmplitude = (TextView) findViewById(R.id.config_textView_lowest_amplitude);
		tvHighestAmplitude = (TextView) findViewById(R.id.config_textView_highest_amplitude);
		
		//Spinners
		spGenerateProfileAfter = (Spinner) findViewById(R.id.config_spinner_generate_profile);
		spIntervalLengths = (Spinner) findViewById(R.id.config_spinner_intervals);
		spConsecutiveAmplitudes = (Spinner) findViewById(R.id.config_spinner_consecutive_amplitudes);
		spLowestAmplitude = (Spinner) findViewById(R.id.config_spinner_lowest_amplitude);
		spHighestAmplitude = (Spinner) findViewById(R.id.config_spinner_highest_amplitude);
		
		spGenerateProfileAfter.setOnItemSelectedListener(this);
		spIntervalLengths.setOnItemSelectedListener(this);
		spConsecutiveAmplitudes.setOnItemSelectedListener(this);
		spLowestAmplitude.setOnItemSelectedListener(this);
		spHighestAmplitude.setOnItemSelectedListener(this);
	}

	private void readAdvancedPrefs() {
		// TODO Auto-generated method stub
		spGenerateProfileAfter.setSelection(config.getGenerateProfileAfter());
		spIntervalLengths.setSelection(config.getIntervalLengths());
		spConsecutiveAmplitudes.setSelection(config.getConsecutiveAmplitudes());
		spLowestAmplitude.setSelection(config.getLowestAmplitude());
		spHighestAmplitude.setSelection(config.getHighestAmplitude());
	}

	private void initNotifications() {
		// TODO Auto-generated method stub
		//Checkbox
		cbEnableNotifications = (CheckBox) findViewById(R.id.config_checkBox_enable_notifications);
		cbEnableVibration = (CheckBox) findViewById(R.id.config_checkBox_enable_vibrations);
		cbNotifyAfterCall= (CheckBox) findViewById(R.id.config_checkBox_enable_aftercall);
		
		
		cbEnableNotifications.setOnCheckedChangeListener(this);
		cbEnableVibration.setOnCheckedChangeListener(this);
		cbNotifyAfterCall.setOnCheckedChangeListener(this);

		// Spinner
		spBlinkColor = (Spinner) findViewById(R.id.config_spinner_blink_color);
		spBlinkColor.setOnItemSelectedListener(this);

		// TextView
		tvBlinkColor = (TextView) findViewById(R.id.config_textView_blink_light);
	}

	private void readNotificationsPrefs() {
		// TODO Auto-generated method stub
		//Checkbox
		cbEnableNotifications.setChecked(config.isEnableNotification());
		cbEnableVibration.setChecked(config.isEnableVibration());
		cbNotifyAfterCall.setChecked(config.isNotifyAfterCall());
		
		//Spinner
		spBlinkColor.setSelection(config.getBlinkColor());
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//spQuickAdd
		if(parent.getId()==R.id.config_spinner_quick_add){
			config.setQuickAddLocation(position);
		}
		//spGenerateProfileAfter = (Spinner) findViewById(R.id.config_spinner_generate_profile);
		if(parent.getId()==R.id.config_spinner_generate_profile){
			config.setGenerateProfileAfter(position);
		}
		//spIntervalLengths = (Spinner) findViewById(R.id.config_spinner_intervals);
		if(parent.getId()==R.id.config_spinner_intervals){
			config.setIntervalLengths(position);
		}
		//spConsecutiveAmplitudes = (Spinner) findViewById(R.id.config_spinner_consecutive_amplitudes);
		if(parent.getId()==R.id.config_spinner_consecutive_amplitudes){
			config.setConsecutiveAmplitudes(position);
		}
		//spLowestAmplitude = (Spinner) findViewById(R.id.config_spinner_lowest_amplitude);
		if(parent.getId()==R.id.config_spinner_lowest_amplitude){
			config.setLowestAmplitude(position);
		}
		//spHighestAmplitude = (Spinner) findViewById(R.id.config_spinner_highest_amplitude);
		if(parent.getId()==R.id.config_spinner_highest_amplitude){
			config.setHighestAmplitude(position);
		}
		
		//spBlinkColor = (Spinner) findViewById(R.id.config_spinner_blink_color);
		if(parent.getId()==R.id.config_spinner_blink_color){
			config.setBlinkColor(position);
		}
		//spSettingsType = (Spinner)findViewById(R.id.config_spinner_settings_type);
		if(parent.getId()==R.id.config_spinner_settings_type){
			llAdvanced.setVisibility(LinearLayout.GONE);
			llBasic.setVisibility(LinearLayout.GONE);
			llNotification.setVisibility(LinearLayout.GONE);
			switch(position){
			case 0:llBasic.setVisibility(LinearLayout.VISIBLE);
				break;
			case 1:llAdvanced.setVisibility(LinearLayout.VISIBLE);
				break;
			case 2:llNotification.setVisibility(LinearLayout.VISIBLE);
				break;
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.config_button_minus){
			int curValue = Integer.parseInt(etAmplitudeRange.getText().toString());
			etAmplitudeRange.setText(""+(curValue-Integer.parseInt(quickAdd[spQuickAdd.getSelectedItemPosition()])));
			curValue = Integer.parseInt(etAmplitudeRange.getText().toString());
			config.setAmplitudeRange(curValue);
			
		}
		if(v.getId()==R.id.config_button_plus){
			int curValue = Integer.parseInt(etAmplitudeRange.getText().toString());
			etAmplitudeRange.setText(""+(curValue+Integer.parseInt(quickAdd[spQuickAdd.getSelectedItemPosition()])));
			curValue = Integer.parseInt(etAmplitudeRange.getText().toString());
			config.setAmplitudeRange(curValue);
		}
		if(v.getId()==R.id.config_button_reset_all){
			AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
			alert.setTitle("Reset preferences");
			alert.setMessage("Reset all your set preferences");
			alert.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					config.resetPreferences();
					readAdvancedPrefs();
					readNotificationsPrefs();
					readSettingsPrefs();
				}
			});
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Your current settings have not be changed.", Toast.LENGTH_SHORT).show();
					
				}
			});
			alert.create();
			alert.show();
			
		}
		if(v.getId()==R.id.config_button_set_defaults){
			
			
			AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
			alert.setTitle("Restore defaults");
			alert.setMessage("Restore all your set preferences to the default factory preferences");
			alert.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					config.setDefaultPreferences();
					readAdvancedPrefs();
					readNotificationsPrefs();
					readSettingsPrefs();
				}
			});
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Your current settings have not be changed.", Toast.LENGTH_SHORT).show();
					
				}
			});
			alert.create();
			alert.show();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean enable) {
		// TODO Auto-generated method stub
		if(buttonView==cbEnableSHC){
			config.setEnableSHC(enable);
		}
		if(buttonView==cbAlertOnHighBp){
			config.setAlertOnHighBp(enable);
		}
		
		if(buttonView==cbSaveRecordedFiles){
			config.setSaveRecordedCalls(enable);
		}
		if(buttonView==cbEnableNotifications){
			config.setEnableNotification(enable);
		}
		if(buttonView==cbNotifyAfterCall){
			config.setNotifyAfterCall(enable);
		}
		if(buttonView==cbEnableVibration){
			config.setEnableVibration(enable);
		}
	}
}
