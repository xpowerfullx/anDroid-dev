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
import hu.obuda.uni.nik.labyrinthmaze.database.RankContract;

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
}
