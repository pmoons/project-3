package com.bignerdranch.android.project3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class Question implements Parcelable {
    public static final String QUESTION_KEY = "question";

    private int mQuestionTextResId;
    private int mBackgroundImageResId;
    private HashMap<Integer, Boolean> mChoices;

    //
    // Parcelable Methods
    //

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mQuestionTextResId);
        out.writeInt(mBackgroundImageResId);
        out.writeValue(mChoices);
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            Integer questionTextResId = in.readInt();
            Integer backgroundImageResId = in.readInt();
            HashMap<Integer, Boolean> choices = (HashMap) in.readValue(HashMap.class.getClassLoader());
            return new Question(questionTextResId, backgroundImageResId, choices);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    //
    // End Parcelable Methods
    //

    public int getQuestionTextResId() {
        return mQuestionTextResId;
    }

    public void setQuestionTextResId(int questionTextResId) {
        mQuestionTextResId = questionTextResId;
    }

    public int getBackgroundImageResId() {
        return mBackgroundImageResId;
    }

    public HashMap<Integer, Boolean> getChoices() {
        return mChoices;
    }

//    public boolean isAnswerTrue() {
//        return mAnswerTrue;
//    }

//    public void setAnswerTrue(boolean answerTrue) {
//        mAnswerTrue = answerTrue;
//    }

    public Question(int questionTextResId, int backgroundImageResId, HashMap<Integer, Boolean> choices) {
        mQuestionTextResId = questionTextResId;
        mBackgroundImageResId = backgroundImageResId;
        mChoices = choices;
    }
}
