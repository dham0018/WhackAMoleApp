package com.dhamu.whackamole;

import java.util.ArrayList;

import com.dhamu.whackamole.adapter.ScoreAdapter;
import com.dhamu.whackamole.database.DatabaseUtility;
import com.dhamu.whackamole.model.ScoreModel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LeaderboardActivity extends Activity {

	TextView txtUserName1, txtUserName2, txtUserName3, txtUserName4, txtUserName5;
	TextView txtUserScore1, txtUserScore2, txtUserScore3, txtUserScore4, txtUserScore5;
	Button btnMainMenu;
	ListView lvScore;
	TextView txtNoRecords;
	boolean isFromMenu = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_leaderboard);
		
		setViews();
		
	}

	private void setViews() {
//		txtUserName1 = (TextView) findViewById(R.id.txtUserName1);
//		txtUserName2 = (TextView) findViewById(R.id.txtUserName2);
//		txtUserName3 = (TextView) findViewById(R.id.txtUserName3);
//		txtUserName4 = (TextView) findViewById(R.id.txtUserName4);
//		txtUserName5 = (TextView) findViewById(R.id.txtUserName5);
//		txtUserScore1 = (TextView) findViewById(R.id.txtUserScore1);
//		txtUserScore2 = (TextView) findViewById(R.id.txtUserScore2);
//		txtUserScore3 = (TextView) findViewById(R.id.txtUserScore3);
//		txtUserScore4 = (TextView) findViewById(R.id.txtUserScore4);
//		txtUserScore5 = (TextView) findViewById(R.id.txtUserScore5);
		
		txtNoRecords = (TextView) findViewById(R.id.txtNoRecord);
		
		lvScore = (ListView) findViewById(R.id.lvScore);
		
		ArrayList<ScoreModel> arrlstScore = DatabaseUtility.getTop5Score(getApplicationContext()); 
		
		if(arrlstScore.size() > 0) {
			ScoreAdapter scoreAdapter = new ScoreAdapter(LeaderboardActivity.this, arrlstScore);
			lvScore.setAdapter(scoreAdapter);
		}
		else {
			txtNoRecords.setVisibility(View.VISIBLE);
		}
		
		btnMainMenu = (Button) findViewById(R.id.btnMainMenu);
		
		isFromMenu = getIntent().getBooleanExtra("isFromMenu", false);
		
		btnMainMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
//				if(isFromMenu) {
//					finish();
//				}
//				else {
//					
//				}
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.leaderboard, menu);
		return true;
	}

}
