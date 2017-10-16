package com.bignerdranch.android.project3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class Question implements Parcelable {
    public static final String QUESTION_KEY = "question";

    private int mQuestionTextResId;
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
        out.writeValue(mChoices);
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            Integer questionTextResId = in.readInt();
            HashMap<Integer, Boolean> choices = (HashMap) in.readValue(HashMap.class.getClassLoader());
            return new Question(questionTextResId, choices);
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

    public HashMap<Integer, Boolean> getChoices() {
        return mChoices;
    }

//    public boolean isAnswerTrue() {
//        return mAnswerTrue;
//    }

//    public void setAnswerTrue(boolean answerTrue) {
//        mAnswerTrue = answerTrue;
//    }

    public Question(int questionTextResId, HashMap<Integer, Boolean> choices) {
        mQuestionTextResId = questionTextResId;
        mChoices = choices;
    }
}
