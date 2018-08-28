package com.example.dbtest02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dbtest02.models.AnswerContract;
import com.example.dbtest02.models.AnswerDbHelper;

public class MainActivity extends AppCompatActivity {

    // https://android.jlelse.eu/architecture-patterns-in-android-abf99f2b6f70
    // https://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.button_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnswerDbHelper mDbHelper = new AnswerDbHelper(MainActivity.this);
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(AnswerContract.AnswerEntry.COLUMN_NAME_DEBUG, 1);

                long newRowId = db.insert(AnswerContract.AnswerEntry.TABLE_NAME, null, values);

                mDbHelper.close();

                startPage1();
            }
        });
    }

    private void startPage1() {
        Intent intent = new Intent(MainActivity.this, Page1Activity.class);
        startActivity(intent);
    }
}
