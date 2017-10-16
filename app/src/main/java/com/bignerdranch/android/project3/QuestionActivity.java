package com.bignerdranch.android.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;

public class QuestionActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageButton mNavLeftImageBtn;
    private ImageButton mNavRightImageBtn;
    private Button mSubmitButton;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_great_barrier_reef, R.drawable.great_barrier_reef,
            new HashMap<Integer, Boolean>() {{
                put(R.string.question_great_barrier_reef_choice_1, false);
                put(R.string.question_great_barrier_reef_choice_2, false);
                put(R.string.question_great_barrier_reef_choice_3, true);
            }}
        ),
        new Question(R.string.question_space, R.drawable.space, new HashMap<Integer, Boolean>() {{
            put(R.string.question_space_choice_1, false);
            put(R.string.question_space_choice_2, true);
            put(R.string.question_space_choice_3, false);
        }}),
        new Question(R.string.question_rainforest, R.drawable.rainforest,
            new HashMap<Integer, Boolean>() {{
                put(R.string.question_rainforest_choice_1, true);
                put(R.string.question_rainforest_choice_2, false);
                put(R.string.question_rainforest_choice_3, false);
            }}
        )
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mNavLeftImageBtn = findViewById(R.id.left_nav);
        mNavRightImageBtn = findViewById(R.id.right_nav);
        mPager = findViewById(R.id.pager);
        mSubmitButton = findViewById(R.id.submit_btn);

        // Hide these items initially
        mNavLeftImageBtn.setVisibility(View.GONE);
        mSubmitButton.setVisibility(View.GONE);

        mNavLeftImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
            }
        });

        mNavRightImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer numCorrect = 0;

                for (Question question : mQuestionBank) {
                    if (question.isCorrect()) {
                        numCorrect++;
                    }
                }

                double percentCorrect = ((double)numCorrect / mQuestionBank.length) * 100;

                String message = getString(R.string.results, Math.round(percentCorrect));
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mNavLeftImageBtn.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.GONE);
                } else if (position == mQuestionBank.length - 1) {
                    mNavRightImageBtn.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                } else {
                    mNavLeftImageBtn.setVisibility(View.VISIBLE);
                    mNavRightImageBtn.setVisibility(View.VISIBLE);
                    mSubmitButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        mPagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class QuestionPagerAdapter extends FragmentPagerAdapter {
        public QuestionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment questionPageFragment = new QuestionPageFragment();
            Question question = mQuestionBank[position];
            Bundle args = new Bundle();

            args.putParcelable(Question.QUESTION_KEY, question);
            questionPageFragment.setArguments(args);

            return questionPageFragment;
        }

        @Override
        public int getCount() {
            return mQuestionBank.length;
        }
    }
}
