package com.zzt8888.listeners;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.zzt8888.constants.Constants;


/**
 * @author Gamin Tsai
 * @brief recycle view 滚动监听器
 * @date 2016/6/12
 */
public abstract class OnRcvScrollListener extends RecyclerView.OnScrollListener implements OnBottomListener {

    private String TAG = getClass().getSimpleName();
    private TextView tvStickyHeaderView;



    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    /**
     * layoutManager的类型（枚举）
     */
    protected LAYOUT_MANAGER_TYPE layoutManagerType;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;
/**
 * 是否正在加载
 */
    // private boolean isLoadingMore = false;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        dealWithStickyHeader(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //  int lastVisibleItemPosition = -1;
        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }

    }

    private void dealWithStickyHeader(RecyclerView recyclerView) {
        if (tvStickyHeaderView == null) return;
        // Get the sticky information from the topmost view of the screen.
        View stickyInfoView = recyclerView.findChildViewUnder(
                tvStickyHeaderView.getMeasuredWidth() / 2, 5);

        if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
            tvStickyHeaderView.setVisibility(View.VISIBLE);
            tvStickyHeaderView.setText(String.valueOf(stickyInfoView.getContentDescription()));
        }

        // Get the sticky view's translationY by the first view below the sticky's height.
        View transInfoView = recyclerView.findChildViewUnder(
                tvStickyHeaderView.getMeasuredWidth() / 2, tvStickyHeaderView.getMeasuredHeight() + 1);

        if (transInfoView != null && transInfoView.getTag() != null) {
            int transViewStatus = (int) transInfoView.getTag();
            int dealtY = transInfoView.getTop() - tvStickyHeaderView.getMeasuredHeight();
            if (transViewStatus == Constants.HAS_STICKY_VIEW || transViewStatus == Constants.FIRST_STICKY_VIEW) {
                // If the first view below the sticky's height scroll off the screen,
                // then recovery the sticky view's translationY.
                if (transInfoView.getTop() > 0) {
                    tvStickyHeaderView.setTranslationY(dealtY);
                } else {
                    tvStickyHeaderView.setTranslationY(0);
                }
            } else if (transViewStatus == Constants.NONE_STICKY_VIEW) {
                tvStickyHeaderView.setTranslationY(0);
            }
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                (lastVisibleItemPosition) >= totalItemCount - 1)) {
            //Log.d(TAG, "is loading more");
            onBottom();
        }
    }

    public void setTvStickyHeaderView(TextView tvStickyHeaderView) {
        this.tvStickyHeaderView = tvStickyHeaderView;
    }

    @Override
    public abstract void onBottom();

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}