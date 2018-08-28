package com.example.dbtest02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Button btnPageFinish = findViewById(R.id.button_next1);

        btnPageFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveQ2Answer(answer);
                nextPage();
            }
        });
    }

    public void nextPage() {
        Intent intent = new Intent(Page2Activity.this, FinishActivity.class);
        startActivity(intent);
    }

}
