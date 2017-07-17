package com.voidx.seek;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class HeadingTextView extends android.support.v7.widget.AppCompatTextView {
    public HeadingTextView(Context context) {
        super(context);
        applyCustomShape(context);
        applyCustomFont(context);
    }

    public HeadingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomShape(context);
        applyCustomFont(context);
    }

    public HeadingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomShape(context);
        applyCustomFont(context);
    }

    private void applyCustomShape(Context context) {
//        setBackgroundColor(Color.WHITE);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface(Font.ROBOTO_MEDIUM, context);
        setTypeface(customFont);

        setTextColor(Color.BLACK);
//        setTypeface(Typeface.DEFAULT_BOLD);
    }
}
