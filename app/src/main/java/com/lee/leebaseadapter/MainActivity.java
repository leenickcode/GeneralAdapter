package com.lee.leebaseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lee.mylibrary.ItemClickListener;

import java.util.ArrayList;

/**
 * @date 创建时间: 2018/7/31
 * @author nick.li
 * 基础效果
 */


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TestAdapter adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView=findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter=new TestAdapter(this,R.layout.item);
        recyclerView.setAdapter(adapter);
        adapter.setData(arrayList);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, View view, Object data) {
                Toast.makeText(MainActivity.this,"点击了第"+position+"个",Toast.LENGTH_SHORT).show();
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
