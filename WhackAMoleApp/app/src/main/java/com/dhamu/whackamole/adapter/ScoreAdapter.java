package com.dhamu.whackamole.adapter;

import java.util.List;

import com.dhamu.whackamole.R;
import com.dhamu.whackamole.model.ScoreModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class ScoreAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ScoreModel> scoreModels;
 
    public ScoreAdapter(Activity activity, List<ScoreModel> scoreModels) {
        this.activity = activity;
        this.scoreModels = scoreModels;
    }
 
    @Override
    public int getCount() {
        return scoreModels.size();
    }
 
    @Override
    public Object getItem(int location) {
        return scoreModels.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.score_item, null);
 
        TextView txtUserName = (TextView) convertView.findViewById(R.id.txtUserName);
        TextView txtUserScore = (TextView) convertView.findViewById(R.id.txtUserScore);
 
        // getting movie data for the row
        ScoreModel scoreModel = scoreModels.get(position);
 
        txtUserName.setText(scoreModel.getUserName());
        txtUserScore.setText(String.valueOf(scoreModel.getScore()));
         
        return convertView;
    }
 
}