package com.winto.develop.ThreeTones.wight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.esri.arcgisruntime.mapping.view.MapView;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.util.DensityUtil;

public class ScaleView extends androidx.appcompat.widget.AppCompatTextView {
    private Context context;

    public ScaleView(Context context) {
        this(context, null);
        this.context = context;
        this.initVariables();
    }

    public ScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
        this.initVariables();
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.initVariables();
    }

    private int scaleWidth;
    private int scaleHeight;
    private int textColor;
    private String text;
    private int textSize;
    private int scaleSpaceText;
    private Paint mPaint;

    private void initVariables() {
        scaleWidth = 104;//
        scaleHeight = 8;//比比例尺宽度例尺高度
        textColor = Color.BLACK;//比例尺字体颜色
        text = "20公里";//比例尺文本
        textSize = 26;//比例尺宽度
        scaleSpaceText = 8;//比例尺文本与图形的间隔高度
        mPaint = new Paint();//画笔
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = getWidthSize(widthMeasureSpec);
        int heightSize = getHeightSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

    private int getWidthSize(int widthMeasureSpec) {
        return MeasureSpec.getSize(widthMeasureSpec);
    }

    /**
     * 测量ScaleView的高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int getHeightSize(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        switch (mode) {
            case MeasureSpec.AT_MOST:
                height = textSize + scaleSpaceText + scaleHeight;
                break;
            case MeasureSpec.EXACTLY: {
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            }
            case MeasureSpec.UNSPECIFIED: {
                height = Math.max(textSize + scaleSpaceText + scaleHeight, MeasureSpec.getSize(heightMeasureSpec));
                break;
            }
        }

        return height;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = scaleWidth;
        if (width > DensityUtil.getScreenWidth(context) / 3) {
            width = DensityUtil.getScreenWidth(context) / 3;
        }
        mPaint.setColor(textColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        float textWidth = mPaint.measureText(text);
        canvas.drawText(text, (width - textWidth) / 2, textSize, mPaint);
        Rect scaleRect = new Rect(0, textSize + scaleSpaceText, width, textSize + scaleSpaceText + scaleHeight);
        drawNinepath(canvas, R.drawable.ic_scale_bar, scaleRect);
    }


    private void drawNinepath(Canvas canvas, int resId, Rect rect) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
        NinePatch patch = new NinePatch(bmp, bmp.getNinePatchChunk(), null);
        patch.draw(canvas, rect);
    }

    public void refreshScaleView(MapView mapView) {
        if (mapView == null) {
            throw new NullPointerException("you can call setMapView(MapView mapView) at first");
        }
        double scale = mapView.getMapScale() / 100;//结果单位米，表示图上1厘米代表*米
        double ppi = getPPIOfDevice();
        if (scale > 0 && scale <= 20) {//换算20米
            String unit = "20米";
            int scaleWidth = (int) (20 * ppi / 2.54 / scale);//264为ppi，264/2.54为1厘米的像素数
            text = unit;
            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 20 && scale <= 50) {//换算50米
            String unit = "50米";
            int scaleWidth = (int) (50 * ppi / 2.54 / scale);

            text = unit;
            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 50 && scale <= 100) {//换算20米
            String unit = "100米";
            int scaleWidth = (int) (100 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 100 && scale <= 200) {//换算20米
            String unit = "200米";
            int scaleWidth = (int) (200 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 200 && scale <= 500) {//换算20米
            String unit = "500米";
            int scaleWidth = (int) (500 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 500 && scale <= 1000) {//换算20米
            String unit = "1公里";
            int scaleWidth = (int) (1000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 1000 && scale <= 2000) {//换算20米
            String unit = "2公里";
            int scaleWidth = (int) (2000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 2000 && scale <= 5000) {//换算20米
            String unit = "5公里";
            int scaleWidth = (int) (5000 * ppi / 2.54 / scale);

            text = unit;
            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 5000 && scale <= 10000) {//换算20米
            String unit = "10公里";
            int scaleWidth = (int) (10000 * ppi / 2.54 / scale);

            text = unit;
            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 10000 && scale <= 20000) {//换算20米
            String unit = "20公里";
            int scaleWidth = (int) (20000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 20000 && scale <= 25000) {//换算20米
            String unit = "25公里";
            int scaleWidth = (int) (25000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 25000 && scale <= 50000) {//换算20米
            String unit = "50公里";
            int scaleWidth = (int) (50000 * ppi / 2.54 / scale);

            text = unit;
            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 50000 && scale <= 100000) {//换算20米
            String unit = "100公里";
            int scaleWidth = (int) (100000 * ppi / 2.54 / scale);

            text = unit;
            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 100000 && scale <= 200000) {//换算20米
            String unit = "200公里";
            int scaleWidth = (int) (200000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 200000 && scale <= 250000) {//换算20米
            String unit = "250公里";
            int scaleWidth = (int) (250000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 250000 && scale <= 500000) {//换算20米
            String unit = "500公里";
            int scaleWidth = (int) (500000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        } else if (scale > 500000 && scale <= scale) {//换算20米
            String unit = "1000公里";
            int scaleWidth = (int) (1000000 * ppi / 2.54 / scale);
            text = unit;

            setScaleWidth(scaleWidth);//更新比例尺长度
        }

        invalidate();
    }

    public void setScaleWidth(int scaleWidth) {
        this.scaleWidth = scaleWidth;
    }

    private double getPPIOfDevice() {
        Point point = new Point();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);//获取屏幕的真实分辨率
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double x = Math.pow(point.x / dm.xdpi, 2);//
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return Math.sqrt(Math.pow(point.x, 2) + Math.pow(point.y, 2)) / screenInches;
    }
}
