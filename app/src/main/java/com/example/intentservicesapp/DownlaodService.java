package com.example.intentservicesapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownlaodService extends IntentService {

   public DownlaodService(){
       super("reeta");
   }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("reeta","OnCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("reeta","OnDestroy");
    }
    public void downloadFile(){
        try{
            File directory=new File(getFilesDir()+File.separator + "Vogella");
            if (!directory.exists()){
                directory.mkdir();
            }
            File file=new File(directory,"vogella.html");
            if (!file.exists()){
                file.createNewFile();
            }
            URL url=new URL("https://www.vogella.com/index.html");
            InputStream inputStream=url.openConnection().getInputStream();
            InputStreamReader reader=new InputStreamReader(inputStream);
            FileOutputStream writer=new FileOutputStream(file,true);
            int data=reader.read();
            while (data!=-1){
                char ch= (char) data;
                writer.write(ch);
                data=reader.read();
            }
            FileInputStream fileInputStream=new FileInputStream(file);
            int fileData=fileInputStream.read();
            StringBuffer stringBuffer=new StringBuffer();
            while (fileData!=-1){
                char ch= (char) fileData;
                stringBuffer=stringBuffer.append(ch);
                fileData=fileInputStream.read();
            }
            Log.d("reeta",stringBuffer.toString());
        }catch (Exception e){

        }
   }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
       Log.d("reeta","OnHandlerIntent"+Thread.currentThread().getName());
        downloadFile();

    }
}