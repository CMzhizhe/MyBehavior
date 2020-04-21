package com.example.myapplicationbehavior.thired.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class ThiredTitleBehavior extends CoordinatorLayout.Behavior<TextView> {
    private String TAG = ThiredTitleBehavior.class.getSimpleName();
    private boolean isFirstLoad = true;

    public ThiredTitleBehavior() {
    }

    public ThiredTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
        if (isFirstLoad){
            isFirstLoad = false;
            child.setY(-child.getHeight());
        }
        return true;
    }

}
