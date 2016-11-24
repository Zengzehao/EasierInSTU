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
import com.avos.avoscloud.SaveCallback;

/**
 * Created by zengzehao on 16-11-24.
 */

public class ChangeNicknameActiviy extends AppCompatActivity {

    EditText newNickname;
    Button change_nickname_success;
    String newNickName_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_nickname);
        AVOSCloud.initialize(this,"6TTLB0Sd6E8EeuwR3uslREsz-gzGzoHsz","zYP2M2Bd9bXR4RzUMKgHgPwz");
        newNickname = (EditText) findViewById(R.id.newNickname);
        change_nickname_success = (Button) findViewById(R.id.change_nickname_success);
        change_nickname_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNickName_content = newNickname.getText().toString();
                AVUser.getCurrentUser().put("nickName", newNickName_content);
                AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Toast.makeText(ChangeNicknameActiviy.this,"修改昵称成功",Toast.LENGTH_SHORT).show();
                            ChangeNicknameActiviy.this.finish();
                            Intent intent = new Intent(ChangeNicknameActiviy.this,PersonInfoActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
