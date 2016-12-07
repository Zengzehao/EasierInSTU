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
import com.avos.avoscloud.BroadcastUtil;
import com.avos.avoscloud.SaveCallback;

import org.w3c.dom.Text;

/**
 * Created by zengzehao on 16-12-6.
 */

public class BuyAddAcivity extends AppCompatActivity {
    TextView cancel;
    TextView add;
    EditText title;
    EditText description;
    EditText price;
    EditText contact;


    String title_content;
    String description_content;
    String price_content;
    String contact_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_add_layout);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyAddAcivity.this.finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BuyAddAcivity.this,"你点击了发布按钮",Toast.LENGTH_SHORT).show();
                getContent();
                AVObject buy = new AVObject("Trade");
                buy.put("userName", AVUser.getCurrentUser().getUsername());
                buy.put("title",title_content);
                buy.put("type","求购");
                buy.put("description",description_content);
                buy.put("price",price_content);
                buy.put("contactInfo",contact_content);
                buy.put("clicks",0);
                buy.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e==null){
                            Toast.makeText(BuyAddAcivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BuyAddAcivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BuyAddAcivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void init(){
        cancel = (TextView) findViewById(R.id.buy_add_cancel);
        add = (TextView) findViewById(R.id.buy_add_success);
        title = (EditText) findViewById(R.id.buy_add_title);
        description = (EditText) findViewById(R.id.buy_add_description);
        price = (EditText) findViewById(R.id.buy_add_price);
        contact = (EditText) findViewById(R.id.buy_add_contanct);

    }
    private void getContent(){
        title_content = title.getText().toString();
        description_content = description.getText().toString();
        price_content = price.getText().toString();
        contact_content = contact.getText().toString();
    }
}
