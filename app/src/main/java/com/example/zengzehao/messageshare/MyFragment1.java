package com.example.zengzehao.messageshare;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class MyFragment1 extends Fragment {

    /*
    private List<Note> mData = null;
    private Context mContext;
    private NoteAdapter mAdapter = null;
    private ListView list_note;
    */
    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab01, container, false);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第一个Fragment");
        ListView list_note = (ListView)view.findViewById(R.id.list_view);
        /*
        mData = new LinkedList<Note>();
        mData.add(new Note("14zhzeng","10分钟前","我要出一把吉他，价钱200元,联系方式66877.我要出一把吉他，价钱200元,联系方式66877.","阅读量 3",R.drawable.touxiang));
        mData.add(new Note("14zhzeng","2小时前","我要出一把吉他，价钱200元","阅读量 3",R.drawable.touxiang));
        mData.add(new Note("14zhzeng","3小时前","我要出一把吉他，价钱200元.我要出一把吉他，价钱200元,联系方式66877.","阅读量 3",R.drawable.touxiang));
        mData.add(new Note("14zhzeng","11-17 11:30","我要出一把吉他，价钱200元.我要出一把吉他，价钱200元,联系方式66877.","阅读量 3",R.drawable.touxiang));
        mData.add(new Note("14zhzeng","11-17 10:00","我要出一把吉他，价钱200元","aaaaa",R.drawable.touxiang));
        mAdapter = new NoteAdapter(mContext,(LinkedList<Note>) mData);

        list_note.setAdapter(mAdapter);
        */
        String[] str = {"Hello","Hello2","Hell3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,str);
        list_note.setAdapter(adapter);
        Log.e("HEHE", "1日狗");
        return view;
    }
}
