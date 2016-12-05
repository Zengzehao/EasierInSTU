package com.example.zengzehao.messageshare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

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
        public CircleImageView portrait;
        public TextView username;
        public TextView time;
        public TextView price;
        public TextView type;
        public TextView description;
        public TextView contact;
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
            zujian.portrait = (CircleImageView) convertView.findViewById(R.id.tab02_listview_portrait);
            zujian.username = (TextView) convertView.findViewById(R.id.tab02_listview_username);
            zujian.time = (TextView) convertView.findViewById(R.id.tab02_listview_time);
            zujian.price = (TextView) convertView.findViewById(R.id.tab02_listview_price);
            zujian.type = (TextView) convertView.findViewById(R.id.tab02_listview_type);
            zujian.description = (TextView) convertView.findViewById(R.id.tab02_listview_description);
            zujian.contact = (TextView) convertView.findViewById(R.id.tab02_listview_contact);
            zujian.button = (Button) convertView.findViewById(R.id.tab02_listview_button);
            convertView.setTag(zujian);
        } else {
            zujian = (Zujian) convertView.getTag();
        }
        //绑定数据

        //zujian.portrait.setBackgroundResource((Integer)data.get(position).get("portrait"));
        zujian.username.setText((String)data.get(position).get("username"));
        zujian.time.setText((String)data.get(position).get("time"));
        zujian.price.setText((String) data.get(position).get("price"));
        zujian.type.setText((String)data.get(position).get("type"));
        zujian.description.setText((String)data.get(position).get("description"));
        zujian.contact.setText((String)data.get(position).get("contact"));
        System.out.println("联系方式"+zujian.contact.getText().toString());
        zujian.button.setOnClickListener(new MyClickListner(zujian.contact.getText().toString()));



        return convertView;
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
           // View view1 = layoutInflater.inflate(R.layout.tab02_listview,null);
           // TextView contact = (TextView) view1.findViewById(R.id.tab02_listview_contact);
          //  String contact_string = contact.getText().toString();

            //System.out.println("联系方式"+contact);
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("联系方式").setMessage(contact);
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            */
            ShowMsg("联系方式",contact,context);
            //Toast.makeText(context,"联系方式"+contact,Toast.LENGTH_SHORT).show();
        }
    }

}