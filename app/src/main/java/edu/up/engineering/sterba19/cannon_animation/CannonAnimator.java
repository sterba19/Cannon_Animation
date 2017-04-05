package edu.up.engineering.sterba19.cannon_animation;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by sterba19 on 4/2/2017.
 */
public class CannonAnimator implements Animator{

    private int count =0, xBall = 110, yBall = 1030;
    private boolean moving = false, fired = false;
    private boolean change = false;
    private CustomRect stop = new CustomRect("stop",Color.rgb(255,0,255),120,0,360,200);
    private CustomRect fire = new CustomRect("fire",Color.rgb(255,0,0),480,0,700,200);
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
        Log.d("moving is",String.valueOf(moving));
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

        if(fired)
        {
            basePaint.setColor(Color.rgb(0,0,0));

            while(xBall<600 && yBall<3000) {
                canvas.save();
                canvas.rotate(count, 110, 1030);
                canvas.drawCircle(xBall, yBall, 20, basePaint);
                canvas.restore();
                xBall++;
                yBall++;
            }

            setFired(false);
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

    public void setMoving(boolean init){ moving = init;}
    public void setFired(boolean init){ fired = init;}
}




