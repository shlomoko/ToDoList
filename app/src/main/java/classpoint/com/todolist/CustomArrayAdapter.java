package classpoint.com.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by Shlomo on 08/03/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<CustomItem> {
    private List<CustomItem> mList;
    private int mId;
    private int resource;
    public CustomArrayAdapter(Context context, int textViewResourceId, List<CustomItem> list)
    {
        super(context, textViewResourceId, list);
        mList = list;
        resource = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, null);
        }
        TextView textView= (TextView) v.findViewById(R.id.lstTodoItems);
        textView.setText(mList.get(position).task);
        TextView date = (TextView) v.findViewById(R.id.date);
        date.setText(mList.get(position).date.toString());
        Date currentDate = new Date();
        //if (mList.get(position).date.getYear() < year || (mList.get(position).date.getYear()== year && mList.get(position).date.getMonth())){
        if(mList.get(position).date.before(currentDate)){
            textView.setTextColor(Color.RED);
            date.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
            date.setTextColor(Color.BLACK);
        }

        return v;
    }
}
