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

    private Paint textPaint;
    private Paint whitePaint;

    public MaterialInitialsDrawable() {
        super();
        createPaints();
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
        if (texts == null) {
            return;
        }
        setTexts(texts);
    }

    public MaterialInitialsDrawable(int[] backgroundColors, String[] texts) {
        this();
        this.backgroundColors = backgroundColors;
        if (texts == null) {
            return;
        }
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
        textPaint.setAlpha(i);
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    public void setTextAlpha(int alpha) {
        textPaint.setAlpha(alpha);
    }

    private void createPaints() {
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAlpha(136);
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
        textPaint.setTextSize((height));
        int angle = 360 / (texts.length == 0 ? 1 : texts.length);
        if (texts.length == 0) {
            throw new IllegalStateException("Texts null");
        }
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
            textPaint.setTextSize(textPaint.getTextSize() / 2);
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
            textPaint.getTextBounds(texts[i], 0, texts[i].length(), textBounds);
            Rect letterPlaces = canvas.getClipBounds();
            float spacePercentage = 0.8F;
            float kernedTextWidth = measureKernedText(texts[i], textPaint, spacePercentage);
            float letterBottom = letterPlaces.bottom - (letterPlaces.height() - textBounds.height()) / 2;
            float letterStart = letterPlaces.left + (letterPlaces.width() - kernedTextWidth) / 2;
            drawKernedText(canvas, texts[i], letterStart, letterBottom, textPaint, spacePercentage);
            if (texts.length > 1) {
                canvas.restore();
            }
        }
        canvas.rotate(25, canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    /**
     * Draw kerned text by drawing the text string character by character with a space in between.
     * Return the width of the text.
     * If canvas is null, the text won't be drawn, but the width will still be returned/
     * kernPercentage determines the space between each letter. If it's 0, there will be no space between letters.
     * Otherwise, there will be space between each letter. The  value is a fraction of the width of a blank space.
     *
     * @author TER
     */
    private void drawKernedText(Canvas canvas, String text, float xOffset, float yOffset, Paint paint, float kernPercentage) {
        Rect textRect = new Rect();
        int space = Math.round(paint.measureText(" ") * kernPercentage);
        for (int i = 0; i < text.length(); i++) {
            String letter = String.valueOf(text.charAt(i));
            if (letter.equalsIgnoreCase("i")) {
                xOffset += space / 2;
            }
            if (canvas != null) {
                canvas.drawText(letter, xOffset, yOffset, paint);
            }
            int charWidth;
            paint.getTextBounds(text, i, i + 1, textRect);
            charWidth = textRect.width() - space;
            xOffset += charWidth;
        }
    }

    private int measureKernedText(String text, Paint paint, float kernPercentage) {
        Rect textRect = new Rect();
        int width = 0;
        int space = Math.round(paint.measureText(" ") * kernPercentage);
        for (int i = 0; i < text.length(); i++) {
            String letter = String.valueOf(text.charAt(i));
            if (letter.equalsIgnoreCase("i")) {
                width += space / 2;
            }
            int charWidth;
            paint.getTextBounds(text, i, i + 1, textRect);
            charWidth = textRect.width() - space;
            if (letter.equalsIgnoreCase("i")) {
                width += space / 2;
            }
            width += charWidth;
        }
        return width + space;
    }
}
