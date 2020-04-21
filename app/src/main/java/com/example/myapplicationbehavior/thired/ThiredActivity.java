package com.example.myapplicationbehavior.thired;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationbehavior.R;
import com.example.myapplicationbehavior.SampleAdatper;

import java.util.ArrayList;
import java.util.List;

public class ThiredActivity extends AppCompatActivity {
    private String TAG = ThiredActivity.class.getSimpleName();
    RecyclerView recyclerView;
    private SampleAdatper sampleAdatper;
    private List<String> stringList = new ArrayList<>();
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thired);
        CoordinatorLayout coordinatorLayout = this.findViewById(R.id.coordinatorlayout);
        recyclerView = findViewById(R.id.my_list);
        linearLayout = findViewById(R.id.ll_thired);
        for (int i = 0; i < 100; i++) {
            stringList.add("我是"+i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        sampleAdatper = new SampleAdatper(stringList,this);
        recyclerView.setAdapter(sampleAdatper);
/*        linearLayout.setNestedScrollingEnabled(true);
        linearLayout.startNestedScroll(View.SCROLL_AXIS_VERTICAL);*/
    }
}
