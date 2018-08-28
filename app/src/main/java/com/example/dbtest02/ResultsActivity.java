package com.example.dbtest02;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dbtest02.models.AnswerContract;
import com.example.dbtest02.models.AnswerDbHelper;
import com.example.dbtest02.models.AnswerRow;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private List<AnswerRow> answerRows = new ArrayList<AnswerRow>();
    private RecyclerView recyclerView;
    private AnswersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recyclerView);

        mAdapter = new AnswersAdapter(answerRows);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //seedAnswerRowsList();
        getAnswerRows();
    }

    private void seedAnswerRowsList() {
        AnswerRow answerRow = new AnswerRow();
        answerRow.addAnswer(1);
        answerRow.addAnswer(2);
        answerRows.add(answerRow);

        answerRow = new AnswerRow();
        answerRow.addAnswer(1);
        answerRow.addAnswer(1);
        answerRows.add(answerRow);

        answerRow = new AnswerRow();
        answerRow.addAnswer(2);
        answerRow.addAnswer(0);
        answerRows.add(answerRow);

        answerRow = new AnswerRow();
        answerRow.addAnswer(0);
        answerRow.addAnswer(0);
        answerRows.add(answerRow);

        mAdapter.notifyDataSetChanged();
    }

    private void getAnswerRows() {
        AnswerDbHelper mDbHelper = new AnswerDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        AnswerRow answerRow;

        String query = "SELECT * FROM ANSWERS";
        Cursor cursor = null;


        try {
            cursor = db.rawQuery(query, null);

            while(cursor.moveToNext()) {
                // fill in the arraylist please
                answerRow = new AnswerRow();
                answerRow.addAnswer(cursor.getInt(cursor.getColumnIndex(AnswerContract.AnswerEntry.COLUMN_NAME_Q1)));
                answerRow.addAnswer(cursor.getInt(cursor.getColumnIndex(AnswerContract.AnswerEntry.COLUMN_NAME_Q2)));
                answerRows.add(answerRow);
            }
            cursor.close();
        } finally {
            //cursor.close();
        }

        mAdapter.notifyDataSetChanged();
    }
}
