package hu.obuda.uni.nik.labyrinthmaze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;

import database.DBHandler;
import database.RankContract;

public class MainActivity extends AppCompatActivity {

    /*DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);

        final EditText input = (EditText) findViewById(R.id.editText);
        Button saveButton = (Button) findViewById(R.id.save);
        Button loadButton = (Button) findViewById(R.id.load);
        final TextView resultTextView = (TextView) findViewById(R.id.result);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] inputString = input.getText().toString().split(",");
                dbHandler.insertRank(inputString[0], Double.parseDouble(inputString[1]));
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                Cursor dbCursor = dbHandler.loadUsers();
                while (!dbCursor.isAfterLast()) {

                    long id = dbCursor.getLong(0);
                    Log.d("DB","id "+ id);
                    String idd = dbCursor.getString(dbCursor.getColumnIndex("id"));
                    String name = dbCursor.getString(dbCursor.getColumnIndex("name"));
                    String time = dbCursor.getString(dbCursor.getColumnIndex("time"));
                    sb.append("RANK: " +idd+" ").append("   NAME: "+name+" ").append("TIME: "+time+" ").append("\n");

                    dbCursor.moveToNext();
                }
                resultTextView.setText(sb.toString());
            }
        });

    }*/
}
