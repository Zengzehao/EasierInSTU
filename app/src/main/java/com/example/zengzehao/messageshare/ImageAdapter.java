package com.example.zengzehao.messageshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.avos.avoscloud.AVUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengzehao on 16-12-8.
 */

public class ImageAdapter extends BaseAdapter {
    List<Image> data = new ArrayList<Image>();
    private Context context;

    public ImageAdapter(List<Image> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.image_item,null);
        ImageView image_item = (ImageView) view.findViewById(R.id.image_item);
        String url = data.get(i).getUrl();
        System.out.println("Imageurl："+url);
        Picasso.with(context).load(url).into(image_item);
        return view;
    }
}
