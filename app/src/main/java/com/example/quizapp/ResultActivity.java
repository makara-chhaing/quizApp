package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView nameTv, scoreTv;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        nameTv = findViewById(R.id.tv_congret_name_id);
        scoreTv = findViewById(R.id.tv_score_id);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        int score = intent.getIntExtra("score", 0);
        nameTv.setText("Congrats: " + name);
        scoreTv.setText("Score: " + score + "%");
    }
    public void replay(View v){
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
    public void quit(View v){
        finishAffinity();
    }
}