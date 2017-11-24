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

    static final String STATE_SCORE_A = "scoreTeamA";
    static final String STATE_SCORE_B = "scoreTeamB";
    static final String STATE_GAME_ENDED = "isGameEnded";

    int scoreTeamA;
    int scoreTeamB;
    TextView scoreViewA;
    TextView scoreViewB;
    Button goalAButton;
    Button goalBButton;
    Button snitchAButton;
    Button snitchBButton;
    RelativeLayout rootView;
    TextView winner;
    Resources res;
    int gold;
    int red;
    int silver;
    int green;
    int nightSky;
    int scrollDark;
    boolean isGameEnded;

    /**
     * including the colors from resources as globals for readability, and so that getResources and
     * getColor won't execute with any end of game or reset.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            scoreTeamA = savedInstanceState.getInt(STATE_SCORE_A);
            scoreTeamB = savedInstanceState.getInt(STATE_SCORE_B);
            isGameEnded = savedInstanceState.getBoolean(STATE_GAME_ENDED);

        } else {
            scoreTeamA = 0;
            scoreTeamB = 0;
            isGameEnded = false;
        }

        scoreViewA = (TextView) findViewById(R.id.score_a);
        scoreViewB = (TextView) findViewById(R.id.score_b);
        goalAButton = (Button) findViewById(R.id.button_goal_a);
        goalBButton = (Button) findViewById(R.id.button_goal_b);
        snitchAButton = (Button) findViewById(R.id.button_snitch_a);
        snitchBButton = (Button) findViewById(R.id.button_snitch_b);
        rootView = (RelativeLayout) findViewById(R.id.root_view);
        winner = (TextView) findViewById(R.id.text_winner);
        res = (Resources) getResources();
        gold = (int) res.getColor(R.color.gryffindorGold);
        red = (int) res.getColor(R.color.gryffindorRed);
        silver = (int) res.getColor(R.color.slytherinSilver);
        green = (int) res.getColor(R.color.slytherinGreen);
        scrollDark = (int) res.getColor(R.color.scrollDark);
        nightSky = (int) res.getColor(R.color.nightSky);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        if (isGameEnded == true) {
            endGame();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        savedInstanceState.putInt(STATE_SCORE_A, scoreTeamA);
        savedInstanceState.putInt(STATE_SCORE_B, scoreTeamB);
        savedInstanceState.putBoolean(STATE_GAME_ENDED, isGameEnded);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
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
        scoreViewB.setText(String.valueOf(score));
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
        isGameEnded = true;
        snitchAButton.setEnabled(false);
        snitchBButton.setEnabled(false);
        goalAButton.setEnabled(false);
        goalBButton.setEnabled(false);
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
        winner.setText("");
        winner.setTextColor(scrollDark);
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
        isGameEnded = false;
    }
}
