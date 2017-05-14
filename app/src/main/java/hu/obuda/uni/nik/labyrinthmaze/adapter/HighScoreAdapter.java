package hu.obuda.uni.nik.labyrinthmaze.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.R;
import hu.obuda.uni.nik.labyrinthmaze.model.HighScore;

/**
 * Created by Budai Krisztián on 2017. 04. 26.
 */

public class HighScoreAdapter extends BaseAdapter {

    static class HighScoreViewHolder {
        TextView rankTextView;
        TextView nameTextView;
        TextView scoreTextView;
    }

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

        HighScoreViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.listitem_high_score, null);
            holder = new HighScoreViewHolder();
            holder.rankTextView = (TextView) convertView.findViewById(R.id.rank);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name);
            holder.scoreTextView = (TextView) convertView.findViewById(R.id.score);
            convertView.setTag(holder);
        } else {
            holder = (HighScoreViewHolder) convertView.getTag();
        }

        HighScore highScore = highScores.get(position);

        if (highScore.getRank() % 2 == 1) {
            convertView.setBackground(ContextCompat.getDrawable(context, R.drawable.rankings_shape_green));
        } else {
            //convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            convertView.setBackground(ContextCompat.getDrawable(context, R.drawable.rankings_shape_primary));
        }

        holder.rankTextView.setText(String.valueOf(highScore.getRank()));
        holder.nameTextView.setText(highScore.getName());
        holder.scoreTextView.setText(String.valueOf(highScore.getScore() + " pont"));

        return convertView;
    }
}