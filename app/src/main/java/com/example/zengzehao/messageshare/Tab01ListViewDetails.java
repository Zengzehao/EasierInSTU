package com.example.zengzehao.messageshare;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.avos.avoscloud.Messages.OpType.query;

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
    private ListView listView;
    String creattime;
    String contact;
    List<Image> mylist = new ArrayList<>();
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMsg("联系方式",contact,Tab01ListViewDetails.this);
            }
        });
        Bundle bundle = getIntent().getExtras();
        String objectId = bundle.getString("objectId");
         creattime= bundle.getString("time");
        contact = bundle.getString("contact");
        AVObject todo = AVObject.createWithoutData("Trade",objectId);

        todo.fetchInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject object, AVException e) {
                username.setText(object.get("userName").toString());
                time.setText(creattime);
                price.setText(object.get("price").toString());
                title.setText(object.get("title").toString());
                description.setText(object.get("description").toString());
              //  List<Object> images = (List<Object>) object.getList("images");

                List<AVObject> images =  object.getList("images");
               // List<String> objects = object.getList("objectsId");

                for (int i=0;i<images.size();i++){
                    //    list.add(new Image("q"));
                    //System.out.println("objectId:"+images.get(i).getObjectId());
                    System.out.println("objectId"+images.get(i).getObjectId());

                    AVQuery<AVObject> query = AVQuery.getQuery("_File");
                    query.whereEqualTo("name", images.get(i).getObjectId());
                    query.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (e != null) {
                                Log.i("bo", "又失败");
                            } else {
                                Log.i("bo", "成功");
                                List<AVObject> users = list;

                                System.out.println(users.get(0).getObjectId());
                                try {
                                    AVFile avFile = AVFile.withObjectId(users.get(0).getObjectId());
                                    System.out.println("URL:"+avFile.getUrl());
                                    mylist.add(new Image(avFile.getUrl()));
                                    System.out.println("tryurl:"+mylist.get(0).getUrl());
                                    System.out.println("mylistzise3:"+mylist.size());
                                    listView.setAdapter(new ImageAdapter(mylist,Tab01ListViewDetails.this));
                                } catch (AVException e1) {
                                    e1.printStackTrace();
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });

                }
                System.out.println("mylistzise:"+mylist.size());
                //listView.setAdapter(new ImageAdapter(mylist,Tab01ListViewDetails.this));
            }
        });
        List<Image> mylist2 = new ArrayList<>();


        mylist2.add(new Image("http://ac-6TTLB0Sd.clouddn.com/81D2QNgtivwTH9iutL3RJB5ELSbZ9qdzA3W2zOiS.png"));

        for (int i=0;i<mylist.size();i++){
            System.out.println("mylistURL:"+mylist.get(i).getUrl());
        }
      //  listView.setAdapter(new ImageAdapter(mylist,Tab01ListViewDetails.this));
        //System.out.println("images:"+images.size());


    }

    private void init(){
        cancel = (TextView) findViewById(R.id.tab01_listview_details_cancel);
        username = (TextView) findViewById(R.id.tab01_listview_details_username);
        time = (TextView) findViewById(R.id.tab01_listview_details_time);
        price = (TextView) findViewById(R.id.tab01_listview_details_price);
        title = (TextView) findViewById(R.id.tab01_listview_details_title);
        description = (TextView) findViewById(R.id.tab01_listview_details_description);
        button = (Button) findViewById(R.id.tab01_listview_details_button);
        listView = (ListView) findViewById(R.id.tab01_details_listview);
    }
    public static void ShowMsg(String title,String msg,Context context) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setPositiveButton("确定",null);
        dlg.show();
    }
}
