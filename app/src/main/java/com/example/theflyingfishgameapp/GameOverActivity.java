package com.example.theflyingfishgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private Button playAgain_btn ;
    private TextView displayScore ;
    private String score ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        playAgain_btn = findViewById(R.id.playagain_btn);
        displayScore= findViewById(R.id.displayscore);

        score=getIntent().getExtras().get("score").toString();

        displayScore.setText("score =" + score);



        playAgain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
