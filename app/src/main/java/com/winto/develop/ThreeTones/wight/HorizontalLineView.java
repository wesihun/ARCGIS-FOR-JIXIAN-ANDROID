package com.winto.develop.ThreeTones.wight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.winto.develop.ThreeTones.R;

public class HorizontalLineView extends View {

    private int borderWidth = 8;
    private Paint paint;
    private int bendSize;

    public HorizontalLineView(Context context) {
        this(context, null);
    }

    public HorizontalLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HorizontalLineView);
        bendSize = (int) a.getDimension(R.styleable.HorizontalLineView_bendSize, 0);

        a.recycle();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(0xffD8D8D8);
        paint.setStrokeWidth(borderWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int pointX = getWidth() / 2;
        int pointY = getHeight() / 2;
        int top = getHeight() / 2 + bendSize;
        int offsetY = (top - getHeight() / 2) / 2;
        int offsetX = borderWidth / 2;

        canvas.drawLine(
                offsetX,
                pointY - offsetY,
                pointX,
                top - offsetY,
                paint);

        canvas.drawLine(
                pointX,
                top - offsetY,
                getWidth() - offsetX,
                pointY - offsetY,
                paint);
    }

    public void setBeanSize(int size) {
        this.bendSize = size;
        invalidate();
    }
}