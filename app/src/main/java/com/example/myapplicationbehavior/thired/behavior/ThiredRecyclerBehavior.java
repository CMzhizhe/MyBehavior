package com.example.myapplicationbehavior.thired.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationbehavior.R;

public class ThiredRecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private boolean isFirstLoad = false;
    private int titleHeight = 0;
    public ThiredRecyclerBehavior() {
    }

    public ThiredRecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        FrameLayout frameLayout = (FrameLayout) dependency;
        if (!isFirstLoad) {
            isFirstLoad = true;
            child.setY(frameLayout.getHeight());
            for (int i = 0; i < parent.getChildCount(); i++) {
                View view = parent.getChildAt(i);
                if (view.getId() == R.id.title){
                    titleHeight = view.getHeight();
                    break;
                }
            }
        }
      /*  float frameLayoutTranslationY = frameLayout.getTranslationY();
        child.setTranslationY(child.getTranslationY() + frameLayoutTranslationY);*/
        return true;
    }
}
