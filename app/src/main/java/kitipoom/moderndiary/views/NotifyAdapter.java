package kitipoom.moderndiary.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.models.NotifyTime;

/**
 * Created by kitipoom on 20/4/2559.
 */
public class NotifyAdapter extends ArrayAdapter<NotifyTime> {

    public NotifyAdapter(Context context, int resource, List<NotifyTime> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =convertView;
        if(v==null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.notify_cell,null);
        }
        TextView timeshow =(TextView)v.findViewById(R.id.timeshow);

        NotifyTime noti = getItem(position);
        String min =noti. getMinute()+"";
        if(noti. getMinute()<10) min = "0"+min;
        if(noti.isNotify()){
            //timeshow.setText(noti.getHour()+" : "+noti. getMinute()+ "  "+noti.getTitle()+" ON");
            timeshow.setText(String.format("%2d : %s %s ON",noti.getHour(),min,noti.getTitle()));
        }
        else{
            //timeshow.setText(noti.getHour()+" : "+noti. getMinute()+ "  "+noti.getTitle()+" OFF");
            timeshow.setText(String.format("%2d : %s %s OFF",noti.getHour(),min,noti.getTitle()));
        }


        return v;
    }
}
