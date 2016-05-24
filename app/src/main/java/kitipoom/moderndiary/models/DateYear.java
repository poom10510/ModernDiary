package kitipoom.moderndiary.models;

/**
 * Created by kitipoom on 18/4/2559.
 */
public class DateYear {
    private DateMonth[] dateList;
    private int year;
    private int month;

    public DateYear(int year){
        this.year=year;
        dateList =new DateMonth[13];
    }
    public int getYear(){
        return this.year;
    }
    public void addMounth(int month,int day){
        if(dateList[month]==null){
            dateList[month]= new DateMonth(month);
        }
        dateList[month].addDay(day);
        return;
    }
    public DateMonth getDateMonth(int month){
        return dateList[month];
    }

    public int getMonth() {
        return month;
    }

    public DateMonth[] getDateList() {
        return dateList;
    }
}
