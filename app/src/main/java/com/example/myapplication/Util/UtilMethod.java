package com.example.myapplication.Util;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.room.Room;


import com.example.myapplication.InitDataBase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UtilMethod {

    public static InitDataBase baseRoomDatabase;

    public static InitDataBase getInstance(Context context) {
        if (baseRoomDatabase == null) {
            baseRoomDatabase = Room.databaseBuilder(context, InitDataBase.class, "zww_database.db").allowMainThreadQueries().build();
        }
        return baseRoomDatabase;
    }
    public static void showToast(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }


}