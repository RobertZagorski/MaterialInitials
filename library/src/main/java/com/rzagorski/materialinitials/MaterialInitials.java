/*
 * Copyright (C) 2016 Robert Zagórski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rzagorski.materialinitials;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * A view, that automatically draws {@link com.rzagorski.materialinitials.MaterialInitialsDrawable}
 * as its background.
 * <p>
 * The view can be controlled by the following {@code .xml} attributes:
 * <ul>
 * <li>
 * {@link com.rzagorski.materialinitials.R.styleable#MaterialInitials_mi_background_colors mi_background_colors}
 * - {@code Integer} array of color resources
 * </li>
 * <li>
 * {@link com.rzagorski.materialinitials.R.styleable#MaterialInitials_mi_rotation mi_rotation}
 * - {@code Float} representing rotation in degrees in clockwise direction</li>
 * <li>
 * {@link com.rzagorski.materialinitials.R.styleable#MaterialInitials_mi_texts mi_texts}
 * - {@code String} array of texts. Each text must contain at most 2 spaces.
 * </li>
 * <li>
 * {@link com.rzagorski.materialinitials.R.styleable#MaterialInitials_mi_texts alpha}
 * - {@code int} represents the alpha channel of a color used to draw initials. Between 0 and 255.
 * </li>
 * </ul>
 * <p>
 * Exposes an API to customise the drawing using the following functions (equivalents of the options above):
 * <ul>
 * <li>
 * {@link #setBackgroundColors(int...)}
 * </li>
 * <li>
 * {@link #setTextRotation(float)}
 * </li>
 * <li>
 * {@link #setTexts(String...)}
 * </li>
 * <li>
 * {@link #setTextAlpha(int)}
 * </li>
 * </ul>
 * <p>
 * Default parameters:
 * <ul>
 * <li>
 * The texts are empty
 * </li>
 * <li>
 * The text color is white (#FFFFFF)
 * </li>
 * <li>
 * The alpha is 136 which is 88 in HEX
 * </li>
 * <li>
 * The background colors are defined as 500 colors from <a href="https://material.google.com/style/color.html#color-color-palette">the material palette</a>
 * </li>
 * </ul>
 * <p>
 * Created by Robert Zagórski on 19.11.2016.
 */

public class MaterialInitials extends View {
    MaterialInitialsDrawable miDrawable;

    public MaterialInitials(Context context) {
        super(context);
    }

    public MaterialInitials(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDrawable(attrs);
    }

    public MaterialInitials(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDrawable(attrs);
    }

    private void initDrawable(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MaterialInitials);
        int color = typedArray.getColor(R.styleable.MaterialInitials_mi_text_color, resolveColor(android.R.color.white));
        int alpha = typedArray.getInt(R.styleable.MaterialInitials_mi_text_alpha, 136);
        int backgroundColorsResource = typedArray.getResourceId(R.styleable.MaterialInitials_mi_background_colors, 0);
        int[] backgroundColors = obtainBackgroundColors(backgroundColorsResource);
        int textsResource = typedArray.getResourceId(R.styleable.MaterialInitials_mi_texts, 0);
        float rotation = typedArray.getFloat(R.styleable.MaterialInitials_mi_rotation, 0);
        String[] texts = obtainTexts(textsResource);
        if (isInEditMode() && texts == null) {
            texts = new String[]{"Android " + Build.VERSION.CODENAME};
        }
        typedArray.recycle();

        miDrawable = new MaterialInitialsDrawable(backgroundColors, texts);
        miDrawable.setTextColor(color);
        miDrawable.setTextAlpha(alpha);
        miDrawable.setTextRotation(rotation);
    }

    private int[] obtainBackgroundColors(int backgroundRes) {
        if (backgroundRes == 0) {
            return null;
        }
        TypedArray backgroundColorsTypedArray = getContext().getResources().obtainTypedArray(backgroundRes);
        int[] backgroundColors = new int[backgroundColorsTypedArray.length()];
        for (int i = 0; i < backgroundColorsTypedArray.length(); ++i) {
            int colorRes = backgroundColorsTypedArray.getInt(i, android.R.color.holo_red_dark);
            backgroundColors[i] = resolveColor(colorRes);
        }
        backgroundColorsTypedArray.recycle();
        return backgroundColors;
    }

    private String[] obtainTexts(int textRes) {
        if (textRes == 0) {
            return null;
        }
        TypedArray backgroundColorsTypedArray = getContext().getResources().obtainTypedArray(textRes);
        String[] backgroundColors = new String[backgroundColorsTypedArray.length()];
        for (int i = 0; i < backgroundColorsTypedArray.length(); ++i) {
            backgroundColors[i] = backgroundColorsTypedArray.getString(i);
        }
        backgroundColorsTypedArray.recycle();
        return backgroundColors;
    }

    private int resolveColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getResources().getColor(color, getContext().getTheme());
        } else {
            return getContext().getResources().getColor(color);
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    /**
     * an array of texts of type {@code String}. Each of them is divided into parts (by space - {@code " "}).
     * From each part the first letter is taken and drawn on background.
     *
     * @param texts an arbitrary number of {@code String} or an array of {@code String}
     */
    public void setTexts(String... texts) {
        miDrawable.setTexts(texts);
    }

    /**
     * an array of colors represented as {@code int}.
     *
     * @param color an arbitrary number of {@code int} or an array of {@code int} representing colors
     */
    public void setBackgroundColors(int... color) {
        miDrawable.setBackgroundColors(color);
    }

    /**
     * A parameter sets an alpha paramter of text color. Must be between 0 and 255.
     *
     * @param alpha an apha of the text drawn on view background
     */
    public void setTextAlpha(int alpha) {
        miDrawable.setTextAlpha(alpha);
    }

    /**
     * A parameter sets the color of text.
     *
     * @param color an int indicating the color of text
     */
    public void setTextColor(int color) {
        miDrawable.setTextColor(color);
    }


    /**
     * Sets the rotation of letters. Measured in degress in clockwise direction. Must be between 0 and 360.
     *
     * @param rotation a parameter indicating rotation of letters
     */
    public void setTextRotation(float rotation) {
        miDrawable.setTextRotation(rotation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getBackground() != null) {
            return;
        }
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        int sizeWithPadding = width < height ? width : height;
        canvas.clipRect((getWidth() - sizeWithPadding) / 2,
                (getHeight() - sizeWithPadding) / 2,
                getWidth() - (getWidth() - sizeWithPadding) / 2,
                getHeight() - (getHeight() - sizeWithPadding) / 2);
        canvas.save();
        miDrawable.draw(canvas);
        canvas.restore();
    }
}
