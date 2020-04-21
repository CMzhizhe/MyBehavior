package com.example.myapplicationbehavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    public RecyclerBehavior() {
    }

    public RecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        return dependency instanceof  TextView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        float dy = dependency.getHeight() + dependency.getTranslationY();
        if (dy <= 0) {
            dy = 0;
        }
        child.setY(dy);
        return true;
    }
}
