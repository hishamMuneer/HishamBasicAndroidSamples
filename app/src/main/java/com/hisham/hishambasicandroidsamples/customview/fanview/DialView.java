package com.hisham.hishambasicandroidsamples.customview.fanview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hisham.hishambasicandroidsamples.R;


/**
 * Created by Hisham on 14/Oct/2018 - 12:24
 */
public class DialView extends View {

    private static final int SELECTION_COUNT = 4; // total default selections
    private int indicatorsCount; // indicators provided by user customized
    private float width;
    private float height;
    private Paint textPaint;
    private Paint dialPaint;
    private float radiusCircle;
    private int activeSelection;
    private final StringBuffer tempLabel = new StringBuffer(8);
    private final float[] tempResult = new float[2];
    private int fanOffColor;
    private int fanOnColor;

    public DialView(Context context) {
        super(context);
        init(null, 0);
    }


    public DialView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DialView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        // process attributes
        fanOffColor = Color.GRAY; // default fan off color
        fanOnColor = Color.GREEN; // default fan on color
        if(attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DialView,
                    defStyleAttr, 0);
            fanOnColor = typedArray.getColor(R.styleable.DialView_fan_on_color, fanOnColor);
            fanOffColor = typedArray.getColor(R.styleable.DialView_fan_off_color, fanOffColor);
            activeSelection = Math.abs(typedArray.getInt(R.styleable.DialView_default_position_of_fan_indicator, 0) % SELECTION_COUNT);
            indicatorsCount = typedArray.getInt(R.styleable.DialView_indicators_count, SELECTION_COUNT);
            typedArray.recycle();
        }

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(40f);
        dialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setDialPaintColor(activeSelection);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activeSelection = (activeSelection + 1) % indicatorsCount;
                setDialPaintColor(activeSelection);
                invalidate();
            }
        });
    }

    private void setDialPaintColor(int activeSelection) {
        if(activeSelection >= 1){
            dialPaint.setColor(fanOnColor);
        } else {
            dialPaint.setColor(fanOffColor);
        }
    }

    private float[] computeXYForPositionCustomIndicators(final int position, final float radius) {
        if(indicatorsCount <= 6){
            return computeXYForPosition(position, radius);
        }
        float[] result = tempResult;
        Double startAngle = Math.PI * (3 / 2d);
        Double angle = startAngle + (position * (Math.PI / indicatorsCount));
        result[0] = (float) (radius * Math.cos(angle * 2)) + (width / 2);
        result[1] = (float) (radius * Math.sin(angle * 2)) + (height / 2);
        if ((angle > Math.toRadians(360))) {
            result[1] += 40;
        }
        return result;
    }
    private float[] computeXYForPosition(final int position, final float radius){
        float[] result = tempResult;
        Double startAngle = Math.PI * (9/8d);
        Double angle = startAngle + (position * (Math.PI / 4));
        result[0] = (float) (radius * Math.cos(angle)) + (this.width / 2);
        result[1] = (float) (radius * Math.sin(angle)) + (this.height / 2);
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w;
        this.height = h;
        this.radiusCircle = (float) (Math.min(this.width, this.height) / 2 * 0.8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(this.width/2, this.height /2, radiusCircle, dialPaint); // draw dial
        // draw text labels
        final float labelRadius = radiusCircle + 20;
        StringBuffer label = tempLabel;
        for(int i = 0; i < indicatorsCount; i++){
            float[] xyData = computeXYForPositionCustomIndicators(i, labelRadius);
            float x = xyData[0];
            float y = xyData[1];
            label.setLength(0);
            label.append(i);
            canvas.drawText(label, 0, label.length(), x, y, textPaint);
        }
        // draw indicator
        final float indicatorRadius = radiusCircle - 35;
        float[] xyData = computeXYForPositionCustomIndicators(activeSelection, indicatorRadius);
        canvas.drawCircle(xyData[0], xyData[1], 20, textPaint);


    }

    public int getFanOffColor() {
        return fanOffColor;
    }

    public DialView setFanOffColor(int fanOffColor) {
        this.fanOffColor = fanOffColor;
        setDialPaintColor(activeSelection);
        invalidate();
        return this;
    }

    public int getFanOnColor() {
        return fanOnColor;
    }

    public DialView setFanOnColor(int fanOnColor) {
        this.fanOnColor = fanOnColor;
        setDialPaintColor(activeSelection);
        invalidate();
        return this;
    }

    public int getIndicatorsCount() {
        return indicatorsCount;
    }

    public DialView setIndicatorsCount(int indicatorsCount) {
//        if(indicatorsCount > 20){
//            this.indicatorsCount = 20;
//        } else {
            this.indicatorsCount = indicatorsCount;
//        }
        invalidate();
        return this;
    }
}
