package com.example.lucifesar.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.View;

import com.example.lucifesar.myapplication.Utils;

public class ImageTextView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = Utils.dp2px(100);

    private RectF rectF = new RectF();

    private float IMAGE_WIDTH = Utils.dp2px(100);

    private String targetString = "美国中文网综合报道，当地时间星期六早晨，宾夕凡尼亚州匹兹堡一间犹太教堂突发枪击事件，目前死亡人数已上升到11人，6人受伤，包括四名警察。枪手确认为具有明显反犹太人倾向的46岁白人男子鲍尔斯（Robert Bowers），有民权组织形容，这是美国历史上伤亡最严重的反犹袭击事件。FBI则表示，枪手的犯案动机仍有待调查。检方27日晚以29项罪名起诉嫌犯。美国总统川普下令美国白宫等场所降半旗致哀。" +
            "川普下令，到10月31日为止，白宫、公共场所、军事哨所、海军基地和军舰的所有旗帜都将降半旗，以示对遇难者的“庄严敬意”。川普此前谴责匹兹堡犹太教堂枪击事件是“大规模谋杀的邪恶行径”，并称必须对抗在任何地点和无论何处出现的反犹太主义。美国中文网综合报道，当地时间星期六早晨，宾夕凡尼亚州匹兹堡一间犹太教堂突发枪击事件，目前死亡人数已上升到11人，6人受伤，包括四名警察。枪手确认为具有明显反犹太人倾向的46岁白人男子鲍尔斯（Robert Bowers），有民权组织形容，这是美国历史上伤亡最严重的反犹袭击事件。FBI则表示，枪手的犯案动机仍有待调查。检方27日晚以29项罪名起诉嫌犯。美国总统川普下令美国白宫等场所降半旗致哀。" +
            "            \"川普下令，到10月31日为止，白宫、公共场所、军事哨所、海军基地和军舰的所有旗帜都将降半旗，以示对遇难者的“庄严敬意”。川普此前谴责匹兹堡犹太教堂枪击事件是“大规模谋杀的邪恶行径”，并称必须对抗在任何地点和无论何处出现的反犹太主义。";

    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    private Bitmap bitmap;

    private float [] measuredWidth = new float[1];
    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setColor(Color.parseColor("#000000"));
        paint.setTextSize(Utils.dp2px(14));
        paint.setStyle(Paint.Style.FILL);
        paint.getFontMetrics(fontMetrics);
        bitmap = Utils.getAvatarBitmap(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(getWidth()/2-radius,getHeight()/2-radius,getWidth()/2+radius,getHeight()/2+radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float bitmapTop = (getHeight()-IMAGE_WIDTH)/2;
        canvas.drawBitmap(bitmap,getWidth()-IMAGE_WIDTH,bitmapTop,paint);



        int count;

        float verticalOffset = -fontMetrics.top;
        float maxWidth;

        for (int start = 0; start < targetString.length(); ) {

            float textTop = verticalOffset + fontMetrics.top;
            float textBottom = verticalOffset+fontMetrics.bottom;
            if(textTop>bitmapTop&&textTop<bitmapTop+IMAGE_WIDTH||textBottom>bitmapTop&&textBottom<bitmapTop+IMAGE_WIDTH){
                maxWidth = getWidth()-IMAGE_WIDTH;
            }else{
                maxWidth = getWidth();
            }

            count = paint.breakText(targetString,start,targetString.length(),true,maxWidth,measuredWidth);
            canvas.drawText(targetString, start, count+start, 0, verticalOffset, paint);

            start += count;
            verticalOffset += paint.getFontSpacing();
        }

    }
}
