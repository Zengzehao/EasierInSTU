package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
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
import java.util.Objects;


public class MyFragment4 extends Fragment {
    private ListView listView;
    private ImageButton top_personinfo;
    private ImageButton top_add;
    List<Map<String,Object>> list;
    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab04,container,false);
        listView = (ListView) view.findViewById(R.id.tab04_listview);
        //允许从主线程请求网络服务
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        list= new getData().doInBackground();
        listView.setAdapter(new Tab04ListViewAdapter(getActivity(),list));
       // txt_content.setText("第四个Fragment");
        //获取个人中心 和发布的ImageBUtton
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
                Toast.makeText(getActivity(),"你点击了发布按钮",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),CarpoolAddActivity.class);

                startActivity(intent);
                System.out.println("跳转到发布界面");
            }
        });

        Log.e("HEHE", "4日狗");
        return view;
    }

    public class getData extends AsyncTask<Void,Void,List<Map<String,Object>>> {
        @Override
        protected List<Map<String,Object>> doInBackground(Void...voids) {

            List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

            String cql = "select * from CarpoolTest";
            try {
                AVCloudQueryResult result = AVQuery.doCloudQuery(cql);
                System.out.println(result);
                List<AVObject> results = (List<AVObject>) result.getResults();
                for (int i = results.size()-1;i>=0;i--){
                    Map<String, Object> map=new HashMap<String, Object>();

                    map.put("portrait",R.drawable.touxiang);
//                    System.out.println("对象"+results.get(i).getAVUser("userName"));
                    //                   System.out.println(results.get(i).getAVUser("userName").get("username"));



                    map.put("username",results.get(i).get("username"));
                    Date date = new Date();
                    String time = ConutDate.conutTwoDate(date,(Date) results.get(i).get("createdAt"));
                    map.put("time","发布于 "+time);
                    map.put("starttime","集合时间："+results.get(i).get("time"));
                    map.put("startplace","出发地点："+results.get(i).get("place_start"));
                    map.put("endplace","目的地点："+results.get(i).get("place_end"));
                    map.put("need","需要人数："+results.get(i).get("need_num").toString());
                    map.put("contact",results.get(i).get("contactInfo"));
                    map.put("clicks","点击量：");
                    map.put("clicks_number",results.get(i).get("clicks").toString());
                    map.put("objectId",results.get(i).getObjectId());
                    list.add(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    public List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<10;i++){
            Map<String,Object> map = new HashMap<String,Object>();
     //       map.put("portrait",R.drawable.touxiang);
            map.put("username","14zhzeng");
            map.put("time","发表于10分钟前");
            map.put("starttime","集合时间:");
            map.put("starttime_display","2016年12月4日 14:00");
            map.put("startplace","出发地点：");
            map.put("starttime_display","汕头大学");
            map.put("endplace","目的地点：");
            map.put("endplace_display","潮汕站");
            map.put("need","需要人数：");
            map.put("need_display","3");
            map.put("clicks","点击量");
            map.put("clicks_display","10");
            list.add(map);
        }
        return list;
    }

}
