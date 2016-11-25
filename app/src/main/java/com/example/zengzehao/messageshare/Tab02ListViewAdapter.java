package com.example.zengzehao.messageshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by zengzehao on 16-11-23.
 */

public class Tab02ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public Tab02ListViewAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应list.xml中的控件
     *
     * @author Administrator
     */
    public final class Zujian {
        public ImageView portrait;
        public TextView username;
        public TextView time;
        public TextView price;
        public TextView type;
        public TextView description;
        public Button button;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian = null;
        if (convertView == null) {
            zujian = new Zujian();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.tab02_listview, null);
            zujian.portrait = (ImageView) convertView.findViewById(R.id.tab02_listview_portrait);
            zujian.username = (TextView) convertView.findViewById(R.id.tab02_listview_username);
            zujian.time = (TextView) convertView.findViewById(R.id.tab02_listview_time);
            zujian.price = (TextView) convertView.findViewById(R.id.tab02_listview_price);
            zujian.type = (TextView) convertView.findViewById(R.id.tab02_listview_type);
            zujian.description = (TextView) convertView.findViewById(R.id.tab02_listview_description);
            zujian.button = (Button) convertView.findViewById(R.id.tab02_listview_button);
            convertView.setTag(zujian);
        } else {
            zujian = (Zujian) convertView.getTag();
        }
        //绑定数据

        zujian.portrait.setBackgroundResource((Integer)data.get(position).get("portrait"));
        zujian.username.setText((String)data.get(position).get("username"));
        zujian.time.setText((String)data.get(position).get("time"));
        zujian.price.setText((String) data.get(position).get("price"));
        zujian.type.setText((String)data.get(position).get("type"));
        zujian.description.setText((String)data.get(position).get("description"));

        return convertView;
    }
}