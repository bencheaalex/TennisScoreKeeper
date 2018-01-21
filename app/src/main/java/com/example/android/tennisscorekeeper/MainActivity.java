package com.example.android.tennisscorekeeper;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

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
    Chronometer cs1;
    Chronometer cs2;
    Chronometer cs3;
    Chronometer cs4;
    Chronometer cs5;
    TextView pp1;
    TextView pp2;
    TextView gp1;
    TextView gp2;
    TextView sp1;
    TextView sp2;
    TextView p1s1;
    TextView p1s2;
    TextView p1s3;
    TextView p1s4;
    TextView p1s5;
    TextView p2s1;
    TextView p2s2;
    TextView p2s3;
    TextView p2s4;
    TextView p2s5;
    TextView message;
    Button ip1, ip2, reset, start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = (Chronometer) findViewById(R.id.mainTimer);
        cs1 = (Chronometer) findViewById(R.id.t1);
        cs2 = (Chronometer) findViewById(R.id.t2);
        cs3 = (Chronometer) findViewById(R.id.t3);
        cs4 = (Chronometer) findViewById(R.id.t4);
        cs5 = (Chronometer) findViewById(R.id.t5);
        pp1 = (TextView) findViewById(R.id.points_player1);
        pp2 = (TextView) findViewById(R.id.points_player2);
        gp1 = (TextView) findViewById(R.id.game_player1);
        gp2 = (TextView) findViewById(R.id.game_player2);
        sp1 = (TextView) findViewById(R.id.set_player1);
        sp2 = (TextView) findViewById(R.id.set_player2);
        p1s1 = (TextView) findViewById(R.id.p1s1);
        p1s2 = (TextView) findViewById(R.id.p1s2);
        p1s3 = (TextView) findViewById(R.id.p1s3);
        p1s4 = (TextView) findViewById(R.id.p1s4);
        p1s5 = (TextView) findViewById(R.id.p1s5);
        p2s1 = (TextView) findViewById(R.id.p2s1);
        p2s2 = (TextView) findViewById(R.id.p2s2);
        p2s3 = (TextView) findViewById(R.id.p2s3);
        p2s4 = (TextView) findViewById(R.id.p2s4);
        p2s5 = (TextView) findViewById(R.id.p2s5);
        message = (TextView) findViewById(R.id.end_game);
        ip1=(Button)findViewById(R.id.ip1);
        ip2=(Button)findViewById(R.id.ip2);
        start=(Button)findViewById(R.id.startButton);
        reset=(Button)findViewById(R.id.resetButton);
        ip1.setEnabled(false);
        ip2.setEnabled(false);
        reset.setEnabled(false);
    }

    public void increase_p1(View view) {
        pointP1 = pointP1 + 1;
        if (tiebreak == 0) {
            verifyP1();
        }
        else {
            verifyT1();
        }
    }

    public void increase_p2(View view) {
        pointP2 = pointP2 + 1;
        if (tiebreak == 0) {
            verifyP2();
        }
        else {
            verifyT2();
        }
    }

    public void verifyP1() {
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
            else {
                gameP1 = gameP1 + 1;
                pointP1 = 0;
                pointP2 = 0;
                display(String.valueOf(pointP1), pp1);
                display(String.valueOf(pointP2), pp2);
                display(String.valueOf(gameP1), gp1);
                verifyG1();
            }

    }


    public void verifyP2() {
        if (pointP2 > 3 && pointP2 - pointP1 > 1) {
            gameP2 = gameP2 + 1;
            pointP1 = 0;
            pointP2 = 0;
            display(String.valueOf(pointP1), pp1);
            display(String.valueOf(pointP2), pp2);
            display(String.valueOf(gameP2), gp2);
            verifyG2();
        } else {
            if (pointP2 == 1)
                display("15", pp2);
            if (pointP2 == 2)
                display("30", pp2);
            if (pointP2 == 3)
                display("40", pp2);
            if (pointP2 > 3)
                if (pointP2 == pointP1) {
                    display("40", pp2);
                    display("40", pp1);
                } else
                    display("ADV", pp2);
        }
    }

    public void display(String what, TextView where) {
        where.setText(String.valueOf(what));
    }

    public void verifyG1() {
        if (gameP1 > 5)
            if (gameP1 - gameP2 > 1) {
                setP1 = setP1 + 1;
                setCounter++;

                if (setCounter == 1) {
                    display(String.valueOf(gameP1), p1s1);
                    display(String.valueOf(gameP2), p2s1);
                    cs1.stop();
                    cs2.setBase(SystemClock.elapsedRealtime());
                    cs2.start();

                }
                if (setCounter == 2) {
                    display(String.valueOf(gameP1), p1s2);
                    display(String.valueOf(gameP2), p2s2);
                    cs2.stop();
                    cs3.setBase(SystemClock.elapsedRealtime());
                    cs3.start();
                }
                if (setCounter == 3) {
                    display(String.valueOf(gameP1), p1s3);
                    display(String.valueOf(gameP2), p2s3);
                    cs3.stop();
                    cs4.setBase(SystemClock.elapsedRealtime());
                    cs4.start();
                }
                if (setCounter == 4) {
                    display(String.valueOf(gameP1), p1s4);
                    display(String.valueOf(gameP2), p2s4);
                    cs4.stop();
                    cs5.setBase(SystemClock.elapsedRealtime());
                    cs5.start();
                }
                if (setCounter == 5) {
                    display(String.valueOf(gameP1), p1s5);
                    display(String.valueOf(gameP2), p2s5);
                    cs5.stop();
                    c.stop();
                }
                gameP1 = 0;
                gameP2 = 0;
                display(String.valueOf(gameP1), gp1);
                display(String.valueOf(gameP2), gp2);
                display(String.valueOf(setP1), sp1);
                verifyS(setP1, "PLAYER1 WIN");
            } else if (gameP1 - gameP2 == 1)
                display(String.valueOf(gameP1), gp1);
            else {
                display("TieBreak", message);
                tiebreak = 1;
            }
        else
            display(String.valueOf(gameP1), gp1);
    }

    public void verifyG2() {
        if (gameP2 > 5)
            if (gameP2 - gameP1 > 1) {
                setP2 = setP2 + 1;
                setCounter++;
                if (setCounter == 1) {
                    display(String.valueOf(gameP1), p1s1);
                    display(String.valueOf(gameP2), p2s1);
                    cs1.stop();
                    cs2.setBase(SystemClock.elapsedRealtime());
                    cs2.start();
                }
                if (setCounter == 2) {
                    display(String.valueOf(gameP1), p1s2);
                    display(String.valueOf(gameP2), p2s2);
                    cs2.stop();
                    cs3.setBase(SystemClock.elapsedRealtime());
                    cs3.start();
                }
                if (setCounter == 3) {
                    display(String.valueOf(gameP1), p1s3);
                    display(String.valueOf(gameP2), p2s3);
                    cs3.stop();
                    cs4.setBase(SystemClock.elapsedRealtime());
                    cs4.start();
                }
                if (setCounter == 4) {
                    display(String.valueOf(gameP1), p1s4);
                    display(String.valueOf(gameP2), p2s4);
                    cs4.stop();
                    cs5.setBase(SystemClock.elapsedRealtime());
                    cs5.start();
                }
                if (setCounter == 5) {
                    display(String.valueOf(gameP1), p1s5);
                    display(String.valueOf(gameP2), p2s5);
                    cs5.stop();
                    c.stop();
                }
                gameP1 = 0;
                gameP2 = 0;
                display(String.valueOf(gameP1), gp1);
                display(String.valueOf(gameP2), gp2);
                display(String.valueOf(setP2), sp2);
                verifyS(setP2, "PLAYER2 WIN");
            } else if (gameP2 - gameP1 == 1)
                display(String.valueOf(gameP2), gp2);
            else {
                display("TieBreak", message);
                tiebreak = 1;
            }
        else
            display(String.valueOf(gameP2), gp2);
    }

    public void verifyS(int i, String k) {
        if (i == 3) {
            display(k, message);
            pointP1 = 0;
            gameP1 = 0;
            pointP2 = 0;
            gameP2 = 0;
            ip1.setEnabled(false);
            ip2.setEnabled(false);
            start.setEnabled(false);
            c.stop();
            cs4.stop();
            cs5.stop();
        }
    }

    public void reset() {
        pointP1 = 0;
        gameP1 = 0;
        setP1 = 0;
        pointP2 = 0;
        gameP2 = 0;
        setP2 = 0;
        display(String.valueOf(pointP1), pp1);
        display(String.valueOf(pointP2), pp2);
        display(String.valueOf(gameP1), gp1);
        display(String.valueOf(gameP2), gp2);
        display(String.valueOf(setP1), sp1);
        display(String.valueOf(setP2), sp2);
        tiebreak = 0;
        setCounter = 0;
        c.stop();
        cs1.setBase(SystemClock.elapsedRealtime());
        cs2.setBase(SystemClock.elapsedRealtime());
        cs3.setBase(SystemClock.elapsedRealtime());
        cs4.setBase(SystemClock.elapsedRealtime());
        cs5.setBase(SystemClock.elapsedRealtime());
        c.setBase(SystemClock.elapsedRealtime());
        pp1.setText("-");
        pp2.setText("-");
        gp1.setText("-");
        gp2.setText("-");
        sp1.setText("-");
        sp2.setText("-");
        p1s1.setText("-");
        p1s2.setText("-");
        p1s3.setText("-");
        p1s4.setText("-");
        p1s5.setText("-");
        p2s1.setText("-");
        p2s2.setText("-");
        p2s3.setText("-");
        p2s4.setText("-");
        p2s5.setText("-");
        message.setText("");
    }

    public void reset_button(View view) {
        reset();
        ip1.setEnabled(false);
        ip2.setEnabled(false);
        start.setEnabled(true);
    }

    public void start_button(View view) {
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
        cs1.setBase(SystemClock.elapsedRealtime());
        cs1.start();
        ip1.setEnabled(true);
        ip2.setEnabled(true);
        reset.setEnabled(true);
        start.setEnabled(false);

    }
    public void verifyT1(){
        display(String.valueOf(pointP1), pp1);
        if(pointP1>6 && pointP1-pointP2>1){
            tiebreak=0;
            message.setText("");
            setP1++;
            display(String.valueOf(setP1), sp1);
            setCounter++;
            gameP1++;

            if (setCounter == 1) {
                display(String.valueOf(gameP1), p1s1);
                display(String.valueOf(gameP2), p2s1);
                cs1.stop();
                cs2.setBase(SystemClock.elapsedRealtime());
                cs2.start();

            }
            if (setCounter == 2) {
                display(String.valueOf(gameP1), p1s2);
                display(String.valueOf(gameP2), p2s2);
                cs2.stop();
                cs3.setBase(SystemClock.elapsedRealtime());
                cs3.start();
            }
            if (setCounter == 3) {
                display(String.valueOf(gameP1), p1s3);
                display(String.valueOf(gameP2), p2s3);
                cs3.stop();
                cs4.setBase(SystemClock.elapsedRealtime());
                cs4.start();
            }
            if (setCounter == 4) {
                display(String.valueOf(gameP1), p1s4);
                display(String.valueOf(gameP2), p2s4);
                cs4.stop();
                cs5.setBase(SystemClock.elapsedRealtime());
                cs5.start();
            }
            if (setCounter == 5) {
                display(String.valueOf(gameP1), p1s5);
                display(String.valueOf(gameP2), p2s5);
                cs5.stop();
                c.stop();
            }
            pointP1=0;
            display(String.valueOf(pointP1), pp1);
            gameP1=0;
            display(String.valueOf(gameP1), gp1);
            pointP2=0;
            display(String.valueOf(pointP2), pp2);
            gameP2=0;
            display(String.valueOf(gameP2), gp2);

        }
    }
    public void verifyT2(){
        display(String.valueOf(pointP2), pp2);
        if(pointP2>6 && pointP2-pointP1>1){
            tiebreak=0;
            setP2++;
            display(String.valueOf(setP2), sp2);
            setCounter++;
            gameP2++;
            if (setCounter == 1) {
                display(String.valueOf(gameP1), p1s1);
                display(String.valueOf(gameP2), p2s1);
                cs1.stop();
                cs2.setBase(SystemClock.elapsedRealtime());
                cs2.start();
            }
            if (setCounter == 2) {
                display(String.valueOf(gameP1), p1s2);
                display(String.valueOf(gameP2), p2s2);
                cs2.stop();
                cs3.setBase(SystemClock.elapsedRealtime());
                cs3.start();
            }
            if (setCounter == 3) {
                display(String.valueOf(gameP1), p1s3);
                display(String.valueOf(gameP2), p2s3);
                cs3.stop();
                cs4.setBase(SystemClock.elapsedRealtime());
                cs4.start();
            }
            if (setCounter == 4) {
                display(String.valueOf(gameP1), p1s4);
                display(String.valueOf(gameP2), p2s4);
                cs4.stop();
                cs5.setBase(SystemClock.elapsedRealtime());
                cs5.start();
            }
            if (setCounter == 5) {
                display(String.valueOf(gameP1), p1s5);
                display(String.valueOf(gameP2), p2s5);
                cs5.stop();
                c.stop();
            }
            pointP2=0;
            display(String.valueOf(pointP2), pp2);
            gameP2=0;
            display(String.valueOf(gameP2), gp2);
            pointP1=0;
            display(String.valueOf(pointP1), pp1);
            gameP1=0;
            display(String.valueOf(gameP1), gp1);

        }
    }

}
