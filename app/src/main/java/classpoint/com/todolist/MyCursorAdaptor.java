package classpoint.com.todolist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Shlomo on 01/04/2016.
 */
public class MyCursorAdaptor extends CursorAdapter {
    private LayoutInflater cursorInflater;

    public MyCursorAdaptor(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView= (TextView) view.findViewById(R.id.lstTodoItems);
        String title = cursor.getString(cursor.getColumnIndex(SQLCommandActivity.TITLE));
        textView.setText(title);
        TextView date = (TextView) view.findViewById(R.id.date);
        Long showdate = cursor.getLong(cursor.getColumnIndex(SQLCommandActivity.DATE));
        System.out.println(showdate);
        Date nowDate = new Date(showdate);
        date.setText(nowDate.toString());
        Long currentDate = new Date().getTime();
        //if (mList.get(position).date.getYear() < year || (mList.get(position).date.getYear()== year && mList.get(position).date.getMonth())){
        if(showdate < currentDate){
            textView.setTextColor(Color.RED);
            date.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
            date.setTextColor(Color.BLACK);
        }
    }
}
