package com.lee.leebaseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lee.mylibrary.slide.DefaultItemToucHleper;
import com.lee.mylibrary.slide.ItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * @author nick.li
 * 滑动 拖拽 测试
 * @date 创建时间: 2018/7/30
 */


public class SlideActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        recyclerView = findViewById(R.id.rv_slide);
        myAdapter = new MyAdapter(this, R.layout.item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback();
        //一 继承SlideRecycleViewAdapter 设置监听器。  绑定RecycleView
        callback.setOnItemTouchCallbackListener(myAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(myAdapter);
        initData();
        myAdapter.setData(arrayList);
    }

    private void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add(i + "");
        }
    }
}
