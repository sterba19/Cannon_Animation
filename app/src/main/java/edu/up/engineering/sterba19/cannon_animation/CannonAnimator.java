package edu.up.engineering.sterba19.cannon_animation;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

import java.util.ArrayList;

/**
 * Created by sterba19 on 4/2/2017.
 */
public class CannonAnimator implements Animator{

    private int count=0, xBallPos = 400, yBallPos = 1030, xBallVel = 40, yBallVel=-20000%(Math.abs(count)+1), accel=1;
    private int xTarg=1800, yTarg=300, yTarg2 =50;
    private boolean moving = false;
    private boolean change = false;
    private CustomRect stop = new CustomRect("stop",Color.rgb(255,0,255),120,0,360,200);
    private CustomRect fire = new CustomRect("fire",Color.rgb(255,0,0),480,0,700,200);
    private CustomCircle cannonBall;
    private ArrayList<CustomCircle> cannonBalls = new ArrayList<>();
    private CustomTarget targets[] = new CustomTarget[2];
    private CustomTarget target1 = new CustomTarget("target1",Color.rgb(0,0,255),xTarg,yTarg,75,false);
    private CustomTarget target2 = new CustomTarget("target2",Color.rgb(0,0,255),xTarg,yTarg2,75,true);

    public CannonAnimator()
    {
        targets[0]=target1;
        targets[1]=target2;
    }
    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return Color.rgb(255, 204,153);
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void tick(Canvas canvas) {

        for (CustomTarget t:targets) {
            t.drawMe(canvas);
            moveTarget(t);
        }

        this.changeAngle(moving);
        Paint basePaint = new Paint();
        basePaint.setStyle(Paint.Style.FILL);
        basePaint.setColor(Color.rgb(128,128,128));

        canvas.save();
        canvas.rotate(count,110,1030);
        canvas.drawOval(100,960,400,1100,basePaint);
        canvas.restore();

        basePaint.setColor(Color.rgb(153,76,0));
        canvas.drawRect(120,1020,240,1140,basePaint);

        stop.drawMe(canvas);
        fire.drawMe(canvas);

        if(cannonBalls!=null) {
            for (CustomCircle cannonBall :cannonBalls) {


                cannonBall.setX(xBallPos);
                cannonBall.setY(yBallPos);
                canvas.save();
                canvas.rotate(count, 110, yBallPos);
                cannonBall.drawMe(canvas);
                canvas.restore();
                xBallPos += xBallVel;
                yBallPos += yBallVel;
                yBallVel += accel;
            }
        }
    }

    @Override
    public void onTouch(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        if(stop.containsPoint(x,y))
        {
            if(moving)
            {
                setMoving(false);
            }
            else
            {
                setMoving(true);
            }
        }

        if(fire.containsPoint(x,y))
        {
            cannonBall = new CustomCircle("cannonBall",Color.rgb(0,0,0),xBallPos,yBallPos,20);
            cannonBalls.add(cannonBall);
        }


    }

    private void changeAngle(boolean stopPressed) {
        if (!stopPressed) {
            if (count == -90) {
                change = true;
                //count++;
            }
            if (count == 0) {
                change = false;
                //count--;
            }
            if (change) {
                count++;
            }
            if (!change) {
                count--;
            }

        }
    }

    private void moveTarget(CustomTarget t){
        if (t.getY() == 300){
            t.setDir(false);
        }
        if (t.getY() == 50){
            t.setDir(true);
        }
        if(t.getDir()) {
            t.setY(t.getY()+15);
        }
        if(!t.getDir()){
            t.setY(t.getY()-15);
        }
    }
    private void setMoving(boolean init){ moving = init;}
}




