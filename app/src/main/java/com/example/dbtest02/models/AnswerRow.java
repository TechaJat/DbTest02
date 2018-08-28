package com.example.dbtest02.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class AnswerRow {
    // Answers row for a session

    private ArrayList<String> questions;
    private ArrayList<Integer> answers;

    public AnswerRow(){
        this.questions = new ArrayList<String>();
        this.answers = new ArrayList<Integer>();
    }

    public AnswerRow(ArrayList<String> question, ArrayList<Integer> answers) {
        this.questions = question;
        this.answers = answers;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<Integer> getAnswer() {
        return answers;
    }

    public int getAnswer(int position){
        return answers.get(position);
    }

    public void addQuestion(String question) {
        this.questions.add(question);
    }

    public void addAnswer(int answer) {
        this.answers.add(answer);
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = new ArrayList<String>(questions);
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = new ArrayList<Integer>(answers);
    }
}
