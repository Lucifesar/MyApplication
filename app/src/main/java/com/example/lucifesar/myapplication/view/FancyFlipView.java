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

public class FancyFlipView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();
    private Bitmap bitmap ;

    private int bitmapWidth = (int) Utils.dp2px(300);
    private float degrees = 20;

    public FancyFlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    {
        camera.setLocation(0,0,Utils.getZForCamera());
        bitmap = Utils.getAvatarBitmap(getResources(),bitmapWidth);

    }
    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = (getWidth()-bitmapWidth)/2;
        int top = (getHeight()-bitmapWidth)/2;
        // 绘制上半部分
        canvas.save();
        canvas.translate(left + bitmapWidth / 2, top + bitmapWidth / 2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(- bitmapWidth, - bitmapWidth, bitmapWidth, 0);
        canvas.rotate(flipRotation);
        canvas.translate(- (left + bitmapWidth / 2), - (top + bitmapWidth / 2));
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(left + bitmapWidth / 2, top + bitmapWidth / 2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(- bitmapWidth, 0, bitmapWidth, bitmapWidth);
        canvas.rotate(flipRotation);
        canvas.translate(- (left + bitmapWidth / 2), - (top + bitmapWidth / 2));
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();
    }
}
