package com.lee.leebaseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.lee.mylibrary.ItemClickListener;
import com.lee.mylibrary.slide.ItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * @author nick.li
 * 滑动 拖拽 测试
 * @date 创建时间: 2018/7/30
 */


public class SlideActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SlideAdapter slideAdapter;
    ArrayList<String> arrayList;
    private static final String TAG = "SlideActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        recyclerView = findViewById(R.id.rv_slide);
        slideAdapter = new SlideAdapter(this, R.layout.item);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback();
        //一 继承SlideRecycleViewAdapter 设置监听器。  绑定RecycleView
        callback.setOnItemTouchCallbackListener(slideAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(slideAdapter);
        initData();
        slideAdapter.setData(arrayList);
        slideAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, View view, Object data) {
                Log.e(TAG, "onClick: "+position );
            }
        });
    }

    private void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add(i + "");
        }
    }
}
