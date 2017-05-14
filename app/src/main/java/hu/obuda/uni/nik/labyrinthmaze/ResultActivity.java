package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;
import hu.obuda.uni.nik.labyrinthmaze.model.HighScore;

/**
 * Created by Budai Krisztián on 2017. 05. 14.
 */

public class ResultActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private String playerName;
    private int playerScore;
    private Bundle bundle;
    private TextView resultTextView;
    private Button newGameButton;
    private Button menuButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
        processResult();
    }

    private void initialize() {
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        menuButton = (Button) findViewById(R.id.menuButton);

        dbHandler = new DBHandler(this);
        bundle = getIntent().getExtras();
        if (bundle != null){
            playerName = bundle.getString("name");
            playerScore = bundle.getInt("score");
        }

        setNewGameButtonOnClickListener();
        setMenuButtonOnClickListener();
    }

    private void setNewGameButtonOnClickListener() {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, NewPlayerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setMenuButtonOnClickListener() {
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void processResult(){
        boolean isNewResult = dbHandler.checkIfEnoughToBeRanked(playerScore);
        if (isNewResult){
            dbHandler.removeLowestRank();
            long newResultId = dbHandler.insertRank(playerName, playerScore);
            int resultPlace = getRankOfNewResult(newResultId);
            String resultText = String.format("%d pontot értél el, amellyel felkerültél a ranglista %d. helyére! :)", playerScore, resultPlace);
            resultTextView.setText(resultText);
        } else {
            String resultText = String.format("%d pontot értél el, ezzel sajnos lecsúsztál a ranglistáról! :(", playerScore);
            resultTextView.setText(resultText);
        }
    }

    private int getRankOfNewResult(long resultId) {
        Cursor dbCursor = dbHandler.loadUsers();
        int rank = 1;
        while (!dbCursor.isAfterLast()) {
            long id = dbCursor.getLong(0);
            if (id == resultId)
                return rank;
            rank++;
            dbCursor.moveToNext();
        }
        return -1; // Elméletileg ilyen nem lehetne.
    }
}