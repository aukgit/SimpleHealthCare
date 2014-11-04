package com.marvik.apps.simplehealthcare;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

public class Database {
	Cursor cursor ;
	Context context;
	DbMafia dbMafia;
	Utils utils;
	SQLiteDatabase sqLiteDatabase;
	SharedPreferences prefs;
	public Database(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
	}
	public void initDatabase(){
		dbMafia = new DbMafia(context);
		
	}
	public void getReadableDatabase(){
		if(dbMafia==null){
		dbMafia = new DbMafia(context);
		}
		sqLiteDatabase = dbMafia.getReadableDatabase();
		
	}
	public void getWritableDatabase(){
		if(dbMafia==null){
			dbMafia = new DbMafia(context);
			}
		sqLiteDatabase = dbMafia.getWritableDatabase();
		
	}
	public void closeDatabase(){
		if(dbMafia==null){
			dbMafia = new DbMafia(context);
			}
		sqLiteDatabase.close();
			dbMafia.close();
		
	}
	public void insertParsedAmplitudes() {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		
		values.put(Utils.COL_CALL_COUNT,getCallCount() );
		values.put(Utils.COL_LOWEST_AMPLITUDE,getLowestParsedAmplitude() );
		values.put(Utils.COL_HIGHEST_AMPLITUDE,getHighestParsedAmplitude() );
		values.put(Utils.COL_AMPLITUDE_RANGE,getAmplitudeRange() );
		values.put(Utils.COL_AVERAGE_AMPLITUDE,getAverageAmplitude() );
		values.put(Utils.COL_MIN_AMPLITUDE,getMinAllowedAmplitude() );
		values.put(Utils.COL_MAX_AMPLITUDE,getMaxAllowedAmplitude() );
		values.put(Utils.COL_INTERVAL_RANGE,getAmplitudeIntervalLength() );
		
		sqLiteDatabase.insert(Utils.VOICE_AMPLITUDES_TABLE, null, values);
		closeDatabase();
	}
	public Cursor query(String voiceAmplitudesTable,String columns[],String selection,String selectionArgs[],String groupBy,String having,String orderBy) {
		// TODO Auto-generated method stub
		cursor = sqLiteDatabase.query(voiceAmplitudesTable, columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
	public Cursor getCursor(){
		return cursor;
	}
	private class DbMafia extends SQLiteOpenHelper {

		public DbMafia(Context context) {

			super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE "+Utils.VOICE_AMPLITUDES_TABLE+" (" 
					+Utils.COL_ID+" integer primary key autoincrement,"
					+Utils.COL_CALL_COUNT +" integer, "
					+Utils.COL_LOWEST_AMPLITUDE +" integer, "
					+Utils.COL_HIGHEST_AMPLITUDE+" integer, "
					+Utils.COL_AMPLITUDE_RANGE+" integer, "
					+Utils.COL_AVERAGE_AMPLITUDE+" integer, "
					+Utils.COL_MIN_AMPLITUDE+" integer, "
					+Utils.COL_MAX_AMPLITUDE+" integer, "
					+Utils.COL_INTERVAL_RANGE+ " integer );");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE "+Utils.VOICE_AMPLITUDES_TABLE);
			onCreate(db);
		}

	}
	public int getCallCount() {
		// TODO Auto-generated method stub
		return prefs.getInt(Utils.CALL_COUNT, 0);
	}
		public int getMaxAllowedAmplitude() {
			// TODO Auto-generated method stub
			String [] maxAllowed = context.getResources().getStringArray(R.array.highest_amplitude);
			return Integer.parseInt(maxAllowed[prefs.getInt(Utils.AMPLITUDE_MAX_ALLOWED, 0)]);
		}
		
		public int getMinAllowedAmplitude() {
			// TODO Auto-generated method stub
			String [] minAllowed = context.getResources().getStringArray(R.array.lowest_amplitude);
			return Integer.parseInt(minAllowed[prefs.getInt(Utils.AMPLITUDE_MIN_ALLOWED, 0)]);
		}
		

		public int getLowestParsedAmplitude() {
			// TODO Auto-generated method stub
			return prefs.getInt(Utils.LOWEST_PARSED_AMPLITUDE, getMinAllowedAmplitude());
		}
		

		public int getHighestParsedAmplitude() {
			// TODO Auto-generated method stub
			return prefs.getInt(Utils.HIGHEST_PARSED_AMPLITUDE, getMaxAllowedAmplitude());
		}

		

		public int getAmplitudeRange() {
			// TODO Auto-generated method stub
			return prefs.getInt(Utils.AMPLITUDE_RANGE, 300);
		}
		

		public int getAverageAmplitude() {
			// TODO Auto-generated method stub
			return prefs.getInt(Utils.AVERAGE_AMPLITUDE, 6000);
		}
		
		public int getAmplitudeIntervalLength() {
			// TODO Auto-generated method stub
			String [] interval = context.getResources().getStringArray(R.array.interval);
			return Integer.parseInt(interval[prefs.getInt(Utils.INTERVAL_LENGTH, 5)]);
		}
		public int getColumn(String columnName) {
			// TODO Auto-generated method stub
			
			return cursor.getInt(cursor.getColumnIndex(columnName));
		}
		
		
}
