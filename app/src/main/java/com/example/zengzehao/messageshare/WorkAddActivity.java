package com.example.zengzehao.messageshare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by zengzehao on 16-11-25.
 */

public class WorkAddActivity extends AppCompatActivity {
    TextView cancel;
    TextView add_success;
    EditText type;
    EditText description;
    EditText price;
    EditText contact;

    String type_content;
    String description_content;
    String price_content;
    String contanct_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_add_layout);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
        initComponents();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkAddActivity.this.finish();
            }
        });

        add_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getComponentsContent();
                System.out.println(type_content + " "+price_content + " " +description_content);
                //AVObject carpool = new AVObject("Carpool");
                AVObject job = new AVObject("JobTest");

                job.put("type",type_content);
                job.put("price",price_content);
                job.put("description",description_content);
                job.put("contactInfo",contanct_content);

                job.put("userName", AVUser.getCurrentUser());
                //carpool.put("userPortrait",new AVFile());
                job.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Toast.makeText(WorkAddActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                            WorkAddActivity.this.finish();
                        }else{
                            System.out.println("发布失败");
                        }
                    }
                });
            }
        });
    }

    private void initComponents(){
        cancel = (TextView) findViewById(R.id.work_add_cancel);
        add_success = (TextView) findViewById(R.id.work_add_success);
        type = (EditText) findViewById(R.id.work_add_type);
        description = (EditText) findViewById(R.id.work_add_description);
        price = (EditText) findViewById(R.id.work_add_price);
        contact = (EditText) findViewById(R.id.work_add_contanct);
    }

    private void getComponentsContent(){
        type_content = type.getText().toString();
        description_content = description.getText().toString();
        price_content = price.getText().toString();
        contanct_content = contact.getText().toString();
    }
}
