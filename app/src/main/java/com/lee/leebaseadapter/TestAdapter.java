package com.lee.leebaseadapter;

import android.content.Context;

import com.lee.mylibrary.BaseRecyclerViewAdapter;
import com.lee.mylibrary.ItemClickListener;
import com.lee.mylibrary.UniversalViewHolder;

/**
 * Created by nick on 2018/7/31.
 *  基础效果适配器
 * @author nick
 */
public class TestAdapter extends BaseRecyclerViewAdapter<String> {
    public TestAdapter(Context mContext, int mDefaultItemLayoutId) {
        super(mContext, mDefaultItemLayoutId);
    }

    @Override
    public void setItemChildListener(UniversalViewHolder helper, int viewType) {
      helper.setItemChildOnClickListener(R.id.textView);
    }

    @Override
    protected void bindData(UniversalViewHolder holder, int position, String bean) {
        holder.getTextView(R.id.textView).setText(bean);
    }
}
