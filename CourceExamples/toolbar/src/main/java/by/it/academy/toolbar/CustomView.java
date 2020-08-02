package by.it.academy.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Этапы инициализации View
 * 1) onAttachedToWindow
 * 2) onMeasure
 * 3) onLayout
 * 4) onDraw
 */
public class CustomView extends View {

    interface TouchActionListener {
        void onTouchDown(int x, int y);
    }

    private int radius = 0;
    private int color = 0;

    private int widthCenter = 0;
    private int heightCenter = 0;

    private Paint paint = new Paint();

    private TouchActionListener touchActionListener;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAttrs(attrs);
    }

    private void getAttrs(@Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewParams);
            radius = typedArray.getDimensionPixelSize(R.styleable.CustomViewParams_circle_radius, 0);
            color = typedArray.getColor(R.styleable.CustomViewParams_circle_color, 0);
            typedArray.recycle();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //AT_MOST, UNSPECIFIED, EXACTLY
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        Log.d("CustomView", "onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widthCenter = w / 2;
        heightCenter = h / 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("CustomView", "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(widthCenter, heightCenter, radius, paint);
        super.onDraw(canvas);
        Log.d("CustomView", "onDraw");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchActionListener != null) {
                touchActionListener.onTouchDown((int) event.getX(), (int) event.getY());
            }
        }
        return super.onTouchEvent(event);
    }

    public void setTouchActionListener(TouchActionListener touchActionListener) {
        this.touchActionListener = touchActionListener;
    }
}
