package com.example.todo.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class My_Adapter {

    public static final String DATABASE_NAME = "MY SQL Data Base";


    public static final String TABLE_NAME = "TODO_TABLE";


    public static final int DB_VERSION = 1;


    public static final String COL_ROW = "SerialNo";
    public static final String COL_TITLE = "Title";
    public static final String COL_DISCRIPTION = "Discription";
    public static final String COL_DATE = "Date";


    String createDB = "CREATE TABLE " + TABLE_NAME + " ( "+COL_ROW+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_TITLE+" text, "+COL_DISCRIPTION+" text, "+COL_DATE+" text"+")";


    private SQLiteDatabase sqLiteDatabase;
    private My_DataBase myDatabase;
    public My_Adapter(Context context){
        myDatabase = new My_DataBase(context);

    }
    public My_Adapter openDatabase() {
        sqLiteDatabase = myDatabase.getWritableDatabase();
        return this;
    }

    public Cursor getAllRecords(){
        String[] COLUMNS = {COL_ROW, COL_TITLE, COL_DISCRIPTION, COL_DATE};
        return sqLiteDatabase.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
    }

    public void insertRecord(Context context, String title, String discription, String date){
        String msg;
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_DISCRIPTION, discription);
        values.put(COL_DATE, date);

        long insert_check = sqLiteDatabase.insert(TABLE_NAME, null, values);
        if (insert_check == -1){
            msg = "Insertion failed";
        }else {
            msg = "inserted Successfully.";

        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public void deleteRecord(String colRow, Context context){
        int id = sqLiteDatabase.delete(TABLE_NAME, COL_ROW+" = "+colRow, null);
        if (id > 0){
            Toast.makeText(context, id+" record deleted.", Toast.LENGTH_SHORT).show();
        }else {
//            Utility.showToast(context, " Something went wrong.");
            Toast.makeText(context, id+"Something went wrong.", Toast.LENGTH_SHORT).show();

        }
    }

    public void updateRecord(Context context , String rowId, String title, String discription, String date){
        String localMsg;
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_DISCRIPTION, discription);
        values.put(COL_DATE, date);


        int updateRecord = sqLiteDatabase.update(TABLE_NAME, values, COL_ROW+" = "+rowId, null);
        if (updateRecord == -1){
            localMsg = "Updation failed";
        }else {
            localMsg = "updated Successfully.";
        }
        Toast.makeText(context, localMsg, Toast.LENGTH_SHORT).show();
    }

    public void delete_All(Context context){
        int delete_check=sqLiteDatabase.delete(TABLE_NAME,null,null);
        if(delete_check>0)
            Toast.makeText(context, "Delete Sucessfull", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

    }
    public class My_DataBase extends SQLiteOpenHelper{


        public My_DataBase(@Nullable Context context) {
            super(context,DATABASE_NAME,null,DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(createDB);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
