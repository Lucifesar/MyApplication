package com.example.lucifesar.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lucifesar.myapplication.Utils;

public class DashBoardView extends View {


    private Paint paint;
    private float radius = Utils.dp2px(150);
    private float angle = 120;
    private float length = Utils.dp2px(100);
    private PathDashPathEffect effect;
    Path path;
    Path dash;



    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(android.R.color.black));
        paint.setStrokeWidth(Utils.dp2px(2));
        path = new Path();
        path.addArc(getWidth()/2-radius,getWidth()/2-radius,
                getWidth()/2+radius,getWidth()/2+radius,90+angle/2,360-angle);
        PathMeasure pathMeasure = new PathMeasure(path,false);

        dash = new Path();
        dash.addRect(0,0,Utils.dp2px(2),Utils.dp2px(10),Path.Direction.CCW);

        effect = new PathDashPathEffect(dash,(pathMeasure.getLength()-Utils.dp2px(2))/20,0,PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);






        canvas.drawArc(getWidth()/2-radius,getWidth()/2-radius,
                getWidth()/2+radius,getWidth()/2+radius,90+angle/2,360-angle,false,paint);



        paint.setPathEffect(effect);
        canvas.drawArc(getWidth()/2-radius,getWidth()/2-radius,
                getWidth()/2+radius,getWidth()/2+radius,90+angle/2,360-angle,false,paint);
        paint.setPathEffect(null);

        canvas.drawLine(getWidth()/2,getWidth()/2,(float) (Math.cos(Math.toRadians(getAngleFromMark(10)))*length+getWidth()/2),
                (float)(Math.sin(Math.toRadians(getAngleFromMark(10)))*length+getWidth()/2),paint);


    }

    int getAngleFromMark(int mark) {
        return (int) (90 + (float) angle / 2 + (360 - (float) angle) / 20 * mark);
    }
}

