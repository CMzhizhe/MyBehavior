package com.example.myapplicationbehavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View>{
    private String TAG = SampleTitleBehavior.class.getSimpleName();
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior() {
        super();
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //决定 child 依赖谁（dependency）。
    //确定使用Behavior的View要依赖的View的类型
    //child代表使用该Behavior的View
    //dependency 表示要监听的view
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof RecyclerView;
    }

    //当被监听的view状态发生改变的时候调用
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (deltaY == 0){
            deltaY = dependency.getY() - child.getHeight();
        }
        Log.i(TAG,"dependency.getY():"+dependency.getY());
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }
}
