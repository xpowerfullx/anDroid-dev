package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hu.obuda.uni.nik.labyrinthmaze.model.Player;

/**
 * Created by kiKu on 2017. 05. 13..
 */

public class NewPlayerActivity extends AppCompatActivity {

    private EditText editTextView;
    private TextView infoTextView;
    private Button gameButton;
    private Pattern namePattern;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        editTextView = (EditText) findViewById(R.id.nameEditText);
        infoTextView = (TextView) findViewById(R.id.infoTextView);
        gameButton = (Button) findViewById(R.id.startButton);
        initialize();
    }

    private void initialize() {
        // Nem kezdődhet és nem végződhet szóközzel.
        namePattern = Pattern.compile("^\\S+(?: \\S+)*$");
        setInfoTextToGone();
        setGameButtonOnClickListener();
    }

    private void setInfoTextToGone() {
        this.infoTextView.setVisibility(View.GONE);
    }

    private void setInfoTextToVisible() {
        this.infoTextView.setVisibility(View.VISIBLE);
    }

    private void setGameButtonOnClickListener() {
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextView.getText().toString();
                if (name.length() != 0) {
                    matcher = namePattern.matcher(name);
                    boolean nameIsOk = matcher.matches();
                    if (nameIsOk) {
                        Intent intent = new Intent(NewPlayerActivity.this, GameActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        infoTextView.setText(getString(R.string.new_player_info_fault));
                        setInfoTextToVisible();
                    }
                } else {
                    infoTextView.setText(getString(R.string.new_player_info_missing));
                    setInfoTextToVisible();
                }
            }
        });
    }
}
