package classpoint.com.todolist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ToDoListManagerActivity extends AppCompatActivity {
    //private ArrayList<CustomItem> mArrayList;
    private MyCursorAdaptor mCustomAdapter;
    private SQLCommandActivity list_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_manager);

        ListView list = (ListView) findViewById(R.id.list);
        list_db = SQLCommandActivity.getInstance(getApplicationContext());
        //mArrayList = new ArrayList<CustomItem>();
        mCustomAdapter = new MyCursorAdaptor(getApplicationContext(), list_db.getData(), 0);
        list.setAdapter(mCustomAdapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog = new Dialog(ToDoListManagerActivity.this);
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View bodyView = inflater.inflate(R.layout.dialog_body, null);
                Cursor cursor = (mCustomAdapter).getCursor();
                cursor.moveToPosition(position);
                final String title = cursor.getString(cursor.getColumnIndex(SQLCommandActivity.TITLE));
                dialog.setTitle(title);
                if (title.toLowerCase().startsWith("call")){
                    Button textView = (Button) bodyView.findViewById(R.id.call_option);
                    textView.setText(title);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String del = "[ ]+";
                            String[] tokens = (title).split(del);
                           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tokens[1]));
                           startActivity(intent);
                        }
                    });
                }
                Button deleteButton = (Button) bodyView.findViewById(R.id.dialog_delete_button);


                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        mArrayList.remove(mArrayList.get(position));
                        Cursor cursor = (mCustomAdapter).getCursor();
                        cursor.moveToPosition(position);
                        int todelete = cursor.getInt(cursor.getColumnIndex(SQLCommandActivity.ID));
                        list_db.delete(todelete);
                        mCustomAdapter.changeCursor(list_db.getData());
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
                Long newDate = Long.valueOf(0);
                if(data.hasExtra("title"))
                {
                    title = data.getStringExtra("title");
                }
                if(data.hasExtra("dueDate"))
                {
                    newDate= data.getLongExtra("dueDate", 0);
                }
                //CustomItem item = new CustomItem(title,newDate);
                list_db.insertToDo(title,newDate);
//                if(!item.task.isEmpty()) {
//                    mArrayList.add(item);
//                }
                mCustomAdapter.changeCursor(list_db.getData());
                mCustomAdapter.notifyDataSetChanged();
                break;
        }
        list_db.close();
    }
    @Override
    protected void onDestroy() {
        list_db.close();
        super.onDestroy();
    }

}
