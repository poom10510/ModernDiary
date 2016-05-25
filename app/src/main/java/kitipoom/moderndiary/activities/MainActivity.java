package kitipoom.moderndiary.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.models.Datasend;
import kitipoom.moderndiary.models.Storage;
import kitipoom.moderndiary.models.Threadruntime;

public class MainActivity extends AppCompatActivity {
    private TextView textcount;
    private  Datasend data=Datasend.getInstant();
    private CalendarView calend;
    private Calendar calendar = Calendar.getInstance();
    private Threadruntime run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponent();


    }

    private void initComponent(){
        textcount = (TextView) findViewById(R.id.Textcount);
        textcount.setText(calendar.getTime() + "");
        Datasend.getInstant().setDate(calendar.get(Calendar.DAY_OF_MONTH), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.YEAR));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DateActivity.class);
                startActivity(intent);
            }
        });
        calend = (CalendarView) findViewById(R.id.calendView);

        calend.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                Datasend.getInstant().setDate(dayOfMonth, (month + 1), year);
                textcount.setText(calendar.getTime() + " " + dayOfMonth + " / " + (month + 1)
                        + " / " + year);
            }
        });

        run = new Threadruntime(this);
        run.start();


    }

    public void updatetime(){
        calendar= Calendar.getInstance();
        int ny,nm,nd,nhour,nmin;
        String title,text;
        nd = calendar.get(Calendar.DAY_OF_MONTH);
        nm = calendar.get(Calendar.MONTH)+1;
        ny = calendar.get(Calendar.YEAR);
        nhour = calendar.get(Calendar.HOUR_OF_DAY);
        nmin = calendar.get(Calendar.MINUTE);
        for(int i =0; i< Storage.getSt().getDateList().size();i++){
            if(ny==Storage.getSt().getDateList().get(i).getYear()){
                textcount.setText(calendar.getTime() + "yp"+nm);
                if(Storage.getSt().getDateList().get(i).getDateMonth(nm)!=null){
                    textcount.setText(textcount.getText() + "mp");
                    if(Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd)!=null) {
                        textcount.setText(textcount.getText() + "dp");
                        if(Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).haveNotilist()) {
                            textcount.setText(textcount.getText() + "is mp");
                            if (Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).getNotifyHour(nhour) != null) {
                                textcount.setText(textcount.getText() + "hp");
                                if (Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).getNotifyHour(nhour).getNotifyMin(nmin) != null) {
                                    textcount.setText(textcount.getText() + "minp");
                                    if (Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).getNotifyHour(nhour).getNotifyMin(nmin).getNotifytime().isNotify()) {
                                        textcount.setText(textcount.getText() + "ok");
                                        title = Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).getNotifyHour(nhour).getNotifyMin(nmin).getNotifytime().getTitle();
                                        text = Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).getNotifyHour(nhour).getNotifyMin(nmin).getNotifytime().getText();

                                        showNotify(title, text);
                                        Storage.getSt().getDateList().get(i).getDateMonth(nm).getDateDay(nd).getNotifyHour(nhour).getNotifyMin(nmin).getNotifytime().close();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private void showNotify(String title,String text) {
        String head = title;
        String msg = text;
        final NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        final Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        final NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.noti_icon)
                .setContentTitle(head)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg).setLights(Color.GREEN, 300, 300)
                .setVibrate(new long[] { 100, 250 })
                .setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
                .setSound(soundUri)
                .setVisibility(Notification.VISIBILITY_PUBLIC);//special

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(new Random().nextInt(), mBuilder.build());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.DiaryDateList) {
            Intent intent = new Intent(MainActivity.this, DiaryListActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.NotifyDateList) {
            Intent intent = new Intent(MainActivity.this, DayNotifyListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Datasend.getInstant().setDate(calendar.get(Calendar.DAY_OF_MONTH), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.YEAR));
    }
}
