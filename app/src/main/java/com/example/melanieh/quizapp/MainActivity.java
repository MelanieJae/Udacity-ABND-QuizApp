package com.example.melanieh.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    int answerQ1Id;
    int answerQ2Id;
    int answerQ3Id;
    int answerQ5Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}


        public void recordChoiceQ1 (View view) {

            answerQ1Id = view.getId();
        }

        public void recordChoiceQ2 (View view) {

            answerQ2Id = view.getId();
        }
        public void recordChoiceQ3 (View view) {

            answerQ3Id = view.getId();
        }


        public void recordChoiceQ5 (View view) {

            answerQ5Id = view.getId();
        }

        public int incrementScore(int score) {
            if (score <= 6) {
                score = score + 1;
            }
            return score;
        }

        public int decrementScore(int score) {
            if (score > 0 ) {
                score = score - 1;
            }
            return score;
        }

    public void displayFinalScore(View view) {

        int answerId = view.getId();
        // grading logic for Qs 1-3 and 5 (radio button format)
        if (answerQ1Id == R.id.q1ans1) {
            score = incrementScore(score);
        }
        if (answerQ2Id == R.id.q2ans2) {
            score = incrementScore(score);
        }
        if (answerQ3Id == R.id.q3ans2) {
            score = incrementScore(score);
        }

        if (answerQ5Id == R.id.q5ans3) {
            score = incrementScore(score);
        }

        // grading logic for Q4 (checkbox format)
        // can only receive one point for the correct answer
        CheckBox box1 = (CheckBox)findViewById(R.id.q4ans1);
        CheckBox box2 = (CheckBox)findViewById(R.id.q4ans2);
        CheckBox box3 = (CheckBox)findViewById(R.id.q4ans3);
        CheckBox box4 = (CheckBox)findViewById(R.id.q4ans4);

        // The logic below requires that:
        // 1. all of the incorrect boxes not be checked in order to first increment
        // the score by checking the correct (first) box, and
        // 2. once the correct box is checked, checking any additional boxes constitutes the
        // submission of an incorrect answer and the removal of the point gained when the correct
        // box was checked.
        // 3. The removal of a point only occurs when this logic is prompted by checking the correct
        // box first, so no points are removed if an incorrect box was first checked.

        if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked()) {
            score = incrementScore(score);
        }

        // grading logic for Q6 (freeform text entry format)
        EditText q6AnsTextView = (EditText) findViewById(R.id.q6ans);
        String q6ans = q6AnsTextView.getText().toString();
        boolean q6AnsCheck = (q6ans.compareToIgnoreCase("kiwi") == 0);

        if (q6AnsCheck) {
            score = incrementScore(score);
        }

        CharSequence finalScoreText = "Your score is: " + score + " out of 6.";
        CharSequence finalScoreMsg;
        if (score >= 0 && score <= 3) {
            finalScoreMsg = finalScoreText;
        } else if (score > 3 && score <= 5) {
            finalScoreMsg = "Good job! " + finalScoreText;
        } else {
            finalScoreMsg = "Great job, you're now a New Zealand trivia expert! "
                    + finalScoreText;
        }

        Toast toast = Toast.makeText(this, finalScoreMsg, Toast.LENGTH_LONG);
        toast.show();
        score = 0;

    }
}






