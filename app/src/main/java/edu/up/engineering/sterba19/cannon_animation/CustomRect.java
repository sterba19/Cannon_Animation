package edu.up.engineering.sterba19.cannon_animation;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * <!-- class CustomRect -->
 *
 * This class defines a custom drawing element that is a rectangle.
 *
 * @author Andrew Nuxoll
 * @version Spring 2015
 * @see CustomElement
 *
 */

public class CustomRect extends CustomElement {


    /** the position and size of the rectangle is stored here */
    protected Rect myRect;

    /** the rectangles dimensions must be defined at construction */
    public CustomRect(String name, int color,
                      int left, int top, int right, int bottom)
    {
        super(name, color);

        this.myRect = new Rect(left, top, right, bottom);
    }


    @Override
    public void drawMe(Canvas canvas) {
        canvas.drawRect(myRect, myPaint);  //main rectangle
        canvas.drawRect(myRect, outlinePaint);  //outline around rectangle
    }

    @Override
    public boolean containsPoint(int x, int y) {

        //Want to check for a tap within a slightly larger rectangle
        int left = this.myRect.left - TAP_MARGIN;
        int top = this.myRect.top - TAP_MARGIN;
        int right = this.myRect.right + TAP_MARGIN;
        int bottom = this.myRect.bottom + TAP_MARGIN;
        Rect r = new Rect(left, top, right, bottom);

        return r.contains(x, y);
    }//contaisPoint


    @Override
    public int getSize() {
        return this.myRect.width() * this.myRect.height();
    }

    @Override
    public void drawHighlight(Canvas canvas) {
        canvas.drawRect(myRect, highlightPaint);
        canvas.drawRect(myRect, outlinePaint);  //keep outline so it stands out
    }

    public void explode(Canvas canvas)
    {
        Paint explosion = new Paint();
        explosion.setColor(Color.rgb(255,0,0));
        canvas.drawCircle(240,1140,100,explosion);
        explosion.setColor(Color.rgb(255,165,0));
        canvas.drawCircle(100,980,100,explosion);
        explosion.setColor(Color.rgb(255,215,0));
        canvas.drawCircle(360,980,100,explosion);
        explosion.setColor(Color.rgb(0,0,0));
        canvas.drawText("KaBOOM!!",100,940,explosion);
    }




}//class CustomRect
