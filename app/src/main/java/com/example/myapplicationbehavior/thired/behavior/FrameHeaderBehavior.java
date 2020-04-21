package com.example.myapplicationbehavior.thired.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationbehavior.thired.ThiredLinLayout;

public class FrameHeaderBehavior extends CoordinatorLayout.Behavior<View> {
    private String TAG = FrameHeaderBehavior.class.getSimpleName();

    public FrameHeaderBehavior() {
    }

    public FrameHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
       /* Log.e(TAG, "child:" + child.toString());
        Log.i(TAG, "target:" + target.toString());*/
        //制造滑动视察，使header的移动比手指滑动慢
        float scrollY = dy / 4.0f;
        if (target instanceof ThiredLinLayout) {
            FrameLayout frameLayout = (FrameLayout) child;
            float transLationY = frameLayout.getTranslationY() - scrollY;
            if (transLationY < -frameLayout.getHeight()){
                transLationY = -frameLayout.getHeight();
            }else if (transLationY > 0){
                transLationY = 0;
            }
            child.setTranslationY(transLationY);
            consumed[1] = dy;
        } else if (target instanceof RecyclerView) {

        }
        /*  if (target instanceof )*/
    }
}
