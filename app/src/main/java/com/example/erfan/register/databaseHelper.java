package com.example.erfan.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String Database_name="Register";
    private static final String Table_name="ap_Rec";
    private static final String Name="Name";
    private static final String Email="E_mail";
    private static final String Phone="PhoneNumber";
    private static final int Db_version=1;
    private static final String sql= "CREATE TABLE "+Table_name+" ( "+Name+" VARCHAR(40), "+Email+" VARCHAR(40), "+Phone+" int )";
    private static final String select = "Select * from "+Table_name;
    private Context context;

    public databaseHelper(Context context) {
        super(context, Database_name, null, Db_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        try{
            db.execSQL(sql);
            Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();
            Log.i(null, "onCreate: "+e );

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }

    public long insertData(String name,String email,String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Email,email);
        contentValues.put(Phone,phone);
       long rowID = db.insert(Table_name,null,contentValues);
        return rowID;
    }

    public Cursor displayAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }
}
