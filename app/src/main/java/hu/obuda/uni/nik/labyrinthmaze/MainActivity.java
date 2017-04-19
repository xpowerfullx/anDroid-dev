package hu.obuda.uni.nik.labyrinthmaze;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int csodavaltozo = 5;



        final Button PlayButton = (Button) findViewById(R.id.playbutton);
        PlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Perform action on click
            }
        });


        final Button HighScoreTableButton = (Button) findViewById(R.id.highscorebutton);
        HighScoreTableButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Perform action on click
            }
        });

        final Button ExitButton = (Button) findViewById(R.id.exitbutton);
        HighScoreTableButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Perform action on click
            }
        });

    }
}
