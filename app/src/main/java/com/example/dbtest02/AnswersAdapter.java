package com.example.dbtest02;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dbtest02.models.AnswerRow;

import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.MyViewHolder> {

    private List<AnswerRow> answersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView answerId, answer1, answer2;

        public MyViewHolder(View view) {
            super(view);
            answerId = view.findViewById(R.id.textView_id);
            answer1 = view.findViewById(R.id.textView_answer_1);
            answer2 = view.findViewById(R.id.textView_answer_2);
        }
    }

    public AnswersAdapter(List<AnswerRow> answersList) {
        this.answersList = answersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AnswerRow answerRow = answersList.get(position);
        holder.answerId.setText(Integer.toString(position));
        holder.answer1.setText(Integer.toString(answerRow.getAnswer(0)));
        holder.answer2.setText(Integer.toString(answerRow.getAnswer(1)));
    }

    @Override
    public int getItemCount() {
        return answersList.size();
    }
}
