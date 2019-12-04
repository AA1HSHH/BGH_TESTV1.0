package com.example.bgh_test;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URI;


public class Music {

     String _title;
     String _album;
     String _artist;
     String _path;
     Bitmap _bitmap;
     MediaPlayer player;



     Uri uri;
    private String _duration;
    int duration;
    int count=0;
    Thread thread;
    int seekto=0;
    boolean lock=false;

    public Music(Context context,String title,String album,String artist,String path,int induration){  //对象初始化
        this._title=title;
        this._album=album;
        this._artist=artist;
        this._path=path;
        this.duration=induration;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try
        {
            mmr.setDataSource(path);
            this._bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.yiliao);

            byte[] pic = mmr.getEmbeddedPicture();
            this._bitmap=Bytes2Bimap(pic);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        this.uri = Uri.parse(path);
        this.player= MediaPlayer.create(context,uri);


    }
//    public Music(Context context,String title,String album,String artist,Uri inuri,int induration){
//        this._title=title;
//        this._album=album;
//        this._artist=artist;
//        File file = new File(inuri.toString());
//        String path = file.getPath();
//        this._path=path;
//        this.duration=induration;
//
//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        try
//        {
//            mmr.setDataSource(path);
//            this._bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.yiliao);
//
//            byte[] pic = mmr.getEmbeddedPicture();
//            this._bitmap=Bytes2Bimap(pic);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        this.uri = inuri;
//        this.player= MediaPlayer.create(context,inuri);
//    }
public Bitmap Bytes2Bimap(byte[] b) {        if (b.length != 0) {
    return BitmapFactory.decodeByteArray(b, 0, b.length);
}
else            return null;
}

    void serviceMusicPlay(int command){   //播放或暂停音乐
//        Uri uri = Uri.parse(path);
//        MediaPlayer player= MediaPlayer.create(mycontext,uri);




        switch(command){
            case 0 :  //start
                //player.prepareAsync();
                player.start();
                break;
            case -1: //stop
                player.seekTo(0);
                player.stop();
                try{
                player.prepare();}
                catch (Exception e){
                    Log.d("1","fail!!!");

                }
                break;
            case 1 ://pause
                player.seekTo(0);
                player.pause();
                break;


        }


    }




    void bindtoButtom(TextView textView, ImageView imageView, final ImageButton imageButton, final SeekBar seekBar, final TextView textView2){   //对底部播放控制进行绑定
        Log.d("1","********************????*"+"    "+this.duration);

        textView.setText(this._title);
        imageView.setImageBitmap(this._bitmap);
        imageButton.setImageResource(R.drawable.stop_music);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count%2 ==0){
                    imageButton.setImageResource(R.drawable.play);
                    player.pause();
                }
                else
                {
                    player.start();
                    imageButton.setImageResource(R.drawable.stop_music);
                }
                count ++;
            }
        });


//        Runnable myRun = new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    int i=0;
////                    while (true){
////                        int currentPosition=player.getCurrentPosition();
////                        Log.d("1","currentPosition=="+currentPosition);
////
////
////                        try{
////
////                            seekBar.setProgress(i);
////                            Log.d("1","i="+i);
////                        }catch (Exception e){
////                            Log.d("1","seekbar erro !!!!!!!");
////                        }
////                        textView2.setText(Integer.toString(currentPosition));
////                        Thread.sleep(1000);
////                        i=currentPosition*100/duration;
////
////                        continue;
////                    }
////
////
////                }catch (Exception e){
////                    e.printStackTrace();
////                }
////
////            }
////        };
        Runnable myRun = new Runnable() {
            @Override
            public void run() {
                try {
                    int i=0;
                    while (true){
                        int currentPosition=player.getCurrentPosition();
                        Log.d("1","currentPosition=="+currentPosition);
                        if(lock==false) {
                            try {

                                seekBar.setProgress(seekto);
                                Log.d("1", "i=" + seekto);
                            } catch (Exception e) {
                                Log.d("1", "seekbar erro !!!!!!!");
                            }
                        }
                        textView2.setText(Integer.toString(currentPosition));
                        Thread.sleep(1000);
                        seekto=currentPosition*100/duration;

                        continue;
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };
        this.thread=new Thread(myRun);
        thread.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int abc;
            int csd;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 abc=progress*duration/100;
                 seekto=progress;
                  csd=progress;
                 seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("1","abc="+abc);
               seekBar.setProgress(csd);
               seekto=csd;
                //seekto=abc;
                player.seekTo(abc);
                lock=true;
                try{
                    Thread.sleep(2000);
                }catch (Exception e){}
                lock=false;





            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {



//                try{
//                    player.prepare();}
//                catch (Exception e){
//                    Log.d("1","fail in last!!!");
//
//                }
                player.seekTo(0);
                MainActivity.button.setImageResource(R.drawable.play);
                count++;
            }
        });

          //匿名类实现
//          new Thread(new Runnable() {
//                 @Override
//                 public void run() {
//                     try {
//                         int i=0;
//                         while (true){
//                             int currentPosition=player.getCurrentPosition();
//                            Log.d("1","currentPosition=="+currentPosition);
//
//
//                             try{
//
//                            seekBar.setProgress(i);
//                                 Log.d("1","i="+i);
//                             }catch (Exception e){
//                                 Log.d("1","seekbar erro !!!!!!!");
//                             }
//                            textView2.setText(Integer.toString(currentPosition));
//                             Thread.sleep(1000);
//                             i=currentPosition*100/duration;
//
//                             continue;
//                         }
//
//
//                     }catch (Exception e){
//                         e.printStackTrace();
//                     }
//                 }
//             }).start();



//        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//
//                serviceMusicPlay(-1);
//                imageButton.setImageResource(R.drawable.play);
//            }
//        });



        //myThread.start();




//        Handler mHandler = new Handler() {
//                            @Override
//                            public void handleMessage(Message msg) {
//                                super.handleMessage(msg);
//                                textView2.setText(msg.what + "");   //在handleMessage中处理消息队列中的消息
//                            }
//                        };
//                        int time=this.player.getDuration();
//                        for (int i = 1; i <= time; i++) {
//                            Message message = Message.obtain(mHandler);
//                            message.what = this.player.getCurrentPosition();
//                            mHandler.sendMessageDelayed(message, 1); //通过延迟发送消息，每隔一秒发送一条消息
//                        }



//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            double rate=1;
//            int start=0;
//
//
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                rate=(double) progress*0.01;
//               Log.d("1","*********************"+Integer.toString(progress));
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("1","*********************"+"123    "+rate);
//
//                //player.seekTo(SEEK_PREVIOUS_SYNC，1000);
//
//                player.seekTo((int)rate*player.getDuration());
//            }




//        });


    }

//    public Music(String path,Context context){
//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        try
//        {
//            mmr.setDataSource(path);
//            this._bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.yiliao);
//            this._title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
//            this._album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
//            this._artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
//            this._duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); // 播放时长单位为毫秒
//            byte[] pic = mmr.getEmbeddedPicture();
//            this._bitmap=Bytes2Bimap(pic);
////            if(pic.length>0){
////                this._bitmap=Bytes2Bimap(pic);
////            }
////            else
////
////                this._bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.notification_icon);
//
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    };


//    void  getPicture (String path,Context context){
//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        try
//        {
//            mmr.setDataSource(path);
//            this._bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.yiliao);
//
//            byte[] pic = mmr.getEmbeddedPicture();
//            this._bitmap=Bytes2Bimap(pic);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
