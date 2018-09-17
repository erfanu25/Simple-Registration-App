package com.example.erfan.register;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    databaseHelper database;

    private EditText name,email,phone;
    private Button register,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new databaseHelper(this);
        SQLiteDatabase db = database.getWritableDatabase();


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        register = findViewById(R.id.regbutton);
        view = findViewById(R.id.viewbutton);



        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String uphone = phone.getText().toString();


                if(uname.length()==0)

                {
                    name.requestFocus();
                    name.setError("FIELD CANNOT BE EMPTY");
                }

                else if(!uname.matches("[a-zA-Z ]+"))
                {
                    name.requestFocus();
                    name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(uemail.length()==0)

                {
                    email.requestFocus();
                    email.setError("FIELD CANNOT BE EMPTY");
                }

                else if(!uemail.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"))
                {
                    email.requestFocus();
                    email.setError("ENTER Correct Email");
                }
                else if(uphone.length()==0)

                {
                    phone.requestFocus();
                    phone.setError("FIELD CANNOT BE EMPTY");
                }
                else
                {

                    long rowID= database.insertData(uname,uemail,uphone);


                    if(rowID>0)
                    {
                        Toast.makeText(getApplicationContext(), "Successfully Your data stored", Toast.LENGTH_LONG).show();
                    }
                }







            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Cursor resultList = database.displayAll();

             if(resultList.getCount()==0)
             {
                 showData("Error","No data Found");
             }
             else
             {
                 StringBuffer stringBuffer = new StringBuffer();
                 while(resultList.moveToNext())
                 {
                     stringBuffer.append("Name : "+resultList.getString(0)+"\n");
                     stringBuffer.append("Email : "+resultList.getString(1)+"\n");
                     stringBuffer.append("Phone : "+resultList.getString(2)+"\n\n\n");
                 }
                 showData("Resultset",stringBuffer.toString());
             }

            }



        });

    }
    public  void showData(String title,String data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();

    }
}
