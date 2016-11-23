package com.example.zengzehao.messageshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.feedback.ThreadActivity;

import java.util.LinkedList;

/**
 * Created by zengzehao on 16-11-18.
 */

public class NoteAdapter extends BaseAdapter {
    private LinkedList<Note> mData;
    private Context mContext;


    public NoteAdapter(Context mContext, LinkedList<Note> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(mContext).inflate(R.layout.note_item,viewGroup,false);
        ImageView img1 = (ImageView) view.findViewById(R.id.imageView);
        TextView username = (TextView) view.findViewById(R.id.textView);
        TextView description = (TextView) view.findViewById(R.id.textView2);
        TextView createTime = (TextView) view.findViewById(R.id.textView3);
        TextView clicks = (TextView) view.findViewById(R.id.textView4);

        img1.setBackgroundResource(mData.get(i).getmIcon());
        username.setText(mData.get(i).getUsername());
        description.setText(mData.get(i).getDescription());
        createTime.setText(mData.get(i).getCreateTime());
        clicks.setText(mData.get(i).getClicks());


        return view;
    }
}
