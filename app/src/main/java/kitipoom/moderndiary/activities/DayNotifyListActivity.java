package kitipoom.moderndiary.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.models.Datasend;
import kitipoom.moderndiary.models.DateDay;
import kitipoom.moderndiary.models.Storage;
import kitipoom.moderndiary.views.DateNotifyAdapter;

public class DayNotifyListActivity extends AppCompatActivity {
    private List<DateDay> daelist;
    private DateNotifyAdapter daenotiadapter;
    private ListView notiListView;
    private Button search_Btm;
    private EditText yearEdit,monthEdit,dayEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_notify_list);
        initcomponent();

    }
    public void initcomponent(){
        daelist = new ArrayList<DateDay>();
        daenotiadapter = new DateNotifyAdapter(this,R.layout.datenoti_cell,daelist);
        notiListView = (ListView) findViewById(R.id.listdatenotify);
        notiListView.setAdapter(daenotiadapter);
       notiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Datasend.getInstant().setDate(daelist.get(i).getDay(), daelist.get(i).getMonth(), daelist.get(i).getYear());
                Storage.getSt().saveDate(daelist.get(i).getDay(), daelist.get(i).getMonth(), daelist.get(i).getYear());
                Intent intent = new Intent(DayNotifyListActivity.this, NotifyListActivity.class);
                startActivity(intent);
            }
        });
        dayEdit = (EditText) findViewById(R.id.notify_day_search);
        monthEdit=(EditText) findViewById(R.id.notify_month_search);
        yearEdit=(EditText) findViewById(R.id.notify_year_search);
        search_Btm=(Button)findViewById(R.id.search_notifylist);
        search_Btm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!dayEdit.getText().toString().equals("")){
                    int d =Integer.parseInt(dayEdit.getText().toString());
                    if(d<=0||d>31){
                        Snackbar.make(v, "Wrong day", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else {
                        if(!monthEdit.getText().toString().equals("")){
                            int m =Integer.parseInt(monthEdit.getText().toString());
                            if(m<=0||m>12){
                                Snackbar.make(v, "Wrong month", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                            else{
                                if (!yearEdit.getText().toString().equals("")){
                                    int y = Integer.parseInt(yearEdit.getText().toString());
                                    if(y<0){
                                        Snackbar.make(v, "Wrong year", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                    else {
                                        Datasend.getInstant().setDate(d,m,y);
                                        Storage.getSt().saveDate(d, m, y);
                                        Intent intent = new Intent(DayNotifyListActivity.this, NotifyListActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        daelist.clear();
        for (int i=0;i< Storage.getSt().getDateList().size();i++){
            if(Storage.getSt().getDateList().get(i).getDateList()!=null) {
                for (int j = 0; j < Storage.getSt().getDateList().get(i).getDateList().length; j++) {
                    if(Storage.getSt().getDateList().get(i).getDateMonth(j)!=null) {
                        for (int k = 0; k < Storage.getSt().getDateList().get(i).getDateMonth(j).getDateList().length; k++) {
                            if(Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k)!=null) {
                                boolean d = Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k).haveNotilist();
                                if (d) {
                                    daelist.add(Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k));
                                }
                            }
                        }
                    }
                }
            }
        }
        daenotiadapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        daelist.clear();
        for (int i=0;i< Storage.getSt().getDateList().size();i++){
            if(Storage.getSt().getDateList().get(i).getDateList()!=null) {
                for (int j = 0; j < Storage.getSt().getDateList().get(i).getDateList().length; j++) {
                    if(Storage.getSt().getDateList().get(i).getDateMonth(j)!=null) {
                        for (int k = 0; k < Storage.getSt().getDateList().get(i).getDateMonth(j).getDateList().length; k++) {
                            if(Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k)!=null) {
                                boolean d = Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k).haveNotilist();
                                if (d) {
                                    daelist.add(Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k));
                                }
                            }
                        }
                    }
                }
            }
        }
        daenotiadapter.notifyDataSetChanged();
    }
}

