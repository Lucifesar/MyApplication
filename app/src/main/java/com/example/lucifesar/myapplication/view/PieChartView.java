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

public class PieChartView extends View {


	private float radius = Utils.dp2px(50);
	private float length = Utils.dp2px(10);

	RectF rectF = new RectF();
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	String [] colors = {"#FF3E487E","#FFDE980B","#FF17CECE","#FFD90F08","#FF900BEF"};
	int [] angles = {120,90,60,30,60};
	int currentAngles = 0;

	public PieChartView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	{

		paint.setStyle(Paint.Style.FILL);
//		rectF.set(getWidth()/2-radius,getHeight()/2-radius,getWidth()/2+radius,getHeight()/2+radius);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		rectF.set(getWidth()/2-radius,getHeight()/2-radius,getWidth()/2+radius,getHeight()/2+radius);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);


		for(int i = 0;i<angles.length;i++){
			paint.setColor(Color.parseColor(colors[i]));
			canvas.save();
			if(i==2){
				canvas.translate((float) Math.cos(Math.toRadians(currentAngles+angles[i]/2))*length,
								 (float) Math.sin(Math.toRadians(currentAngles+angles[i]/2))*length);
			}
			canvas.drawArc(rectF,currentAngles,angles[i],true,paint);

			canvas.restore();

			currentAngles+=angles[i];

		}

	}
}
