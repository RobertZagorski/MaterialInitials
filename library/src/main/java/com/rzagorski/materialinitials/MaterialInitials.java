package com.rzagorski.materialinitials;

import android.content.Context;
import android.graphics.Canvas;
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
        miDrawable = new MaterialInitialsDrawable();
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

    public void setTexts(String... texts) {
        miDrawable.setTexts(texts);
    }

    public void setBackgroundColors(int... color) {
        miDrawable.setBackgroundColors(color);
    }

    @Override
    public void draw(Canvas canvas) {
        if (getBackground() != null || getDrawable() != null) {
            super.draw(canvas);
            return;
        }
        miDrawable.draw(canvas);
    }
}
