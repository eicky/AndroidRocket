package com.eicky;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Eicky
 * @Description: 浮动窗口
 * @date: 2017/1/16 15:57
 * @version: V1.0
 */
public class FloatView extends LinearLayout {
    private Context mContext;
    private TextView packageName, className;
    private Point downPoint, movePoint;
    private WindowManager mWindowManager;

    public FloatView(Context context) {
        super(context);
        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.float_window, this);
        packageName = (TextView) findViewById(R.id.package_name);
        className = (TextView) findViewById(R.id.class_name);
        findViewById(R.id.close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "关闭悬浮窗", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, TrackerService.class);
                intent.putExtra(TrackerService.TYPE_KEY, TrackerService.Type.CLOSE.code);
                mContext.startService(intent);
            }
        });
    }

    public void updateDisplay(String packageNameStr, String classNameStr){
        packageName.setText(packageNameStr);
        className.setText(classNameStr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downPoint = new Point((int) event.getRawX(), (int) event.getRawY());
                break;

            case MotionEvent.ACTION_MOVE:
                movePoint = new Point((int) event.getRawX(), (int) event.getRawY());
                int dx = movePoint.x - downPoint.x;
                int dy = movePoint.y - downPoint.y;
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.getLayoutParams();
                layoutParams.x += dx;
                layoutParams.y += dy;
                mWindowManager.updateViewLayout(this, layoutParams);
                downPoint = movePoint;
                break;
        }
        return false;
    }
}
