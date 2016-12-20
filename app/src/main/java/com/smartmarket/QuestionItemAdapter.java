package com.smartmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smartmarket.data.question.Questions;
import com.smartmarket.ui.QuestionUIData;

import java.util.List;

/**
 * Created by Glauco on 16/12/2016.
 */

public class QuestionItemAdapter extends ArrayAdapter<QuestionUIData> {

    public QuestionItemAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public QuestionItemAdapter(Context context, int resource, List<QuestionUIData> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.question_item, null);
        }

        QuestionUIData questionData = getItem(position);
        if (questionData != null) {
            TextView questionTitle = (TextView) v.findViewById(R.id.questionItemTitle);
            TextView questionText = (TextView) v.findViewById(R.id.questionItemText);

            if(questionTitle != null) {
                questionTitle.setText(questionData.getItemTitle());
            }

            if (questionText != null) {
                questionText.setText(questionData.getQuestion());
            }
        }
        return v;
    }
}
