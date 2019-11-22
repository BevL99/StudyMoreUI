package com.example.studymore;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studymore.Multithreader.AsyncTaskDelegateListScore;
import com.example.studymore.Multithreader.GetScoreAsyncTask;
import com.example.studymore.ui.Quiz.Score;
import com.example.studymore.ui.Quiz.ScoreAdapter;
import com.example.studymore.ui.Quiz.ScoreDatabase;

import java.util.ArrayList;


public class QuizScoresActivity extends AppCompatActivity implements AsyncTaskDelegateListScore {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Score> scores = new ArrayList<>();
    private ScoreDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_scores);

        recyclerView = findViewById(R.id.quizScore_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Context context = getApplicationContext();
        database = ScoreDatabase.getInstance(context);

        GetScoreAsyncTask getFlashCardsAsyncTask = new GetScoreAsyncTask();
        getFlashCardsAsyncTask.setDatabase(database);
        getFlashCardsAsyncTask.setDelegate(QuizScoresActivity.this);
        getFlashCardsAsyncTask.execute();

    }

    public void handleTaskResult(ArrayList<Score> result) {
        scores = result;

        //set recycle view after results returned
        ScoreAdapter scoreAdapter = new ScoreAdapter();
        scoreAdapter.setData(scores);
        recyclerView.setAdapter(scoreAdapter);
    }
}
