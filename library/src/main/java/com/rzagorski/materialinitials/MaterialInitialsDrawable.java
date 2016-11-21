package com.rzagorski.materialinitials;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
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
        int angle = 360 / (texts.length == 0 ? 1 : texts.length);
        RectF[] letterPlaces = new RectF[texts.length];
        for (int i = 0; i < texts.length; ++i) {
            Paint paint = new Paint();
            int color;
            if (backgroundColors != null) {
                color = backgroundColors[i];
            } else {
                color = backgroundColorsMaterial500[(int) Math.round(Math.random() * (backgroundColorsMaterial500.length - 1))];
            }
            paint.setColor(color);
            canvas.drawArc(new RectF(0, 0, width, height), i * angle, angle, true, paint);
            if (texts.length > 1) {
                canvas.drawLine(width / 2, height / 2,
                        (int) (width / 2 + width / 2 * Math.cos(Math.toRadians(i * angle))),
                        (int) (height / 2 + height / 2 * Math.sin(Math.toRadians(i * angle))),
                        whitePaint);
            }
            letterPlaces[i] = getTextClipRect(width, height, i, angle);
        }
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#88FFFFFF"));
        paint.setTextSize((int) (height * 1.2));
        if (texts.length > 1) {
            paint.setTextSize(paint.getTextSize() / 2);
        }
        for (int i = 0; i < texts.length; ++i) {
            float verticalDifference = letterPlaces[i].bottom - letterPlaces[i].top;
            float horizontalDifference = letterPlaces[i].right - letterPlaces[i].left;
            if (texts.length == 1) {
                letterPlaces[i].bottom -= verticalDifference * 0.1;
                letterPlaces[i].left -= horizontalDifference * 0.2;
            }
            if (texts.length == 2) {
                letterPlaces[i].bottom -= verticalDifference * 0.1;
                letterPlaces[i].left += horizontalDifference * 0.1;
            }
            if (isVertical(letterPlaces[i]) && texts.length == 3) {
                letterPlaces[i].left -= horizontalDifference * 0.5;
                letterPlaces[i].top += verticalDifference * 0.25;
                letterPlaces[i].bottom *= 0.75;
            }
            if (texts.length > 1) {
                Path clipPath = new Path();
                clipPath.addArc(new RectF(0, 0, width, height), i * angle, angle);
                clipPath.lineTo(width / 2, height / 2);
                clipPath.close();
                canvas.save();
                canvas.clipPath(clipPath, Region.Op.INTERSECT);
            }

            float textWidth = paint.measureText(texts[i]);
            float letterBeginning;
            for (int j = 0; j < texts[i].length(); ++j) {
                float beginning = texts.length == 4 ? 0 : 0.09F;
                float spacing;
                switch (texts[i].length()) {
                    case 1:
                        spacing = 0.33F;
                        break;
                    case 2:
                        spacing = j * 0.27F;
                        break;
                    case 3:
                        beginning -= 0.12F;
                        spacing = j * 0.18F;
                        break;
                    default:
                        spacing = 0.33F;
                        break;
                }
                letterBeginning = (beginning + spacing) * textWidth;
                canvas.drawText(texts[i].toCharArray(), j, 1, letterPlaces[i].left + letterBeginning, letterPlaces[i].bottom, paint);
            }
            if (texts.length > 1) {
                canvas.restore();
            }
        }
        canvas.rotate(25, canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    private RectF getTextClipRect(int width, int height, int circleSector, int sectorAngle) {
        RectF rectF;
        if (sectorAngle == 360) {
            rectF = new RectF(0, 0, width, height);
            return rectF;
        }
        rectF = new RectF(Math.round(width / 2 + (width / 2 * Math.cos(Math.toRadians(circleSector * sectorAngle))) * 0.9),
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
}
