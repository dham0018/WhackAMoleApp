package com.dhamu.whackamole;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameOverActivity extends Activity {

	Button btnPlayAgain, btnMainMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game_over);
		
		setViews();
	}
	
	private void setViews() {
		btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
		btnMainMenu = (Button) findViewById(R.id.btnMainMenu);
		
		btnPlayAgain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(GameOverActivity.this, RoundDisplayActivity.class);
				intent.putExtra("round", 1);
				startActivity(intent);
			}
		});
		
		btnMainMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_over, menu);
		return true;
	}

}
