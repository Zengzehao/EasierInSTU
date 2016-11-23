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
import java.util.Objects;

/**
 * Created by zengzehao on 16-11-23.
 */

public class Tab04ListViewAdapter extends BaseAdapter {

    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public Tab04ListViewAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应tab04_listview.xml的控件
     * @author Zengzehao
     */
    public final class Zujian{
        public ImageView tab04_listview_portrait;
        public TextView tabb04_listview_username;
        public TextView tabb04_listview_time;
        public TextView tabb04_listview_starttime;
        public TextView tabb04_listview_starttime_display;
        public TextView tabb04_listview_startplace;
        public TextView tabb04_listview_startplace_display;
        public TextView tabb04_listview_endplace;
        public TextView tabb04_listview_endplace_display;
        public TextView tabb04_listview_need;
        public TextView tabb04_listview_need_display;
        public TextView tabb04_listview_clicks;
        public TextView tabb04_listview_clicks_display;
        public Button tab04_listview_button;
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
        if(view == null){
            zujian = new Zujian();
            //实例化组件
            view = layoutInflater.inflate(R.layout.tab04_listview,null);
//            zujian.tab04_listview_portrait = (ImageView) view.findViewById(R.id.tab04_listview_portrait);
            zujian.tab04_listview_portrait = (ImageView)view.findViewById(R.id.tab04_listview_portrait);
            zujian.tabb04_listview_username = (TextView) view.findViewById(R.id.tab04_listview_username);
            zujian.tabb04_listview_time = (TextView) view.findViewById(R.id.tab04_listview_time);
            zujian.tabb04_listview_starttime = (TextView) view.findViewById(R.id.tab04_listview_starttime);
            zujian.tabb04_listview_starttime_display = (TextView) view.findViewById(R.id.tab04_listview_starttime_display);
            zujian.tabb04_listview_startplace = (TextView) view.findViewById(R.id.tab04_listview_startplace);
            zujian.tabb04_listview_startplace_display = (TextView) view.findViewById(R.id.tab04_listview_startplace_display);
            zujian.tabb04_listview_endplace = (TextView)view.findViewById(R.id.tab04_listview_endplace);
            zujian.tabb04_listview_endplace_display = (TextView) view.findViewById(R.id.tab04_listview_endplace_dispaly);
            zujian.tabb04_listview_need = (TextView)view.findViewById(R.id.tab04_listview_need);
            zujian.tabb04_listview_need_display = (TextView)view.findViewById(R.id.tab04_listview_need_display);
            zujian.tabb04_listview_clicks = (TextView) view.findViewById(R.id.tab04_listview_clicks);
            zujian.tabb04_listview_clicks_display = (TextView) view.findViewById(R.id.tab04_listview_clicks_display);
            zujian.tab04_listview_button = (Button) view.findViewById(R.id.tab04_listview_button);
            view.setTag(zujian);
        }else{
            zujian = (Zujian) view.getTag();
        }

        //绑定数据

 //       zujian.tab04_listview_portrait.setBackgroundResource((Integer) data.get(i).get("portrait"));
        zujian.tabb04_listview_username.setText((String)data.get(i).get("username"));
        zujian.tabb04_listview_time.setText((String)data.get(i).get("time"));
        zujian.tabb04_listview_starttime.setText((String)data.get(i).get("starttime"));
        zujian.tabb04_listview_starttime_display.setText((String)data.get(i).get("starttime_display"));
        zujian.tabb04_listview_startplace.setText((String)data.get(i).get("startplace"));
        zujian.tabb04_listview_startplace_display.setText((String)data.get(i).get("startplace_display"));
        zujian.tabb04_listview_endplace.setText((String)data.get(i).get("endplace"));
        zujian.tabb04_listview_endplace_display.setText((String)data.get(i).get("endplace_display"));
        zujian.tabb04_listview_clicks.setText((String)data.get(i).get("clicks"));
        zujian.tabb04_listview_clicks_display.setText((String)data.get(i).get("clicks_display"));
        zujian.tabb04_listview_need.setText((String)data.get(i).get("need"));
        zujian.tabb04_listview_need_display.setText((String)data.get(i).get("need_display"));
        return view;
    }
}
