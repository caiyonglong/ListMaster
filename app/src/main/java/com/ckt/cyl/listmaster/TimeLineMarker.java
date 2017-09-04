package com.ckt.cyl.listmaster;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by D22434 on 2017/8/7.
 */

public class TimeLineMarker extends View {

    private int mMarkerSize;
    private int mMarkerColor = Color.BLUE;
    private int mMarkerOuter = Color.BLUE;
    private int mLineSize = 12;
    private int mBeginLine = Color.GRAY;
    private int mEndLine = Color.GRAY;

    private boolean showBegin = true, showEnd = true;


    private Paint mBeginPaint;
    private Paint mEndPaint;
    private Paint mPaint;
    private Paint mCirclePaint;

    private float x, y, r;


    public TimeLineMarker(Context context) {
        this(context, null);
    }

    public TimeLineMarker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineMarker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    /**
     * 加载自定义属性
     *
     * @param attrs
     */
    private void init(AttributeSet attrs) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TimeLineMarker, 0, 0);

        mMarkerSize = a.getDimensionPixelSize(R.styleable.TimeLineMarker_markerSize, mMarkerSize);
        mLineSize = a.getDimensionPixelSize(R.styleable.TimeLineMarker_lineSize, mLineSize);
        mBeginLine = a.getColor(R.styleable.TimeLineMarker_beginLine, mBeginLine);
        mEndLine = a.getColor(R.styleable.TimeLineMarker_endLine, mEndLine);
        mMarkerColor = a.getColor(R.styleable.TimeLineMarker_marker, mMarkerColor);
        mMarkerOuter = a.getColor(R.styleable.TimeLineMarker_outer, mMarkerOuter);


        mBeginPaint = new Paint();
        mBeginPaint.setColor(mBeginLine);
        mBeginPaint.setStrokeWidth(2);
        mBeginPaint.setStyle(Paint.Style.STROKE);
        mBeginPaint.setAntiAlias(true);


        mEndPaint = new Paint();
        mEndPaint.setColor(mEndLine);
        mEndPaint.setStrokeWidth(2);
        mEndPaint.setStyle(Paint.Style.STROKE);
        mEndPaint.setAntiAlias(true);

        mPaint = new Paint();
        mPaint.setColor(mMarkerColor);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(mMarkerOuter);
        mCirclePaint.setStrokeWidth(3);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        x = measure(widthMeasureSpec) / 2;
        y = measure(heightMeasureSpec) / 2;
        r = 20;

    }

    protected int measure(int measureSpec) {
        int size = 0;
        int measureMode = MeasureSpec.getMode(measureSpec);
        if (measureMode == MeasureSpec.UNSPECIFIED) {
            size = 250;
        } else {
            size = MeasureSpec.getSize(measureSpec);
        }
        return size;

    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (showBegin)
            canvas.drawLine(x, y, x, 0, mBeginPaint);

        if (showEnd)
            canvas.drawLine(x, y, x, y * 2, mEndPaint);

        canvas.drawCircle(x, y, r, mPaint);
        canvas.drawCircle(x, y, r, mCirclePaint);

        super.onDraw(canvas);
    }
    
    public void setLineSize(int lineSize) {
        if (mLineSize != lineSize) {
            this.mLineSize = lineSize;
            invalidate();
        }
    }

    public void setMarkerSize(int markerSize) {
        if (this.mMarkerSize != markerSize) {
            mMarkerSize = markerSize;
            invalidate();
        }
    }

    public void setBeginLine(boolean isShow) {
        showBegin = isShow;
        invalidate();
    }

    public void setEndLine(boolean isShow) {
        showEnd = isShow;
        invalidate();
    }


}
