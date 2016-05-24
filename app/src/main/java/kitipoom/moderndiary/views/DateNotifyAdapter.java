package kitipoom.moderndiary.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.activities.DayNotifyListActivity;
import kitipoom.moderndiary.models.DateDay;
import kitipoom.moderndiary.models.Diary;

/**
 * Created by kitipoom on 24/4/2559.
 */
public class DateNotifyAdapter extends ArrayAdapter<DateDay> {

    public DateNotifyAdapter(Context context, int resource, List<DateDay> objects) {
        super(context, resource, objects);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View v =convertView;
        if(v==null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.datenoti_cell,null);
        }
        TextView dateshow =(TextView) v.findViewById(R.id.Dateshow);

        DateDay dae = getItem(position);
        dateshow.setText(dae.getDay()+"/"+dae.getMonth()+"/"+dae.getYear());
        return v;
    }
}
