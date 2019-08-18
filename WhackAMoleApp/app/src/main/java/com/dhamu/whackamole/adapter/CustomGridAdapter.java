package com.dhamu.whackamole.adapter;

import com.dhamu.whackamole.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class CustomGridAdapter extends BaseAdapter{
      private Context mContext;
      private final String[] mole;
//      private final int[] Imageid;
 
        public CustomGridAdapter(Context c, String[] mole) {
            mContext = c;
//            this.Imageid = Imageid;
            this.mole = mole;
        }
 
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mole.length;
        }
 
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }
 
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            if (convertView == null) {
 
                grid = new View(mContext);
                grid = inflater.inflate(R.layout.grid_single, null);
                
                LinearLayout layoutMole = (LinearLayout) grid.findViewById(R.id.layoutMole);
                layoutMole.setVisibility(View.VISIBLE);
                
                TextView txtMole = (TextView) grid.findViewById(R.id.txtMoleName);
                txtMole.setText(mole[position]);
            } else {
                grid = (View) convertView;
            }
 
            return grid;
        }
}