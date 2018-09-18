package com.lee.leebaseadapter;

import android.content.Context;

import com.lee.mylibrary.SlideMenuRecycleViewAdapter;
import com.lee.mylibrary.UniversalViewHolder;

/**
 * Created by nick on 2018/8/2.
 *
 * @author nick
 */
public class SlideMenuAdapter  extends SlideMenuRecycleViewAdapter<String>{
    public SlideMenuAdapter(Context mContext, int mDefaultItemLayoutId) {
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
