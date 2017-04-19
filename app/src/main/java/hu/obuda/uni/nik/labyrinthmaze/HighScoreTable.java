package hu.obuda.uni.nik.labyrinthmaze;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;

public class HighScoreTable extends AppCompatActivity {

    DBHandler dbHandler= new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_table);
        TextView result= (TextView) findViewById(R.id.resultTextView);

        StringBuilder sb = new StringBuilder();
        Cursor dbCursor = dbHandler.loadUsers();
        while (!dbCursor.isAfterLast()) {

            long id = dbCursor.getLong(0);
            //Log.d("DB","id "+ id);
            String idd = dbCursor.getString(dbCursor.getColumnIndex("id"));
            String name = dbCursor.getString(dbCursor.getColumnIndex("name"));
            String time = dbCursor.getString(dbCursor.getColumnIndex("time"));
            sb.append("RANK: " +idd+" ").append("   NAME: "+name+" ").append("TIME: "+time+" ").append("\n");

            dbCursor.moveToNext();
        }
        result.setText(sb.toString());



    }
}
