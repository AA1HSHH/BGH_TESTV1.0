package com.example.bgh_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat.Builder;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {


    //public static final android.R.attr R = ;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS =1 ;
    public static final int NOTIFICATION_ID = 888;
    private ListView listView;	//用来查询媒体库
    int buttonnum=0;
    MediaPlayer player;
    static  ImageButton button;
    static  TextView text1;
    static ImageView image;
    static SeekBar seekBar;
    static TextView text3;




    public Bitmap Bytes2Bimap(byte[] b) {        if (b.length != 0) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
    else            return null;
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case  MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }








    protected void onCreate(Bundle savedInstanceState) {

/*
* 处理请求
* */
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
//        else {
//            // Permission has already been granted
//        }






        super.onCreate(savedInstanceState);
//        if (getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }
//        setTheme(android.R.style.Theme_Black_NoTitleBar);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button1);
        button.setImageResource(R.drawable.play);
        text1=findViewById(R.id.text);
        image=findViewById(R.id.image);
        text3=findViewById(R.id.text3);
        seekBar=findViewById(R.id.seekbar);



        listView=(ListView)findViewById(R.id.listView1);



        MusicItem Mymusic= new MusicItem();
        Mymusic.MusicItem(this,listView);
        text1.setText("欢迎使用");


        image.setImageResource(R.drawable.yiliao);



//        PendingIntent contentIntent = PendingIntent.getActivity(
////                this, 0, new Intent(this，MainActivity.class), 0);

        //NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"1")
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        Notification notification = new Notification.Builder(this,"1")
//                .setSmallIcon(R.drawable.notification_icon)
//                .setContentTitle("saasas")
//                .setContentText("asasas")
//
//                .build();
//
//
//
//        notificationManager.notify(NOTIFICATION_ID ,notification);














        //获取数据

 //       setListData();









//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
//                + getResources().getResourcePackageName(R.raw.a) + "/"
//                + getResources().getResourceTypeName(R.raw.a) + "/"
//                + getResources().getResourceEntryName(R.raw.a));
////        String str = toString(uri);
//        String TAG="1";
//            Log.d(TAG,"******"+uri.toString());
//
//
//
//        try
//        {
//            mmr.setDataSource(MainActivity.this,uri);
//            String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
//            //Log.d(TAG, "title:" + title);
//            String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
//            //Log.d(TAG, "album:" + album);
//
//            String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
//            //Log.d(TAG, "artist:" + artist);
//            String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); // 播放时长单位为毫秒
//            //Log.d(TAG, "duration:" + duration);
//            //text1.setText(title+"\n"+album+"\n"+artist+"\n"+duration);
//            text1.setText(title+"\n"+artist);
//
//            byte[] pic = mmr.getEmbeddedPicture();
//            Bitmap bitmap=Bytes2Bimap(pic);
//            image.setImageBitmap(bitmap);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//
//        //player = MediaPlayer.create(MainActivity.this, R.raw.a);
//        player=MediaPlayer.create(MainActivity.this,uri);
//        button.setOnClickListener(new View.OnClickListener() {
//                                      public void onClick(View v) {
//                                      //Log.(1,"**********"+buttonnum);
//                                          Log.d("1","**********"+buttonnum);
//                                          if(buttonnum%2==0) {
//                                              button.setImageResource(R.drawable.stop_music);
//
//                                              player.start();
//                                          }
//                                          else{
//                                              button.setImageResource(R.drawable.play);
//
//                                              player.pause();
//                                          }
//                                          buttonnum++;
//
//
//                                      }
//
//
//                                  });








    }

//    void sendNotification() {
////
////        //Get an instance of NotificationManager//
////
////        NotificationCompat.Builder mBuilder =
////                new NotificationCompat.Builder(this)
////                        .setSmallIcon(R.drawable.notification_icon)
////                        .setContentTitle("My notification")
////                        .setContentText("Hello World!");
////
////
////        // Gets an instance of the NotificationManager service//
////
////        NotificationManager mNotificationManager =
////
////                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////
////        // When you issue multiple notifications about the same type of event,
////        // it’s best practice for your app to try to update an existing notification
////        // with this new information, rather than immediately creating a new notification.
////        // If you want to update this notification at a later date, you need to assign it an ID.
////        // You can then use this ID whenever you issue a subsequent notification.
////        // If the previous notification is still visible, the system will update this existing notification,
////        // rather than create a new one. In this example, the notification’s ID is 001//
////
////
////    }
}



//    public void setListData(){				//查询数据库
//         c=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                 new String[]{
//                         MediaStore.Audio.Media.TITLE,
//                         MediaStore.Audio.Media.DURATION,
//                         MediaStore.Audio.Media.ARTIST,
//                         MediaStore.Audio.Media._ID,
//                         MediaStore.Audio.Media.ALBUM,
//                         MediaStore.Audio.Media.DATA,
//                         MediaStore.Audio.Media.ALBUM_ID
//                 }, null, null, null);
//
//         				c.moveToFirst();
//         		//得到媒体库中音乐的个数
//                    int count=c.getCount();				//初始化数组
//         _ids=new int[count];
//         _album_id=new int[count];
//         durations=new int[count];
//         _titles=new String[count];
//         albums=new String[count];
//         artists=new String[count];
//         _path=new String[count];
//         //为数组赋值
//        for (int i = 0; i < count; i++) {
//            _ids[i] = c.getInt(3);
//            _album_id[i]=c.getInt(6);
//            durations[i]=c.getInt(1);
//            _titles[i] = c.getString(0).trim();
//            albums[i] = c.getString(4).trim();
//            artists[i] = c.getString(2).trim();
//            _path[i] = c.getString(5).trim();
//            c.moveToNext();		}
//        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.my_list_item,_path));
//    }






