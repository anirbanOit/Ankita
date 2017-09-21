package com.oit.test.gallery;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomGrid extends BaseAdapter{
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;
    private boolean [] selectedList;
    //private Activity sActivity;

    public CustomGrid(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
        selectedList = new boolean[web.length];
        for (int i=0;i<web.length;i++){
            selectedList [i] = false;
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
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

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;


        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
        }
        else {
            grid = (View) convertView;
        }
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(web[position]);
            imageView.setImageResource(Imageid[position]);

            //grid = (View) convertView;

        return grid;
    }*/


    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
       // if(view == null) {
            //LayoutInflater li = sActivity.getLayoutInflater();
            //view = li.inflate(R.layout.grid_single, parent, false);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_single, parent, false);

            holder = new ViewHolder();
            holder.text = view.findViewById(R.id.grid_text);
            holder.imageview = view.findViewById(R.id.grid_image);
            holder.checkbox = view.findViewById(R.id.check1);

            view.setTag(holder);
//        }
//        else {
//            holder = (ViewHolder)view.getTag();
//        }
        holder.checkbox.setChecked(selectedList[position]);

        holder.text.setText(web[position]);
        holder.imageview.setImageResource(Imageid[position]);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View viewitem) {
                //CheckBox cb = (CheckBox) viewitem;
                selectedList [position] = !selectedList[position];

                //if(selectedList[position]) {
                    //cb.setChecked(selectedList[position]);
                //}
                holder.checkbox.setChecked(selectedList[position]);
            }

        });
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selectedList [position] = b;
                //holder.checkbox.setChecked(selectedList[position]);
            }
        });
        return view;
    }
    private static class ViewHolder{
        private TextView text;
        private ImageView imageview;
        private CheckBox checkbox;
    }
}