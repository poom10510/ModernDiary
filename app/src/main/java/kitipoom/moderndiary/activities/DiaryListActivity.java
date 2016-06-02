package kitipoom.moderndiary.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import kitipoom.moderndiary.models.Diary;
import kitipoom.moderndiary.models.Storage;
import kitipoom.moderndiary.views.DateAdapter;

public class DiaryListActivity extends AppCompatActivity {
    private List<Diary> daelist;
    private DateAdapter daeadapter;
    private ListView diaryListView;
    private Button search_Btm;
    private EditText yearEdit,monthEdit,dayEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        initcomponent();

    }
    public void initcomponent(){
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        daelist = new ArrayList<Diary>();
        daeadapter = new DateAdapter(this,R.layout.diary_cell,daelist);
        diaryListView = (ListView) findViewById(R.id.listdatediary);
        diaryListView.setAdapter(daeadapter);
        diaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Datasend.getInstant().setDate(daelist.get(i).getDay(), daelist.get(i).getMonth(), daelist.get(i).getYear());
                Intent intent = new Intent(DiaryListActivity.this, DateActivity.class);
                startActivity(intent);
            }
        });
        dayEdit = (EditText) findViewById(R.id.diary_day_search);
        monthEdit=(EditText) findViewById(R.id.diary_month_search);
        yearEdit=(EditText) findViewById(R.id.diary_year_search);
        search_Btm=(Button)findViewById(R.id.search_diarylist);
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
                                        Intent intent = new Intent(DiaryListActivity.this, DateActivity.class);
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
                                boolean d = Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k).getDiary().isOpen();
                                if (d) {
                                    daelist.add(Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k).getDiary());
                                }
                            }
                        }
                    }
                }
            }
        }
        daeadapter.notifyDataSetChanged();
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
                                boolean d = Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k).getDiary().isOpen();
                                if (d) {
                                    daelist.add(Storage.getSt().getDateList().get(i).getDateMonth(j).getDateDay(k).getDiary());
                                }
                            }
                        }
                    }
                }
            }
        }
        daeadapter.notifyDataSetChanged();
    }
}
