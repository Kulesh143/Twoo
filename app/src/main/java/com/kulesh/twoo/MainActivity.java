package com.kulesh.twoo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kulesh.twoo.database.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//View.OnLongClickListener listener=new View.OnLongClickListener() {
//    @Override
//    public boolean onLongClick(View view) {
//        ((EditText)view).setText("");
//        return true;
//    }
//};
    }



    public void save(View view) {
        String name=((EditText)findViewById(R.id.name)).getText().toString();
        String pass=((EditText)findViewById(R.id.pass)).getText().toString();
        String mobile=((EditText)findViewById(R.id.mobile)).getText().toString();
        SQLiteHelper helper=new SQLiteHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("INSERT INTO user(name,mobile,password)VALUES('"+name+"','"+mobile+"','"+pass+"')");
        ((EditText)findViewById(R.id.name)).setText("");
        ((EditText)findViewById(R.id.mobile)).setText("");
        ((EditText)findViewById(R.id.pass)).setText("");
        Toast.makeText(getApplicationContext(), "Successfully Saved!!!", Toast.LENGTH_SHORT).show();
    }

    public void search(View view) {
        SQLiteHelper helper=new SQLiteHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        String mobile=((EditText)findViewById(R.id.mobile)).getText().toString();
        Cursor data=db.rawQuery("SELECT * FROM user WHERE mobile='"+mobile+"'",null);
        if(data.moveToNext()){
            ((EditText)findViewById(R.id.name)).setText(data.getString(1));
            ((EditText)findViewById(R.id.pass)).setText(data.getString(2));
            Toast.makeText(getApplicationContext(), "Successfully Searched!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "user not found", Toast.LENGTH_SHORT).show();
        }
    }
}