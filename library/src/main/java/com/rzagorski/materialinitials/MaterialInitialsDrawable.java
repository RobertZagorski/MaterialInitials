package com.rzagorski.materialinitials;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/**
 * Created by Robert Zagórski on 20.11.2016.
 */

public class MaterialInitialsDrawable extends Drawable {
    private int[] backgroundColorsMaterial500;
    private int[] backgroundColors;
    private String[] texts;

    public MaterialInitialsDrawable() {
        super();
        texts = new String[]{};
        backgroundColorsMaterial500 = new int[]{Color.rgb(244, 67, 54),
                Color.rgb(233, 30, 99),
                Color.rgb(156, 39, 176),
                Color.rgb(103, 58, 183),
                Color.rgb(63, 81, 181),
                Color.rgb(33, 150, 243),
                Color.rgb(3, 169, 244),
                Color.rgb(0, 188, 212),
                Color.rgb(0, 150, 136),
                Color.rgb(76, 175, 80),
                Color.rgb(139, 195, 74),
                Color.rgb(205, 220, 57),
                Color.rgb(255, 235, 59),
                Color.rgb(255, 193, 7),
                Color.rgb(255, 152, 0),
                Color.rgb(255, 87, 34),
                Color.rgb(121, 85, 72),
                Color.rgb(158, 158, 158),
                Color.rgb(96, 125, 139)};
    }

    public MaterialInitialsDrawable(String[] texts) {
        this();
        setTexts(texts);
    }

    public MaterialInitialsDrawable(int[] backgroundColors, String[] texts) {
        this.backgroundColors = backgroundColors;
        setTexts(texts);
    }

    public void setTexts(String... inputTexts) {
        if (inputTexts == null) {
            throw new IllegalArgumentException("Passed texts cannot be null");
        }
        texts = new String[inputTexts.length];
        for (int i = 0; i < 4 && i < inputTexts.length; ++i) {
            if (inputTexts[i] == null) {
                throw new IllegalArgumentException("Passed texts cannot be null");
            }
            String[] parts;
            if (inputTexts[i].contains(" ")) {
                parts = inputTexts[i].split(" ");
            } else {
                parts = new String[]{inputTexts[i]};
            }
            String outputText = "";
            for (int j = 0; j < parts.length && j < 3; ++j) {
                outputText += parts[j].substring(0, 1);
            }
            texts[i] = outputText;
        }
    }

    public void setBackgroundColors(int[] colors) {
        backgroundColors = colors;
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Path path = new Path();
        path.addCircle(width / 2, height / 2, width / 2, Path.Direction.CW);
        canvas.clipPath(path);
        //canvas.drawColor(Color.parseColor("#FF0000"));
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStrokeWidth(4);
        whitePaint.setColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#88FFFFFF"));
        paint.setTextSize((height));
        int angle = 360 / (texts.length == 0 ? 1 : texts.length);
        for (int i = 0; i < texts.length; ++i) {
            Paint backgroundPaint = new Paint();
            int color;
            if (backgroundColors != null) {
                color = backgroundColors[i];
            } else {
                color = backgroundColorsMaterial500[(int) Math.round(Math.random() * (backgroundColorsMaterial500.length - 1))];
            }
            backgroundPaint.setColor(color);
            canvas.drawArc(new RectF(0, 0, width, height), i * angle, angle, true, backgroundPaint);
            if (texts.length > 1) {
                canvas.drawLine(width / 2, height / 2,
                        (int) (width / 2 + width / 2 * Math.cos(Math.toRadians(i * angle))),
                        (int) (height / 2 + height / 2 * Math.sin(Math.toRadians(i * angle))),
                        whitePaint);
            }
        }
        if (texts.length > 1) {
            paint.setTextSize(paint.getTextSize() / 2);
        }
        for (int i = 0; i < texts.length; ++i) {
            if (texts.length > 1) {
                canvas.save();
                Path clipPath = new Path();
                clipPath.addArc(new RectF(0, 0, width, height), i * angle, angle);
                clipPath.lineTo(width / 2, height / 2);
                clipPath.close();
                canvas.clipPath(clipPath, Region.Op.INTERSECT);
            }
            Rect textBounds = new Rect();
            paint.getTextBounds(texts[i], 0, texts[i].length(), textBounds);
            Rect letterPlaces = canvas.getClipBounds();
            float originalTextWidth = textBounds.width();
            textBounds.left += originalTextWidth * 0.09;
            textBounds.right -= originalTextWidth * 0.09;
            float textWidth = textBounds.width();
            for (int j = 0; j < texts[i].length(); ++j) {
                float letterStart = letterPlaces.left + (letterPlaces.width() - textBounds.width()) / 2 + j * textWidth / texts[i].length();
                float letterWidth = paint.measureText(texts[i].substring(j, j + 1));
                if (originalTextWidth / texts[i].length() > letterWidth && j > 0) {
                    letterStart += (paint.measureText(texts[i].substring(j - 1, j)) - letterWidth) / 2;
                }
                float letterBottom = letterPlaces.bottom - (letterPlaces.height() - textBounds.height()) / 2;
                canvas.drawText(texts[i].toCharArray(), j, 1, letterStart, letterBottom, paint);
            }
            if (texts.length > 1) {
                canvas.restore();
            }
        }
        canvas.rotate(25, canvas.getWidth() / 2, canvas.getHeight() / 2);
    }
}
