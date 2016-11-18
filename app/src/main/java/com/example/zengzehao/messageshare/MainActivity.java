package com.example.zengzehao.messageshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
       /* AVObject testObject = new AVObject("TestObject");
        testObject.put("words","Hello World!");
        testObject.put("name","testObject");
        testObject.put("num",001);
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.d("saved","success!");
                }
            }
        });

        boolean bool = true;
        int number = 2015;
        String string = number + " 年度音乐排行";
        Date date = new Date();

        byte[] data = "短篇小说".getBytes();
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(number);
        arrayList.add(string);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("数字", number);
        hashMap.put("字符串", string);

        AVObject object = new AVObject("DataTypes");
        object.put("testBoolean", bool);
        object.put("testInteger", number);
        object.put("testDate", date);
        object.put("testData", data);
        object.put("testArrayList", arrayList);
        object.put("testHashMap", hashMap);
        object.saveInBackground();
        */

       // String name;
       // String words;
        AVObject testObject = AVObject.createWithoutData("TestObject","5829d217a22b9d006975c257");

        testObject.put("num",2);
        testObject.put("name","namechange");
        testObject.saveInBackground();

        
    }
}
