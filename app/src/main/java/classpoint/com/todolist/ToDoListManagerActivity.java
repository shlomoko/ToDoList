package classpoint.com.todolist;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoListManagerActivity extends AppCompatActivity {
    private ArrayList<String> mArrayList;
    private CustomArrayAdapter mCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_manager);
        //String[] todo = getResources().getStringArray(R.array.todo);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,todo );

        ListView list = (ListView) findViewById(R.id.list);
        mArrayList = new ArrayList<String>();
        mCustomAdapter = new CustomArrayAdapter(getApplicationContext(), R.layout.list_layout, R.id.lstTodoItems, mArrayList);
        list.setAdapter(mCustomAdapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog = new Dialog(ToDoListManagerActivity.this);
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View bodyView = inflater.inflate(R.layout.dialog_body, null);
                dialog.setTitle(mArrayList.get(position));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuItemAdd:
                addItem();
                mCustomAdapter.notifyDataSetChanged();
                return true;
        }
        return  false;
    }

    private void addItem(){
        EditText newToDo= (EditText)findViewById(R.id.edtNewItem);
        String mText = newToDo.getText().toString();
        if (!mText.isEmpty()) {
            mArrayList.add(mText);
        }
        newToDo.setText("");
    }

}
