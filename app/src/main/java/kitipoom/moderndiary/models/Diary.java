package kitipoom.moderndiary.models;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

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

    public Diary(int year,int month,int day){
        this.text="Hello World";
        this.day=day;
        this.month=month;
        this.year=year;
        this.bmp = null;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;

    }

    public boolean isOpen() {
        return open;
    }

    public void delete(){
        this.text = "Hello World";
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
    }
    public void saveDiary(String text,Bitmap bmp){
        setText(text);
        setBmp(bmp);
        open = true;
    }
}
