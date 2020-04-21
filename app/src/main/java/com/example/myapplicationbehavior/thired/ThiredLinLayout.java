package com.example.myapplicationbehavior.thired;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

public class ThiredLinLayout extends LinearLayout implements NestedScrollingChild3 {
    private String TAG=ThiredLinLayout.class.getSimpleName();
    private final int[] offset = new int[2];
    private final int[] consumed = new int[2];
    private int lastY;

    private NestedScrollingChildHelper mScrollingChildHelper;

    public ThiredLinLayout(Context context) {
        this(context,null);
    }

    public ThiredLinLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ThiredLinLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNestedScrollingEnabled(true);
        //setClickable(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                stopNestedScroll(ViewCompat.TYPE_TOUCH);
                lastY = (int) event.getY();
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL,ViewCompat.TYPE_TOUCH);
            }
            break;
            case MotionEvent.ACTION_MOVE:{
                int dy = lastY - (int) (event.getY());
                dispatchNestedPreScroll(0, dy, consumed, offset,ViewCompat.TYPE_TOUCH);
                lastY = (int) event.getY();
            }
            break;

            case MotionEvent.ACTION_CANCEL:
            {
                stopNestedScroll(ViewCompat.TYPE_TOUCH);
            }
            break;
        }
        return true;
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        getmScrollingChildHelper().setNestedScrollingEnabled(true);
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return getmScrollingChildHelper().startNestedScroll(axes,type);
    }

    @Override
    public void stopNestedScroll(int type) {
        getmScrollingChildHelper().stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return getmScrollingChildHelper().hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return getmScrollingChildHelper().dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow,type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return getmScrollingChildHelper().dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow,type);
    }

    @Override
    public void dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @NonNull int[] consumed) {
        getmScrollingChildHelper().dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow,type,consumed);
    }

    public NestedScrollingChildHelper getmScrollingChildHelper() {
        if (mScrollingChildHelper == null){
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper;
    }
}
