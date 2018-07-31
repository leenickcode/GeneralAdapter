package com.lee.mylibrary.slide;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by nick on 2018/7/30.
 *
 * @author nick
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    /**
     * Item操作的回调
     */
    private OnItemTouchCallbackListener onItemTouchCallbackListener;

    /**
     * 是否可以拖拽
     */
    private boolean isCanDrag = true;
    /**
     * 是否可以被滑动
     */
    private boolean isCanSwipe = true;

    /**
     * 设置Item操作的回调，去更新UI和数据源
     *
     * @param onItemTouchCallbackListener
     */
    public void setOnItemTouchCallbackListener(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    public void setDragEnable(boolean canDrag) {
        isCanDrag = canDrag;
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setSwipeEnable(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }
    /**
     * 当Item被长按的时候是否可以被拖拽
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    /**
     * Item是否可以被滑动(H：左右滑动，V：上下滑动)
     *
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }


    //控制拖拽 和滑动的方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){

        }else if (layoutManager instanceof LinearLayoutManager){
            int orientation=((LinearLayoutManager) layoutManager).getOrientation();
            if (orientation==LinearLayoutManager.HORIZONTAL){
                int dragFlag=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                int swipeFlag =ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlag,swipeFlag);
            }else if (orientation==LinearLayoutManager.VERTICAL){
                int dragFlag=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                int swipeFlag =ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlag,swipeFlag);
            }

        }
        return 0 ;
    }

    /**
     * 当 item被拖拽的时候回调
     * @param recyclerView recyclerView
     * @param viewHolder  拖拽的viewHolder
     * @param target 目的地的ViewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (onItemTouchCallbackListener != null) {
            return onItemTouchCallbackListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    /**
     * 当Item被滑动删除到不见
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (onItemTouchCallbackListener != null) {
            onItemTouchCallbackListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }
    public interface OnItemTouchCallbackListener {
        /**
         * 当某个Item被滑动删除的时候
         *
         * @param adapterPosition item的position
         */
        void onSwiped(int adapterPosition);

        /**
         * 当两个Item位置互换的时候被回调
         *
         * @param srcPosition    拖拽的item的position
         * @param targetPosition 目的地的Item的position
         * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
         */
        boolean onMove(int srcPosition, int targetPosition);

    }
}
