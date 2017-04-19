package hu.obuda.uni.nik.labyrinthmaze;
import android.content.Intent;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;

import static android.R.attr.onClick;
import static android.R.attr.value;


public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Button PlayButton = (Button) findViewById(R.id.playbutton);
        PlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Perform action on click
            }
        });


        final Button HighScoreTableButton = (Button) findViewById(R.id.highscorebutton);

        HighScoreTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScoreTable.class);
                TextView editText = (TextView) findViewById(R.id.resultTextView);
                startActivity(intent);
            }
        });



        final Button ExitButton = (Button) findViewById(R.id.exitbutton);


    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, HighScoreTable.class);
        TextView editText = (TextView) findViewById(R.id.resultTextView);
        String message = editText.getText().toString();
        intent.putExtra("asd", message);
        startActivity(intent);
    }

/*
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
