package com.fenjuly.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liurongchan with love on 15/8/11.
 */
public class ArrowDownloadButton extends View {

    private static final int BLUE_ONE = Color.rgb(46, 164, 242);
    private static final int WHILE = Color.rgb(255, 255, 255);
    private static final float RADIUS = 180;
    private static final int TRI_POINT_NUMBER = 17;
    private static final float MAX_WAVE_HEIGHT = 10;
    private static final float MIN_WAVE_HEIGHT = 5;
    private static final int PROGRESS = 100;
    private static final int ANGLE = 360;
    private static final float TEXT_Y = 67.5f;
    private static final float OFFSET = 10;
    private static final float SMALL_RADIUS = 5;
    private static final float TEXT_SIZE = 40;
    private static final float ARC_WIDTH = 20;
    private static final float ARROW_WIDTH = 10;
    private static final float TRI_WIDTH = 10;
    private static final float LOADING_WIDTH = 10;

    private static final float STEP = 2;
    private static final float ELASTICITY_STEP = 10;
    private static final float ROPE_STEP_X = 30;
    private static final float ROPE_STEP_Y = 32;
    private static final float ROPE_HEAD_STEP_Y = 17;
    private static final float JUMP_STEP = 45;
    private static final float DOWN_STEP = 7.5f;
    private static final float TRI_STEP = 16.875f;
    private static final float TIME_STEP = 20;
    private static final float HOOK_STEP_Y = 15;
    private static final float HOOK_COUNT = 4;
    private static final float LITTLE_STEP = 8;
    private static final int DURATION = 20;
    private static final int COMPLETE_DURATION = 20;

    /**
     * start instance
     **/
    private static final String INSTANCE_STATE = "instance_state";
    /**
     *
     */
    private static final String X_I = "x";
    private static final String Y_I = "y";
    private static final String RADIUS_I = "radius";
    private static final String MAX_WAVE_HEIGHT_I = "max_wave_height";
    private static final String MIN_WAVE_HEIGHT_I = "min_wave_height";
    private static final String TEXT_Y_I = "text_y";
    private static final String STEP_I = "step";
    private static final String ELASTICITY_STEP_I = "elasticity_step";
    private static final String ROPE_STEP_X_I = "rope_step_x";
    private static final String ROPE_STEP_Y_I = "rope_step_y";
    private static final String ROPE_HEAD_STEP_Y_I = "rope_head_step_y";
    private static final String JUMP_STEP_I = "jump_step";
    private static final String DOWN_STEP_I = "down_step";
    private static final String TRI_STEP_I = "tri_step";
    private static final String HOOK_STEP_Y_I = "hook_step";
    private static final String LITTLE_STEP_I = "little_step";
    private static final String SMALL_RADIUS_I = "small_radius";
    private static final String TEXT_SIZE_I = "text_size";
    private static final String ARC_WIDTH_I = "arc_width";
    private static final String ARROW_WIDTH_I = "arrow_width";
    private static final String TRI_WIDTH_I = "tri_width";
    private static final String LOADING_WIDTH_I = "loading_width";
    private static final String IS_FIRST_I = "is_first";
    private static final String IS_ANIMATING_I = "is_animating";
    private static final String BEZIER_I = "bezier";
    private static final String IS_LOADING_I = "is_loading";
    private static final String IS_COMPLETED_I = "is_completed";
    private static final String IS_END_I = "is_end";
    private static final String COUNT_I = "count";
    private static final String LENGTH_I = "length";
    private static final String CURRENT_TIME_I = "current_time";
    private static final String WAVE_HEIGHT_I = "wave_height";
    private static final String PROGRESS_I = "progress";
    private static final String HOOK_COUNT_I = "hook_count";
    private static final String LENGTH_X_I = "length_x";
    private static final String LENGTH_Y_I = "length_y";


    private float x = 550;
    private float y = 550;
    private float radius = RADIUS;
    private float maxWaveHeight = MAX_WAVE_HEIGHT;
    private float minWaveHeight = MIN_WAVE_HEIGHT;
    private float textY = TEXT_Y;
    private float step = STEP;
    private float elasticityStep = ELASTICITY_STEP;
    private float ropeStepX = ROPE_STEP_X;
    private float ropeStepY = ROPE_STEP_Y;
    private float ropeHeadStepY = ROPE_HEAD_STEP_Y;
    private float jumpStep = JUMP_STEP;
    private float downStep = DOWN_STEP;
    private float triStep = TRI_STEP;
    private float hookStepY = HOOK_STEP_Y;
    private float littleStep = LITTLE_STEP;
    private float smallRadius = SMALL_RADIUS;
    private float textSize = TEXT_SIZE;
    private float arcWidth = ARC_WIDTH;
    private float arrowWidth = ARROW_WIDTH;
    private float triWidth = TRI_WIDTH;
    private float loadingWidth = LOADING_WIDTH;

    private Paint arrowPaint;
    private Paint arcPaint;
    private Paint smallPaint;
    private Paint triPaint;
    private Paint loadingPaint;
    private Paint textPaint;

    private Path arrowPath;
    private Path triPath;
    private Path textPath;

    private RectF oval;

    private Point a;
    private Point b;
    private Point c;
    private Point d;
    private Point e;
    private Point jumpPoint;

    private List<Point> triPoints = new ArrayList<>();

    private boolean isFirst = true;
    private boolean isAnimating = false;
    private boolean bezier = false;
    private boolean isLoading = false;
    private boolean isCompleted = false;
    private boolean isEnd = false;
    private int count = 0;
    private float length;
    private int currentTime = 0;
    private float waveHeight = MIN_WAVE_HEIGHT;
    private float progress = 0;
    private int hookCount = 0;
    float lengthX = 3 * radius / 4;
    float lengthY = 3 * radius / 4;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        if (progress > 100) {
            this.progress = 100;
        } else {
            this.progress = progress;
        }

        if (progress == 100) {
            isLoading = false;
            isCompleted = true;
        }
    }

    public ArrowDownloadButton(Context context) {
        this(context, null);
    }

    public ArrowDownloadButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowDownloadButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isFirst) {
            init();
            isFirst = false;
        }
        canvas.drawCircle(x, y, radius, arcPaint);
        drawArrow(canvas);
        if (isAnimating) {
            animating();
        }
        if (isLoading) {
            loading(canvas);
        }
        if (isCompleted) {
            afterCompleted(canvas);
        }
    }

    private void init() {
        float temp = getHeight() > getWidth() ? getWidth() / 2 : getHeight() / 2;
        radius = temp - temp * OFFSET / RADIUS - temp * ELASTICITY_STEP / RADIUS - 6;
        x = getPaddingLeft() + getWidth() / 2;
        y = getPaddingTop() + getHeight() / 2;
        maxWaveHeight = convert(MAX_WAVE_HEIGHT);
        minWaveHeight = convert(MIN_WAVE_HEIGHT);
        textY = convert(TEXT_Y);
        step = convert(STEP);
        elasticityStep = convert(ELASTICITY_STEP);
        ropeStepX = convert(ROPE_STEP_X);
        ropeStepY = convert(ROPE_STEP_Y);
        ropeHeadStepY = convert(ROPE_HEAD_STEP_Y);
        jumpStep = convert(JUMP_STEP);
        downStep = convert(DOWN_STEP);
        triStep = convert(TRI_STEP);
        hookStepY = convert(HOOK_STEP_Y);
        littleStep = convert(LITTLE_STEP);
        smallRadius = convert(SMALL_RADIUS);
        textSize = convert(TEXT_SIZE);
        arcWidth = convert(ARC_WIDTH);
        arrowWidth = convert(ARROW_WIDTH);
        triWidth = convert(TRI_WIDTH);
        loadingWidth = convert(LOADING_WIDTH);
        lengthX = 3 * radius / 4;
        lengthY = 3 * radius / 4;

        arrowPath = new Path();
        triPath = new Path();
        textPath = new Path();
        oval = new RectF();
        oval.left = x - radius;
        oval.top = y - radius;
        oval.right = x + radius;
        oval.bottom = y + radius;
        length = radius / 2;
        initializePaints();
        initializePoints();
    }

    /**
     * start animating before loading
     */
    public void startAnimating() {
        isAnimating = true;
        invalidate();
    }

    /**
     * reset to initial state
     */
    public void reset() {
        isAnimating = false;
        isLoading = false;
        bezier = false;
        isCompleted = false;
        isEnd = false;
        length = radius / 2;
        count = 0;
        hookCount = 0;
        jumpPoint.x = -1;
        progress = 0;
        lengthX = 3 * radius / 4;
        lengthY = 3 * radius / 4;
        a.y = y + length;
        b.y = y - length;
        e.y = y + length;
        c.x = x - length / 2;
        c.y = y + length / 2;
        d.x = x + length / 2;
        d.y = y + length / 2;
        invalidate();
    }

    /**
     * animating
     */
    public void animating() {
        if (count < 19) {
            length = length * 3 / 4;
            a.y = y + length;
            b.y = y - length;

            if (((count + 1) % 3) == 0 && count < 9) {
                e.y = e.y + step;
                c.y = c.y + step;
                d.y = d.y + step;
            }
            if (count > 8 && count < 12) {
                jumpPoint.x = x;
                jumpPoint.y = y - jumpStep * (count - 8);
                c.x = c.x - ropeStepX;
                c.y = c.y - ropeHeadStepY;
                d.x = d.x + ropeStepX;
                d.y = d.y - ropeHeadStepY;
                e.y = e.y - ropeStepY;
            }
            if (count > 11) {
                bezier = true;
                if (count == 12) {
                    jumpPoint.y = jumpPoint.y - jumpStep * 2;
                } else {
                    jumpPoint.y = jumpPoint.y + downStep;
                    if (count < 16) {
                        int time1 = 15 - count;
                        e.y = y + time1 * elasticityStep;
                    }
                }
            }
            count++;
            postInvalidateDelayed(DURATION);
        } else {
            isAnimating = false;
            bezier = false;
            if (progress != 100) {
                isLoading = true;
            } else {
                isLoading = false;
                isCompleted = true;
            }
        }
    }

    /**
     * under loading
     *
     * @param canvas Target Canvas
     */
    private void loading(Canvas canvas) {
        Point currentPoint = triPoints.get(0);
        Point nextPoint;
        for (int i = 0; i < TRI_POINT_NUMBER; i++) {
            Point p = triPoints.get(i);
            p.x = (x - 3 * radius / 4) + triStep * i;
            p.y = y + calculateTri(TIME_STEP * i, currentTime);
        }
        for (int i = 1; i < TRI_POINT_NUMBER; i++) {
            nextPoint = triPoints.get(i);
            triPath.reset();
            triPath.moveTo(currentPoint.x, currentPoint.y);
            triPath.lineTo(nextPoint.x, nextPoint.y);
            canvas.drawCircle(nextPoint.x, nextPoint.y, smallRadius, smallPaint);
            canvas.drawPath(triPath, triPaint);
            currentPoint = nextPoint;
        }
        textPath.moveTo(x - textSize, y + textY);
        textPath.lineTo(x + textSize, y + textY);
        canvas.drawTextOnPath((int) progress + "%", textPath, 0, 0, textPaint);
        currentTime = (int) (currentTime + TIME_STEP);
        float sweepAngle = (progress / PROGRESS * ANGLE);
        canvas.drawArc(oval, 270, 0 - sweepAngle, false, loadingPaint);
        postInvalidateDelayed(DURATION);
    }

    /**
     * the method do such things:
     * 1.draw arrow.
     * 2.when animate was completed, let the small ball jump.
     *
     * @param canvas Target Canvas
     */
    protected void drawArrow(Canvas canvas) {
        if (jumpPoint.x != -1) {
            canvas.drawCircle(jumpPoint.x, jumpPoint.y, smallRadius, smallPaint);
        }
        if (bezier) {
            arrowPath.reset();
            arrowPath.moveTo(c.x, c.y);
            arrowPath.quadTo(e.x, e.y, d.x, d.y);
            canvas.drawPath(arrowPath, arrowPaint);
        }  else if (isLoading) {
        } else if (isCompleted) {
        }  else if (isEnd) {
            canvas.drawCircle(x, y, radius, loadingPaint);
            drawArrowOrHook(canvas);
        } else {
            arrowPath.reset();
            arrowPath.moveTo(a.x, a.y);
            arrowPath.lineTo(b.x, b.y);
            canvas.drawPath(arrowPath, arrowPaint);

            canvas.drawCircle(a.x, a.y, smallRadius, smallPaint);
            canvas.drawCircle(b.x, b.y, smallRadius, smallPaint);

            drawArrowOrHook(canvas);

        }
    }

    /**
     * draw arrow or hook
     *
     * @param canvas Target Canvas
     */
    private void drawArrowOrHook(Canvas canvas) {
        arrowPath.reset();
        arrowPath.moveTo(e.x, e.y);
        arrowPath.lineTo(c.x, c.y);
        canvas.drawPath(arrowPath, arrowPaint);
        arrowPath.reset();
        arrowPath.moveTo(e.x, e.y);
        arrowPath.lineTo(d.x, d.y);
        canvas.drawPath(arrowPath, arrowPaint);

        canvas.drawCircle(c.x, c.y, smallRadius, smallPaint);
        canvas.drawCircle(d.x, d.y, smallRadius, smallPaint);
        canvas.drawCircle(e.x, e.y, smallRadius, smallPaint);
    }

    /**
     * the animate after loading
     *
     * @param canvas Target Canvas
     */
    private void afterCompleted(Canvas canvas) {
        canvas.drawCircle(x, y, radius, loadingPaint);
        if (hookCount == HOOK_COUNT - 1) {
            e.y = e.y + littleStep;
            c.x = c.x - littleStep;
            d.x = d.x + littleStep;
            d.y = d.y - littleStep;
            isCompleted = false;
            isEnd = true;
        } else {
            e.x = x;
            e.y = y + hookStepY * (hookCount + 1);
            lengthX = lengthX * 3 / 4;
            c.x = x - lengthX * 3 / 4;
            c.y = y;
            d.x = x + lengthY - radius / (float) 8 * (hookCount + 1);
            d.y = y - hookStepY * (hookCount + 1);
            hookCount++;
        }
        drawArrowOrHook(canvas);
        postInvalidateDelayed(COMPLETE_DURATION);

    }

    protected void initializePaints() {
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(arcWidth);
        arcPaint.setColor(BLUE_ONE);

        arrowPaint = new Paint();
        arrowPaint.setAntiAlias(true);
        arrowPaint.setStyle(Paint.Style.STROKE);
        arrowPaint.setStrokeWidth(arrowWidth);
        arrowPaint.setColor(WHILE);

        smallPaint = new Paint();
        smallPaint.setAntiAlias(true);
        smallPaint.setStyle(Paint.Style.FILL);
        smallPaint.setColor(WHILE);

        triPaint = new Paint();
        triPaint.setAntiAlias(true);
        triPaint.setStyle(Paint.Style.STROKE);
        triPaint.setStrokeWidth(triWidth);
        triPaint.setColor(WHILE);

        loadingPaint = new Paint();
        loadingPaint.setAntiAlias(true);
        loadingPaint.setStyle(Paint.Style.STROKE);
        loadingPaint.setStrokeWidth(loadingWidth);
        loadingPaint.setColor(WHILE);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(1);
        textPaint.setColor(WHILE);
        textPaint.setTextSize(textSize);
    }

    protected void initializePoints() {
        a = new Point(x, y + radius / 2);
        b = new Point(x, y - radius / 2);
        c = new Point(x - radius / 4, y + radius / 4);
        d = new Point(x + radius / 4, y + radius / 4);
        e = new Point(x, y + radius / 2);
        jumpPoint = new Point();

        for (int i = 0; i < TRI_POINT_NUMBER; i++) {
            Point point = new Point();
            point.x = (x - 3 * radius / 4) + triStep * i;
            point.y = y + calculateTri(TIME_STEP * i, 0);
            triPoints.add(point);
        }
    }

    /**
     * calculate the wave
     *
     * @param originalTime original time
     * @param currentTime  current time
     * @return wave
     */
    private float calculateTri(float originalTime, float currentTime) {
        if (progress < PROGRESS / 3) {
            waveHeight = MIN_WAVE_HEIGHT;
        } else if (progress < PROGRESS * 2 / 3) {
            waveHeight = maxWaveHeight;
        } else {
            waveHeight = minWaveHeight;
        }
        return (float) (waveHeight * Math.sin((Math.PI / 80) * (originalTime + currentTime)));
    }

    private float convert(float original) {
        return radius * original / RADIUS;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putFloat(X_I, x);
        bundle.putFloat(Y_I, y);
        bundle.putFloat(RADIUS_I, radius);
        bundle.putFloat(MAX_WAVE_HEIGHT_I, maxWaveHeight);
        bundle.putFloat(MIN_WAVE_HEIGHT_I, minWaveHeight);
        bundle.putFloat(TEXT_Y_I, textY);
        bundle.putFloat(STEP_I, step);
        bundle.putFloat(ELASTICITY_STEP_I, elasticityStep);
        bundle.putFloat(ROPE_STEP_X_I, ropeStepX);
        bundle.putFloat(ROPE_STEP_Y_I, ropeStepY);
        bundle.putFloat(ROPE_HEAD_STEP_Y_I, ropeHeadStepY);
        bundle.putFloat(JUMP_STEP_I, jumpStep);
        bundle.putFloat(DOWN_STEP_I, downStep);
        bundle.putFloat(TRI_STEP_I, triStep);
        bundle.putFloat(HOOK_STEP_Y_I, hookStepY);
        bundle.putFloat(LITTLE_STEP_I, littleStep);
        bundle.putFloat(SMALL_RADIUS_I, smallRadius);
        bundle.putFloat(TEXT_SIZE_I, textSize);
        bundle.putFloat(ARC_WIDTH_I, arcWidth);
        bundle.putFloat(ARROW_WIDTH_I, arrowWidth);
        bundle.putFloat(TRI_WIDTH_I, triWidth);
        bundle.putFloat(LOADING_WIDTH_I, loadingWidth);
        bundle.putBoolean(IS_FIRST_I, isFirst);
        bundle.putBoolean(IS_ANIMATING_I, isAnimating);
        bundle.putBoolean(BEZIER_I, bezier);
        bundle.putBoolean(IS_LOADING_I, isLoading);
        bundle.putBoolean(IS_COMPLETED_I, isCompleted);
        bundle.putBoolean(IS_END_I, isEnd);
        bundle.putInt(COUNT_I, count);
        bundle.putFloat(LENGTH_I, length);
        bundle.putInt(CURRENT_TIME_I, currentTime);
        bundle.putFloat(WAVE_HEIGHT_I, waveHeight);
        bundle.putFloat(PROGRESS_I, progress);
        bundle.putInt(HOOK_COUNT_I, hookCount);
        bundle.putFloat(LENGTH_X_I, lengthX);
        bundle.putFloat(LENGTH_Y_I, lengthY);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
            x = bundle.getFloat(X_I);
            y = bundle.getFloat(Y_I);
            radius = bundle.getFloat(RADIUS_I);
            maxWaveHeight = bundle.getFloat(MAX_WAVE_HEIGHT_I);
            minWaveHeight = bundle.getFloat(MIN_WAVE_HEIGHT_I);
            textY = bundle.getFloat(TEXT_Y_I);
            step = bundle.getFloat(STEP_I);
            elasticityStep = bundle.getFloat(ELASTICITY_STEP_I);
            ropeStepX = bundle.getFloat(ROPE_STEP_X_I);
            ropeStepY = bundle.getFloat(ROPE_STEP_Y_I);
            ropeHeadStepY = bundle.getFloat(ROPE_HEAD_STEP_Y_I);
            jumpStep = bundle.getFloat(JUMP_STEP_I);
            downStep = bundle.getFloat(DOWN_STEP_I);
            triStep = bundle.getFloat(TRI_STEP_I);
            hookStepY = bundle.getFloat(HOOK_STEP_Y_I);
            littleStep = bundle.getFloat(LITTLE_STEP_I);
            smallRadius = bundle.getFloat(SMALL_RADIUS_I);
            textSize = bundle.getFloat(TEXT_SIZE_I);
            arcWidth = bundle.getFloat(ARC_WIDTH_I);
            arrowWidth = bundle.getFloat(ARROW_WIDTH_I);
            triWidth = bundle.getFloat(TRI_WIDTH_I);
            loadingWidth = bundle.getFloat(LOADING_WIDTH_I);
            isFirst = bundle.getBoolean(IS_FIRST_I);
            isAnimating = bundle.getBoolean(IS_ANIMATING_I);
            bezier = bundle.getBoolean(BEZIER_I);
            isLoading = bundle.getBoolean(IS_LOADING_I);
            isCompleted = bundle.getBoolean(IS_COMPLETED_I);
            isEnd = bundle.getBoolean(IS_END_I);
            count = bundle.getInt(COUNT_I);
            length = bundle.getFloat(LENGTH_I);
            currentTime = bundle.getInt(CURRENT_TIME_I);
            waveHeight = bundle.getFloat(WAVE_HEIGHT_I);
            progress = bundle.getFloat(PROGRESS_I);
            hookCount = bundle.getInt(HOOK_COUNT_I);
            lengthX = bundle.getFloat(LENGTH_X_I);
            lengthY = bundle.getFloat(LENGTH_Y_I);
        }
        super.onRestoreInstanceState(state);
    }

    static class Point {
        public float x;
        public float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            x = -1;
            y = -1;
        }
    }
}
