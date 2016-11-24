package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import static com.example.zengzehao.messageshare.R.layout.log_in;

/**
 * Created by zengzehao on 16-11-17.
 */
public class Login extends AppCompatActivity {
    ImageView logo;
    EditText username;
    EditText password;
    Button login;
    TextView forget_password;
    TextView user_register;

    String username_text;
    String password_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(log_in);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
        initComponents();


        //为登录按钮注册监听
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getComponentsText();
                AVUser.logInInBackground(username_text, password_text, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if(e == null){
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this,"用户名或者密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        //为忘记密码TextView注册监听
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,ResetPassword.class);
                startActivity(intent);
            }
        });

        //为用户注册TextView注册监听
        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents(){
        logo = (ImageView)findViewById(R.id.logo);
        username = (EditText) findViewById(R.id.user_Name);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        forget_password = (TextView) findViewById(R.id.forget_password);
        user_register = (TextView) findViewById(R.id.user_register);
    }

    private void getComponentsText(){
        username_text = username.getText().toString();
        password_text = password.getText().toString();
    }

}
