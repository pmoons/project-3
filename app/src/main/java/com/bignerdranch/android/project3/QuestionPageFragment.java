package com.bignerdranch.android.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Map;

public class QuestionPageFragment extends Fragment {

    private Question mQuestion;
    private TextView mQuestionTextView;
    private ImageView mImageView;
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
        mImageView = view.findViewById(R.id.question_background_image_view);
        mRadioGroup = view.findViewById(R.id.question_radio_group);

        mQuestionTextView.setText(getString(mQuestion.getQuestionTextResId()));
        mImageView.setImageResource(mQuestion.getBackgroundImageResId());

        Integer radioButtonCounter = 0;

        for (Map.Entry<Integer, Boolean> choice : mQuestion.getChoices().entrySet()) {
            String choiceText = getString(choice.getKey());
            final Boolean choiceValue = choice.getValue();
            RadioButton choiceRadioBtn = getLayoutInflater()
                    .inflate(R.layout.radio_button_choice_view, null)
                    .findViewById(R.id.radio_button_choice);

            choiceRadioBtn.setText(choiceText);
            choiceRadioBtn.setId(radioButtonCounter); // Unique ID needed for RadioGroup
            choiceRadioBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQuestion.setAnswer(choiceValue);
                }
            });
            radioButtonCounter++;

            mRadioGroup.addView(choiceRadioBtn);
        }

        return view;
    }
}
