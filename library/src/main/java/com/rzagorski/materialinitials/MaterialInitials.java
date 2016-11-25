package com.rzagorski.materialinitials;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Robert Zagórski on 19.11.2016.
 */

public class MaterialInitials extends ImageView {
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
        int color = typedArray.getColor(R.styleable.MaterialInitials_mi_textColor, resolveColor(android.R.color.white));
        int alpha = typedArray.getInt(R.styleable.MaterialInitials_mi_textAlpha, 136);
        int backgroundColorsResource = typedArray.getResourceId(R.styleable.MaterialInitials_mi_background_colors, 0);
        int[] backgroundColors = obtainBackgroundColors(backgroundColorsResource);
        int textsResource = typedArray.getResourceId(R.styleable.MaterialInitials_mi_texts, 0);
        String[] texts = obtainTexts(textsResource);
        if (isInEditMode() && texts == null) {
            texts = new String[]{"Android " + Build.VERSION.CODENAME};
        }
        typedArray.recycle();
        miDrawable = new MaterialInitialsDrawable(backgroundColors, texts);
        miDrawable.setTextColor(color);
        miDrawable.setAlpha(alpha);
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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return getContext().getResources().getColor(color);
        } else {
            return getContext().getResources().getColor(color, getContext().getTheme());
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    public void setTexts(String... texts) {
        miDrawable.setTexts(texts);
    }

    public void setBackgroundColors(int... color) {
        miDrawable.setBackgroundColors(color);
    }

    public void setTextAlpha(int alpha) {
        miDrawable.setTextAlpha(alpha);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getBackground() != null || getDrawable() != null) {
            super.onDraw(canvas);
            return;
        }
        miDrawable.draw(canvas);
    }
}
