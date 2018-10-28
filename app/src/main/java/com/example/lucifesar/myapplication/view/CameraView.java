package com.example.lucifesar.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lucifesar.myapplication.Utils;

public class CameraView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();
    private Bitmap bitmap ;

    private int bitmapWidth = 600;
    private float degrees = 20;

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    {
        camera.rotateX(45);
        camera.setLocation(0,0,Utils.getZForCamera());
        bitmap = Utils.getAvatarBitmap(getResources(),bitmapWidth);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = (getWidth()-bitmapWidth)/2;
        int top = (getHeight()-bitmapWidth)/2;
        // 绘制上半部分
        canvas.save();
        canvas.translate(left + bitmapWidth / 2, top + bitmapWidth / 2);
        canvas.rotate(-degrees);
        canvas.clipRect(- bitmapWidth, - bitmapWidth, bitmapWidth, 0);
        canvas.rotate(degrees);
        canvas.translate(- (left + bitmapWidth / 2), - (top + bitmapWidth / 2));
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(left + bitmapWidth / 2, top + bitmapWidth / 2);
        canvas.rotate(-degrees);
        camera.applyToCanvas(canvas);
        canvas.clipRect(- bitmapWidth, 0, bitmapWidth, bitmapWidth);
        canvas.rotate(degrees);
        canvas.translate(- (left + bitmapWidth / 2), - (top + bitmapWidth / 2));
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();
    }
}
