package hu.obuda.uni.nik.labyrinthmaze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;

public class HighScoreTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_table);
        ListView list= (ListView) findViewById(R.id.highscorelist);
        DBHandler dbh= new DBHandler(this);

        Cursor dbCursor = dbHandler.loadUsers();
        while (!dbCursor.isAfterLast()) {
            {

                list.a
            }}


    }
}
