package com.bignerdranch.android.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Map;

public class QuestionPageFragment extends Fragment {

    private Question mQuestion;
    private TextView mQuestionTextView;
    private RadioGroup mRadioGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQuestion = getArguments().getParcelable(Question.QUESTION_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(
                R.layout.fragment_question_page, container, false);
        mQuestionTextView = view.findViewById(R.id.question_text_view);
        mRadioGroup = view.findViewById(R.id.question_radio_group);

        mQuestionTextView.setText(getString(mQuestion.getQuestionTextResId()));

        for (Map.Entry<Integer, Boolean> choice : mQuestion.getChoices().entrySet()) {
            String choiceText = getString(choice.getKey());
            Boolean isAnswer = choice.getValue();
            RadioButton choiceRadioBtn = new RadioButton(getContext());

            choiceRadioBtn.setText(choiceText);
            mRadioGroup.addView(choiceRadioBtn);
        }

        return view;
    }
}
