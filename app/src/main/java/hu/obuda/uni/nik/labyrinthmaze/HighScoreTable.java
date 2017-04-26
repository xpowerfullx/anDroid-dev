package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.adapter.HighScoreAdapter;
import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;
import hu.obuda.uni.nik.labyrinthmaze.database.RankContract;
import hu.obuda.uni.nik.labyrinthmaze.model.HighScore;

public class HighScoreTable extends AppCompatActivity {

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

        /*TextView result= (TextView) findViewById(R.id.resultTextView);

        if(dbHandler.GetDbCount()==0){
            dbHandler.insertRank("Barna",5000);
            dbHandler.insertRank("Krisztian",3790);
            dbHandler.insertRank("NTomi",404);
            dbHandler.insertRank("KTomi",990);
            dbHandler.insertRank("Adam",3566);
            dbHandler.insertRank("GiziNeni",10223);
            dbHandler.insertRank("MariNeni",9087);
            dbHandler.insertRank("Barna",433);
        }

        StringBuilder sb = new StringBuilder();
        Cursor dbCursor = dbHandler.loadUsers();
        int rank =1;
        while (!dbCursor.isAfterLast() && dbCursor.getLong(0)<11) {

            long id = dbCursor.getLong(0);
            //Log.d("DB","id "+ id);
            String idd = dbCursor.getString(dbCursor.getColumnIndex("id"));
            String name = dbCursor.getString(dbCursor.getColumnIndex("name"));
            String score = dbCursor.getString(dbCursor.getColumnIndex("score"));

            sb.append("RANK: " +rank +" ").append("   NAME: "+name+" ").append("SCORE: "+score+" ").append("\n");
            rank++;
            dbCursor.moveToNext();
        }
        result.setText(sb.toString());*/
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
        while (!dbCursor.isAfterLast()) {
            long id = dbCursor.getLong(0);
            highScores.add(createHighScoreObject((int) id,
                    dbCursor.getString(dbCursor.getColumnIndex("name")),
                    dbCursor.getInt(dbCursor.getColumnIndex("score"))));
            dbCursor.moveToNext();
        }
        return highScores;
    }

    private HighScore createHighScoreObject(int rank, String name, int score) {
        return new HighScore(rank, name, score);
    }
}