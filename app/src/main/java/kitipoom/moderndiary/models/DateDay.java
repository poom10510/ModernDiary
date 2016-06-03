package kitipoom.moderndiary.models;

import java.io.Serializable;

/**
 * Created by kitipoom on 18/4/2559.
 */
public class DateDay implements Serializable {
    private int day,month,year;
    private Diary diary;
    private NotifyHour[] notifyList;
    private boolean notilist;

    public DateDay(int day){
        this.day=day;
        this.month = Datasend.getInstant().getMonth();
        this.year = Datasend.getInstant().getYear();
        this.diary = new Diary(Datasend.getInstant().getYear(),Datasend.getInstant().getMonth(),Datasend.getInstant().getDay());
        notilist=false;

        notifyList= new NotifyHour[24];
    }

    public Diary getDiary() {
        return diary;
    }
    public int getDay() {
        return day;
    }
    public void addHour(int hour,int min){
        if(notifyList[hour]==null){
            notifyList[hour]=new NotifyHour(hour);
        }
        notifyList[hour].addMin(min);
        return;
    }
    public NotifyHour[] getNotifyList() {
        return notifyList;
    }
    public NotifyHour getNotifyHour(int hour){
        return notifyList[hour];
    }

    public boolean haveNotilist() {
        return notilist;
    }

    public void setNotilist(boolean notilist) {
        this.notilist = notilist;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
