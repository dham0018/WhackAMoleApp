package com.dhamu.whackamole.database;

import java.util.ArrayList;

import com.dhamu.whackamole.model.ScoreModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseUtility {

	static String TAG = "DatabaseUtility";
	//table field..
	public static final String SCORE_ID = "ScoreID", USER_NAME = "UserName", SCORE = "Score";
	
	//Create Tables
	public static final String CREATE_SQL_SCORE = "create table " + DatabaseHelper.TABLE_SCORE+ "(" + SCORE_ID + " integer primary key autoincrement, " + USER_NAME + " text, " + SCORE + " integer " + ")";
	
	static boolean isDelete = false;
	
	public static void insertScore(Context context, ScoreModel scoreModel) {
		try {
			Log.v(TAG, "in insertScore");
			SQLiteDatabase database = DatabaseHelper.getReadableDatabase(context);
			ContentValues values = new ContentValues();
			values.put(USER_NAME, scoreModel.getUserName());
			values.put(SCORE, scoreModel.getScore());
			database.insert(DatabaseHelper.TABLE_SCORE, null, values);
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ScoreModel> getTop5Score(Context context) {
		SQLiteDatabase dataBase = DatabaseHelper.getReadableDatabase(context);
		ArrayList<ScoreModel> scoreModels = new ArrayList<ScoreModel>();
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_SCORE + " ORDER BY " + SCORE + " DESC LIMIT 5 ";
        Cursor cursor = dataBase.rawQuery(selectQuery, null);
        Log.v(TAG, "cursor size = " + cursor.getColumnCount());
        if (cursor.moveToFirst()) {
            do {
            	ScoreModel scoreModel = new ScoreModel();
            	scoreModel.setScoreId(Integer.parseInt(cursor.getString(0)));
            	scoreModel.setUserName(cursor.getString(1));
            	scoreModel.setScore(Integer.parseInt(cursor.getString(2)));
            	scoreModels.add(scoreModel);
            } while (cursor.moveToNext());
        }
		return scoreModels;
	}
	
	public static boolean isScoreWillStore(Context context, int score) {
		SQLiteDatabase dataBase = DatabaseHelper.getReadableDatabase(context);
		ArrayList<ScoreModel> scoreModels = new ArrayList<ScoreModel>();
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_SCORE + " ORDER BY " + SCORE + " DESC LIMIT 5 ";
        Cursor cursor = dataBase.rawQuery(selectQuery, null);
        Log.v(TAG, "cursor size = " + cursor.getColumnCount());
        if (cursor.moveToFirst()) {
            do {
            	ScoreModel scoreModel = new ScoreModel();
            	scoreModel.setScoreId(Integer.parseInt(cursor.getString(0)));
            	scoreModel.setUserName(cursor.getString(1));
            	scoreModel.setScore(Integer.parseInt(cursor.getString(2)));
            	scoreModels.add(scoreModel);
            } while (cursor.moveToNext());
        }
        if(scoreModels.size() < 5) {
        	return true;
        }
        else {
			if(score > scoreModels.get(scoreModels.size() - 1).getScore()) {
				return true;
			}
		}
		return false;
	}
	
//	for get max id from table column
//	public int getMaxColumnData() {
//	    mDb = mDbManager.getReadableDatabase();
//	    final SQLiteStatement stmt = mDb.compileStatement("SELECT MAX(column) FROM Table");
//	    return (int) stmt.simpleQueryForLong();
//	}
}