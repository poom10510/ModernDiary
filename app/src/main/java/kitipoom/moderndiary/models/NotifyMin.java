package kitipoom.moderndiary.models;

/**
 * Created by kitipoom on 19/4/2559.
 */
public class NotifyMin {
    private NotifyTime notifytime;
    private int minute;

    public NotifyMin(int hour ,int minute){
        this.minute=minute;
        this.notifytime=new NotifyTime(hour,minute);
    }

    public int getMinute() {
        return minute;
    }
    public NotifyTime getNotifytime() {
        return notifytime;
    }
}
