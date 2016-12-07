package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.example.zengzehao.messageshare.tools.ConutDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFragment3 extends Fragment {

    private ListView listView;
    private ImageButton top_personinfo;
    private ImageButton top_add;

    private TextView menu1;
    private TextView menu2;

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab03,container,false);
        listView = (ListView)view.findViewById(R.id.tab03_listview);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第三个Fragment");
        //允许从主线程请求网络服务
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        List<Map<String, Object>> list= new MyFragment3.getData().doInBackground();
        listView.setAdapter(new Tab03ListViewAdapter(getActivity(), list));

        top_personinfo = (ImageButton) view.findViewById(R.id.top_personinfo);
        top_add = (ImageButton) view.findViewById(R.id.top_add);
        top_personinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"你点击了个人中心",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),PersonInfoActivity.class);
                startActivity(intent);
            }
        });

        top_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"你点击了发布按钮",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(),WorkAddActivity.class);

                //startActivity(intent);
                //System.out.println("跳转到发布界面");
                // showPopMenu();
                initPopWindow(view);
            }
        });
        Log.e("HEHE", "1日狗");
        return view;
    }

    private void initPopWindow(View v) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu2, null, false);
        menu1 = (TextView) view.findViewById(R.id.menu1);
        menu2 = (TextView) view.findViewById(R.id.menu2);

        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v,-5,25);

        //设置popupWindow里的按钮的事件
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AskExpressAddActivity.class);
                startActivity(intent);
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "你点击了呵呵~", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),HelpExpressAddactivity.class);
                startActivity(intent);
                popWindow.dismiss();
            }
        });
    }
    public class getData extends AsyncTask<Void,Void,List<Map<String,Object>>> {





        @Override
        protected List<Map<String,Object>> doInBackground(Void...voids) {

            List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

            String cql = "select * from Express";
            try {
                AVCloudQueryResult result = AVQuery.doCloudQuery(cql);
                System.out.println(result);
                List<AVObject> results = (List<AVObject>) result.getResults();
                for (int i = results.size()-1;i>=0;i--){
                    Map<String, Object> map=new HashMap<String, Object>();

                    map.put("portrait",R.drawable.touxiang);
//                    System.out.println("对象"+results.get(i).getAVUser("userName"));
                    //                   System.out.println(results.get(i).getAVUser("userName").get("username"));



                    map.put("username",results.get(i).get("userName"));
                    Date date = new Date();
                    String time = ConutDate.conutTwoDate(date,(Date) results.get(i).get("createdAt"));
                    map.put("time","发布于 "+time);
                    map.put("type",results.get(i).get("type"));
                    map.put("description",results.get(i).get("description"));
                    map.put("contact",results.get(i).get("contactInfo"));
                    map.put("endtime",results.get(i).get("deadline"));
                    map.put("btn_text",results.get(i).get("btn_text"));
                    list.add(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
