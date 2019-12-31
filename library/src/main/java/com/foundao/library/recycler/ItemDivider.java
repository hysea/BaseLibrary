package com.foundao.library.recycler;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDivider extends RecyclerView.ItemDecoration {

    private int lineHeight;
    private int lineColor;
    private int lineDrawable;
    private boolean includeEdge;

    private int oritation = LinearLayoutManager.VERTICAL;
    private Paint mPaint;

    private ItemDivider(Builder builder) {
        this.lineHeight = builder.lineHeight;
        this.lineColor = builder.lineColor;
        this.lineDrawable = builder.lineDrawable;
        this.includeEdge = builder.includeEdge;

        mPaint = new Paint();
        if (lineColor <= 0) {
            lineColor = Color.GRAY;
        }

        if (lineHeight <= 0) {
            lineHeight = 2;
        }
        mPaint.setColor(lineColor);
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineDrawable() {
        return lineDrawable;
    }

    public void setLineDrawable(int lineDrawable) {
        this.lineDrawable = lineDrawable;
    }

    public boolean isIncludeEdge() {
        return includeEdge;
    }

    public void setIncludeEdge(boolean includeEdge) {
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            handleLinearLayoutManager(outRect, view, parent);
        }

        if (layoutManager instanceof GridLayoutManager) {
            handleGridLayoutManager(outRect, view, parent);
        }
    }

    private void handleLinearLayoutManager(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent) {
        int position = parent.getChildAdapterPosition(view);
        int orientation = getOrientation(parent);

        if (orientation == LinearLayoutManager.VERTICAL) {
            if (position == 0 && isIncludeEdge()) {
                outRect.top = lineHeight;
            }

            if (position == parent.getChildCount() - 1 && isIncludeEdge()) {
                outRect.bottom = lineHeight;
            } else {
                outRect.bottom = lineHeight;
            }
        } else {
            if (position == 0 && isIncludeEdge()) {
                outRect.left = lineHeight;
            }

            if (position == parent.getChildCount() - 1 && isIncludeEdge())
                outRect.right = lineHeight;
            else {
                outRect.right = lineHeight;
            }
        }
    }

    private void handleGridLayoutManager(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();

        int column = position % spanCount;
        if (isIncludeEdge()) {
            outRect.left = lineHeight - column * lineHeight / spanCount;
            outRect.right = (column + 1) * lineHeight / spanCount;
            if (position < spanCount) {
                outRect.top = lineHeight;
            }
            outRect.bottom = lineHeight;
        } else {
            outRect.left = column * lineHeight / spanCount;
            outRect.right = lineHeight - (column + 1) * lineHeight / spanCount;
            if (position < spanCount) {
                outRect.top = lineHeight;
            }
            outRect.bottom = lineHeight;
        }
    }

    public int getOrientation(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation();
        }
        return LinearLayoutManager.VERTICAL;
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getLayoutManager() == null || parent.getLayoutManager() instanceof GridLayoutManager) {
            return;
        }

        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }


    /**
     * 列表为竖直方向
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize - 1; i++) { // childSize - 1：不绘制最后一个Item的分割线
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + lineHeight;
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 列表为水平方向
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + lineHeight;
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    static class Builder {
        private int lineHeight;
        private int lineColor;
        private int lineDrawable;
        private boolean includeEdge;

        public Builder setLineHeight(int lineHeight) {
            this.lineHeight = lineHeight;
            return this;
        }

        public Builder setLineColor(int lineColor) {
            this.lineColor = lineColor;
            return this;
        }

        public Builder setLineDrawable(int lineDrawable) {
            this.lineDrawable = lineDrawable;
            return this;
        }

        public Builder setIncludeEdge(boolean includeEdge) {
            this.includeEdge = includeEdge;
            return this;
        }

        public ItemDivider build() {
            return new ItemDivider(this);
        }
    }
}
