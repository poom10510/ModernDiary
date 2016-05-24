package kitipoom.moderndiary.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kitipoom on 18/4/2559.
 */
public class Storage {
    private static Storage st;
    private List<DateYear> dateList;
    private int year;
    private Storage(){
        dateList =new ArrayList<DateYear>();
    }

    public static Storage getSt() {
        if(st==null){
            st = new Storage();
        }
        return st;
    }
    public boolean findYear(int year){

        for(int i=0;i<this.dateList.size();i++){
            if(dateList.get(i).getYear()==year){
                this.year=i;
                return true;
            }
        }
        return false;
    }
    public void saveDate(int day ,int mounth,int year){
        if(!findYear(year)){
            DateYear y = new DateYear(year);
            dateList.add(y);
            findYear(year);
        }
        dateList.get(this.year).addMounth(mounth,day);
        return;

    }

    public List<DateYear> getDateList() {
        return dateList;
    }
    public DateYear getDateyear(){
        return dateList.get(this.year);
    }


}
