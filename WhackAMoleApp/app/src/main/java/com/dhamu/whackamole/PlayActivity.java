package com.dhamu.whackamole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.dhamu.whackamole.adapter.CustomGridAdapter;
import com.dhamu.whackamole.database.DatabaseUtility;
import com.dhamu.whackamole.utility.MyAnimation;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity implements OnClickListener {

	public static int TOTAL_MOLE_ON_LAYOUT = 12;
	int rndMax = 12;
	int rndMin = 1;
	
	TextView txtTimer, txtCounter;
//	GridView gvMoles;
	RelativeLayout layoutMole1, layoutMole2, layoutMole3, layoutMole4, layoutMole5, layoutMole6, layoutMole7, layoutMole8, 
				 layoutMole9, layoutMole10, layoutMole11, layoutMole12;
	TextView txtMole1, txtMole2, txtMole3, txtMole4, txtMole5, txtMole6, txtMole7, txtMole8, txtMole9, txtMole10, txtMole11, 
			 txtMole12;
	ImageView ivMole1, ivMole2, ivMole3, ivMole4, ivMole5, ivMole6, ivMole7, ivMole8, ivMole9, ivMole10, ivMole11, ivMole12;
	ImageView ivHit1, ivHit2, ivHit3, ivHit4, ivHit5, ivHit6, ivHit7, ivHit8, ivHit9, ivHit10, ivHit11, ivHit12;
	ImageView ivHammer;
	RelativeLayout layoutMain;
	
	int round;
	int intUserScore;
	int intTotalScore = 5;
	int intUserTime;
	int intTotalTime = 10000; // in milliseconds
	
	MediaPlayer mpSmash, mpBg;
	
	LinkedHashMap<Integer, RelativeLayout> lhmMoleLayouts;
	LinkedHashMap<Integer, Integer> lhmMoleDrawable;
	LinkedHashMap<Integer, ImageView> lhmMoles;
	LinkedHashMap<ImageView, Integer> lhmTempMoles;
	LinkedHashMap<ImageView, String> lhmStrikedMoleName;
	LinkedHashMap<ImageView, String> lhmMoleName;
	
	Random random;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_play);
		
		setViews();
		
	}

	private void setViews() {
		txtTimer = (TextView) findViewById(R.id.txtTimer);
		txtCounter = (TextView) findViewById(R.id.txtCounter);
		
//		gvMoles = (GridView) findViewById(R.id.gvMoles);
		
		round = getIntent().getIntExtra("round", 1);
		
		/*if(round >=1 && round < 6) {
			intTotalScore = 5;
			intTotalTime = 10000;
		}
		else if (round >= 6 && round < 12) {
			intTotalScore = 6;
			intTotalTime = 9000;
		}
		else if (round >= 12 && round < 18) {
			intTotalScore = 7;
			intTotalTime = 8000;
		}
		else if (round >= 18 && round < 26) {
			intTotalScore = 8;
			intTotalTime = 7000;
		}
		else if (round >= 26 && round < 35) {
			intTotalScore = 9;
			intTotalTime = 6000;
		}
		else if (round >= 35 && round < 46) {
			intTotalScore = 10;
			intTotalTime = 5000;
		}
		else {
			intTotalScore = 10;
			intTotalTime = 4000;
		}*/
		
		
//		CustomGridAdapter adapter = new CustomGridAdapter(PlayActivity.this, moles);
////        grid=(GridView)findViewById(R.id.grid);
//		
//        gvMoles.setAdapter(adapter);
//        gvMoles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
// 
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(PlayActivity.this, "You Clicked at " +moles[+ position], Toast.LENGTH_SHORT).show();
//            }
//        });

        layoutMole1 = (RelativeLayout) findViewById(R.id.layoutMole1);
        layoutMole1.setOnClickListener(this);
        layoutMole2 = (RelativeLayout) findViewById(R.id.layoutMole2);
        layoutMole2.setOnClickListener(this);
        layoutMole3 = (RelativeLayout) findViewById(R.id.layoutMole3);
        layoutMole3.setOnClickListener(this);
        layoutMole4 = (RelativeLayout) findViewById(R.id.layoutMole4);
        layoutMole4.setOnClickListener(this);
        layoutMole5 = (RelativeLayout) findViewById(R.id.layoutMole5);
        layoutMole5.setOnClickListener(this);
        layoutMole6 = (RelativeLayout) findViewById(R.id.layoutMole6);
        layoutMole6.setOnClickListener(this);
        layoutMole7 = (RelativeLayout) findViewById(R.id.layoutMole7);
        layoutMole7.setOnClickListener(this);
        layoutMole8 = (RelativeLayout) findViewById(R.id.layoutMole8);
        layoutMole8.setOnClickListener(this);
        layoutMole9 = (RelativeLayout) findViewById(R.id.layoutMole9);
        layoutMole9.setOnClickListener(this);
        layoutMole10 = (RelativeLayout) findViewById(R.id.layoutMole10);
        layoutMole10.setOnClickListener(this);
        layoutMole11 = (RelativeLayout) findViewById(R.id.layoutMole11);
        layoutMole11.setOnClickListener(this);
        layoutMole12 = (RelativeLayout) findViewById(R.id.layoutMole12);
        layoutMole12.setOnClickListener(this);
        
        txtMole1 = (TextView) findViewById(R.id.txtMole1);
        txtMole2 = (TextView) findViewById(R.id.txtMole2);
        txtMole3 = (TextView) findViewById(R.id.txtMole3);
        txtMole4 = (TextView) findViewById(R.id.txtMole4);
        txtMole5 = (TextView) findViewById(R.id.txtMole5);
        txtMole6 = (TextView) findViewById(R.id.txtMole6);
        txtMole7 = (TextView) findViewById(R.id.txtMole7);
        txtMole8 = (TextView) findViewById(R.id.txtMole8);
        txtMole9 = (TextView) findViewById(R.id.txtMole9);
        txtMole10 = (TextView) findViewById(R.id.txtMole10);
        txtMole11 = (TextView) findViewById(R.id.txtMole11);
        txtMole12 = (TextView) findViewById(R.id.txtMole12);
        
        ivMole1 = (ImageView) findViewById(R.id.ivMole1);
        ivMole2 = (ImageView) findViewById(R.id.ivMole2);
        ivMole3 = (ImageView) findViewById(R.id.ivMole3);
        ivMole4 = (ImageView) findViewById(R.id.ivMole4);
        ivMole5 = (ImageView) findViewById(R.id.ivMole5);
        ivMole6 = (ImageView) findViewById(R.id.ivMole6);
        ivMole7 = (ImageView) findViewById(R.id.ivMole7);
        ivMole8 = (ImageView) findViewById(R.id.ivMole8);
        ivMole9 = (ImageView) findViewById(R.id.ivMole9);
        ivMole10 = (ImageView) findViewById(R.id.ivMole10);
        ivMole11 = (ImageView) findViewById(R.id.ivMole11);
        ivMole12 = (ImageView) findViewById(R.id.ivMole12);
        
        ivHit1 = (ImageView) findViewById(R.id.ivHit1);
        ivHit2 = (ImageView) findViewById(R.id.ivHit2);
        ivHit3 = (ImageView) findViewById(R.id.ivHit3);
        ivHit4 = (ImageView) findViewById(R.id.ivHit4);
        ivHit5 = (ImageView) findViewById(R.id.ivHit5);
        ivHit6 = (ImageView) findViewById(R.id.ivHit6);
        ivHit7 = (ImageView) findViewById(R.id.ivHit7);
        ivHit8 = (ImageView) findViewById(R.id.ivHit8);
        ivHit9 = (ImageView) findViewById(R.id.ivHit9);
        ivHit10 = (ImageView) findViewById(R.id.ivHit10);
        ivHit11 = (ImageView) findViewById(R.id.ivHit11);
        ivHit12 = (ImageView) findViewById(R.id.ivHit12);
        
        ivHammer = (ImageView) findViewById(R.id.ivHammer);
        layoutMain = (RelativeLayout) findViewById(R.id.layoutMain);
        
        lhmMoleLayouts = new LinkedHashMap<Integer, RelativeLayout>();
        
        lhmMoleLayouts.put(1, layoutMole1);
        lhmMoleLayouts.put(2, layoutMole2);
        lhmMoleLayouts.put(3, layoutMole3);
        lhmMoleLayouts.put(4, layoutMole4);
        lhmMoleLayouts.put(5, layoutMole5);
        lhmMoleLayouts.put(6, layoutMole6);
        lhmMoleLayouts.put(7, layoutMole7);
        lhmMoleLayouts.put(8, layoutMole8);
        lhmMoleLayouts.put(9, layoutMole9);
        lhmMoleLayouts.put(10, layoutMole10);
        lhmMoleLayouts.put(11, layoutMole11);
        lhmMoleLayouts.put(12, layoutMole12);
        
        lhmMoleDrawable = new LinkedHashMap<Integer, Integer>();
        lhmMoleDrawable.put(1, R.drawable.mole1);
        lhmMoleDrawable.put(2, R.drawable.mole2);
        lhmMoleDrawable.put(3, R.drawable.mole3);
        lhmMoleDrawable.put(4, R.drawable.mole4);
        lhmMoleDrawable.put(5, R.drawable.mole5);
        lhmMoleDrawable.put(6, R.drawable.mole6);
        lhmMoleDrawable.put(7, R.drawable.mole7);
        lhmMoleDrawable.put(8, R.drawable.mole8);
        lhmMoleDrawable.put(9, R.drawable.mole9);
        lhmMoleDrawable.put(10, R.drawable.mole10);
        lhmMoleDrawable.put(11, R.drawable.mole11);
        lhmMoleDrawable.put(12, R.drawable.mole12);
        
        lhmMoles = new LinkedHashMap<Integer, ImageView>();
        lhmMoles.put(1, ivMole1);
        lhmMoles.put(2, ivMole2);
        lhmMoles.put(3, ivMole3);
        lhmMoles.put(4, ivMole4);
        lhmMoles.put(5, ivMole5);
        lhmMoles.put(6, ivMole6);
        lhmMoles.put(7, ivMole7);
        lhmMoles.put(8, ivMole8);
        lhmMoles.put(9, ivMole9);
        lhmMoles.put(10, ivMole10);
        lhmMoles.put(11, ivMole11);
        lhmMoles.put(12, ivMole12);
        
        lhmTempMoles = new LinkedHashMap<ImageView, Integer>();
        
        lhmStrikedMoleName = new LinkedHashMap<ImageView, String>();
        lhmStrikedMoleName.put(ivMole1, "M1a");
        lhmStrikedMoleName.put(ivMole2, "M2a");
        lhmStrikedMoleName.put(ivMole3, "M3a");
        lhmStrikedMoleName.put(ivMole4, "M4a");
        lhmStrikedMoleName.put(ivMole5, "M5a");
        lhmStrikedMoleName.put(ivMole6, "M6a");
        lhmStrikedMoleName.put(ivMole7, "M7a");
        lhmStrikedMoleName.put(ivMole8, "M8a");
        lhmStrikedMoleName.put(ivMole9, "M9a");
        lhmStrikedMoleName.put(ivMole10, "M10a");
        lhmStrikedMoleName.put(ivMole11, "M11a");
        lhmStrikedMoleName.put(ivMole12, "M12a");
        
        lhmMoleName = new LinkedHashMap<ImageView, String>();
        lhmMoleName.put(ivMole1, "M1");
        lhmMoleName.put(ivMole2, "M2");
        lhmMoleName.put(ivMole3, "M3");
        lhmMoleName.put(ivMole4, "M4");
        lhmMoleName.put(ivMole5, "M5");
        lhmMoleName.put(ivMole6, "M6");
        lhmMoleName.put(ivMole7, "M7");
        lhmMoleName.put(ivMole8, "M8");
        lhmMoleName.put(ivMole9, "M9");
        lhmMoleName.put(ivMole10, "M10");
        lhmMoleName.put(ivMole11, "M11");
        lhmMoleName.put(ivMole12, "M12");
        
        
        if(round ==1) {
			intTotalScore = 10;
			intTotalTime = 30000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole1);
			}
		}
		else if (round == 2) {
			intTotalScore = 10;
			intTotalTime = 28000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole2);
			}
		}
		else if (round == 3) {
			intTotalScore = 10;
			intTotalTime = 26000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole3);
			}
		}
		else if (round == 4) {
			intTotalScore = 10;
			intTotalTime = 24000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole4);
			}
		}
		else if (round == 5) {
			intTotalScore = 10;
			intTotalTime = 22000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole5);
			}
		}
		else if (round == 6) {
			intTotalScore = 10;
			intTotalTime = 20000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole6);
			}
		}
		else if (round == 7) {
			intTotalScore = 10;
			intTotalTime = 18000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole7);
			}
		}
		else if (round == 8) {
			intTotalScore = 10;
			intTotalTime = 16000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole8);
			}
		}
		else if (round == 9) {
			intTotalScore = 10;
			intTotalTime = 14000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole9);
			}
		}
		else if (round == 10) {
			intTotalScore = 10;
			intTotalTime = 12000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole10);
			}
		}
		else if (round == 11) {
			intTotalScore = 10;
			intTotalTime = 10000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole11);
			}
		}
		else if (round == 12) {
			intTotalScore = 10;
			intTotalTime = 8000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole12);
			}
		}
		else if (round == 13) {
			intTotalScore = 10;
			intTotalTime = 7000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole13);
			}
		}
		else if (round == 14) {
			intTotalScore = 10;
			intTotalTime = 6000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole14);
			}
		}
		else if (round == 15) {
			intTotalScore = 10;
			intTotalTime = 5000;
			for (int i = 1; i <= lhmMoles.size(); i++) {
				lhmMoles.get(i).setImageResource(R.drawable.mole15);
			}
		}
		
		txtCounter.setText(intUserScore + "/" + intTotalScore);
        
        
        intUserTime = intTotalTime;
        
        random = new Random();
        
        
        AudioManager manager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
        
        mpBg = MediaPlayer.create(this, R.raw.bg);
        mpBg.setLooping(true);
        mpBg.start();
        
        mpBg.setVolume(10, 10);
        
//        AudioManager manager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//        manager.setStreamVolume(AudioManager.STREAM_MUSIC, 40, 0);
        
        mpSmash = MediaPlayer.create(this, R.raw.smash);
        
        mpSmash.setVolume(40, 40);
        
        
//        try {
//            mpSmash.prepare();
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        //Timer
////        (new Thread(new Runnable()
//        final Thread thread = new Thread(new Runnable()
//        {
//
//            @Override
//            public void run()
//            {
//                while (!Thread.interrupted())
//                    try
//                    {
//                        Thread.sleep(1000);
////                    	Thread.sleep(100);
//                        runOnUiThread(new Runnable() // start actions in UI thread
//                        {
//
//                            @Override
//                            public void run()
//                            {
////                            	
////                            	final int randomLayoutNumber = random.nextInt(rndMax - rndMin + 1) + rndMin;
////                            	
//////                            	new Handler().postDelayed(new Runnable() {
//////                        			
//////                        			@Override
//////                        			public void run() {
////                        	        	
////                        	        	Log.d("TTT", "randomNumber when visible = " + randomLayoutNumber);
////                        	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.VISIBLE);
//////                        			}
//////                        		}, 500);
//                            	
//                            	
////                                displayData(); // this action have to be in UI thread
//                            	intUserTime = intUserTime - 1000;
//                            	
//                            	txtTimer.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(intUserTime)));
//                     
//                            	Log.d("TTT", "intUserTime = " + intUserTime);
//                            	if(intUserTime == 0) {
//                            		//Game Result
//                            		Thread.currentThread().interrupt();
////                        			Thread.currentThread().stop();
////                            		Thread.currentThread().destroy();
////                            		thread.stop();
////                            		stopThread(thread);
//                            		if(!nextRound()) {
//                            			Log.d("TTT", "round = " + round + " ... isScoreWillStore = " + DatabaseUtility.isScoreWillStore(getApplicationContext(), intUserScore));
//                            			if(round == 0 || !DatabaseUtility.isScoreWillStore(getApplicationContext(), intUserScore)) {
//	                            			finish();
//	                            			Intent intentGameOver = new Intent(PlayActivity.this, GameOverActivity.class);
//	                            			intentGameOver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
//	                            			startActivity(intentGameOver);
//                            			}
//                            			else {
//                            				//Add score
//                            				finish();
//											Intent intentScore = new Intent(PlayActivity.this, ScoreActivity.class);
//											intentScore.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
//											intentScore.putExtra("score", intUserScore);
//	                            			startActivity(intentScore);
//                            			}
//                            			intUserTime = intTotalTime;
//                            			intUserScore = 0;
//                            		}
//                            	}
//                            }
//                        });
//                    }
//                    catch (InterruptedException e)
//                    {
//                    	e.printStackTrace();
//                        // ooops
//                    }
//            }
////        })).start(); // the while thread will start in BG thread
//        });
//        thread.start(); // the while thread will start in BG thread


        Log.d("TTT", "============intUserTime = " + intUserTime);
        CountDownTimer waitTimer;
        waitTimer = new CountDownTimer(intTotalTime, 1000) {

          public void onTick(long millisUntilFinished) {
             //called every 300 milliseconds, which could be used to
             //send messages or some other action
        	  
        	  intUserTime = intUserTime - 1000;
            	
            	txtTimer.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(intUserTime)));
     
            	Log.d("TTT", "intUserTime ontick = " + intUserTime);
            	if(intUserTime == 0) {
            		//Game Result
            		this.cancel();
//            		Thread.currentThread().interrupt();
//        			Thread.currentThread().stop();
//            		Thread.currentThread().destroy();
//            		thread.stop();
//            		stopThread(thread);
            		if(!nextRound()) {
            			Log.d("TTT", "round = " + round + " ... isScoreWillStore = " + DatabaseUtility.isScoreWillStore(getApplicationContext(), intUserScore));
            			if(round <= 1 || !DatabaseUtility.isScoreWillStore(getApplicationContext(), intUserScore)) {
                  			finish();
                  			Intent intentGameOver = new Intent(PlayActivity.this, GameOverActivity.class);
                  			startActivity(intentGameOver);
            			}
            			else {
            				//Add score
            				finish();
            				Log.d("TTT", "intUserScore final = " + intUserScore);
            				if(round == 15) {
            					Intent intentCongrates = new Intent(PlayActivity.this, CongratulationActivity.class); 
	                  			startActivity(intentCongrates);
            				}
            				else {
								Intent intentScore = new Intent(PlayActivity.this, ScoreActivity.class); 
								intentScore.putExtra("score", round);
	                  			startActivity(intentScore);
            				}
            			}
            			intUserTime = intTotalTime;
            			intUserScore = 0;
            			round = 1;
            		}
            	}
        	  
          }

          public void onFinish() {
             //After 60000 milliseconds (60 sec) finish current 
             //if you would like to execute something when time finishes
        	  
        	  intUserTime = intUserTime - 1000;
            	
            	txtTimer.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(intUserTime)));
     
            	Log.d("TTT", "intUserTime onfinish = " + intUserTime);
            	if(intUserTime == 0) {
            		//Game Result
            		this.cancel();
//            		Thread.currentThread().interrupt();
//        			Thread.currentThread().stop();
//            		Thread.currentThread().destroy();
//            		thread.stop();
//            		stopThread(thread);
            		if(!nextRound()) {
            			Log.d("TTT", "round = " + round + " ... isScoreWillStore = " + DatabaseUtility.isScoreWillStore(getApplicationContext(), intUserScore));
            			if(round <= 1 || !DatabaseUtility.isScoreWillStore(getApplicationContext(), intUserScore)) {
                  			finish();
                  			Intent intentGameOver = new Intent(PlayActivity.this, GameOverActivity.class);
                  			startActivity(intentGameOver);
            			}
            			else {
            				//Add score
            				finish();
            				if(round == 15) {
            					Intent intentCongrates = new Intent(PlayActivity.this, CongratulationActivity.class); 
	                  			startActivity(intentCongrates);
            				}
            				else {
								Intent intentScore = new Intent(PlayActivity.this, ScoreActivity.class);
								intentScore.putExtra("score", round);
	                  			startActivity(intentScore);
            				}
            			}
            			intUserTime = intTotalTime;
            			intUserScore = 0;
            			round = 1;
            		}
            	}
          }
        }.start();
        
        
        
        
        //Random layout
//        (new Thread(new Runnable()
//        {
//
//            @Override
//            public void run()
//            {
//                while (!Thread.interrupted())
//                    try
//                    {
//                    	Thread.sleep(200);
//                        runOnUiThread(new Runnable() // start actions in UI thread
//                        {
//
//                            @Override
//                            public void run()
//                            {
//                            	
//                            	final int randomLayoutNumber = random.nextInt(rndMax - rndMin + 1) + rndMin;
//                            	
////                            	new Handler().postDelayed(new Runnable() {
////                        			
////                        			@Override
////                        			public void run() {
//                        	        	
//                        	        	Log.d("TTT", "randomNumber when visible = " + randomLayoutNumber);
//                        	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.VISIBLE);
//                        	        	
////                        	        	lhmMoleLayouts.get(randomLayoutNumber).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate_layout));
////                        			}
////                        		}, 500);
//                        	        	
//                        	        	
//                	        	new Handler().postDelayed(new Runnable() {
//                        			
//                        			@Override
//                        			public void run() {
//                        	        	
//                        	        	Log.d("TTT", "randomNumber when invisible = " + randomLayoutNumber);
//                        	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.INVISIBLE);
//                        			}
//                        		}, 800);
//                            	
//
//                            }
//                        });
//                    }
//                    catch (InterruptedException e)
//                    {
//                    	e.printStackTrace();
//                        // ooops
//                    }
//            }
//        })).start(); // the while thread will start in BG thread
        
        CountDownTimer layoutTimer;
//        waitTimer = new CountDownTimer(intTotalTime, 200) {
        waitTimer = new CountDownTimer(intTotalTime, 100) {

          public void onTick(long millisUntilFinished) {
             //called every 300 milliseconds, which could be used to
             //send messages or some other action
        	  
        	  final int randomLayoutNumber = random.nextInt(rndMax - rndMin + 1) + rndMin;
          	
//          	new Handler().postDelayed(new Runnable() {
//      			
//      			@Override
//      			public void run() {
      	        	
      	        	Log.d("TTT", "randomNumber when visible = " + randomLayoutNumber);
      	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.VISIBLE);

      	        	Log.d("TTT", "round = " + round);
      	        	Log.d("TTT", "rndMin = " + rndMin);
      	        	final int randomMole = random.nextInt(round - rndMin + 1) + rndMin;
      	        	lhmMoles.get(randomLayoutNumber).setImageResource(lhmMoleDrawable.get(randomMole));
      	        	
      	        	lhmTempMoles.put(lhmMoles.get(randomLayoutNumber), randomMole);
      	        	
//      	        	lhmMoleLayouts.get(randomLayoutNumber).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate_layout));
//      			}
//      		}, 500);
      	        	
      	        	
	        	new Handler().postDelayed(new Runnable() {
      			
	      			@Override
	      			public void run() {
	      	        	
	      	        	Log.d("TTT", "randomNumber when invisible = " + randomLayoutNumber);
	      	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.INVISIBLE);
	      	        	
	      	        	ivHit1.setVisibility(View.GONE);
	      	        	ivHit2.setVisibility(View.GONE);
	      	        	ivHit3.setVisibility(View.GONE);
	      	        	ivHit4.setVisibility(View.GONE);
	      	        	ivHit5.setVisibility(View.GONE);
	      	        	ivHit6.setVisibility(View.GONE);
	      	        	ivHit7.setVisibility(View.GONE);
	      	        	ivHit8.setVisibility(View.GONE);
	      	        	ivHit9.setVisibility(View.GONE);
	      	        	ivHit10.setVisibility(View.GONE);
	      	        	ivHit11.setVisibility(View.GONE);
	      	        	ivHit12.setVisibility(View.GONE);
	      			}
	        	}, 1200);
        	  
          }

          public void onFinish() {
             //After 60000 milliseconds (60 sec) finish current 
             //if you would like to execute something when time finishes
        	  
        	  final int randomLayoutNumber = random.nextInt(rndMax - rndMin + 1) + rndMin;
            	
//        	new Handler().postDelayed(new Runnable() {
//    			
//    			@Override
//    			public void run() {
    	        	
    	        	Log.d("TTT", "randomNumber when visible = " + randomLayoutNumber);
    	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.VISIBLE);
    	        	
//    	        	lhmMoleLayouts.get(randomLayoutNumber).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate_layout));
//    			}
//    		}, 500);
    	        	
    	        	
	        	new Handler().postDelayed(new Runnable() {
    			
    			@Override
    			public void run() {
    	        	
    	        	Log.d("TTT", "randomNumber when invisible = " + randomLayoutNumber);
    	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.INVISIBLE);
    	        	
    	        	ivHit1.setVisibility(View.GONE);
      	        	ivHit2.setVisibility(View.GONE);
      	        	ivHit3.setVisibility(View.GONE);
      	        	ivHit4.setVisibility(View.GONE);
      	        	ivHit5.setVisibility(View.GONE);
      	        	ivHit6.setVisibility(View.GONE);
      	        	ivHit7.setVisibility(View.GONE);
      	        	ivHit8.setVisibility(View.GONE);
      	        	ivHit9.setVisibility(View.GONE);
      	        	ivHit10.setVisibility(View.GONE);
      	        	ivHit11.setVisibility(View.GONE);
      	        	ivHit12.setVisibility(View.GONE);
    			}
    		}, 1200);
        	  
          }
        }.start();
        
        
        Log.d("TTT", "intUserScore = " + intUserScore + " ... intTotalScore = " + intTotalScore + " .... intUserTime = " + intUserTime);
//        while((intUserScore == intTotalScore) || (intUserTime == 0)) {
//        while(true) {
////        random.nextInt(max - min + 1) + min
//        	
//        	final int randomLayoutNumber = random.nextInt(rndMax - rndMin + 1) + rndMin;
//        	
//        	new Handler().postDelayed(new Runnable() {
//    			
//    			@Override
//    			public void run() {
//    	        	
//    	        	Log.d("TTT", "randomNumber when visible = " + randomLayoutNumber);
//    	        	lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.VISIBLE);
//    			}
//    		}, 500);
//
//        	new Handler().postDelayed(new Runnable() {
//    			
//    			@Override
//    			public void run() {
//    				Log.d("TTT", "randomNumber when invisible = " + randomLayoutNumber);
//    				lhmMoleLayouts.get(randomLayoutNumber).setVisibility(View.INVISIBLE);
//    			}
//    		}, 500);
//        }
        
        
	}
	
	
	public void stopThread(Thread thread) {
		thread.stop();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	public boolean nextRound() {
		if(intUserScore == intTotalScore) {
			round++;
			finish();
			Intent intent = new Intent(PlayActivity.this, RoundDisplayActivity.class);
			intent.putExtra("round", round);
			startActivity(intent);
			
			intUserTime = intTotalTime;
			intUserScore = 0;
			
			return true;
		}
		return false;
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.layoutMole1:
				smashMole(ivHit1, ivMole1, txtMole1, layoutMole1);
				break;
				
			case R.id.layoutMole2:
				smashMole(ivHit2, ivMole2, txtMole2, layoutMole2);
				break;
				
			case R.id.layoutMole3:
				smashMole(ivHit3, ivMole3, txtMole3, layoutMole3);
				break;
				
			case R.id.layoutMole4:
				smashMole(ivHit4, ivMole4, txtMole4, layoutMole4);
				break;
				
			case R.id.layoutMole5:
				smashMole(ivHit5, ivMole5, txtMole5, layoutMole5);
				break;
				
			case R.id.layoutMole6:
				smashMole(ivHit6, ivMole6, txtMole6, layoutMole6);
				break;
				
			case R.id.layoutMole7:
				smashMole(ivHit7, ivMole7, txtMole7, layoutMole7);
				break;
				
			case R.id.layoutMole8:
				smashMole(ivHit8, ivMole8, txtMole8, layoutMole8);
				break;
				
			case R.id.layoutMole9:
				smashMole(ivHit9, ivMole9, txtMole9, layoutMole9);
				break;
				
			case R.id.layoutMole10:
				smashMole(ivHit10, ivMole10, txtMole10, layoutMole10);
				break;
				
			case R.id.layoutMole11:
				smashMole(ivHit11, ivMole11, txtMole11, layoutMole11);
				break;
				
			case R.id.layoutMole12:
				smashMole(ivHit12, ivMole12, txtMole12, layoutMole12);
				break;
	
			default:
				break;
		}
	}
	
	
	@SuppressLint("NewApi") 
	private void smashMole(final ImageView ivHit, final ImageView ivMole, final TextView txtMole, final RelativeLayout layoutMole) {
		
//		Animation anim = new MyAnimation(ivHammer, 100);
//        anim.setDuration(1000);
//        ivHammer.startAnimation(anim);
		
		int[] ivMoleCorners = new int[2];
		ivMole.getLocationOnScreen(ivMoleCorners);
		Log.d("TTT", "x = " + ivMoleCorners[0] + " .. y = " + ivMoleCorners[1]);
//		ivHammer.setX(ivMoleCorners[0]);
//		ivHammer.setY(ivMoleCorners[1]);
//		ivHammer.setLeft(ivMoleCorners[0]);
//		ivHammer.setTop(ivMoleCorners[1]);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
		params.leftMargin = ivMoleCorners[0] - 50;
		params.topMargin = ivMoleCorners[1] - 410;
//		layoutMain.addView(ivHammer, params);
		ivHammer.setLayoutParams(params);
		
		
//		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
//
//		params.addRule(RelativeLayout.ABOVE, lhmTempMoles.get(ivMole));
//
//		ivHammer.setLayoutParams(params);
		
		
		ivHammer.setVisibility(View.VISIBLE);
		
		Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.hammer);
//		LayoutAnimationController animController = new LayoutAnimationController(rotateAnim, 0);
//		layout.setLayoutAnimation(animController);
	    ivHammer.startAnimation(rotateAnim);
	    new Handler().postDelayed(new Runnable() {
  			@Override
  			public void run() {
  				ivHammer.setVisibility(View.GONE);
  			}
    	}, 200);
		
		Log.d("TTT", "round = " + round + " ... tempMole = " + lhmTempMoles.get(ivMole));
		if(round == lhmTempMoles.get(ivMole)) {
		
	//		mpSmash.setVolume(100, 100);
			mpSmash.start();
			
//			ivHit.setVisibility(View.VISIBLE);
//	//		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
//	//        ivHit.startAnimation(animation);
//			
//			new Handler().postDelayed(new Runnable() {
//	  			@Override
//	  			public void run() {
//	  				ivHit.setVisibility(View.GONE);
//	  			}
//	    	}, 100);
			
			ivMole.setImageResource(R.drawable.whacak_mole);
			txtMole.setText(lhmStrikedMoleName.get(ivMole));
			
	        new Handler().postDelayed(new Runnable() {
	  			@Override
	  			public void run() {
	  				if(round == 1) {
	  					ivMole.setImageResource(R.drawable.mole1);
	  				}
	  				else if (round == 2) {
	  					ivMole.setImageResource(R.drawable.mole2);
					}
	  				else if (round == 3) {
	  					ivMole.setImageResource(R.drawable.mole3);
					}
	  				else if (round == 4) {
	  					ivMole.setImageResource(R.drawable.mole4);
					}
	  				else if (round == 5) {
	  					ivMole.setImageResource(R.drawable.mole5);
					}
	  				else if (round == 6) {
	  					ivMole.setImageResource(R.drawable.mole6);
					}
	  				else if (round == 7) {
	  					ivMole.setImageResource(R.drawable.mole7);
					}
	  				else if (round == 8) {
	  					ivMole.setImageResource(R.drawable.mole8);
					}
	  				else if (round == 9) {
	  					ivMole.setImageResource(R.drawable.mole9);
					}
	  				else if (round == 10) {
	  					ivMole.setImageResource(R.drawable.mole10);
					}
	  				else if (round == 11) {
	  					ivMole.setImageResource(R.drawable.mole11);
					}
	  				else if (round == 12) {
	  					ivMole.setImageResource(R.drawable.mole12);
					}
	  				else if (round == 13) {
	  					ivMole.setImageResource(R.drawable.mole13);
					}
	  				else if (round == 14) {
	  					ivMole.setImageResource(R.drawable.mole14);
					}
	  				else if (round == 15) {
	  					ivMole.setImageResource(R.drawable.mole15);
					}
	  				txtMole.setText(lhmMoleName.get(ivMole));
	  				
	  				intUserScore++;
					txtCounter.setText(intUserScore + "/" + intTotalScore);
					layoutMole.setVisibility(View.INVISIBLE);
					nextRound();
	//				mpSmash.stop();
	  			}
	    	}, 200);
		}
	}
	
	@Override
	protected void onPause() {
		if(mpBg != null) {
			if(mpBg.isPlaying())
				mpBg.stop();
		}
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		if(mpBg != null) {
			if(mpBg.isPlaying())
				mpBg.stop();
		}
		super.onDestroy();
	}
	
	@Override
	protected void onResume() {
		if(mpBg != null) {
			if(!mpBg.isPlaying())
				mpBg.start();
		}
		super.onResume();
	}
	
}
