package com.example.dbtest02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbtest02.models.AnswerContract;
import com.example.dbtest02.models.AnswerDbHelper;

public class Page1Activity extends AppCompatActivity {

    private int answer = 0;
    private int lastCheckedId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        RadioGroup radioGroup = findViewById(R.id.radioGroup_q_1);

//        RadioButton checkedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

        final TextView textViewDebug = findViewById(R.id.textView_debug);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton checkedRadioButton = radioGroup.findViewById(id);

                boolean isChecked = checkedRadioButton.isChecked();

                if (isChecked) {
                    String entry[] = getResources().getResourceEntryName(id).split("-");
                    answer = Integer.parseInt(entry[1]);

                    if(lastCheckedId == checkedRadioButton.getId()) {
                        Toast.makeText(Page1Activity.this, "lastCheckedId == id", Toast.LENGTH_SHORT).show();
                    } else {
                        lastCheckedId = id;
                    }

                    String text = "CRB1: " + id +
                            "\nName: " + checkedRadioButton.getText() +
                            "\nResourceEN: " + getResources().getResourceEntryName(id) +
                            "\nResourceN: " + getResources().getResourceName(id) +
                            "\nAnswer: " + answer +
                            "\nLastId: " + lastCheckedId;
                    textViewDebug.setText(text);

                    //Toast.makeText(Page1Activity.this, "isChecked", Toast.LENGTH_SHORT).show();
                }
                //radioGroup.clearCheck();
                //checkedRadioButton.setChecked(false);
            }
        });

        /*AnswerDbHelper mDbHelper = new AnswerDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AnswerContract.AnswerEntry.COLUMN_NAME_Q1, 0);
        values.put(AnswerContract.AnswerEntry.COLUMN_NAME_Q2, 1);

        long newRowId = db.insert(AnswerContract.AnswerEntry.TABLE_NAME, null, values);

        mDbHelper.close();*/

        Button btnPage2 = findViewById(R.id.button_next1);

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveQ1Answer(answer);
                nextPage();
            }
        });
    }

    public void nextPage() {
        Intent intent = new Intent(Page1Activity.this, Page2Activity.class);
        startActivity(intent);
    }

    public void saveQ1Answer(int answer) {
        AnswerDbHelper mDbHelper = new AnswerDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AnswerContract.AnswerEntry.COLUMN_NAME_Q1, answer);

        //long newRowId = db.insert(AnswerContract.AnswerEntry.TABLE_NAME, null, values);

        int lastRowId = -1;

        //String query = "SELECT _id FROM answers WHERE _id = (SELECT MAX(_id) FROM answers)";
        //String query = "SELECT * FROM answers WHERE _id = (SELECT max(_id) FROM answers) order by _id desc limit 1";
        String query = "SELECT * FROM answers ORDER BY _id DESC LIMIT 1";
        Cursor cursor = null;
        try {
            cursor = mDbHelper.getReadableDatabase().rawQuery(query, null);
            cursor.moveToFirst();
            lastRowId = cursor.getInt(cursor.getColumnIndex(AnswerContract.AnswerEntry._ID));
            /*while (cursor.moveToNext()) {
                lastRowId = cursor.getColumnIndex(AnswerContract.AnswerEntry._ID);
                //Toast.makeText(this, "Last Row = " + cursor.getColumnIndex(AnswerContract.AnswerEntry.COLUMN_NAME_DEBUG), Toast.LENGTH_SHORT).show();
            }*/
        } finally {
            cursor.close();
        }

        Toast.makeText(this, "Last Row = " + lastRowId, Toast.LENGTH_SHORT).show();

        db.update(AnswerContract.AnswerEntry.TABLE_NAME, values, "_id=" + lastRowId, null);

        mDbHelper.close();
    }
}
