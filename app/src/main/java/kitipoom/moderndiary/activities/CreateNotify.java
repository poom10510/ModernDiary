package kitipoom.moderndiary.activities;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.models.Datasend;
import kitipoom.moderndiary.models.Storage;

public class CreateNotify extends AppCompatActivity {

    private Button confirmBtn,checkBtn,deleteBtn,notiBtn;
    private EditText Timehour,Timemin,Title,Text;
    public int hour,min;
    private int day,month,year;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notify);
        initComponent();
    }
    private void initComponent(){
        setTime();
        setDate();
        check=false;
        Title = (EditText) findViewById(R.id.Notifytitle);
        Text= (EditText) findViewById(R.id.Notifytext);
        //loadNotify();

        Timehour = (EditText) findViewById(R.id.timeHouradd);
        Timemin = (EditText) findViewById(R.id.timeMinuteadd);

        Timehour.setText(hour+"");
        Timemin.setText(min+"");

        checkBtn = (Button) findViewById(R.id.check_time);
        checkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!Timehour.getText().toString().equals("")) {
                    int h = Integer.parseInt(Timehour.getText().toString());
                    if (h < 0 || h >= 24) {
                        Snackbar.make(v, "Wrong Hour", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        if (!Timemin.getText().toString().equals("")) {
                            int m = Integer.parseInt(Timemin.getText().toString());
                            if (m < 0 || m >= 60) {
                                Snackbar.make(v, "Wrong Minute", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            } else {
                                Datasend.getInstant().setTime(h, m);
                                setTime();
                                Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).addHour(h,m);
                                loadNotify();
                                setStatus();
                                check=true;
                            }
                        }
                    }
                }

            }
        });

        confirmBtn = (Button)findViewById(R.id.confirm_time_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(!TimeTitle.getText().toString().equals("")) {

                }*/
               /* Storage.getSt().getDateyear().getDateMounth(mounth).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().setTitle(Title.getText().toString());
                Storage.getSt().getDateyear().getDateMounth(mounth).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().setText(Text.getText().toString());*/
                if(check) {
                    Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().confirmopen(Title.getText().toString(), Text.getText().toString());
                    finish();
                }
                else{
                    Snackbar.make(v, "Please check", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        deleteBtn = (Button) findViewById(R.id.delete_time_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(check) {
                    Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().close();
                    finish();
                }
                else{
                    Snackbar.make(v, "Please check", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        notiBtn = (Button) findViewById(R.id.notify_time_btn);
        notiBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(check) {
                    Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().changeNotify();
                    setStatus();
                }
                else{
                    Snackbar.make(v, "Please check", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }
    public void setStatus(){
        boolean no = Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().isNotify();
        if(no){
            notiBtn.setText("Notify: on");
        }
        else{
            notiBtn.setText("Notify: off");
        }
        return;
    }
    public void loadNotify(){
        Title.setText( Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().getTitle());
        Text.setText( Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(hour).getNotifyMin(min).getNotifytime().getText());
    }
    public void setTime(){
        hour=Datasend.getInstant().getHour();
        min=Datasend.getInstant().getMinute();
    }
    public void setDate(){
        day= Datasend.getInstant().getDay();
        month=Datasend.getInstant().getMonth();
        year=Datasend.getInstant().getYear();
    }

}
