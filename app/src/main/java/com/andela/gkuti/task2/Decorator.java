package com.andela.gkuti.task2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Decorator class
 */
public class Decorator extends RecyclerView.ItemDecoration {
    private Context context;

    /**
     * Constructor for Decorator class
     */
    public Decorator(Context context) {
        this.context = context;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.lightAsh));
        paint.setStrokeWidth(1.0f);
        float startX = 20.0f;
        for (int i = 0, count = parent.getChildCount(); i < count; ++i) {
            View child = parent.getChildAt(i);
            float startY = child.getBottom();
            float stopX = child.getRight();
            c.drawLine(startX, startY, stopX, startY, paint);
        }

    }
}
