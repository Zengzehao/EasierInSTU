package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFragment2 extends Fragment {

    private ListView listView;
    private ImageButton top_personinfo;
    private ImageButton top_add;

    public MyFragment2() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab02, container, false);
        listView = (ListView)view.findViewById(R.id.tab02_listview);
        List<Map<String, Object>> list=getData();
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

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
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
        return list;
    }
}
