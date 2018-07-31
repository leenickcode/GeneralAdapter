package com.lee.mylibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lee.mylibrary.slide.ItemTouchHelperCallback;

import java.util.Collections;

/**
 * Created by nick on 2018/7/30.
 *  侧滑删除，滑动拖拽
 * @author nick
 */
public abstract class SlideRecycleViewAdapter<T> extends BaseRecyclerViewAdapter<T> implements ItemTouchHelperCallback.OnItemTouchCallbackListener {
    private RecyclerView mRecyclerView;
    public SlideRecycleViewAdapter(Context mContext, int mDefaultItemLayoutId) {
        super(mContext, mDefaultItemLayoutId);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    @Override
    public void onSwiped(int adapterPosition) {
        getData().remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    @Override
    public boolean onMove(int srcPosition, int targetPosition) {
        Collections.swap(getData(),srcPosition,targetPosition);
        notifyItemMoved(srcPosition,targetPosition);
        return true;
    }
}
