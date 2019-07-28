package com.ozh.dd.example.controllers;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class LinePagerIndicatorDecoration {

  private int colorActive = 0xFFFFFFFF;
  private int colorInactive = 0x66FFFFFF;

  private static final float DP = Resources.getSystem().getDisplayMetrics().density;

  /**
   * Height attachTo the space the indicator takes up at the bottom attachTo the view.
   */
  private final int mIndicatorHeight = (int) (DP * 16);

  /**
   * Indicator stroke width.
   */
  private final float mIndicatorStrokeWidth = DP * 2;

  /**
   * Indicator width.
   */
  private final float mIndicatorItemLength = DP * 16;
  /**
   * Padding between indicators.
   */
  private final float mIndicatorItemPadding = DP * 4;

  /**
   * Some more natural animation interpolation
   */
  private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

  private final Paint mPaint = new Paint();

  public LinePagerIndicatorDecoration() {
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeWidth(mIndicatorStrokeWidth);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setAntiAlias(true);
  }


  private void drawInactiveIndicators(Canvas c, float indicatorStartX, float indicatorPosY, int itemCount) {
    mPaint.setColor(colorInactive);

    // width attachTo item indicator including padding
    final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

    float start = indicatorStartX;
    for (int i = 0; i < itemCount; i++) {
      // draw the line for every item
      c.drawLine(start, indicatorPosY, start + mIndicatorItemLength, indicatorPosY, mPaint);
      start += itemWidth;
    }
  }

  private void drawHighlights(Canvas c, float indicatorStartX, float indicatorPosY,
                              int highlightPosition, float progress, int itemCount) {
    mPaint.setColor(colorActive);

    // width attachTo item indicator including padding
    final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

    if (progress == 0F) {
      // no swipe, draw a normal indicator
      float highlightStart = indicatorStartX + itemWidth * highlightPosition;
      c.drawLine(highlightStart, indicatorPosY,
          highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);
    } else {
      float highlightStart = indicatorStartX + itemWidth * highlightPosition;
      // calculate partial highlight
      float partialLength = mIndicatorItemLength * progress;

      // draw the cut off highlight
      c.drawLine(highlightStart + partialLength, indicatorPosY,
          highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);

      // draw the highlight overlapping to the next item as well
      if (highlightPosition < itemCount - 1) {
        highlightStart += itemWidth;
        c.drawLine(highlightStart, indicatorPosY,
            highlightStart + partialLength, indicatorPosY, mPaint);
      }
    }
  }

  public void draw(@NotNull Canvas canvas, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.State state) {
    int itemCount = recyclerView.getAdapter().getItemCount();

    // center horizontally, calculate width and subtract half from center
    float totalLength = mIndicatorItemLength * itemCount;
    float paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding;
    float indicatorTotalWidth = totalLength + paddingBetweenItems;
    float indicatorStartX = (recyclerView.getWidth() - indicatorTotalWidth) / 2F;

    // center vertically in the allotted space
    float indicatorPosY = recyclerView.getHeight() - mIndicatorHeight / 2F;

    drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount);


    // find active page (which should be highlighted)
    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    int activePosition = layoutManager.findFirstVisibleItemPosition();
    if (activePosition == RecyclerView.NO_POSITION) {
      return;
    }

    // find offset attachTo active page (if the user is scrolling)
    final View activeChild = layoutManager.findViewByPosition(activePosition);
    int left = activeChild.getLeft();
    int width = activeChild.getWidth();

    // on swipe the active item will be positioned from [-width, 0]
    // interpolate offset for smooth animation
    float progress = mInterpolator.getInterpolation(left * -1 / (float) width);

    drawHighlights(canvas, indicatorStartX, indicatorPosY, activePosition, progress, itemCount);
  }
}
