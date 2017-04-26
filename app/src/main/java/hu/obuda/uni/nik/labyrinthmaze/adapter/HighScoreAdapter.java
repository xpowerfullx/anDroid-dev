package hu.obuda.uni.nik.labyrinthmaze.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.R;
import hu.obuda.uni.nik.labyrinthmaze.model.HighScore;

/**
 * Created by Budai Krisztián on 2017. 04. 26.
 */

public class HighScoreAdapter extends BaseAdapter {

    private List<HighScore> highScores;
    private Context context;

    public HighScoreAdapter(Context context, List<HighScore> highScores) {
        this.context = context;
        this.highScores = highScores;
    }

    @Override
    public int getCount() {
        return highScores == null ? 0 : highScores.size();
    }

    @Override
    public Object getItem(int position) {
        return highScores == null ? null : highScores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listHighScoreView = convertView;
        if (listHighScoreView == null)
            listHighScoreView = View.inflate(parent.getContext(), R.layout.listitem_high_score, null);
        LinearLayout baseLinearLayout = (LinearLayout) listHighScoreView.findViewById(R.id.base);
        TextView rankTextView = (TextView) listHighScoreView.findViewById(R.id.rank);
        TextView nameTextView = (TextView) listHighScoreView.findViewById(R.id.name);
        TextView scoreTextView = (TextView) listHighScoreView.findViewById(R.id.score);

        HighScore highScore = highScores.get(position);
        if (highScore.getRank() % 2 == 1){
            baseLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.rankings_green));
        } else {
            baseLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.rankings_orange));
        }
        rankTextView.setText(highScore.getRank());
        nameTextView.setText(highScore.getName());
        scoreTextView.setText(highScore.getScore());
        return listHighScoreView;
    }
}