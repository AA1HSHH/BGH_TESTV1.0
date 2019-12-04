package com.example.bgh_test;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
//实例化local music

public class MusicItem {

//    String name;
//    Uri songUri;
//    Uri albumUri;
//    Bitmap thumb;
//    long duration;
//
//    MusicItem(Uri songUri, Uri albumUri, String strName, long duration) {
//        this.name = strName;
//        this.songUri = songUri;
//        this.duration = duration;
//        this.albumUri = albumUri;
//    }
    Music[] music;
    private int[] _ids;
    private int[] _album_id;
    private int[] durations;
    private String[]_titles;
    private String[] albums;
    private String[] artists;
    private String[] _path;
    private Uri[] _uri;
    //Uri uri = Uri.parse(path);
    private AlertDialog ad = null;
    private AlertDialog.Builder  builder = null; 		//列表框，用来展示音乐名称
    public int count;
    String TAG="1";


    void MusicItem(Context context,ListView listView){
         Cursor c = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media.TITLE,  //0
                        MediaStore.Audio.Media.DURATION,//1
                        MediaStore.Audio.Media.ARTIST,//2
                        MediaStore.Audio.Media._ID,//3
                        MediaStore.Audio.Media.ALBUM,//4
                        MediaStore.Audio.Media.DATA,//5
                        MediaStore.Audio.Media.ALBUM_ID//6
                        //MediaStore.Audio.Media.
                }, null, null, null);
        c.moveToFirst();

        //得到媒体库中音乐的个数
         count=c.getCount();				//初始化数组
        _ids=new int[count];
        _album_id=new int[count];
        durations=new int[count];
        _titles=new String[count];
        albums=new String[count];
        artists=new String[count];
        _path=new String[count];
        _uri=new Uri[count];
        music=new Music[count];

        for (int i = 0; i < count; i++) {
            _ids[i] = c.getInt(3);
            _album_id[i]=c.getInt(6);
            durations[i]=c.getInt(1);
            _titles[i] = c.getString(0).trim();
            albums[i] = c.getString(4).trim();
            artists[i] = c.getString(2).trim();
            _path[i] = c.getString(5).trim();
            music[i]= new Music(context,_titles[i],albums[i],artists[i],_path[i],durations[i]);
            c.moveToNext();		}

        Myadapter myadapter=new Myadapter(context,count,music);
        listView.setAdapter(myadapter);


    }






}

