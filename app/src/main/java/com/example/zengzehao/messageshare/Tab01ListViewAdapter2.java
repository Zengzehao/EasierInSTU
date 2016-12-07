package com.example.zengzehao.messageshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zengzehao on 16-12-7.
 */

public class Tab01ListViewAdapter2 extends BaseAdapter {

    private List<Tab01ListView> data ;
    private Context context;

    public Tab01ListViewAdapter2(List<Tab01ListView> data, Context context) {
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
    public final class Zujian {
        public CircleImageView portrait;
        public TextView username;
        public TextView time;
        public TextView type;
        public TextView title;
        public TextView contact;
        public TextView clicks;
        public TextView clicks_number;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Zujian zujian = null;
        if(zujian == null){
            zujian = new Zujian();
            view = LayoutInflater.from(context).inflate(R.layout.tab01_listview,null);
            //获取组件的实例
            zujian.portrait = (CircleImageView) view.findViewById(R.id.tab01_listview_portrait);
            zujian.username = (TextView) view.findViewById(R.id.tab01_listview_username);
            zujian.time = (TextView) view.findViewById(R.id.tab01_listview_time);
            zujian.type = (TextView) view.findViewById(R.id.tab01_listview_type);
            zujian.title = (TextView) view.findViewById(R.id.tab01_listview_title);
            zujian.contact = (TextView) view.findViewById(R.id.tab01_listview_contact);
            zujian.clicks = (TextView) view.findViewById(R.id.tab01_listview_clicks);
            zujian.clicks_number = (TextView) view.findViewById(R.id.tab01_listview_clicks_number);
            view.setTag(view);
        }else{
            zujian = (Zujian) view.getTag();
        }

        //绑定数据
        zujian.username.setText((String) data.get(i).getUsername());
        zujian.time.setText((String) data.get(i).getTime());
        zujian.type .setText((String)data.get(i).getType());
        zujian.title.setText((String) data.get(i).getTitle());
        //zujian.contact.setText((String) data.get(i).get("contact"));
        zujian.clicks_number.setText((String.valueOf(data.get(i).getClicks_number())));

        return view;
    }
}
