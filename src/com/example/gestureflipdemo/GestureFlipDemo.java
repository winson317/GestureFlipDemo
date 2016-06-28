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
	
	ViewFlipper flipper;  //ViewFlipperʵ��
	GestureDetector detector; //�������Ƽ����ʵ��
	Animation[] animations = new Animation[4]; //����һ���������飬����ΪViewFlipperָ���л�����Ч��
	final int FLIP_DISTANCE = 50; //�������ƶ�������֮�����С����

    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        detector = new GestureDetector(this);  //�������Ƽ����
        flipper = (ViewFlipper) this.findViewById(R.id.flipper); //���ViewFlipperʵ��
        //ΪViewFlipper���5��ImageView���
        flipper.addView(addImageView(R.drawable.a));
        flipper.addView(addImageView(R.drawable.b));
        flipper.addView(addImageView(R.drawable.c));
        flipper.addView(addImageView(R.drawable.d));
        flipper.addView(addImageView(R.drawable.e));
        //��ʼ��Animation����
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
        animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
        animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);  
    }
    
    //����ImageView�Ĺ��߷���
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
		return detector.onTouchEvent(event); //����Activity�ϵĴ����¼�����GestureDetector����
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
		 * �����һ�������¼���X������ڵڶ��������¼���X�����ҳ���FLIP_DISTANCE
		 * Ҳ�������ƴ������󻬶�
		 */
		if (event1.getX() - event2.getX() > FLIP_DISTANCE)
		{
			//Ϊflipper�����л��Ķ���Ч��
			flipper.setInAnimation(animations[0]);
			flipper.setOutAnimation(animations[1]);
			flipper.showPrevious();
			return true;
		}
		/*
		 * ����ڶ��������¼���X������ڵ�һ�������¼���X�����ҳ���FLIP_DISTANCE
		 * Ҳ�������ƴ������һ���
		 */
		if (event2.getX() - event1.getX() > FLIP_DISTANCE)
		{
			//Ϊflipper�����л��Ķ���Ч��
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
