package classpoint.com.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shlomo on 16/03/2016.
 */
public class AddNewTodoItemActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_body);
        final EditText edtNewItem = (EditText)findViewById(R.id.add_text);
        final DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        Button btnOK = (Button)findViewById(R.id.btnOK);
        Intent intent = getIntent();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                setResult(RESULT_OK, result);
                finish();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEditText = edtNewItem.getText().toString();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                Calendar cal = Calendar.getInstance();
                cal.set(year,month,day);
                Date date = cal.getTime();
                Intent result = new Intent();
                result.putExtra("title", strEditText);
                result.putExtra("dueDate", date.getTime());
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
//    String task;
//    Date date;
//    AddNewTodoItemActivity(String t, Date d){
//        task = t;
//        date = d;
//    }
}
