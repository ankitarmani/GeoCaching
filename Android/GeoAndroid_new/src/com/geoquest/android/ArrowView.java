package com.geoquest.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * .
 * @author Beatriz .
 */
public class ArrowView extends View {

    /**
     * .
     */
    private Paint mPaint = new Paint();
    /**
     * .
     */
    private Path mPath = new Path();
    /**
     * .
     */
    private float[] mValues;
    /**
     * .
     */
    private double directionAngle;
    /**
     * .
     */
    private static final int MIN = 40;
    /**
     * .
     */
    private static final int MID = 60;
    /**
     * .
     */
    private static final int MAX = 120;

    /**
     * .
     * @param context .
     */
    public ArrowView(final Context context) {
        super(context);
        mPath.moveTo(0, -MID);
        mPath.lineTo(-MIN, MAX);
        mPath.lineTo(0, MID);
        mPath.lineTo(MIN, MAX);
        mPath.close();
    }

    /**
     * .
     * @param canvas .
     */
    @Override
    protected final void onDraw(final Canvas canvas) {
        Paint paint = mPaint;

        canvas.drawColor(Color.BLACK);

        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        if (mValues != null) {
            //Log.d("value:",-mValues[0]+"");
            canvas.rotate(-mValues[0] + (float) directionAngle);
        }
        canvas.drawPath(mPath, mPaint);

    }

    /**
     * .
     * @param mValues1 .
     */
    public final void setValues(final float[] mValues1) {
        this.mValues = mValues1;
    }

    /**
     * .
     * @param directionAngle1 .
     */
    public final void setDirectionAngle(final double directionAngle1) {
        this.directionAngle = directionAngle1;
    }
}
