package com.example.android.quidditchscorekeeper;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    /**
     * including the colors from resources as globals for readability, and so that getResources and
     * getColor won't execute with any end of game or reset.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_a);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * increase the score of team A by 10 and display the new score
     *
     * @param v
     */
    public void goalA(View v) {
        scoreTeamA = scoreTeamA + 10;
        displayForTeamA(scoreTeamA);
    }

    /**
     * increase the score of team A by 10 and display the new score
     *
     * @param v
     */
    public void snitchA(View v) {
        scoreTeamA = scoreTeamA + 150;
        displayForTeamA(scoreTeamA);
        endGame();
    }

    /**
     * Display the given score for Team B
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_b);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * increase the score of team B by 150 and display the new score
     *
     * @param v
     */
    public void goalB(View v) {
        scoreTeamB = scoreTeamB + 10;
        displayForTeamB(scoreTeamB);
    }

    /**
     * increase the score of team B by 150 and display the new score
     *
     * @param v
     */
    public void snitchB(View v) {
        scoreTeamB = scoreTeamB + 150;
        displayForTeamB(scoreTeamB);
        endGame();
    }

    /**
     * disable score buttons
     * display winner in appropriate color
     */
    public void endGame() {
        Button goalAButton = (Button) findViewById(R.id.button_goal_a);
        Button goalBButton = (Button) findViewById(R.id.button_goal_b);
        Button snitchAButton = (Button) findViewById(R.id.button_snitch_a);
        Button snitchBButton = (Button) findViewById(R.id.button_snitch_b);
        snitchAButton.setEnabled(false);
        snitchBButton.setEnabled(false);
        goalAButton.setEnabled(false);
        goalBButton.setEnabled(false);
        RelativeLayout rootView = (RelativeLayout) findViewById(R.id.root_view);
        TextView winner = (TextView) findViewById(R.id.text_winner);
        Resources res = getResources();
        int gold = res.getColor(R.color.gryffindorGold);
        int red = res.getColor(R.color.gryffindorRed);
        int silver = res.getColor(R.color.slytherinSilver);
        int green = res.getColor(R.color.slytherinGreen);
        int scrollDark = res.getColor(R.color.scrollDark);
        if (scoreTeamA > scoreTeamB) {
            winner.setTextColor(gold);
            winner.setText(R.string.win_a);
            rootView.setBackgroundColor(red);
        } else if (scoreTeamB > scoreTeamA) {
            winner.setTextColor(silver);
            winner.setText(R.string.win_b);
            rootView.setBackgroundColor(green);
        } else {
            winner.setTextColor(scrollDark);
            winner.setText(R.string.draw);
        }
    }

    /**
     * for end game button
     * ends game
     *
     * @param view
     */
    public void captainsAgreement(View view) {
        endGame();
    }

    /**
     * reenable buttons
     * reset score
     * reset background color
     * reset winner announcement
     */
    public void reset(View view) {
        Resources res = getResources();
        int nightSky = res.getColor(R.color.nightSky);
        int scrollDark = res.getColor(R.color.scrollDark);
        TextView winner = (TextView) findViewById(R.id.text_winner);
        winner.setText("");
        winner.setTextColor(scrollDark);
        RelativeLayout rootView = (RelativeLayout) findViewById(R.id.root_view);
        rootView.setBackgroundColor(nightSky);
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        Button goalAButton = (Button) findViewById(R.id.button_goal_a);
        Button goalBButton = (Button) findViewById(R.id.button_goal_b);
        Button snitchAButton = (Button) findViewById(R.id.button_snitch_a);
        Button snitchBButton = (Button) findViewById(R.id.button_snitch_b);
        snitchAButton.setEnabled(true);
        snitchBButton.setEnabled(true);
        goalAButton.setEnabled(true);
        goalBButton.setEnabled(true);
    }
}
