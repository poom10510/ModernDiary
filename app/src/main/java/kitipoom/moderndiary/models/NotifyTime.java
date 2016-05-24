package kitipoom.moderndiary.models;

import java.io.Serializable;

/**
 * Created by kitipoom on 19/4/2559.
 */
public class NotifyTime implements Serializable {
    private boolean notify;
    private int hour,minute;
    private boolean turn;
    private String text,title;

    public NotifyTime(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
        this.title="This time";
        this.text="Hello";
        this.notify=false;
        this.turn=false;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
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
        this.title="This time";
        this.text="Hello";
        return;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }
}
