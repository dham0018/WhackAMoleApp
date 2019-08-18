package com.dhamu.whackamole;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class RoundDisplayActivity extends Activity {

	TextView txtRound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_round_display);
		
		setViews();
		
	}

	private void setViews() {
		txtRound = (TextView) findViewById(R.id.txtRound);
		
		final int round = getIntent().getIntExtra("round", 1);
		
		txtRound.setText(String.valueOf(round));
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				finish();
				Intent intent = new Intent(RoundDisplayActivity.this, PlayActivity.class);
				intent.putExtra("round", round);
				startActivity(intent);
			}
		}, 800);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.round_display, menu);
		return true;
	}

}
