package com.itraveltech.m1app.views;

/**
 * Created by Jimmy on 2023/5/16.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.itraveltech.m1app.R;

public class ProgressBarWithDotsAndLineView extends View {

    private static final int NUM_DOTS = 5;
    private static final int DOT_RADIUS = 15;
    private static final int LINE_WIDTH = 10;
    private static final int SPACE_BETWEEN_DOTS = 150;

    private Paint dotPaint;
    private Paint linePaint;

    private int progress;

    public ProgressBarWithDotsAndLineView(Context context) {
        super(context);
        init();
    }

    public ProgressBarWithDotsAndLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressBarWithDotsAndLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dotPaint = new Paint();
        dotPaint.setColor(getResources().getColor(R.color.main_green));

        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.main_green));
        linePaint.setStrokeWidth(LINE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewWidth = getWidth();
        int viewHeight = getHeight();

        int totalLineWidth = SPACE_BETWEEN_DOTS * (NUM_DOTS - 1);
        int dotsWidth = (DOT_RADIUS * 2) * NUM_DOTS;
        int startX = (viewWidth - totalLineWidth - dotsWidth) / 2;
        int startY = viewHeight / 2;

        for (int i = 0; i < NUM_DOTS; i++) {
            boolean isFilled = (i < progress);
            int dotX = startX + (SPACE_BETWEEN_DOTS + (DOT_RADIUS * 2)) * i;
            canvas.drawCircle(dotX, startY, DOT_RADIUS, dotPaint);
            if (isFilled) {
                canvas.drawCircle(dotX, startY, DOT_RADIUS - (LINE_WIDTH / 2), linePaint);
            }
        }

        for (int i = 0; i < NUM_DOTS - 1; i++) {
            boolean isFilled = (i < progress - 1);
            int lineStartX = startX + (SPACE_BETWEEN_DOTS + (DOT_RADIUS * 2)) * i + DOT_RADIUS;
            int lineEndX = lineStartX + SPACE_BETWEEN_DOTS;
            if (isFilled) {
                canvas.drawLine(lineStartX, startY, lineEndX, startY, linePaint);
            } else {
                canvas.drawLine(lineStartX, startY, lineEndX, startY, dotPaint);
            }
        }
    }

    public void setProgress(int progress) {
        this.progress = Math.max(0, Math.min(progress, NUM_DOTS));
        invalidate();
    }
}