package com.example.airf.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileWriter {

    OutputStream os;

    BufferedOutputStream bos;

    String fileName;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    FileWriter(@NonNull Context context, @NonNull String fileName){
        Cursor cursor = context.getContentResolver().query(MediaStore.Downloads.EXTERNAL_CONTENT_URI,null,null,null);
        ParcelFileDescriptor parcelFileDescriptor;

        this.fileName = fileName;
    }

    public void write(byte[] buff) throws IOException {
        bos.write(buff);
    }

    public void write(byte[] buff, int off, int length) throws IOException {
        bos.write(buff, off, length);
    }

    public void write(int i) throws IOException {
        bos.write(i);
    }

    public void close() throws IOException {
        bos.close();
        os.close();
    }
}
