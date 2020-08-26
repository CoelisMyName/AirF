package com.example.airf.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor {
    File file;
    ZipOutputStream zos;
    byte[] buff = new byte[1024];

    public ZipCompressor(File file, OutputStream os){
        zos = new ZipOutputStream(os);
        this.file = file;
    }

    public void zip() throws IOException {
        zip(file,file.getName());
        zos.close();
    }

    private void zip(File file, String base) throws IOException {
        if(file.isDirectory()){
            File[] fl = file.listFiles();
            if(base.length() != 0){
                zos.putNextEntry(new ZipEntry(base + "/"));
            }
            for(File f : fl){
                zip(f,base + "/" + f.getName());
            }
        }
        else if(file.isFile()){
            zos.putNextEntry(new ZipEntry(base));
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int length;
            while (( length = bis.read(buff) )!= -1){
                zos.write(buff,0,length);
            }
            try {
                bis.close();
            }catch (IOException ignored){
            }
            try {
                fis.close();
            }catch (IOException ignored){
            }
        }
    }
}
