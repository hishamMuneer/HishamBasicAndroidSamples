package com.hisham.hishambasicandroidsamples.customview.simple_doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Hisham on 14/Oct/2018 - 11:39
 */
public class DoodleView extends View {

    private Paint paintDoodle = new Paint();
    private Path pathDoodle = new Path();

    public DoodleView(Context context) {
        super(context);
        init(null, 0 , 0 );
    }

    public DoodleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0,0);
    }


    public DoodleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    public DoodleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        paintDoodle.setColor(Color.RED);
        paintDoodle.setAntiAlias(true); // smooths out the edges
        paintDoodle.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0, 0, getWidth(), getHeight(), paintDoodle);
        canvas.drawPath(pathDoodle, paintDoodle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pathDoodle.moveTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_MOVE:
                pathDoodle.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
