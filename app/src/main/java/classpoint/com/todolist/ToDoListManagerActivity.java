package classpoint.com.todolist;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ToDoListManagerActivity extends AppCompatActivity {
    private ArrayList<CustomItem> mArrayList;
    private CustomArrayAdapter mCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_manager);

        ListView list = (ListView) findViewById(R.id.list);
        mArrayList = new ArrayList<CustomItem>();
        mCustomAdapter = new CustomArrayAdapter(getApplicationContext(), R.layout.list_layout, mArrayList);
        list.setAdapter(mCustomAdapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog = new Dialog(ToDoListManagerActivity.this);
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View bodyView = inflater.inflate(R.layout.dialog_body, null);
                dialog.setTitle(mArrayList.get(position).task);
                if (mArrayList.get(position).task.startsWith("call") || mArrayList.get(position).task.startsWith("Call")){
                    Button textView = (Button) bodyView.findViewById(R.id.call_option);
                    textView.setText(mArrayList.get(position).task);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String del = "[ ]+";
                            String[] tokens = (mArrayList.get(position).task).split(del);
                           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tokens[1]));
                           startActivity(intent);
                        }
                    });
                }
                Button deleteButton = (Button) bodyView.findViewById(R.id.dialog_delete_button);


                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mArrayList.remove(mArrayList.get(position));
                        mCustomAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(bodyView);
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.list_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuItemAdd:
                Intent intent = new Intent(getApplicationContext(), AddNewTodoItemActivity.class);
                startActivityForResult(intent, 01);
                return true;
        }
        return  false;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case 01:
                String title ="";
                Date newDate= new Date();
                if(data.hasExtra("title"))
                {
                    title = data.getStringExtra("title");
                }
                if(data.hasExtra("dueDate"))
                {
                    newDate.setTime(data.getLongExtra("dueDate", -1));
                }
                CustomItem item = new CustomItem(title,newDate);
                if(!item.task.isEmpty()) {
                    mArrayList.add(item);
                }
                mCustomAdapter.notifyDataSetChanged();
                break;

        }
    }


}
