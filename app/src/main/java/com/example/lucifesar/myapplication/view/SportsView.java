package com.example.lucifesar.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lucifesar.myapplication.Utils;

public class SportsView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = Utils.dp2px(80);

    private RectF rectF = new RectF();

    private String targetString = "abab";

    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setStrokeWidth(Utils.dp2px(10));
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(getWidth()/2-radius,getHeight()/2-radius,getWidth()/2+radius,getHeight()/2+radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(Utils.dp2px(10));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#90A4AE"));
        canvas.drawArc(rectF,0,360,false,paint);

        paint.setStrokeWidth(Utils.dp2px(10));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#FF4081"));
        canvas.drawArc(rectF,-90,270,false,paint);

        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Utils.dp2px(30));
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.ascent+fontMetrics.descent)/2;
        canvas.drawText(targetString,0,targetString.length(),getWidth()/2,getHeight()/2-offset,paint);
    }
}
