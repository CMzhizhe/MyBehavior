package com.example.myapplicationbehavior.thired.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationbehavior.R;

public class ThiredRecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private String TAG = ThiredRecyclerBehavior.class.getSimpleName();
    private boolean isFirstLoad = true;
    private Context mContext;
    private int titleHeight = 0;

    public ThiredRecyclerBehavior() {
    }

    public ThiredRecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        if (isFirstLoad) {
            isFirstLoad = false;
            for (int i = 0; i < parent.getChildCount(); i++) {
                View view = parent.getChildAt(i);
                if (view.getId() == R.id.title) {
                    titleHeight = view.getHeight();
                    break;
                }
            }
        }
        FrameLayout frameLayout = (FrameLayout) dependency;
        float translatyY = frameLayout.getHeight() - frameLayout.getTranslationY() / getHeaderOffset() * (frameLayout.getHeight() - titleHeight);
        child.setTranslationY(translatyY);
        return true;
    }


    private int getHeaderOffset() {
        return mContext.getResources().getDimensionPixelOffset(R.dimen.header_offset);
    }

}
