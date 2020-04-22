package com.example.myapplicationbehavior.thired.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationbehavior.R;
import com.example.myapplicationbehavior.thired.ThiredLinLayout;

public class FrameHeaderBehavior extends CoordinatorLayout.Behavior<View> {
    private String TAG = FrameHeaderBehavior.class.getSimpleName();
    private Context mContext;
    private boolean isFirstLoad = true;
    private int titleHeight = 0;
    public FrameHeaderBehavior() {
    }

    public FrameHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        if (isFirstLoad){
            isFirstLoad = false;
            for (int i = 0; i < coordinatorLayout.getChildCount(); i++) {
                View view = coordinatorLayout.getChildAt(i);
                if (view.getId() == R.id.title){
                    titleHeight = view.getHeight();
                    break;
                }
            }
        }


       /* Log.e(TAG, "child:" + child.toString());
        Log.i(TAG, "target:" + target.toString());*/
        //制造滑动视察，使header的移动比手指滑动慢
        float scrollY = dy / 4.0f;
        if (target instanceof ThiredLinLayout) {
            FrameLayout frameLayout = (FrameLayout) child;
            float transLationY = frameLayout.getTranslationY() - scrollY;
            if (transLationY < getHeaderOffset()){
                transLationY = getHeaderOffset();
            }else if (transLationY > 0){
                transLationY = 0;
            }
            child.setTranslationY(transLationY);
            consumed[1] = dy;
        } else if (target instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) target;
            FrameLayout frameLayout = (FrameLayout) child;
            float transLationY = frameLayout.getTranslationY() - scrollY;
            if (transLationY <= getHeaderOffset()){
                transLationY = getHeaderOffset();
            }else if (transLationY >= 0){
                transLationY = 0;
            }
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position =  linearLayoutManager.findFirstVisibleItemPosition();
            if (dy > 0){
                if (transLationY == getHeaderOffset() || transLationY == 0){
                    if (recyclerView.canScrollVertically(dy)) {//是否可以滑动
                        recyclerView.scrollBy(0,dy);
                    }else {
                        consumed[1] = dy;
                    }
                    child.setTranslationY(transLationY);
                }else {
                    consumed[1] = dy;
                    child.setTranslationY(transLationY);
                }
            }else {
                if (recyclerView.canScrollVertically(dy)) {//是否可以滑动
                    recyclerView.scrollBy(0,dy);
                }else {
                    consumed[1] = dy;
                    child.setTranslationY(transLationY);
                }
            }

        }
    }


    private int getHeaderOffset(){
        return mContext.getResources().getDimensionPixelOffset(R.dimen.header_offset);
    }

}
