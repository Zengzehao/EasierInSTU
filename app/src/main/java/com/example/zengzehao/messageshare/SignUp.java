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
import com.avos.avoscloud.RequestEmailVerifyCallback;
import com.avos.avoscloud.SignUpCallback;

/**
 * Created by zengzehao on 16-11-18.
 */

public class SignUp extends AppCompatActivity {
    EditText email;
    EditText username;
    EditText nickname;
    EditText password1;
    EditText password2;
    Button register;
    String email_text;
    String username_text;
    String nickname_text;
    String password1_text;
    String password2_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
        initComponents();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getComponentsText();
                AVUser user = new AVUser();  //新建AVUser实例
                AVUser.requestEmailVerfiyInBackground(email_text, new RequestEmailVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){

                        }
                    }
                });
                System.out.println(111+username_text+" "+email_text+" "+password1_text+" "+nickname_text);
                user.setUsername(username_text);
                user.setEmail(email_text);
                user.setPassword(password1_text);
                user.put("nickName",nickname_text);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Toast.makeText(SignUp.this,"请登录汕大邮箱进行验证！",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,Login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignUp.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
    private void initComponents(){
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.user_Name);
        nickname = (EditText) findViewById(R.id.nick_Name);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);
        register = (Button) findViewById(R.id.register);
    }

    private void getComponentsText(){
        email_text = email.getText().toString();
        username_text = username.getText().toString();
        nickname_text = nickname.getText().toString();
        password1_text = password1.getText().toString();
        password2_text = password2.getText().toString();
    }
}
