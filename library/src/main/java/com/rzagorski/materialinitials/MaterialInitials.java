package com.rzagorski.materialinitials;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Robert Zagórski on 19.11.2016.
 */

public class MaterialInitials extends ImageView {
    private int[] textColors;
    private int[] backgroundColors;
    private String[] texts;

    public MaterialInitials(Context context) {
        super(context);
    }

    public MaterialInitials(Context context, AttributeSet attrs) {
        super(context, attrs);
        texts = new String[]{"AB", "CD", "EF"/*, "G H"*/};
        backgroundColors = new int[]{context.getResources().getColor(android.R.color.holo_blue_dark),
                context.getResources().getColor(android.R.color.holo_orange_dark),
                context.getResources().getColor(android.R.color.holo_red_dark)/*,
                context.getResources().getColor(android.R.color.holo_green_dark)*/};
    }

    public MaterialInitials(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    @Override
    public void draw(Canvas canvas) {
        if (getBackground() != null || getDrawable() != null) {
            super.draw(canvas);
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Path path = new Path();
        path.addCircle(width / 2, height / 2, width / 2, Path.Direction.CW);
        canvas.clipPath(path);
        //canvas.drawColor(Color.parseColor("#FF0000"));
        Paint whitePaint = new Paint();
        whitePaint.setColor(getContext().getResources().getColor(android.R.color.white));
        whitePaint.setStrokeWidth(4);
        whitePaint.setColor(getContext().getResources().getColor(android.R.color.white));
        int angle = 360 / texts.length;
        RectF[] letterPlaces = new RectF[texts.length];
        for (int i = 0; i < texts.length; ++i) {
            Paint paint = new Paint();
            paint.setColor(backgroundColors[i]);
            canvas.drawArc(new RectF(0, 0, width, height), i * angle, angle, true, paint);
            canvas.drawLine(width / 2, height / 2,
                    (int) (width / 2 + width / 2 * Math.cos(Math.toRadians(i * angle))),
                    (int) (height / 2 + height / 2 * Math.sin(Math.toRadians(i * angle))),
                    whitePaint);
            letterPlaces[i] = getTextClipRect(width, height, i, angle);
        }
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#88FFFFFF"));
        paint.setTextSize((int) (height * 1.2));
        Paint secPaint = new Paint();
        secPaint.setColor(Color.parseColor("#88000000"));
        secPaint.setTextSize((int) (height * 1.2));
        if (texts.length > 1) {
            paint.setTextSize(paint.getTextSize() / 2);
        }
        for (int i = 0; i < texts.length; ++i) {
            float verticalDifference = letterPlaces[i].bottom - letterPlaces[i].top;
            float horizontalDifference = letterPlaces[i].right - letterPlaces[i].left;
            if (texts.length == 2) {
                letterPlaces[i].bottom -= verticalDifference * 0.1;
                letterPlaces[i].left += horizontalDifference * 0.1;
            }
            if (isVertical(letterPlaces[i]) && texts.length == 3) {
                letterPlaces[i].left -= horizontalDifference * 0.5;
                letterPlaces[i].top += verticalDifference * 0.25;
                letterPlaces[i].bottom *= 0.75;
            }
            Path clipPath = new Path();
            clipPath.addArc(new RectF(0, 0, width, height), i * angle, angle);
            clipPath.lineTo(width / 2, height / 2);
            clipPath.close();
            canvas.save();
            canvas.clipPath(clipPath, Region.Op.INTERSECT);


            float textWidth = paint.measureText(texts[i]);
            float letterBeginning;
            for (int j = 0 ; j < texts[i].length() ; ++j) {
                letterBeginning = (0.09F + j * 0.27F) * textWidth;
                canvas.drawText(texts[i].toCharArray(), j, 1, letterPlaces[i].left + letterBeginning, letterPlaces[i].bottom, paint);
            }
            canvas.restore();
        }
        canvas.rotate(25, canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    private RectF getTextClipRect(int width, int height, int circleSector, int sectorAngle) {
        RectF rectF = new RectF(Math.round(width / 2 + (width / 2 * Math.cos(Math.toRadians(circleSector * sectorAngle))) * 0.9),
                Math.round(height / 2 + (height / 2 * Math.sin(Math.toRadians(circleSector * sectorAngle))) * 0.9),
                Math.round(width / 2 + (width / 2 * Math.cos(Math.toRadians((circleSector + 1) * sectorAngle))) * 0.9),
                Math.round(height / 2 + (height / 2 * Math.sin(Math.toRadians((circleSector + 1) * sectorAngle))) * 0.9));
        if (rectF.left > rectF.right) {
            float temp = rectF.left;
            rectF.left = rectF.right;
            rectF.right = temp;
        }
        if (rectF.top > rectF.bottom) {
            float temp = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = temp;
        }
        if (rectF.left == rectF.right) {
            rectF.left = 0;
        }
        if (rectF.top == rectF.bottom && texts.length == 2 && circleSector == 1) {
            rectF.top = 0;
        } else if (rectF.top == rectF.bottom && texts.length == 2 && circleSector == 0) {
            rectF.bottom = height;
        }
        return rectF;
    }

    private boolean isVertical(RectF rectF) {
        return rectF.right - rectF.left < rectF.bottom - rectF.top;
    }

    private Point[] getPoints(int x0, int y0, int r, int noOfDividingPoints) {
        double angle = 0;
        Point[] points = new Point[noOfDividingPoints];
        for (int i = 0; i < noOfDividingPoints; i++) {
            angle = i * (360 / noOfDividingPoints);
            points[i] = new Point((int) (x0 + r * Math.cos(Math.toRadians(angle))),
                    (int) (y0 + r * Math.sin(Math.toRadians(angle))));
        }
        return points;
    }
}
