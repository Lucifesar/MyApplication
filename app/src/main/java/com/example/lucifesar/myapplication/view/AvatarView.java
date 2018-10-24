package com.example.lucifesar.myapplication.view;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.example.lucifesar.myapplication.R;
import com.example.lucifesar.myapplication.Utils;

public class AvatarView extends View {



	float radius = Utils.dp2px(50);
	float boundsWidth = Utils.dp2px(5);
	RectF rectF = new RectF();
	RectF boundsRectF = new RectF();



	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

	public AvatarView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	{

		paint.setStrokeWidth(boundsWidth);
		paint.setColor(Color.parseColor("#FF0000"));


	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		rectF.set(w/2-radius,h/2-radius,w/2+radius,h/2+radius);
		boundsRectF.set(w/2-radius+boundsWidth,h/2-radius+boundsWidth,w/2+radius-boundsWidth,h/2+radius-boundsWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);


		canvas.drawOval(rectF,paint);


		int save = canvas.saveLayer(rectF,paint);

		canvas.drawOval(boundsRectF,paint);

		paint.setXfermode(xfermode);
		canvas.drawBitmap(getAvatarBitmap((int) (radius*2)),(float)getWidth()/2-radius,(float)getHeight()/2-radius,paint);
		paint.setXfermode(null);

		canvas.restoreToCount(save);

	}


	private Bitmap getAvatarBitmap(int width){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian,options);
		options.inJustDecodeBounds = false;
		options.inDensity = options.outWidth;
		options.inTargetDensity = width;
		return BitmapFactory.decodeResource(getResources(),R.drawable.avatar_rengwuxian,options);

	}
}
