package hu.obuda.uni.nik.labyrinthmaze;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.adapter.HighScoreAdapter;
import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;
import hu.obuda.uni.nik.labyrinthmaze.model.HighScore;

public class HighScoreTableActivity extends AppCompatActivity {

    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_table);

        ListView highScoreListView = (ListView) findViewById(R.id.highScoreList);
        generateTestData();
        List<HighScore> highScores = loadHighScores();
        HighScoreAdapter highScoreAdapter = new HighScoreAdapter(this, highScores);
        highScoreListView.setAdapter(highScoreAdapter);
    }

    private void generateTestData() {
        if (dbHandler.GetDbCount() == 0) {
            dbHandler.insertRank("Barna", 5000);
            dbHandler.insertRank("Krisztian", 3790);
            dbHandler.insertRank("NTomi", 4104);
            dbHandler.insertRank("KTomi", 9910);
            dbHandler.insertRank("Adam", 3566);
            dbHandler.insertRank("GiziNeni", 10223);
            dbHandler.insertRank("MariNeni", 9087);
            dbHandler.insertRank("Barna", 433);
            dbHandler.insertRank("Sanyi", 10223);
            dbHandler.insertRank("Pista", 92087);
            dbHandler.insertRank("Elek", 43233);
        }
    }

    private List<HighScore> loadHighScores() {
        Cursor dbCursor = dbHandler.loadUsers();
        List<HighScore> highScores = new ArrayList<HighScore>();
        int rank = 1;
        while (!dbCursor.isAfterLast()) {
            long id = dbCursor.getLong(0);
            highScores.add(createHighScoreObject(rank,
                    dbCursor.getString(dbCursor.getColumnIndex("name")),
                    dbCursor.getInt(dbCursor.getColumnIndex("score"))));
            rank++;
            dbCursor.moveToNext();
        }
        return highScores;
    }

    private HighScore createHighScoreObject(int rank, String name, int score) {
        return new HighScore(rank, name, score);
    }
}