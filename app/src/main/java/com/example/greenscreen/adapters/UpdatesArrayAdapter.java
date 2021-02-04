package com.example.greenscreen.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class UpdatesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mName;
    private String[] mPost;

    public UpdatesArrayAdapter(Context mContext, int resource, String[] mName, String[] mPost){
        super(mContext, resource);
        this.mContext = mContext;
        this.mName = mName;
        this.mPost = mPost;


    }
    @Override
    public Object getItem(int position){
        String name = mName[position];
        String post = mPost[position];
        return String.format("%s \nContinent: %s", name, post);
    }
    @Override
    public int getCount() {
        return mName.length;

    }
}