package com.smartmarket;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartmarket.data.question.ChatMessage;
import com.smartmarket.ui.QuestionUIData;

import java.util.List;

/**
 * Created by glauco on 27/12/16.
 */

public class ChatItemAdapter extends ArrayAdapter<ChatMessage> {

    public ChatItemAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ChatItemAdapter(Context context, int resource, List<ChatMessage> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.chat_item, null);
        }

        ChatMessage chatMessage = getItem(position);
        if (chatMessage != null) {
            LinearLayout innerLayout = (LinearLayout) v.findViewById(R.id.contentWithBackground);
            LinearLayout outerLayout = (LinearLayout) v.findViewById(R.id.content);
            TextView message = (TextView) v.findViewById(R.id.message);

            int backgroundId = 0;
            int gravity = 0;
            int align1 = 0;
            int align2 = 0;

            if (chatMessage.getType() == ChatMessage.MY_MESSAGE) {
                backgroundId = R.drawable.in_message_bg;
                gravity = Gravity.RIGHT;
                align1 = RelativeLayout.ALIGN_PARENT_LEFT;
                align2 = RelativeLayout.ALIGN_PARENT_RIGHT;
            } else {
                backgroundId = R.drawable.out_message_bg;
                gravity = Gravity.LEFT;
                align1 = RelativeLayout.ALIGN_PARENT_RIGHT;
                align2 = RelativeLayout.ALIGN_PARENT_LEFT;
            }

            innerLayout.setBackgroundResource(backgroundId);
            LinearLayout.LayoutParams innerLayoutParams = (LinearLayout.LayoutParams) innerLayout.getLayoutParams();
            innerLayoutParams.gravity = gravity;
            innerLayout.setLayoutParams(innerLayoutParams);

            RelativeLayout.LayoutParams outerLayoutParams = (RelativeLayout.LayoutParams) outerLayout.getLayoutParams();
            outerLayoutParams.addRule(align1, 0);
            outerLayoutParams.addRule(align2);
            outerLayout.setLayoutParams(outerLayoutParams);

            LinearLayout.LayoutParams textLayoutParams = (LinearLayout.LayoutParams) message.getLayoutParams();
            textLayoutParams.gravity = gravity;
            message.setLayoutParams(textLayoutParams);

            message.setText(chatMessage.getMessage());
        }
        return v;
    }
}
