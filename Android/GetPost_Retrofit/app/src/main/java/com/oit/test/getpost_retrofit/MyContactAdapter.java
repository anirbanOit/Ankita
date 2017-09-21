package com.oit.test.getpost_retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyContactAdapter extends ArrayAdapter<Contact> {

    List<Contact> contactList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyContactAdapter(Context context, List<Contact> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        contactList = objects;
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Contact item = getItem(position);

        String first = item.getFirstName();
        String last = first + item.getLastName();
        vh.textViewName.setText(last);
        vh.textViewGender.setText(item.getGender());
        //Picasso.with(context).load(item.getPhoto()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        //public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewGender;

        private ViewHolder(RelativeLayout rootView, TextView textViewName, TextView textViewGender) {
            this.rootView = rootView;
            //this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewGender = textViewGender;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            //ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewGender = (TextView) rootView.findViewById(R.id.textViewGender);
            return new ViewHolder(rootView, textViewName, textViewGender);
        }
    }
}