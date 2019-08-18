package com.dhamu.whackamole.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Database Name
	private static final String DATABASE_NAME = "dbWhackamole";
	static final String TABLE_SCORE = "tblScore";
	// Database Version
	private static final int DATABASE_VERSION = 1;
			
	@SuppressLint("NewApi")
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DatabaseUtility.CREATE_SQL_SCORE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
			onCreate(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Purpose to Read a database
	 */
	public static SQLiteDatabase getReadableDatabase(Context context) {
		DatabaseHelper messageDatabaseHelper = new DatabaseHelper(context);
		return messageDatabaseHelper.getReadableDatabase();
	}
}