package com.androiddesk.base.component.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class TextLengthWatcher implements TextWatcher {
    private List<EditText> mEditTextList;
    private List<Integer> mLengthList;//最小长度集合

    public boolean mLastState = false;//上一次的状态
    public OnTextsLengthEnoughListener mListener;

    public interface OnTextsLengthEnoughListener {
        /**
         * @param isStateChanged isLengthEnough 状态改变
         * @param isLengthEnough 所有的内容输入都足够长
         */
        void isAllLengthEnough(boolean isStateChanged, boolean isLengthEnough);
    }

    public TextLengthWatcher() {
        mEditTextList = new ArrayList<>();
        mLengthList = new ArrayList<>();
    }

    public TextLengthWatcher add(EditText editText, int minLength) {
        if (editText == null) return this;
        editText.addTextChangedListener(this);
        mEditTextList.add(editText);
        mLengthList.add(minLength);
        return this;
    }

    public void setLengthEnoughListener(OnTextsLengthEnoughListener listener) {
        mListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        boolean isAllLengthEnough = true;
        for (int i = 0; i < mEditTextList.size(); i++) {
            if (mEditTextList.get(i).getText().toString().trim().length() < mLengthList.get(i)) {
                isAllLengthEnough = false;
                break;
            }
        }

        boolean isStateChanged = mLastState != isAllLengthEnough;
        mLastState = isAllLengthEnough;
        if (mListener != null) mListener.isAllLengthEnough(isStateChanged, isAllLengthEnough);
    }
}
