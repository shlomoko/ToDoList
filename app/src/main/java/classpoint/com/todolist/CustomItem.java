package classpoint.com.todolist;

import android.widget.EditText;

import java.util.Date;

/**
 * Created by Shlomo on 16/03/2016.
 */
public class CustomItem {
    String task;
    Date date;

    CustomItem(String t,Date d){
        date = d;
        task = t;
    }
}
