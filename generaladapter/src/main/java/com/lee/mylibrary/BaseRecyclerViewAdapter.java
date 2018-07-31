package com.lee.mylibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nicklxz
 * @date 2017/12/26
 * 通用适配器
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<UniversalViewHolder> {
    private Context mContext;
    private int mDefaultItemLayoutId;
    private List<T> mData;
    private static final String TAG = "BaseRecyclerViewAdapter";

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    /**
     * @param mContext             上下文
     * @param mDefaultItemLayoutId item布局Id
     */
    public BaseRecyclerViewAdapter(Context mContext, int mDefaultItemLayoutId) {
        this(mContext);
        this.mDefaultItemLayoutId = mDefaultItemLayoutId;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * 获取数据集合
     *
     * @return 整个数据集合
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 清空数据
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UniversalViewHolder viewHolder = new UniversalViewHolder(LayoutInflater.from(mContext).inflate(viewType, parent, false));
        setItemChildListener(viewHolder, viewType);
        viewHolder.setItemClickListener(itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UniversalViewHolder holder, int position) {
        T bean = getItem(position);
        bindData(holder, position, bean);
    }

    /**
     * 绑定数据的再封装
     *
     * @param holder   viewHolder
     * @param position 当前item下标
     * @param bean     对应的数据实体
     */
    protected  abstract void bindData(UniversalViewHolder holder, int position, T bean) ;

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 如果要使用多种的布局的话，请重写这方法返回不同的布局资源id
     */
    @Override
    public int getItemViewType(int position) {
        if (mDefaultItemLayoutId == 0) {
            throw new RuntimeException("请在 " + this.getClass().getSimpleName() + " 中重写 getItemViewType 方法返回布局资源 id，" +
                    "或者使用 两个参数的构造方法把布局资源id传进来");
        }
        return mDefaultItemLayoutId;
    }

    /**
     * 为item的孩子节点设置监听器，并不是每一个数据列表都要为item的子控件添加事件监听器，所以这里采用了空实现，需要设置事件监听器时重写该方法即可
     *
     * @param helper
     */
    public void setItemChildListener(UniversalViewHolder helper, int viewType) {
    }

    /**
     * 点击事件监听
     */
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener listener) {
        itemClickListener = listener;
    }

    /**
     * 回去当前item的数据实体
     *
     * @param position 下标
     * @return T
     */
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 在集合末尾 增加更多数据
     *
     * @param data 数据
     */
    public void addLastData(List<T> data) {
        mData.addAll(data);
        notifyItemRangeInserted(mData.size(), data.size());
    }

    /**
     * 在集合前面 插入更多数据
     *
     * @param data 数据
     */
    public void addFirstData(List<T> data) {
        mData.addAll(0, data);
        notifyItemRangeInserted(0, data.size());
    }

    /**
     * 删除Item
     *
     * @param position item的下标
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
