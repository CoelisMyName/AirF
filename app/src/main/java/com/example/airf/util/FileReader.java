package com.example.airf.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;

public class FileReader {

    String fileName;

    long fileSize;

    InputStream is;

    BufferedInputStream bis;

    /**
     *
     * @param context
     * @param uri
     * @throws FileNotFoundException
     * 耗时操作
     */
    public FileReader(@NonNull Context context, @NonNull Uri uri) throws FileNotFoundException {
        is = context.getContentResolver().openInputStream(uri);
        bis = new BufferedInputStream(is);
        Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null);
        int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
        cursor.moveToFirst();
        fileName = cursor.getString(nameIndex);
        fileSize = cursor.getLong(sizeIndex);
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public int read(byte[] buff) throws IOException {
        return bis.read(buff);
    }

    public int read(byte[] buff,int off, int length) throws IOException {
        return bis.read(buff,off,length);
    }

    public int read() throws IOException {
        return bis.read();
    }

    public void close() throws IOException {
        bis.close();
        is.close();
    }
}
