package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by zengzehao on 16-12-6.
 */

public class SellAddActivity extends AppCompatActivity {
    TextView cancel;
    TextView add;
    EditText title;
    EditText description;
    EditText price;
    EditText contact;
    ImageButton add_pic;

    String title_content;
    String description_content;
    String price_content;
    String contact_content;
    
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_add_layout);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SellAddActivity.this.finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent();
                AVObject sell = new AVObject("Trade");
                sell.put("userName", AVUser.getCurrentUser().getUsername());
                sell.put("title",title_content);
                sell.put("description",description_content);
                sell.put("price",price_content);
                sell.put("contactInfo",contact_content);
                sell.put("type","出售");
                sell.put("clicks",0);
                sell.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e==null){
                            Toast.makeText(SellAddActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                            //SellAddActivity.this.finish();
                            Intent intent = new Intent(SellAddActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
    private void init(){
        cancel = (TextView) findViewById(R.id.sell_add_cancel);
        add = (TextView) findViewById(R.id.sell_add_success);
        title = (EditText) findViewById(R.id.sell_add_title);
        description = (EditText) findViewById(R.id.sell_add_description);
        price = (EditText) findViewById(R.id.sell_add_price);
        contact = (EditText) findViewById(R.id.sell_add_contanct);

    }
    private void getContent(){
        title_content = title.getText().toString();
        description_content = description.getText().toString();
        price_content = price.getText().toString();
        contact_content = contact.getText().toString();
    }
}
