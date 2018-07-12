package com.smartmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartmarket.data.question.Questions;
import com.smartmarket.ui.QuestionUIData;

import java.util.List;

/**
 * Created by Glauco on 16/12/2016.
 */

public class DefaultAdapter extends ArrayAdapter<QuestionUIData> {

    public DefaultAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public DefaultAdapter(Context context, int resource, List<QuestionUIData> items) {
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
            ImageView thumbnail = (ImageView) v.findViewById(R.id.thumbnail);

            if(questionTitle != null) {
                questionTitle.setText(questionData.getItemTitle());
            }
            if (questionText != null) {
                questionText.setText(questionData.getQuestion());
            }
            if (thumbnail != null) {
                thumbnail.setImageBitmap(questionData.getThumbnail());
            }
        }
        return v;
    }
}
