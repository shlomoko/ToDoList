package classpoint.com.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shlomo on 08/03/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {
    private List<String> mList;
    private int mId;
    private int resource;
    public CustomArrayAdapter(Context context, int textViewResourceId, int id, List<String> list)
    {
        super(context, textViewResourceId, id, list);
        mList = list;
        mId = id;
        resource = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, null);
        }
        TextView textView= (TextView) v.findViewById(mId);
        textView.setText(mList.get(position));
        if(position%2 == 0){
            textView.setTextColor(Color.GREEN);
        } else {
            textView.setTextColor(Color.BLUE);
        }
        return v;
    }
}
