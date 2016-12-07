package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by zengzehao on 16-12-6.
 */

public class HelpExpressAddactivity extends AskExpressAddActivity {
    TextView cancel;
    TextView add;
    TextView description;
    TextView contact;
    TextView endtime;
    String description_content;
    String contact_content;
    String endtime_content;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_express_add_layout);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpExpressAddactivity.this.finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent();
                AVObject help_express = new AVObject("Express");
                help_express.put("description",description_content);
                help_express.put("contactInfo",contact_content);
                help_express.put("deadline",endtime_content);
                help_express.put("type","找我拿");
                help_express.put("btn_text","找TA拿");
                help_express.put("userName", AVUser.getCurrentUser().getUsername());
                help_express.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Toast.makeText(HelpExpressAddactivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                            //AskExpressAddActivity.this.finish();
                            //MyFragment3 myFragment1 = new MyFragment3();
                            //Intent intent = new Intent(AskExpressAddActivity.this,myFragment1.getActivity().getClass());
                            //startActivity(intent);
                            //Intent intent = new Intent(WorkAddActivity.this,MyFragment2.class);
                            //startActivity(intent);
                            Intent intent = new Intent(HelpExpressAddactivity.this,MainActivity.class);
                            intent.putExtra("Page",2);
                            startActivity(intent);
                        }else{
                            System.out.println("发布失败");
                        }
                    }
                });
            }
        });
    }
    private void init(){
        cancel = (TextView) findViewById(R.id.help_express_add_cancel);
        add = (TextView) findViewById(R.id.help_express_add_success);
        description = (TextView) findViewById(R.id.help_express_add_description);
        contact = (TextView) findViewById(R.id.help_express_add_contact);
        endtime = (TextView) findViewById(R.id.help_express_add_endtime);
    }
    private void getContent(){
        description_content = description.getText().toString();
        contact_content = contact.getText().toString();
        endtime_content = endtime.getText().toString();
    }
}
