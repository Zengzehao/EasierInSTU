package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.example.zengzehao.messageshare.tools.ConutDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFragment2 extends Fragment {
    public static final String TAG_DEMO = "Demo";
    private ListView listView;
    private ImageButton top_personinfo;
    private ImageButton top_add;
    List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();


    public MyFragment2() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.tab02, container, false);
        listView = (ListView)view.findViewById(R.id.tab02_listview);

        /*
        //允许从主线程请求网络服务
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        */

        //List<Map<String, Object>> list= new getData().doInBackground();
        getData getdata = new getData();
        getdata.execute();

        listView.setAdapter(new Tab02ListViewAdapter(getActivity(), list));
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
                Intent intent = new Intent(getActivity(),WorkAddActivity.class);

                startActivity(intent);
                System.out.println("跳转到发布界面");
            }
        });



        return view;
    }


    public class getData extends AsyncTask<Void,Void,List<Map<String,Object>>>{





        @Override
        protected List<Map<String,Object>> doInBackground(Void...voids) {

            list=new ArrayList<Map<String,Object>>();

            String cql = "select * from JobTest";
            try {
                AVCloudQueryResult result = AVQuery.doCloudQuery(cql);
                System.out.println(result);
                List<AVObject> results = (List<AVObject>) result.getResults();
                for (int i = results.size()-1;i>=0;i--){
                    Map<String, Object> map=new HashMap<String, Object>();

                    map.put("portrait",R.drawable.touxiang);
//                    System.out.println("对象"+results.get(i).getAVUser("userName"));
 //                   System.out.println(results.get(i).getAVUser("userName").get("username"));
                    String username = results.get(i).get("userName").toString();
                    AVQuery<AVObject> query = AVQuery.getQuery("_User");
                    query.whereEqualTo("username", username);
                    query.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (e != null) {
                                Log.i("bo", "又失败");
                            } else {
                                Log.i("bo", "成功");
                                List<AVObject> users = list;

                                System.out.println(users.get(0).getAVFile("image").getUrl());
                                map.put("portraitUrl",users.get(0).getAVFile("image").getUrl());
                            }
                        }
                    });


                    map.put("username",results.get(i).get("userName"));
                    Date date = new Date();
                    String time = ConutDate.conutTwoDate(date,(Date) results.get(i).get("createdAt"));
                    map.put("time","发布于 "+time);
                    map.put("price",results.get(i).get("price"));
                    map.put("type","类型:"+results.get(i).get("type"));
                    map.put("description",results.get(i).get("description"));
                    map.put("contact",results.get(i).get("contactInfo"));
                    list.add(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
      //  List<AVObject> results = (List<AVObject>) result.getResults();
        /*
        try {
            AVCloudQueryResult avCloudQueryResult= AVQuery.doCloudQuery(cql);

            List<AVObject> results = (List<AVObject>) avCloudQueryResult.getResults();
            for (int i=0;i<results.size();i++){
                System.out.println("价格 "+results.get(i).get("price"));
                System.out.println("类型 "+results.get(i).get("type"));
                System.out.println("描述 "+results.get(i).get("description"));
                System.out.println("联系方式 "+results.get(i).get("contactInfo"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        /*
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                List<AVObject> results= (List<AVObject>) avCloudQueryResult.getResults();

                for (int i=0;i<results.size();i++){
                    System.out.println("价格 "+results.get(i).get("price"));
                    System.out.println("类型 "+results.get(i).get("type"));
                    System.out.println("描述 "+results.get(i).get("description"));
                    System.out.println("联系方式 "+results.get(i).get("contactInfo"));

                }



            }
        });
        */

        /*
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();

            map.put("portrait",R.drawable.touxiang);
            map.put("username","14zhzeng");
            map.put("time","发布于 两个小时前");
            map.put("price","50/小时");
            map.put("type","类型：家教");
            map.put("description","在金平区，高三，数学和物理，每个星期两天，每天两小时，最好是男生");
            list.add(map);
        }
        */
        for (int i=0;i<10;i++){

           // System.out.println("价格 "+results.get(i).get("price"));
         //   System.out.println("类型 "+results.get(i).get("type"));
          //  System.out.println("描述 "+results.get(i).get("description"));
          //  System.out.println("联系方式 "+results.get(i).get("contactInfo"));

            Map<String, Object> map=new HashMap<String, Object>();
            /*
            map.put("portrait",R.drawable.touxiang);
            map.put("username",results.get(i).get("userName"));
            map.put("time",results.get(i).get("time"));
            map.put("price",results.get(i).get("price"));
            map.put("type",results.get(i).get("type"));
            map.put("description",results.get(i).get("description"));
            */
            map.put("portrait",R.drawable.touxiang);
            map.put("username","14zhzeng");
            map.put("time","发布于 两个小时前");
            map.put("price","50/小时");
            map.put("type","类型：家教");
            map.put("description","在金平区，高三，数学和物理，每个星期两天，每天两小时，最好是男生");
            list.add(map);
        }


        return list;
    }


}
