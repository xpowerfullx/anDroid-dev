package hu.obuda.uni.nik.labyrinthmaze;
import android.content.Intent;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import hu.obuda.uni.nik.labyrinthmaze.database.DBHandler;


public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button PlayButton = (Button) findViewById(R.id.playbutton);
        PlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPlayerActivity.class);
                startActivity(intent);
            }
        });

        final Button HighScoreTableButton = (Button) findViewById(R.id.highscorebutton);
        HighScoreTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScoreTableActivity.class);
                startActivity(intent);
            }
        });
    }
}
