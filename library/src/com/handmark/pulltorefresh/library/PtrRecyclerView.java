package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Vincent on 15/4/6.
 */
public class PtrRecyclerView extends PullToRefreshBase<RecyclerView> {

    public PtrRecyclerView(Context context) {
        super(context);
    }

    public PtrRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PtrRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(R.id.recyclerview);
        return recyclerView;
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        mRefreshableView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        mRefreshableView.setLayoutManager(layoutManager);
    }

    @Override protected boolean isReadyForPullEnd() {
        int lastVisiblePostion =
            mRefreshableView.getChildAdapterPosition(mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1));
        if (lastVisiblePostion >= mRefreshableView.getAdapter().getItemCount() - 1) {
            return mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1).getBottom()
                <= mRefreshableView.getBottom();
        }
        return false;
    }

    @Override protected boolean isReadyForPullStart() {
        if (mRefreshableView.getChildCount() <= 0) return true;
        int firstVisiblePosition = mRefreshableView.getChildAdapterPosition(mRefreshableView.getChildAt(0));
        if (firstVisiblePosition == 0) {
            return mRefreshableView.getChildAt(0).getTop() == mRefreshableView.getPaddingTop();
        } else {
            return false;
        }
    }
}
