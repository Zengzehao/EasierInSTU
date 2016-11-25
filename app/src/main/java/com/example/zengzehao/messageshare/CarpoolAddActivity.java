package com.example.zengzehao.messageshare;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.util.List;

/**
 * Created by zengzehao on 16-11-23.
 */

public class CarpoolAddActivity extends AppCompatActivity {

    TextView cancel;
    TextView add_success;
    EditText starttime;
    EditText startplace;
    EditText endplace;
    EditText need;
    EditText contact;

    String starttime_content;
    String startplace_content;
    String endplace_content;
    int need_content = 0;
   // int clicks = 0;
    String contact_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpool_add_layout);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
        initComponets();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarpoolAddActivity.this.finish();
            }
        });

        add_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getComponentsContent();
                System.out.println(starttime_content + " "+ startplace_content + " " + endplace_content + " "+ need_content + " "+ contact_content);

                //AVObject carpool = new AVObject("Carpool");
                AVObject carpool = new AVObject("CarpoolTest");

                carpool.put("time",starttime_content);
                carpool.put("place_start",startplace_content);
                carpool.put("place_end",endplace_content);
                carpool.put("need_num",need_content);
                carpool.put("contactInfo",contact_content);
                carpool.put("userName", AVUser.getCurrentUser());
                carpool.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Toast.makeText(CarpoolAddActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                            CarpoolAddActivity.this.finish();
                        }else{
                            System.out.println("发布失败");
                        }
                    }
                });

              /*
                AVQuery<AVObject> query = new AVQuery<AVObject>("CarpoolTest");
                try {
                    List<AVObject> avObjects = query.find();
                    String a1 = avObjects.get(0).getString("time");
                    System.out.println("时间"+a1);
                } catch (AVException e) {
                    e.printStackTrace();
                }
                */
            }
        });
    }

    private void initComponets(){
        cancel = (TextView) findViewById(R.id.cancel);
        add_success = (TextView) findViewById(R.id.add_success);
        starttime = (EditText) findViewById(R.id.starttime);
        startplace = (EditText) findViewById(R.id.startpalce);
        endplace = (EditText) findViewById(R.id.endplace);
        need = (EditText) findViewById(R.id.need);
        contact = (EditText) findViewById(R.id.contact);
    }

    private void getComponentsContent(){
        starttime_content = starttime.getText().toString();
        startplace_content = startplace.getText().toString();
        endplace_content = endplace.getText().toString();
        need_content = Integer.parseInt(need.getText().toString());
        contact_content = contact.getText().toString();
    }
}
