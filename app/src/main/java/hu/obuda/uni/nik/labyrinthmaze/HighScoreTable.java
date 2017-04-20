package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;
import hu.obuda.uni.nik.labyrinthmaze.database.RankContract;

public class HighScoreTable extends AppCompatActivity {

    DBHandler dbHandler= new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_table);

        TextView result= (TextView) findViewById(R.id.resultTextView);

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
        result.setText(sb.toString());

    }
}
