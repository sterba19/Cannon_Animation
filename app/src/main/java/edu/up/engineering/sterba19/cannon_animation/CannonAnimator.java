package edu.up.engineering.sterba19.cannon_animation;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.MotionEvent;

/**
 * Created by sterba19 on 4/2/2017.
 */
public class CannonAnimator implements Animator{

    private int count,x,y;
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

        Paint basePaint = new Paint();
        basePaint.setStyle(Paint.Style.FILL);

        basePaint.setColor(Color.rgb(128,128,128));
        canvas.drawOval(100,100,300,300,basePaint);

        basePaint.setColor(Color.rgb(153,76,0));
        canvas.drawRect(120,120,240,240,basePaint);


    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
