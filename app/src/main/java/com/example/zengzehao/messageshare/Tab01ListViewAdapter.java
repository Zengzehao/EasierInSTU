package com.example.zengzehao.messageshare;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zengzehao on 16-12-7.
 */

public class Tab01ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public Tab01ListViewAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public final class Zujian {
        public CircleImageView portrait;
        public TextView username;
        public TextView time;
        public TextView type;
        public TextView description;
        public TextView contact;
        public TextView clicks;
        public TextView clicks_number;
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
        Zujian zujian = null;
        if(zujian == null){
            zujian = new Zujian();
            view = layoutInflater.inflate(R.layout.tab01_listview,null);
            //获取组件的实例
            zujian.portrait = (CircleImageView) view.findViewById(R.id.tab01_listview_portrait);
            zujian.username = (TextView) view.findViewById(R.id.tab01_listview_username);
            zujian.time = (TextView) view.findViewById(R.id.tab01_listview_time);
            zujian.type = (TextView) view.findViewById(R.id.tab01_listview_type);
            zujian.description = (TextView) view.findViewById(R.id.tab01_listview_description);
            zujian.contact = (TextView) view.findViewById(R.id.tab01_listview_contact);
            zujian.clicks = (TextView) view.findViewById(R.id.tab01_listview_clicks);
            zujian.clicks_number = (TextView) view.findViewById(R.id.tab01_listview_clicks_number);
            view.setTag(view);
        }else{
            zujian = (Zujian) view.getTag();
        }

        //绑定数据
        zujian.username.setText((String) data.get(i).get("username"));
        zujian.time.setText((String) data.get(i).get("time"));
        zujian.type .setText((String)data.get(i).get("type"));
        zujian.description.setText((String) data.get(i).get("description"));
        zujian.contact.setText((String) data.get(i).get("contact"));
        zujian.clicks_number.setText((String) data.get(i).get("clicks_number"));

        return view;
    }




}
