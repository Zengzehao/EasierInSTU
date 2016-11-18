package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;

/**
 * Created by zengzehao on 16-11-18.
 */

public class ResetPassword extends AppCompatActivity{
    EditText email;
    Button reset;

    String email_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
        initComponents();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_text = email.getText().toString();
                System.out.println(email_text);
                AVUser.requestPasswordResetInBackground(email_text, new RequestPasswordResetCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            Toast.makeText(ResetPassword.this,"请登录汕大邮箱重置密码",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ResetPassword.this,Login.class);
                            startActivity(intent);
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    private void initComponents(){
        email = (EditText) findViewById(R.id.email);
        reset = (Button) findViewById(R.id.reset);
    }
}
