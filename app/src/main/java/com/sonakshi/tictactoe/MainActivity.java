package com.sonakshi.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int a=0,zzz=1;
    boolean gameisact=true;
    boolean gameover;
    //a=odd red & a=even blue
    int[] gs={2,2,2,2,2,2,2,2,2};
    int[][] winningpos={{0,1,2},{6,7,8},{0,3,6},{1,4,7},{3,4,5},{2,5,8},{0,4,8},{2,4,6}};
    //int[][] winningpos={{0,1,2},{6,7,8},{0,3,6},{1,4,7},{3,4,5},{2,5,8},{0,4,8},{2,4,6}};


    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());
        if (gs[tapped] == 2 && gameisact)
        {
            gs[tapped] = a;
            counter.setTranslationY(-1000f);
            if (a == 0)
            {
                counter.setImageResource(R.drawable.blue);
                a = 1;
                MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.tic);
                mp.start();
            }
            else
                {
                counter.setImageResource(R.drawable.red);
                a = 0;
                    MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.tac);
                    mp.start();
            }
            counter.animate().translationYBy(1000f).setDuration(1);
            for (int w[] : winningpos)
            {
                if (gs[w[0]] == gs[w[1]] && gs[w[1]] == gs[w[2]] && gs[w[0]] != 2) //&& gs[w[1]] != 2  && gs[w[2]] != 2 )
                {
                    gameisact = false;
                    String win = "Red";
                    if (gs[w[0]] == 0) {
                        win = "Blue";
                    }
                    TextView msg = (TextView) findViewById(R.id.msg);
                    //someone has won
                    msg.setText(win + " has won!");
                    MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.claps);
                    mp.start();
                    LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
                    ll1.setVisibility(View.VISIBLE);
                    zzz = 2;

                }
            }
            gameover = true;
            for (int counterState : gs)
            {
                if (counterState == 2)
                {
                    gameover = false;
                }
            }
            if(gameover && zzz==1)
            {
                    TextView msg = (TextView) findViewById(R.id.msg);
                    msg.setText("It's a draw");
                    MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.draw);
                    mp.start();
                    LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
                    ll1.setVisibility(View.VISIBLE);
            }
        }
    }
    public void playagain(View view)
    {
        zzz=1;
        gameisact=true;
        LinearLayout ll1=(LinearLayout)findViewById(R.id.ll1);
        ll1.setVisibility(View.INVISIBLE);
        a=0;
        for(int i=0;i<gs.length;i++)
        {
            gs[i]=2;
        }
        GridLayout gl=(GridLayout) findViewById(R.id.gl);
        for(int i=0;i<gl.getChildCount();i++)
        {
            ((ImageView)gl.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}