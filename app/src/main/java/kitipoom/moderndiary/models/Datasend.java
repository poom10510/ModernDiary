package kitipoom.moderndiary.models;

import kitipoom.moderndiary.activities.MainActivity;

/**
 * Created by kitipoom on 18/4/2559.
 */
public class Datasend {
    private static Datasend data;
    private int day,month,year;
    private int hour,minute;
    private MainActivity main;
    private Datasend(){

    }
    public static Datasend getInstant(){
        if(data==null){
            data=new Datasend();
        }
        return data;
    }
    public void setDate(int day ,int mounth,int year){
        this.day=day;
        this.month=mounth;
        this.year=year;
    }
    public void setTime(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public MainActivity getMain() {
        return main;
    }

}
