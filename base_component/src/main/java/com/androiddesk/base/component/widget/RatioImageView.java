package com.androiddesk.base.component.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.androiddesk.base.component.R;

/**
 * 按照宽高比例,测量自身大小的ImageView
 * Created by jiangp on 16/4/5.
 */
public class RatioImageView extends ImageView {
    private final int DEFAULT_RATIO = -1;
    private float mRatio; // 比例 width / height

    public RatioImageView(Context context) {
        this(context, null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttr(attrs);
    }

    private void handleAttr(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        mRatio = ta.getFloat(R.styleable.RatioImageView_width_height_radio, DEFAULT_RATIO);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRatio > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width / mRatio);
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
