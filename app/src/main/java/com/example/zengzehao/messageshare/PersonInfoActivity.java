package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zengzehao on 16-11-24.
 */

public class PersonInfoActivity extends AppCompatActivity {
    TextView cancel;

    ImageView portrait;
    ImageView change_portrait;
    ImageView change_nickname;


    TextView username;
    TextView email;
    TextView nickname;

   //String username_content;
    //String email_content;
    //String nickname_content;

    Button logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_layout);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");

        cancel = (TextView) findViewById(R.id.personinfo_cancel);

        portrait = (ImageView) findViewById(R.id.portrait);
        change_portrait = (ImageView) findViewById(R.id.change_portrait);
        change_nickname = (ImageView) findViewById(R.id.change_personinfo_nackname);
        username = (TextView) findViewById(R.id.personinfo_username);
        email = (TextView) findViewById(R.id.personinfo_email);
        nickname = (TextView) findViewById(R.id.personinfo_nickname);


        logout = (Button) findViewById(R.id.logout);

        updatePersinInfo();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonInfoActivity.this.finish();
            }
        });

        change_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonInfoActivity.this,ChangeNicknameActiviy.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonInfoActivity.this,Login.class);
                startActivity(intent);
            }
        });

    }

    private void updatePersinInfo(){

        username.setText(AVUser.getCurrentUser().getUsername());
        email.setText(AVUser.getCurrentUser().getEmail());
        nickname.setText(AVUser.getCurrentUser().get("nickName").toString());
        if(AVUser.getCurrentUser().getAVFile("image") != null){
            Picasso.with(PersonInfoActivity.this).load(AVUser.getCurrentUser().getAVFile("image") == null ? "www" : AVUser.getCurrentUser().getAVFile("image").getUrl()).into(portrait);
        }
      //  System.out.println(AVUser.getCurrentUser().getAVFile("image").getUrl());





    }


}
