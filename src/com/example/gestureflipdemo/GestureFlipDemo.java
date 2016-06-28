package com.example.gestureflipdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class GestureFlipDemo extends Activity implements OnGestureListener{
	
	ViewFlipper flipper;  //ViewFlipper实例
	GestureDetector detector; //定义手势检测器实例
	Animation[] animations = new Animation[4]; //定义一个动画数组，用于为ViewFlipper指定切换动画效果
	final int FLIP_DISTANCE = 50; //定义手势动作两点之间的最小距离

    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        detector = new GestureDetector(this);  //创建手势检测器
        flipper = (ViewFlipper) this.findViewById(R.id.flipper); //获得ViewFlipper实例
        //为ViewFlipper添加5个ImageView组件
        flipper.addView(addImageView(R.drawable.a));
        flipper.addView(addImageView(R.drawable.b));
        flipper.addView(addImageView(R.drawable.c));
        flipper.addView(addImageView(R.drawable.d));
        flipper.addView(addImageView(R.drawable.e));
        //初始化Animation数组
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
        animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
        animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);  
    }
    
    //定义ImageView的工具方法
    private View addImageView(int resId)
    {
    	ImageView imageView = new ImageView(this);
    	imageView.setImageResource(resId);
    	imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event); //将该Activity上的触发事件交给GestureDetector处理
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		/*
		 * 如果第一个触点事件的X坐标大于第二个触点事件的X坐标且超过FLIP_DISTANCE
		 * 也就是手势从右向左滑动
		 */
		if (event1.getX() - event2.getX() > FLIP_DISTANCE)
		{
			//为flipper设置切换的动画效果
			flipper.setInAnimation(animations[0]);
			flipper.setOutAnimation(animations[1]);
			flipper.showPrevious();
			return true;
		}
		/*
		 * 如果第二个触点事件的X坐标大于第一个触点事件的X坐标且超过FLIP_DISTANCE
		 * 也就是手势从左向右滑动
		 */
		if (event2.getX() - event1.getX() > FLIP_DISTANCE)
		{
			//为flipper设置切换的动画效果
			flipper.setInAnimation(animations[2]);
			flipper.setOutAnimation(animations[3]);
			flipper.showNext();
			return true;
		}
		
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
