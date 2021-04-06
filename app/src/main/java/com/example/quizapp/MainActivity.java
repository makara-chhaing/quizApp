package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameEt;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.et_name_id);

        Intent data = getIntent();
        if (data!= null) {
            name = data.getStringExtra("name");
            if(name != null){
                nameEt.setText(name);
            }
        }

    }
    public void startQuiz(View v){
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        if(!nameEt.getText().toString().isEmpty()) {
            intent.putExtra("name", nameEt.getText().toString());
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(MainActivity.this, "Name field cannot be empty!", Toast.LENGTH_LONG).show();
        }

    }


}