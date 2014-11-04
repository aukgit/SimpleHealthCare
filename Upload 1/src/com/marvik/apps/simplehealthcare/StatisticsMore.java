package com.marvik.apps.simplehealthcare;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StatisticsMore extends Activity {
	ListView lvStatistics;
	StatisticsAdapter statisticsAdapter;
	List<CallStats>lCallStats;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats_more);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		lvStatistics = (ListView)findViewById(R.id.stats_more_listView_stats);
		lCallStats = new ArrayList<StatisticsMore.CallStats>();
		readDatabase();
		lvStatistics.setAdapter(new StatisticsAdapter());
	}
	
	
	private void readDatabase() {
		// TODO Auto-generated method stub
		Database database = new Database(StatisticsMore.this);
		database.getReadableDatabase();
		database.query(Utils.VOICE_AMPLITUDES_TABLE,null,null,null,null,null,null);
		for(database.getCursor().moveToFirst();!database.getCursor().isAfterLast();database.getCursor().moveToNext()){
			lCallStats.add(new CallStats("Call "+database.getColumn(Utils.COL_CALL_COUNT), ""+database.getColumn(Utils.COL_LOWEST_AMPLITUDE), ""+ database.getColumn(Utils.COL_LOWEST_AMPLITUDE)*1.5, ""+ database.getColumn(Utils.COL_HIGHEST_AMPLITUDE),""+ database.getColumn(Utils.COL_HIGHEST_AMPLITUDE)*1.5, ""+ database.getColumn(Utils.COL_AMPLITUDE_RANGE),""+ database.getColumn(Utils.COL_AMPLITUDE_RANGE)*1.5,""+ database.getColumn(Utils.COL_AVERAGE_AMPLITUDE),""+ database.getColumn(Utils.COL_MIN_AMPLITUDE),""+ database.getColumn(Utils.COL_MAX_AMPLITUDE),""+ database.getColumn(Utils.COL_INTERVAL_RANGE)));
		}
		database.getCursor().close();
		database.closeDatabase();
	}
		

	class StatisticsAdapter extends ArrayAdapter<CallStats>{

		public StatisticsAdapter() {
			super(StatisticsMore.this, R.layout.stats_ui_more, lCallStats);
			// TODO Auto-generated constructor stub
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View statsView = convertView;
			if(statsView==null){
				statsView = getLayoutInflater().inflate(R.layout.stats_ui_more, parent, false);
			}
			CallStats callStats = lCallStats.get(position);
			
			//LinearLayout llAdvanced = (LinearLayout)statsView.findViewById(R.id.stats_ui_more_linearLayout_advanced_view);
		
			
			TextView tvCallNumber = (TextView)statsView.findViewById(R.id.stats_ui_more_textView_call_no); 
			TextView tvLowestAmplitudeAmplitude= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_lowest_amplitudes_amplitudes);
			TextView tvLowestAmplitudeDecibels= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_lowest_amplitude_decibels);
			TextView tvHighestAmplitudeAmplitude= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_highest_amplitudes_amplitudes);
			TextView tvHighestAmplitudeDecibels= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_highest_amplitude_decibels); 
			TextView tvAmplitudeRangeAmplitudes= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_amplitude_range_amplitudes);
			TextView tvAmplitudeRangeDecibels= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_amplitude_range_decibels); 
			TextView tvAverageAmplitudeAmplitudes= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_average_amplitude_amplitudes); 
			TextView tvLowestAllowedAmplitude= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_lowest_allowed_amplitudes_amplitude); 
			TextView tvHighestAllowedAmplitude= (TextView)statsView.findViewById(R.id.stats_ui_more_textView_highest_allowed_amplitudes_amplitude); 
			TextView tvAmplitudeInterval = (TextView)statsView.findViewById(R.id.stats_ui_more_textView_amplitude_interval_amplitude); 
			
			//set text
			tvCallNumber.setText(callStats.getCallNumber());
			tvLowestAmplitudeAmplitude.setText(callStats.getLowestAmplitudeAmplitude());
			tvLowestAmplitudeDecibels.setText(callStats.getLowestAmplitudeDecibels());
			tvHighestAmplitudeAmplitude.setText(callStats.getHighestAmplitudeAmplitude());
			tvHighestAmplitudeDecibels.setText(callStats.getHighestAmplitudeDecibels()); 
			tvAmplitudeRangeAmplitudes.setText(callStats.getAmplitudeRangeAmplitudes());
			tvAmplitudeRangeDecibels.setText(callStats.getAmplitudeRangeDecibels()); 
			tvAverageAmplitudeAmplitudes.setText(callStats.getAverageAmplitudeAmplitudes()); 
			tvLowestAllowedAmplitude.setText(callStats.getLowestAllowedAmplitude()); 
			tvHighestAllowedAmplitude.setText(callStats.getHighestAllowedAmplitude()); 
			tvAmplitudeInterval.setText(callStats.getAmplitudeInterval());
			return statsView;
		}
		
	}
	class CallStats{
		 String callNumber, lowestAmplitudeAmplitude, lowestAmplitudeDecibels,
		 highestAmplitudeAmplitude, highestAmplitudeDecibels, amplitudeRangeAmplitudes, amplitudeRangeDecibels,
		 averageAmplitudeAmplitudes,  lowestAllowedAmplitude,highestAllowedAmplitude,  amplitudeInterval;
		
		CallStats(String callNumber,String lowestAmplitudeAmplitude,String lowestAmplitudeDecibels,
		String highestAmplitudeAmplitude,String highestAmplitudeDecibels,String amplitudeRangeAmplitudes,
		String amplitudeRangeDecibels ){
			this.callNumber = callNumber;
			this.lowestAmplitudeAmplitude = lowestAmplitudeAmplitude;
			this.lowestAmplitudeDecibels = lowestAmplitudeDecibels;
			this.highestAmplitudeAmplitude = highestAmplitudeAmplitude;
			this.highestAmplitudeDecibels = highestAmplitudeDecibels;
			this.amplitudeRangeAmplitudes = amplitudeRangeAmplitudes;
			this.amplitudeRangeDecibels = amplitudeRangeDecibels;
			
		}
		
		CallStats(String averageAmplitudeAmplitudes, String lowestAllowedAmplitude,String highestAllowedAmplitude, String amplitudeInterval){
			
			this.averageAmplitudeAmplitudes = averageAmplitudeAmplitudes;
			this.lowestAllowedAmplitude = lowestAllowedAmplitude;
			this.highestAllowedAmplitude=highestAllowedAmplitude;
			this.amplitudeInterval = amplitudeInterval;
		}
		CallStats(String callNumber,String lowestAmplitudeAmplitude,String lowestAmplitudeDecibels,
		String highestAmplitudeAmplitude,String highestAmplitudeDecibels,String amplitudeRangeAmplitudes,String amplitudeRangeDecibels,
		String averageAmplitudeAmplitudes, String lowestAllowedAmplitude,String highestAllowedAmplitude, String amplitudeInterval){
			this.callNumber = callNumber;
			this.lowestAmplitudeAmplitude = lowestAmplitudeAmplitude;
			this.lowestAmplitudeDecibels = lowestAmplitudeDecibels;
			this.highestAmplitudeAmplitude = highestAmplitudeAmplitude;
			this.highestAmplitudeDecibels = highestAmplitudeDecibels;
			this.amplitudeRangeAmplitudes = amplitudeRangeAmplitudes;
			this.amplitudeRangeDecibels = amplitudeRangeDecibels;
			this.averageAmplitudeAmplitudes = averageAmplitudeAmplitudes;
			this.lowestAllowedAmplitude = lowestAllowedAmplitude;
			this.highestAllowedAmplitude=highestAllowedAmplitude;
			this.amplitudeInterval = amplitudeInterval;
		}

		/**
		 * @return the callNumber
		 */
		public String getCallNumber() {
			return callNumber;
		}

		/**
		 * @param callNumber the callNumber to set
		 */
		public void setCallNumber(String callNumber) {
			this.callNumber = callNumber;
		}

		/**
		 * @return the lowestAmplitudeAmplitude
		 */
		public String getLowestAmplitudeAmplitude() {
			return lowestAmplitudeAmplitude;
		}

		/**
		 * @param lowestAmplitudeAmplitude the lowestAmplitudeAmplitude to set
		 */
		public void setLowestAmplitudeAmplitude(String lowestAmplitudeAmplitude) {
			this.lowestAmplitudeAmplitude = lowestAmplitudeAmplitude;
		}

		/**
		 * @return the lowestAmplitudeDecibels
		 */
		public String getLowestAmplitudeDecibels() {
			return lowestAmplitudeDecibels;
		}

		/**
		 * @param lowestAmplitudeDecibels the lowestAmplitudeDecibels to set
		 */
		public void setLowestAmplitudeDecibels(String lowestAmplitudeDecibels) {
			this.lowestAmplitudeDecibels = lowestAmplitudeDecibels;
		}

		/**
		 * @return the highestAmplitudeAmplitude
		 */
		public String getHighestAmplitudeAmplitude() {
			return highestAmplitudeAmplitude;
		}

		/**
		 * @param highestAmplitudeAmplitude the highestAmplitudeAmplitude to set
		 */
		public void setHighestAmplitudeAmplitude(String highestAmplitudeAmplitude) {
			this.highestAmplitudeAmplitude = highestAmplitudeAmplitude;
		}

		/**
		 * @return the highestAmplitudeDecibels
		 */
		public String getHighestAmplitudeDecibels() {
			return highestAmplitudeDecibels;
		}

		/**
		 * @param highestAmplitudeDecibels the highestAmplitudeDecibels to set
		 */
		public void setHighestAmplitudeDecibels(String highestAmplitudeDecibels) {
			this.highestAmplitudeDecibels = highestAmplitudeDecibels;
		}

		/**
		 * @return the amplitudeRangeAmplitudes
		 */
		public String getAmplitudeRangeAmplitudes() {
			return amplitudeRangeAmplitudes;
		}

		/**
		 * @param amplitudeRangeAmplitudes the amplitudeRangeAmplitudes to set
		 */
		public void setAmplitudeRangeAmplitudes(String amplitudeRangeAmplitudes) {
			this.amplitudeRangeAmplitudes = amplitudeRangeAmplitudes;
		}

		/**
		 * @return the amplitudeRangeDecibels
		 */
		public String getAmplitudeRangeDecibels() {
			return amplitudeRangeDecibels;
		}

		/**
		 * @param amplitudeRangeDecibels the amplitudeRangeDecibels to set
		 */
		public void setAmplitudeRangeDecibels(String amplitudeRangeDecibels) {
			this.amplitudeRangeDecibels = amplitudeRangeDecibels;
		}

		/**
		 * @return the averageAmplitudeAmplitudes
		 */
		public String getAverageAmplitudeAmplitudes() {
			return averageAmplitudeAmplitudes;
		}

		/**
		 * @param averageAmplitudeAmplitudes the averageAmplitudeAmplitudes to set
		 */
		public void setAverageAmplitudeAmplitudes(String averageAmplitudeAmplitudes) {
			this.averageAmplitudeAmplitudes = averageAmplitudeAmplitudes;
		}

		/**
		 * @return the lowestAllowedAmplitude
		 */
		public String getLowestAllowedAmplitude() {
			return lowestAllowedAmplitude;
		}

		/**
		 * @param lowestAllowedAmplitude the lowestAllowedAmplitude to set
		 */
		public void setHighestAllowedAmplitude(String highestAllowedAmplitude) {
			this.highestAllowedAmplitude = highestAllowedAmplitude;
		}
		/**
		 * @return the lowestAllowedAmplitude
		 */
		public String getHighestAllowedAmplitude() {
			return highestAllowedAmplitude;
		}

		/**
		 * @param lowestAllowedAmplitude the lowestAllowedAmplitude to set
		 */
		public void setLowestAllowedAmplitude(String lowestAllowedAmplitude) {
			this.lowestAllowedAmplitude = lowestAllowedAmplitude;
		}

		/**
		 * @return the amplitudeInterval
		 */
		public String getAmplitudeInterval() {
			return amplitudeInterval;
		}
		

		/**
		 * @param amplitudeInterval the amplitudeInterval to set
		 */
		public void setAmplitudeInterval(String amplitudeInterval) {
			this.amplitudeInterval = amplitudeInterval;
		}
	}
	
	
}