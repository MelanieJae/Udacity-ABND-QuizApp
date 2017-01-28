package com.example.melanieh.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    int score = 0;
    int answerQ1Id;
    int answerQ2Id;
    int answerQ3Id;
    int answerQ5Id;
    @BindView(R.id.quizheading) TextView quizHeadingView;
    @BindView(R.id.q1text) TextView q1TextView;
    @BindView(R.id.q1radiobtns) RadioGroup q1BtnsRadioGroupView;
    @BindView(R.id.q1ans1) RadioButton q1a1BtnView;
    @BindView(R.id.q1ans2) RadioButton q1a2BtnView;
    @BindView(R.id.q2text) TextView q2TextView;
    @BindView(R.id.q2radiobtns) RadioGroup q2BtnsRadioGroupView;
    @BindView(R.id.q2ans1) RadioButton q2a1BtnView;
    @BindView(R.id.q2ans2) RadioButton q2a2BtnView;
    @BindView(R.id.q3text) TextView q3TextView;
    @BindView(R.id.q3radiobtns) RadioGroup q3BtnsRadioGroupView;
    @BindView(R.id.q3ans1) RadioButton q3a1BtnView;
    @BindView(R.id.q3ans2) RadioButton q3a2BtnView;
    @BindView(R.id.q4text) TextView q4TextView;
    @BindView(R.id.q4ans1) CheckBox box1;
    @BindView(R.id.q4ans2) CheckBox box2;
    @BindView(R.id.q4ans3) CheckBox box3;
    @BindView(R.id.q4ans4) CheckBox box4;
    @BindView(R.id.q5text) TextView q5TextView;
    @BindView(R.id.q5radiobtns) RadioGroup q5BtnsRadioGroupView;
    @BindView(R.id.q5ans1) RadioButton q5a1BtnView;
    @BindView(R.id.q5ans2) RadioButton q5a2BtnView;
    @BindView(R.id.q5ans3) RadioButton q5a3BtnView;
    @BindView(R.id.q6ans) EditText q6AnsEditText;
    @BindView(R.id.toggle_btn) Button scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

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

    public void displayFinalScore(View view) {
        dismissKeyboard(q6AnsEditText);
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
        String q6ans = q6AnsEditText.getText().toString();
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

        Toast.makeText(this, finalScoreMsg, Toast.LENGTH_LONG).show();
        score = 0;
        resetQuizForm();
    }

    private void resetQuizForm() {
        dismissKeyboard(q6AnsEditText);
        q1BtnsRadioGroupView.clearCheck();
        q2BtnsRadioGroupView.clearCheck();
        q3BtnsRadioGroupView.clearCheck();
        q5BtnsRadioGroupView.clearCheck();
        if (box1.isChecked()) {
            box1.performClick();
        }
        if (box2.isChecked()) {
            box2.performClick();
        }
        if (box3.isChecked()) {
            box3.performClick();
        }
        if (box4.isChecked()) {
            box4.performClick();
        }
        q6AnsEditText.setText("");
//        onStop();
    }

    private void dismissKeyboard(EditText view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}






