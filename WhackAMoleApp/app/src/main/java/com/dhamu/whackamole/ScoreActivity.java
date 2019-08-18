package com.dhamu.whackamole;

import com.dhamu.whackamole.database.DatabaseUtility;
import com.dhamu.whackamole.model.ScoreModel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreActivity extends Activity {

	TextView txtScore;
	EditText edtUserName;
	Button btnOK;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_score);
		
		setViews();
	}

	private void setViews() {
		txtScore = (TextView) findViewById(R.id.txtScore);
		edtUserName = (EditText) findViewById(R.id.edtUserName);
		btnOK = (Button) findViewById(R.id.btnOk);
		
		final int score = getIntent().getIntExtra("score", 0);
		txtScore.setText("New Score: " + String.valueOf(score));
		
		btnOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!edtUserName.getText().toString().trim().equals("")) {
					edtUserName.setBackgroundResource(R.drawable.edittext_back);
					ScoreModel scoreModel = new ScoreModel();
					scoreModel.setUserName(edtUserName.getText().toString());
					scoreModel.setScore(score);
					DatabaseUtility.insertScore(getApplicationContext(), scoreModel);
					
					finish();
					Intent intent = new Intent(ScoreActivity.this, LeaderboardActivity.class);
					startActivity(intent);
				}
				else {
					edtUserName.setBackgroundResource(R.drawable.edittext_validation);
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}

}
