package com.lee.mylibrary;

import android.view.View;

/**
 * Created by nicklxz on 2017/12/5.
 * 适配器单击监听
 * @author nicklxz
 */

public interface ItemClickListener {
    /**
     * recycleView的item点击监听
     * @param position 当前点击item的下标
     * @param view     响应点击的view
     * @param data     当前item对应的model
     */
    void onClick(int position, View view, Object data);

}
