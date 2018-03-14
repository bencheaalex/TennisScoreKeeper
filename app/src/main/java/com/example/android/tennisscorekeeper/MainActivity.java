package com.example.android.tennisscorekeeper;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.valueOf;

public class MainActivity extends AppCompatActivity {

    int pointP1 = 0;
    int gameP1 = 0;
    int setP1 = 0;
    int pointP2 = 0;
    int gameP2 = 0;
    int setP2 = 0;
    int tiebreak = 0;
    int setCounter = 0;
    Chronometer c;
    TextView pp1;
    TextView pp2;
    TextView gp1;
    TextView gp2;
    TextView sp1;
    TextView sp2;
    TextView[] gamesViewP1 = new TextView[5];
    int[] gamesP1 = new int[5];
    TextView[] gamesViewP2 = new TextView[5];
    int[] gamesP2 = new int[5];
    TextView messageView;
    String message = "";

    Button ip1, ip2, reset, start;
    boolean sip1, sip2, sReset, sStart, end;
    long time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = (Chronometer) findViewById(R.id.mainTimer);
        pp1 = (TextView) findViewById(R.id.points_player1);
        pp2 = (TextView) findViewById(R.id.points_player2);
        gp1 = (TextView) findViewById(R.id.game_player1);
        gp2 = (TextView) findViewById(R.id.game_player2);
        sp1 = (TextView) findViewById(R.id.set_player1);
        sp2 = (TextView) findViewById(R.id.set_player2);
        gamesViewP1[0] = (TextView) findViewById(R.id.p1s1);
        gamesViewP1[1] = (TextView) findViewById(R.id.p1s2);
        gamesViewP1[2] = (TextView) findViewById(R.id.p1s3);
        gamesViewP1[3] = (TextView) findViewById(R.id.p1s4);
        gamesViewP1[4] = (TextView) findViewById(R.id.p1s5);
        gamesViewP2[0] = (TextView) findViewById(R.id.p2s1);
        gamesViewP2[1] = (TextView) findViewById(R.id.p2s2);
        gamesViewP2[2] = (TextView) findViewById(R.id.p2s3);
        gamesViewP2[3] = (TextView) findViewById(R.id.p2s4);
        gamesViewP2[4] = (TextView) findViewById(R.id.p2s5);
        messageView = (TextView) findViewById(R.id.end_game);
        ip1 = (Button) findViewById(R.id.ip1);
        ip2 = (Button) findViewById(R.id.ip2);
        start = (Button) findViewById(R.id.startButton);
        reset = (Button) findViewById(R.id.resetButton);
        ip1.setEnabled(false);
        sip1 = false;
        ip2.setEnabled(false);
        sip2 = false;
        reset.setEnabled(false);
        sReset = false;
        start.setEnabled(true);
        sStart = true;
        end = false;
    }

    public void increase_p1(View view) {
        pointP1 = pointP1 + 1;
        setCounter = setP1 + setP2;
        if (tiebreak == 0) {
            verifyPointsP1();
            displayPointsP1();
            displayPointsP2();
        } else {
            verifyTiebreakP1();
            display("" + pointP1, pp1);
        }
        if (setP1 == 3)
            endGame("Player 1");
    }

    public void increase_p2(View view) {
        pointP2 = pointP2 + 1;
        setCounter = setP1 + setP2;
        if (tiebreak == 0) {
            verifyPointsP2();
            displayPointsP1();
            displayPointsP2();
        } else {
            display("" + pointP2, pp2);
            verifyTiebreakP2();
        }
        if (setP2 == 3)
            endGame("Player 2");
    }

    public void verifyPointsP1() {
        if (pointP1 > 3 && pointP1 - pointP2 > 1) {
            gameP1 = gameP1 + 1;
            display("" + gameP1, gp1);
            pointP1 = 0;
            pointP2 = 0;
            verifyGameP1();
        }
    }

    public void verifyPointsP2() {
        if (pointP2 > 3 && pointP2 - pointP1 > 1) {
            gameP2 = gameP2 + 1;
            display("" + gameP2, gp2);
            pointP1 = 0;
            pointP2 = 0;
            verifyGameP2();
        }
    }

    public void verifyTiebreakP1() {
        if (pointP1 > 6 && pointP1 - pointP2 > 1) {
            tiebreak = 0;
            message = "";
            display(message, messageView);
            messageView.setText("");
            setP1++;
            gamesP1[setCounter] = gameP1 + 1;
            display("" + gamesP1[setCounter], gamesViewP1[setCounter]);
            gamesP2[setCounter] = gameP2;
            gameP1 = 0;
            gameP2 = 0;
            pointP1 = 0;
            pointP2 = 0;
            displayPointsP1();
            displayPointsP2();
            display("" + gameP1, gp1);
            display("" + gameP2, gp2);
            display("" + setP1, sp1);
            display("" + setP2, sp2);
        }
    }

    public void verifyTiebreakP2() {
        if (pointP2 > 6 && pointP2 - pointP1 > 1) {
            tiebreak = 0;
            message = "";
            display(message, messageView);
            messageView.setText("");
            setP2++;
            gamesP1[setCounter] = gameP1;
            gamesP2[setCounter] = gameP2 + 1;
            display("" + gamesP2[setCounter], gamesViewP2[setCounter]);
            gameP1 = 0;
            gameP2 = 0;
            pointP1 = 0;
            pointP2 = 0;
            displayPointsP1();
            displayPointsP2();
            display("" + gameP1, gp1);
            display("" + gameP2, gp2);
            display("" + setP1, sp1);
            display("" + setP2, sp2);
        }
    }

    public void verifyGameP1() {
        gamesP1[setCounter] = gameP1;
        gamesP2[setCounter] = gameP2;
        display("" + gamesP1[setCounter], gamesViewP1[setCounter]);
        display("" + gamesP2[setCounter], gamesViewP2[setCounter]);
        if (gameP1 > 5)
            if (gameP1 - gameP2 > 1) {
                setP1 = setP1 + 1;
                display("" + setP1, sp1);
                gameP1 = 0;
                gameP2 = 0;
                display("" + gameP1, gp1);
                display("" + gameP2, gp2);
            } else if (gameP1 - gameP2 == 0) {
                tiebreak = 1;
                message = "TIEBREAK";
                display(message, messageView);
            }
    }

    public void verifyGameP2() {
        gamesP1[setCounter] = gameP1;
        gamesP2[setCounter] = gameP2;
        display("" + gamesP1[setCounter], gamesViewP1[setCounter]);
        display("" + gamesP2[setCounter], gamesViewP2[setCounter]);
        if (gameP2 > 5)
            if (gameP2 - gameP1 > 1) {
                setP2 = setP2 + 1;
                display("" + setP2, sp2);
                gameP1 = 0;
                gameP2 = 0;
                display("" + gameP1, gp1);
                display("" + gameP2, gp2);
            } else if (gameP2 - gameP1 == 0) {
                tiebreak = 1;
                message = "TIEBREAK";
                display(message, messageView);
            }
    }

    public void display(String what, TextView where) {
        where.setText(String.valueOf(what));
    }

    public void displayPointsP1() {
        if (tiebreak == 0) {
            if (pointP1 == 0)
                display("0", pp1);
            if (pointP1 == 1)
                display("15", pp1);
            if (pointP1 == 2)
                display("30", pp1);
            if (pointP1 == 3)
                display("40", pp1);
            if (pointP1 > 3)
                if (pointP1 == pointP2) {
                    display("40", pp1);
                    display("40", pp2);
                } else if (pointP1 - pointP2 == 1)
                    display("ADV", pp1);
        } else
            display("" + pointP1, pp1);
    }

    public void displayPointsP2() {
        if (tiebreak == 0) {
            if (pointP2 == 0)
                display("0", pp2);
            if (pointP2 == 1)
                display("15", pp2);
            if (pointP2 == 2)
                display("30", pp2);
            if (pointP2 == 3)
                display("40", pp2);
            if (pointP2 > 3)
                if (pointP1 == pointP2) {
                    display("40", pp1);
                    display("40", pp2);
                } else if (pointP2 - pointP1 == 1)
                    display("ADV", pp2);
        } else
            display("" + pointP2, pp2);
    }

    public void endGame(String x) {
        message = x + " win!!!";
        display(message, messageView);
        ip1.setEnabled(false);
        sip1 = false;
        ip2.setEnabled(false);
        sip2 = false;
        c.stop();
        end = true;
    }

    public void start_button(View view) {
        ip1.setEnabled(true);
        sip1 = true;
        ip2.setEnabled(true);
        sip2 = true;
        start.setEnabled(false);
        sStart = false;
        reset.setEnabled(true);
        sReset = true;
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
    }

    public void reset_button(View view) {
        c.setBase(SystemClock.elapsedRealtime());
        ip1.setEnabled(false);
        sip1 = false;
        ip2.setEnabled(false);
        sip2 = false;
        start.setEnabled(true);
        sStart = true;
        reset.setEnabled(false);
        sReset = false;
        pointP1 = 0;
        pointP2 = 0;
        gameP1 = 0;
        gameP2 = 0;
        setP1 = 0;
        setP2 = 0;
        message = "";
        for (int i = 0; i < 5; i++) {
            gamesP1[i] = 0;
            gamesP2[i] = 0;
        }
        displayALL();
    }

    public void displayALL() {
        displayPointsP1();
        displayPointsP2();
        display("" + gameP1, gp1);
        display("" + gameP2, gp2);
        display("" + setP1, sp1);
        display("" + setP2, sp2);
        display(message, messageView);
        for (int i = 0; i < 5; i++) {
            display("" + gamesP1[i], gamesViewP1[i]);
            display("" + gamesP2[i], gamesViewP2[i]);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sPointP1", pointP1);
        outState.putInt("sGameP1", gameP1);
        outState.putInt("sSetP1", setP1);
        outState.putInt("sPointP2", pointP2);
        outState.putInt("sGameP2", gameP2);
        outState.putInt("sSetP2", setP2);
        outState.putInt("sTiebreak", tiebreak);
        outState.putInt("sSetCounter", setCounter);
        outState.putIntArray("sGamesP1", gamesP1);
        outState.putIntArray("sGamesP2", gamesP2);
        outState.putBoolean("start", sStart);
        outState.putBoolean("reset", sReset);
        outState.putBoolean("sip1", sip1);
        outState.putBoolean("sip2", sip2);
        outState.putString("message", message);
        outState.putBoolean("end", end);
        time = c.getBase() - SystemClock.elapsedRealtime();
        c.stop();
        outState.putLong("time", time);
        outState.putString("message", String.valueOf(message));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pointP1 = savedInstanceState.getInt("sPointP1");
        pointP2 = savedInstanceState.getInt("sPointP2");
        gameP1 = savedInstanceState.getInt("sGameP1");
        gameP2 = savedInstanceState.getInt("sGameP2");
        setP1 = savedInstanceState.getInt("sSetP1");
        setP2 = savedInstanceState.getInt("sSetP2");
        tiebreak = savedInstanceState.getInt("sTiebreak");
        setCounter = savedInstanceState.getInt("sSetCounter");
        gamesP1 = savedInstanceState.getIntArray("sGamesP1");
        gamesP2 = savedInstanceState.getIntArray("sGamesP2");
        sStart = savedInstanceState.getBoolean("start");
        sReset = savedInstanceState.getBoolean("reset");
        sip1 = savedInstanceState.getBoolean("sip1");
        sip2 = savedInstanceState.getBoolean("sip2");
        message = savedInstanceState.getString("message");
        end = savedInstanceState.getBoolean("end");
        displayALL();
        start.setEnabled(sStart);
        reset.setEnabled(sReset);
        ip1.setEnabled(sip1);
        ip2.setEnabled(sip2);
        c.setBase(SystemClock.elapsedRealtime() + savedInstanceState.getLong("time", 0));
        if (sStart)
            c.setBase(SystemClock.elapsedRealtime());
        if (!sStart)
            c.start();
        if (end) {
            c.stop();
        }
    }
}
