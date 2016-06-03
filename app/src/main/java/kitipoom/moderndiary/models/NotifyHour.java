package kitipoom.moderndiary.models;

/**
 * Created by kitipoom on 18/4/2559.
 */
public class NotifyHour {
    private NotifyMin[] notifyList;
    private int hour;

    public NotifyHour(int hour){
        this.hour=hour;
        this.notifyList=new NotifyMin[60];
    }
    public void addMin(int min){
        if(notifyList[min]==null){
            notifyList[min]=new NotifyMin(hour,min);
        }
        return;
    }

    public NotifyMin[] getNotifyList() {
        return notifyList;
    }

    public NotifyMin getNotifyMin(int min){
        return notifyList[min];
    }

    public int getHour() {
        return hour;
    }
}
