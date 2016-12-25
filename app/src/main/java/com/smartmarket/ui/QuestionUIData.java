package com.smartmarket.ui;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glauco on 19/12/2016.
 */

public class QuestionUIData implements Parcelable {

    private String itemTitle;
    private String itemId;
    private String question;
    private Long questionId;
    private Bitmap thumbnail;

    protected QuestionUIData(Parcel in) {
        itemTitle = in.readString();
        itemId = in.readString();
        question = in.readString();
        questionId = in.readLong();
    }

    public QuestionUIData() { }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionText(String question) {
        this.question = question;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(itemTitle);
        dest.writeString(itemId);
        dest.writeString(question);
        dest.writeLong(questionId);
    }

    public static final Creator<QuestionUIData> CREATOR = new Creator<QuestionUIData>() {
        @Override
        public QuestionUIData createFromParcel(Parcel in) {
            return new QuestionUIData(in);
        }

        @Override
        public QuestionUIData[] newArray(int size) {
            return new QuestionUIData[size];
        }
    };

}
