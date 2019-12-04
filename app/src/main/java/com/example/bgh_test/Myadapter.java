package com.example.bgh_test;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.core.content.ContextCompat.getSystemService;

public class Myadapter extends BaseAdapter {
    LayoutInflater mylayoutInflater;
    Context mycontext;
    int count;
    //List<Music> musicList;
    Music[] musicList;
    int currentplay=-1;
    int nowpaly=-1;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

//        final int positions=position;
//        ViewHolder viewHolder;
//        if(convertView==null){
//            viewHolder=new ViewHolder();
//        convertView = mylayoutInflater.inflate(R.layout.myadapter_item, null);
//        viewHolder.imageView = convertView.findViewById(R.id.adapter_image);
//        viewHolder.textView = convertView.findViewById(R.id.adapter_text);
//        viewHolder.relativeLayout=convertView.findViewById(R.id.myrela);
//            convertView.setTag(viewHolder);
//    }else
//        {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        //textView.setText(musicList[position]);
//        viewHolder.textView.setText(musicList[position]._title);
//        viewHolder.imageView.setImageBitmap(musicList[position]._bitmap);



//        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast ts = Toast.makeText(mycontext,"hello"+,Toast.LENGTH_LONG);
////               ts.show();
//
//
//                if(positions!=currentplay){
//                    if(currentplay!=-1)
//                        musicList[currentplay].serviceMusicPlay(1);//
//                    currentplay=positions;
//                    musicList[positions].serviceMusicPlay(0);
//                }
//                musicList[positions].bindtoButtom(MainActivity.text1,MainActivity.image,MainActivity.button);






                    final int positions=position;
                    View view = mylayoutInflater.inflate(R.layout.myadapter_item, null);
                    ImageView imageView = view.findViewById(R.id.adapter_image);
                    TextView textView = view.findViewById(R.id.adapter_text);
                    RelativeLayout relativeLayout=view.findViewById(R.id.myrela);


                //textView.setText(musicList[position]);
                textView.setText(musicList[position]._title+"."+musicList[position].duration);
                imageView.setImageBitmap(musicList[position]._bitmap);

               relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Timer mTimer = new Timer();
//                        TimerTask mTimerTask = new TimerTask()
//                        {//创建一个线程来执行run方法中的代码
//                             	public void run() {	//要执行的代码
//                                  }};
//
//                        mTimer.schedule(mTimerTask, 3000);
//
//                        new Handler().postDelayed(new Runnable() {
//                                //在当前线程（也即主线程中）开启一个消息处理器，并在3秒后在主线程中执行，从而来更新UI	@
//                         	public void run() {		//有关更新UI的代码
//                                }}, 3000);
//                        Handler mHandler = new Handler() {
//                            @Override
//                            public void handleMessage(Message msg) {
//                                super.handleMessage(msg);
//                                MainActivity.text3.setText(msg.what + "");   //在handleMessage中处理消息队列中的消息
//                            }
//                        };
//                        int time=musicList[position].player.getDuration();
//                        for (int i = 1; i <= time; i++) {
//                            Message message = Message.obtain(mHandler);
//                            message.what = musicList[position].player.getCurrentPosition();
//                            mHandler.sendMessageDelayed(message, 1); //通过延迟发送消息，每隔一秒发送一条消息
//                        }







                        if(positions!=currentplay){
                            if(currentplay!=-1){
                                musicList[currentplay].serviceMusicPlay(-1);//
                                musicList[currentplay].thread.interrupt();
                                Log.d("1",musicList[currentplay]._title+"stop !!!!");

                            }

                            currentplay=positions;

                            musicList[positions].serviceMusicPlay(0);
                            Log.d("1",musicList[positions]._title+"play !!!!");
                        }
                        musicList[positions].bindtoButtom(MainActivity.text1,MainActivity.image,MainActivity.button,MainActivity.seekBar,MainActivity.text3);


                   






//                MediaPlayer player;
//                if(currentplay==-1){
//                    Uri uri = Uri.parse(musicList[position]._path);
//                    player= MediaPlayer.create(mycontext,uri);
//                    currentplay=position;
//                    player.start();
//                }
//                else
//                if(position!=currentplay){
//                    player.pause();
//                }
               // musicList[currentplay].serviceMusicPlay(0);







            }
        });

        return view;
    }

//    public Myadapter(Context context,int incount,List<Music> inmusiclist){
//        super();
//        this.mycontext=context;
//        this.count=incount;
//        this.musicList=inmusiclist;
//    }
//public Myadapter(Context context, int incount, String inmusiclist[] ){
//        super();
//        this.mycontext=context;
//        this.count=incount;
//        this.musicList=inmusiclist;
//        mylayoutInflater= LayoutInflater.from(context);
//    }
    public Myadapter(Context context, int incount, Music inmusiclist[] ){
        super();
        this.mycontext=context;
        this.count=incount;
        this.musicList=inmusiclist;
        mylayoutInflater= LayoutInflater.from(context);
    }
    void Makeplay(String path){
        Uri uri = Uri.parse(path);
        MediaPlayer player= MediaPlayer.create(mycontext,uri);
        player.start();
    }
    void Pauseplay(String path){
        Uri uri = Uri.parse(path);
        MediaPlayer player= MediaPlayer.create(mycontext,uri);
        player.pause();
    }

    void serviceMusicPlay(String path,int command) {
        Uri uri = Uri.parse(path);
        MediaPlayer player = MediaPlayer.create(mycontext, uri);
        if (command == 1)
            player.start();
        else
            player.pause();

    }
    static class ViewHolder{
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;
    }





}
