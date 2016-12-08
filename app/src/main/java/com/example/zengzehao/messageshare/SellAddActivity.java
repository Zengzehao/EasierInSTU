package com.example.zengzehao.messageshare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

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
    ImageView add_pic;

    String title_content;
    String description_content;
    String price_content;
    String contact_content;

    private PhotoAdapter photoAdapter;

    private ArrayList<String> selectedPhotos = new ArrayList<>();


    
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_add_layout);
        init();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        System.out.println("resyslerview:"+recyclerView);
        photoAdapter = new PhotoAdapter(this, selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);
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
                System.out.println("size:"+selectedPhotos.size());
               // HashMap<String, AVFile> hashMap = new HashMap<>();
               ArrayList<AVFile> arrayList = new ArrayList<>();
                for (int i =0;i<selectedPhotos.size();i++){
                    System.out.println(selectedPhotos.get(i));
                    Bitmap bitmap =convertToBitmap(selectedPhotos.get(i),50,50);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] data = baos.toByteArray();
                    arrayList.add(new AVFile(i+".png",data));
                    //hashMap.put("image",new AVFile(i+".png",data));
                }
                sell.put("images",arrayList);
                //sell.put("images",hashMap);
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

        add_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPicker.builder()
                        .setPhotoCount(3)
                        .setShowCamera(true)
                        .setSelected(selectedPhotos)
                        .start(SellAddActivity.this);

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                (view, position) ->
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(position)
                                .start(SellAddActivity.this)
        ));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                System.out.println("photos:"+photos);
            }
            selectedPhotos.clear();

            if (photos != null) {

                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
    // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    this.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }
    public Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int)scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }


    private void init(){
        cancel = (TextView) findViewById(R.id.sell_add_cancel);
        add = (TextView) findViewById(R.id.sell_add_success);
        add_pic = (ImageView) findViewById(R.id.sell_add_picture_button);
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
