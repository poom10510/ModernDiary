package kitipoom.moderndiary.models;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;

/**
 * Created by kitipoom on 19/4/2559.
 */
public class NotifyTime implements Serializable {
    private boolean notify;
    private int hour,minute;
    private boolean turn;
    private String text,title,filename;


    public NotifyTime(int hour,int minute){
        Datasend data= Datasend.getInstant();
        this.hour=hour;
        this.minute=minute;
        this.filename= data.getDay()+"."+data.getMonth()+"."+data.getYear()+"."+this.hour+"."+this.minute;
        this.title=readFile(internalInputStream("h"));
        this.text=readFile(internalInputStream("t"));
        this.notify=false;
        this.turn=false;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        writeFile(internalOutputStream("h"), this.title);
    }

    public void setText(String text) {
        this.text = text;
        writeFile(internalOutputStream("t"), this.text);
    }

    public boolean isNotify() {
        return notify;
    }

    public boolean isTurn() {
        return turn;
    }

    public String getText() {
        return text;
    }
    public void changeNotify(){
        this.notify=!this.notify;
    }
    public void confirmopen(String title,String text){
        setText(text);
        setTitle(title);
        this.turn=true;
        return;
    }
    public void close(){
        this.notify=false;
        this.turn=false;
        this.title="";
        writeFile(internalOutputStream("h"), this.title);
        this.text="";
        writeFile(internalOutputStream("t"), this.text);
        return;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    private FileOutputStream internalOutputStream(String type) {
        try {
            return  Datasend.getInstant().getMain().openFileOutput(filename+type, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private FileInputStream internalInputStream(String type){
        try {
            return Datasend.getInstant().getMain().openFileInput(filename+type);
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
                turn = true;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
}
