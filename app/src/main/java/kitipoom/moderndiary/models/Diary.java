package kitipoom.moderndiary.models;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.activities.MainActivity;

/**
 * Created by kitipoom on 19/4/2559.
 */
public class Diary  implements Serializable {
    private String text;
    private boolean open=false;
    private int year,month,day;
    private Bitmap bmp;
    private String filename;

    public Diary(int year,int month,int day){
        this.day=day;
        this.month=month;
        this.year=year;
        this.filename = this.day+"."+this.month+"."+this.year;
        this.text=readFile(internalInputStream());
        this.bmp = getThumbnail();

    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        writeFile(internalOutputStream(), this.text);

    }

    public boolean isOpen() {
        return open;
    }

    public void delete(){
        this.text = "";
        writeFile(internalOutputStream(), this.text);
        this.bmp = null;

        open = false;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
        saveImageToInternalStorage(bmp);

    }
    public void saveDiary(String text,Bitmap bmp){
        setText(text);
        setBmp(bmp);
        open = true;
    }

    private FileOutputStream internalOutputStream() {
        try {
            return  Datasend.getInstant().getMain().openFileOutput(filename, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private FileInputStream internalInputStream(){
        try {
            return Datasend.getInstant().getMain().openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void writeFile(FileOutputStream fos, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFile(FileInputStream fis) {
        String ret = "Hello world";
        String receiveString;
        try {
            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                fis.close();
                ret = stringBuilder.toString();
                open = true;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
            FileOutputStream fos = Datasend.getInstant().getMain().openFileOutput(filename+".p.png", Context.MODE_PRIVATE);

            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }

    public Bitmap getThumbnail() {
        Bitmap thumbnail = null;
        if (thumbnail == null) {
            try {
                //File filePath = Datasend.getInstant().getMain().getFileStreamPath(filename);
                File filePath = Datasend.getInstant().getMain().getFileStreamPath(filename+".p.png");
                FileInputStream fi = new FileInputStream(filePath);
                thumbnail = BitmapFactory.decodeStream(fi);
            } catch (Exception ex) {
                Log.e("getThumbnail() on internal storage", ex.getMessage());
            }
        }
        return thumbnail;
    }

}
