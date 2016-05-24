package kitipoom.moderndiary.models;

/**
 * Created by kitipoom on 18/4/2559.
 */
public class DateMonth {
    private DateDay[] dateList;
    private int month;
    
    public DateMonth(int month){
        this.month = month;
        dateList =new DateDay[32];
    }
    public int getMonth(){
        return this.month;
    }
    public void addDay(int day){
        if(dateList[day]==null){
            dateList[day]= new DateDay(day);
        }
        return;
    }
    public DateDay getDateDay(int day){
        return dateList[day];
    }

    public DateDay[] getDateList() {
        return dateList;
    }
}
