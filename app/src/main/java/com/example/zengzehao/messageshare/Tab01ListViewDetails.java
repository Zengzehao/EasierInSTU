package com.example.zengzehao.messageshare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.GetCallback;

import java.util.ArrayList;

/**
 * Created by zengzehao on 16-12-7.
 */

public class Tab01ListViewDetails extends AppCompatActivity {
    private TextView username;
    private TextView time;
    private TextView price;
    private  TextView title;
    private TextView description;
    private Button button;
    private TextView cancel;
    String creattime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_listview_details_layout);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tab01ListViewDetails.this.finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        String objectId = bundle.getString("objectId");
         creattime= bundle.getString("time");
        AVObject todo = AVObject.createWithoutData("Trade",objectId);
        todo.fetchInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject object, AVException e) {
                username.setText(object.get("userName").toString());
                time.setText(creattime);
                price.setText(object.get("price").toString());
                title.setText(object.get("title").toString());
                description.setText(object.get("description").toString());
                ArrayList<AVFile> images = (ArrayList<AVFile>) object.get("images");
                System.out.println("images:"+images.size());
            }
        });


    }

    private void init(){
        cancel = (TextView) findViewById(R.id.tab01_listview_details_cancel);
        username = (TextView) findViewById(R.id.tab01_listview_details_username);
        time = (TextView) findViewById(R.id.tab01_listview_details_time);
        price = (TextView) findViewById(R.id.tab01_listview_details_price);
        title = (TextView) findViewById(R.id.tab01_listview_details_title);
        description = (TextView) findViewById(R.id.tab01_listview_details_description);
        button = (Button) findViewById(R.id.tab01_listview_details_button);
    }
}
