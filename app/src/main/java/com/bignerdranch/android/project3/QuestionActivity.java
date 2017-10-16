package com.bignerdranch.android.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.HashMap;

public class QuestionActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_great_barrier_reef, new HashMap<Integer, Boolean>() {{
            put(R.string.question_great_barrier_reef_choice_1, false);
            put(R.string.question_great_barrier_reef_choice_2, false);
            put(R.string.question_great_barrier_reef_choice_3, true);
        }}),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mPager = findViewById(R.id.pager);
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
