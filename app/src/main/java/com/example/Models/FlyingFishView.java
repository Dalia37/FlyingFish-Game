package com.example.Models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.theflyingfishgameapp.GameOverActivity;
import com.example.theflyingfishgameapp.R;

public class FlyingFishView extends View {
    private Bitmap fish[] = new Bitmap[2]; ;
    private Bitmap backgraoundImage ;
    private Paint scorePaint = new Paint() ;
    private Bitmap life[] =new Bitmap[2];


    private int fishx=10 ;
    private int fishy ;
    private int fishspeed ;
    private int canvaseWidth , canvaswHight ;
    private boolean touch = false ;

    private  int yallowx , yallowy , yallowspeed = 16;
    private Paint yallowPant = new Paint();

    private int greenx , greeny , greenspeed=20 ;
    private  Paint greenPaint = new Paint();


    private int redx , redy , redspeed=25 ;
    private  Paint redpaint = new Paint();

    private int score , lifeCounterOffish ;

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgraoundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

         life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
         life[1]= BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
         fishy = 550 ;
         score = 0 ;

         lifeCounterOffish=3;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvaseWidth=canvas.getWidth();
        canvaswHight=canvas.getHeight();
        canvas.drawBitmap(backgraoundImage,0,0,null);

        yallowPant.setColor(Color.GREEN);
        yallowPant.setAntiAlias(false);

        greenPaint.setColor(Color.YELLOW);
        greenPaint.setAntiAlias(false);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);

        int minfishy = fish[0].getHeight();
        int maxfishy = canvaswHight - fish[0].getHeight() * 3 ;
        fishy=fishy+fishspeed;

        if (fishy < minfishy){
            fishy = minfishy ;
        }
        if (fishy > maxfishy){
            fishy = maxfishy ;
        }
        fishspeed = fishspeed+2 ;
        if (touch){
            canvas.drawBitmap(fish[1],fishx,fishy,null);
            touch = false;
        }
        else {
            canvas.drawBitmap(fish[0],fishx,fishy,null);
        }





       //yallowball

        yallowx=yallowx-yallowspeed;

        if (hitballcheaker(yallowx,yallowy)){
            score= score + 10 ;
            yallowx=-100;

        }

        if (yallowx<0){
            yallowx=canvaseWidth+21;
            yallowy=(int) Math.floor(Math.random()* (maxfishy - minfishy))+maxfishy;

        }
        canvas.drawCircle(yallowx,yallowy,25,yallowPant);


        //greenball
        greenx=greenx-greenspeed;

        if (hitballcheaker(greenx,greeny)){
            score= score + 20 ;
            greenx=-100;

        }

        if (greenx<0){
            greenx=canvaseWidth+21;
            greenx=(int) Math.floor(Math.random()* (maxfishy - minfishy))+maxfishy;

        }
        canvas.drawCircle(greenx,greenx,25,greenPaint);

        //redball

        redx=redx-redspeed;

        if (hitballcheaker(redx,redy)){
            score= score + 20 ;
            redx=-100;
            lifeCounterOffish--;
            
            if (lifeCounterOffish ==0 ){
                Toast.makeText(getContext(), "game Over", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), GameOverActivity.class);
                intent.putExtra("score",score);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
            }

        }

        if (redx<0){
            redx=canvaseWidth+21;
            redx=(int) Math.floor(Math.random()* (maxfishy - minfishy))+maxfishy;

        }
        canvas.drawCircle(redx,greenx,30,redpaint);

        canvas.drawText("score :"+ score,20,60,scorePaint);

        for (int i = 0 ; i<3 ; i++){
            int x= (int) (500+ life[0].getWidth() *1.5 * i);
            int y = 30 ;

            if (i<lifeCounterOffish){
                canvas.drawBitmap(life[0],x,y,null);

            }
            else {
                canvas.drawBitmap(life[1],x,y,null);

            }
        }









    }

    public  boolean hitballcheaker(int x  , int y ){
        if(fishx<x && x<(fishx+fish[0].getWidth() ) && fishy<y && y< (fishy+fish[0].getHeight()))
        {
            return true;
        }
        return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch=true;
            fishspeed=22;

        }
        return  true;
    }
}
