package com.lee.mylibrary;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nicklxz on 2017/12/26.
 * 通用view holder
 * @author nick
 */

public class UniversalViewHolder extends RecyclerView.ViewHolder {
    /**
     * 控件的容器，，，效率上不如HashMap（一个是二分法，一个是hash算法）,但是节约内存。
     */
    private SparseArrayCompat<View> mViews;
    /**
     * 当前view
     */
    private View itemView;

     UniversalViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        mViews = new SparseArrayCompat<>();
    }

    /**
     * 获取控件
     * @param viewId 控件Id
     * @return 控件
     */
    private  <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取 ImageView
     *
     * @param viewId 控件Id
     * @return ImageView
     */
    public ImageView getImageView(int viewId) {
        return getView(viewId);
    }

    /**
     * 获取TextView
     *
     * @param viewId 控件Id
     * @return TextView
     */
    public TextView getTextView(int viewId) {
        return getView(viewId);
    }

    /**
     * 设置item点击事件
     *
     * @param viewId 要监听的view id
     */
    public void setItemChildOnClickListener(int viewId) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onClick(getAdapterPosition(), v, null);
                    }
                }
            });
        }
    }

    /**
     * 回调给adapter
     */
    private ItemClickListener itemClickListener;

    /**
     * adapter 的监听回调
     * @param listener listener
     */
    public void setItemClickListener(ItemClickListener listener) {
        itemClickListener = listener;
    }
}
