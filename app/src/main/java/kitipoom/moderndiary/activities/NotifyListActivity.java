package kitipoom.moderndiary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.models.Datasend;
import kitipoom.moderndiary.models.NotifyTime;
import kitipoom.moderndiary.models.Storage;
import kitipoom.moderndiary.views.NotifyAdapter;

public class NotifyListActivity extends AppCompatActivity {
    private TextView date;
    private int day,month,year;
    private ListView notiListview;
    private List<NotifyTime> notilist;
    private NotifyAdapter notiAdapter;
    private Button saveBtn,deleteBtn;
    private Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponent();

    }
    private void initComponent(){
        setDate();
        date = (TextView) findViewById(R.id.Datetext2);
        date.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.Datetextsizes));
        date.setText(day + "/" + month + "/" + year);

        notilist = new ArrayList<NotifyTime>();
        notiAdapter = new NotifyAdapter(this,R.layout.notify_cell,notilist);
        notiListview = (ListView)findViewById(R.id.listnotify);
        notiListview.setAdapter(notiAdapter);
        notiListview.setOnItemClickListener(new AdapterView.OnItemClickListener() { //important

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Datasend.getInstant().setTime(notilist.get(i).getHour(), notilist.get(i).getMinute());
                Intent intent = new Intent(NotifyListActivity.this, CreateNotify.class);
                startActivity(intent);
            }
        });

        saveBtn = (Button) findViewById(R.id.save_list);
        saveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).setNotilist(true);
                finish();
            }
        });
        deleteBtn= (Button) findViewById(R.id.delete_list);
        deleteBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).setNotilist(false);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNotify);
        fab.setOnClickListener(new View.OnClickListener() {             //important
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                // Datasend.getInstant().setTime(0,0);
                Datasend.getInstant().setTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

                Intent intent = new Intent(NotifyListActivity.this, CreateNotify.class);
                startActivity(intent);
            }
        });
    }
    public void setDate(){
        day= Datasend.getInstant().getDay();
        month=Datasend.getInstant().getMonth();
        year=Datasend.getInstant().getYear();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        notilist.clear();

        for(int i=0;i< Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyList().length;i++){
            if(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i)!=null){
                for(int j=0;j<Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyList().length;j++){
                    if(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyMin(j)!=null){
                        if(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyMin(j).getNotifytime().isTurn()){
                            notilist.add(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyMin(j).getNotifytime());
                        }
                    }
                }
            }


        }
       // NotifyTime a = new NotifyTime(5,11);
        //notilist.add(a);
        notiAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        notilist.clear();

        for(int i=0;i< Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyList().length;i++){
            if(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i)!=null){
                for(int j=0;j<Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyList().length;j++){
                    if(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyMin(j)!=null){
                        if(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyMin(j).getNotifytime().isTurn()){
                            notilist.add(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getNotifyHour(i).getNotifyMin(j).getNotifytime());
                        }
                    }
                }
            }


        }
       /* NotifyTime a = new NotifyTime(5,10);
        notilist.add(a);*/
        notiAdapter.notifyDataSetChanged();


    }
}
