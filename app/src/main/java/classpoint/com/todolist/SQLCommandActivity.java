package classpoint.com.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLCommandActivity extends SQLiteOpenHelper{
    private static SQLCommandActivity singletonDB;
    public static final String TABLE_NAME = "todo";
    public static final String ID = "_id";
    public static final String TITLE = "name";
    public static final String DATE = "date";


    private SQLCommandActivity(Context context){
        super(context, "todo_db",null,1);
    }


    public static SQLCommandActivity getInstance(Context context){
        if(singletonDB==null){
            singletonDB = new SQLCommandActivity(context);
        }
        return singletonDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                ID + " integer primary key autoincrement, " +
                DATE + " string, " + TITLE + " string );" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertToDo(String name, Long date)
    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(TITLE, name);
//        Calendar dueDate = Calendar.getInstance();
//        dueDate.set(year, month, day);
//        contentValues.put(DATE, dueDate.getTime().toString());
//        db.insert(TABLE_NAME, null, contentValues);
//        return true;
//
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, name);
        Long dueDate;
        dueDate = date;
        contentValues.put(DATE, dueDate);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from "+TABLE_NAME, null );
    }

    public boolean delete(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=" + Integer.toString(id), null) > 0;
    }

}
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.Calendar;
//
///**
// * Created by Shlomo on 01/04/2016.
// */
//public class SQLCommandActivity extends SQLiteOpenHelper{
//    public static final String TABLE_NAME = "TodoList";
//    public static final String ID = "id";
//    public static final String TITLE = "title";
//    public static final String DATE = "date";
//    private static SQLCommandActivity singletonDB;
//
//    private SQLCommandActivity(Context context){
//            super(context, "todo_db" ,null,1);
//    }
//
//    public static SQLCommandActivity getInstance(Context context){
//        if(singletonDB==null){
//            singletonDB = new SQLCommandActivity(context);
//        }
//        return singletonDB;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
//                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                TITLE + " string, " + DATE + " INTEGER );");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//    public boolean insertToDo(String name, Long date)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(TITLE, name);
//        Calendar dueDate = Calendar.getInstance();
//        dueDate.setTimeInMillis(date);
//        contentValues.put(DATE, dueDate.getTime().toString());
//        db.insert(TABLE_NAME, null, contentValues);
//        return true;
//    }
//
//    public Cursor getData(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery( "select * from "+TABLE_NAME, null );
//    }
//
//    public boolean delete(Integer id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, ID + "=" + Integer.toString(id), null) > 0;
//    }
//}
