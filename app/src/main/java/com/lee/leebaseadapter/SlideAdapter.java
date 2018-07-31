package com.lee.leebaseadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lee.mylibrary.SlideRecycleViewAdapter;
import com.lee.mylibrary.UniversalViewHolder;

/**
 * Created by nick on 2018/7/30.
 *
 * @author nick
 */
public class SlideAdapter extends SlideRecycleViewAdapter<String> {
    private static final String TAG = "SlideAdapter";
    public SlideAdapter(Context mContext, int mDefaultItemLayoutId) {
        super(mContext, mDefaultItemLayoutId);
    }

    @Override
    protected void bindData(UniversalViewHolder holder, int position, String bean) {
        holder.getTextView(R.id.textView).setText(bean);
    }
}
