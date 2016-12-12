package com.example.zengzehao.messageshare;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zengzehao on 16-12-7.
 */

public class Tab03ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public Tab03ListViewAdapter(Context context, List<Map<String, Object>> data) {
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
        public CircleImageView portrait;
        public TextView username;
        public TextView time;
        public TextView type;
        public TextView description;
        public TextView contact;
        public TextView endtime;
        public TextView endtime_display;
        public Button button;
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
            view = layoutInflater.inflate(R.layout.tab03_listiew,null);
            zujian.portrait = (CircleImageView) view.findViewById(R.id.tab03_listview_portrait);
            zujian.username = (TextView) view.findViewById(R.id.tab03_listview_username);
            zujian.time = (TextView) view.findViewById(R.id.tab03_listview_time);
            zujian.type = (TextView) view.findViewById(R.id.tab03_listview_type);
            zujian.description = (TextView) view.findViewById(R.id.tab03_listview_description);
            zujian.contact = (TextView) view.findViewById(R.id.tab03_listview_contact);
            zujian.endtime = (TextView) view.findViewById(R.id.tab03_listview_endtime);
            zujian.endtime_display = (TextView) view.findViewById(R.id.tab03_listview_endtime_display);
            zujian.button = (Button) view.findViewById(R.id.tab03_listview_button);
            view.setTag(view);
        }else{
            zujian = (Zujian) view.getTag();
        }
        //绑定数据
        String URL = (String) data.get(i).get("portraitUrl");
        System.out.println("ViewportraitURL3:"+(String) data.get(i).get("portraitUrl"));
        Picasso.with(context).load(URL).into(zujian.portrait);
        zujian.username.setText((String)data.get(i).get("username"));
        zujian.time.setText((String)data.get(i).get("time"));
        zujian.type.setText((String)data.get(i).get("type"));
        zujian.description.setText((String)data.get(i).get("description"));
        zujian.contact.setText((String)data.get(i).get("contact"));
        zujian.endtime_display.setText((String)data.get(i).get("endtime"));
        zujian.button.setText((String)data.get(i).get("btn_text"));
        zujian.button.setOnClickListener(new MyClickListner(zujian.contact.getText().toString()));

        return view;
    }

    public static void ShowMsg(String title,String msg,Context context) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setPositiveButton("确定",null);
        dlg.show();
    }
    class MyClickListner implements View.OnClickListener{

        String contact;
        MyClickListner(String contact){
            this.contact = contact;
        }
        @Override
        public void onClick(View view) {

            ShowMsg("联系方式",contact,context);
            //Toast.makeText(context,"联系方式"+contact,Toast.LENGTH_SHORT).show();
        }
    }
}
