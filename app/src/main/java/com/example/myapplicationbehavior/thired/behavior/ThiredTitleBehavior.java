package com.example.myapplicationbehavior.thired.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.myapplicationbehavior.R;

public class ThiredTitleBehavior extends CoordinatorLayout.Behavior<TextView> {
    private String TAG = ThiredTitleBehavior.class.getSimpleName();
    private Context mContext;

    public ThiredTitleBehavior() {
    }

    public ThiredTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
        FrameLayout frameLayout = (FrameLayout) dependency;
        float translatyY = -(1 - frameLayout.getTranslationY() / getHeaderOffset()) * child.getHeight();
        child.setTranslationY(translatyY);
        return true;
    }

    private int getHeaderOffset(){
        return mContext.getResources().getDimensionPixelOffset(R.dimen.header_offset);
    }
}
