package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView question, progressText, welcomeText;
    Button firstAnswer, secondAnswer, thirdAnswer, submit, selected;
    Button[] btnGroup;
    int questionNumber = 0, progress = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        welcomeText = findViewById(R.id.welcome_tv_id);
        progressBar = findViewById(R.id.pb_progressbar_id);
        progressText = findViewById(R.id.tv_progress_id);
        question = findViewById(R.id.tv_question_id);
        firstAnswer = findViewById(R.id.btn_1st_answer_id);
        secondAnswer = findViewById(R.id.btn_2nd_answer_id);
        thirdAnswer = findViewById(R.id.btn_3rd_answer_id);
        submit = findViewById(R.id.btn_submit);


        Intent i = getIntent();
        if (i != null)
        {
            String name = i.getStringExtra("name");
            if (!name.isEmpty() && progress == 0){
                welcomeText.setText("Welcome: " + name + "!");
            }
        }


        btnGroup = new Button[]{
                firstAnswer,
                secondAnswer,
                thirdAnswer
        };

        question.setText(Quiz.question[0]);
        firstAnswer.setText(Quiz.answer1[0]);
        secondAnswer.setText(Quiz.answer2[0]);
        thirdAnswer.setText(Quiz.answer3[0]);
        progressBar.setProgress(progress);

        progressText.setText((questionNumber+1) + "/5");
    }

    // renders a set of question and answers.
    public void renderQuiz(){
        for(int i = 0; i < 5; i++){
            if(questionNumber == i){
                question.setText(Quiz.question[i]);
                firstAnswer.setText(Quiz.answer1[i]);
                secondAnswer.setText(Quiz.answer2[i]);
                thirdAnswer.setText(Quiz.answer3[i]);
            }
        }
    }

    // Highlights only one button that is selected.
    public void highlightSelection(View v){
        // A condition to test if answer is clicked when showing the result.
        if(submit.getText().toString().equals("Next Question")) {
            return;
        }
        selected = (Button) v;
        for (Button b : btnGroup ){
            // Making sure that only one button highlight in green.
            if (selected == b){
                selected.setBackgroundColor(Color.parseColor("#d6b84d"));
            }
            // Other buttons are in grey.
            else{
                b.setBackgroundColor(Color.parseColor("#FF8F8E8E"));
            }
        }

    }

    // Check if the given button contains the correct answer.
    // By getting the string from that button and compare with correct answer in the quiz file.
    private boolean checkAnswer(Button b){
        if(b.getText().toString().equals(Quiz.correct_answer[questionNumber])){
            return true;
        }
        return false;
    }

    // check if the button that is click is the correct answer.
    public void submitAnswer(View v){
        // check if the answer is not chosen.
        if (selected == null){
            Toast.makeText(this, "No answer chose!", Toast.LENGTH_LONG).show();
            return;
        }
        // check for the button click.
        boolean isCorrect = checkAnswer(selected);

        // if it is correct then label in green and add 20 to score.
        if (isCorrect){
            selected.setBackgroundColor(Color.parseColor("#8db074"));
            score += 20;
        }
        // if it is no then highlight it with red, and highlight the correct answer.
        else{
            // sets the selected button background to red if it is wrong.
            selected.setBackgroundColor(Color.parseColor("#e32d55"));

            // finds the correct answer and highlights in green.
            for(Button b : btnGroup){
                if(checkAnswer(b)){
                    b.setBackgroundColor(Color.parseColor("#8db074"));
                }
            }
        }

        // if the progress is less tha 80%
        if (progress < 80) {
            progress += 20;
            progressBar.setProgress(progress);
            ((Button) v).setText("Next Question");
        }
        // if the progress is over 80%
        else{
            progress += 20;
            progressBar.setProgress(progress);
            ((Button) v).setText("See Reseult");
        }
        // Clear welcome text
        if(progress > 0){
            welcomeText.setText("");
        }
    }

    public void NextQuestion(View v){
        // if the answer is not checked yet then check it, otherwise move on to the next question.
        if (((Button) v).getText().toString().toLowerCase().equals("submit")){
            submitAnswer(v);
            return;
        }
        // set all button to grey color.
        for(Button b : btnGroup){
            b.setBackgroundColor(Color.parseColor("#FF8F8E8E"));
        }
        // if the progress is over 100 then shows result.
        if(progress >= 100){
            Intent mintent = getIntent();
            if(mintent == null) startActivity( new Intent(this, ResultActivity.class));
            String name = mintent.getStringExtra("name");
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
        // otherwise, increase the number of question.
        else{
            questionNumber++;
            progressText.setText((questionNumber+1) + "/5");
            renderQuiz();
        }
        ((Button) v).setText("submit");
        // set selected button to null, so it is easy to see if the button is click or not on the next question.
        selected = null;
    }
}