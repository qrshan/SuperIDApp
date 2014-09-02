package com.simu.superid.wiget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.simu.superid.R;
import com.simu.superid.myinterface.HomeClickListener;

public class HomeButton extends ImageView {

	private Bitmap bitmap;
	private Bitmap home_icon;

	private int state = 0; // 按下

	private int color;
	private float textsize;
	private boolean vertical;
	private int home;
	private String text;

	private int screenW;
	private int screenH;
	// 点击事件
	private HomeClickListener listener = null;

	private int[] colors = { getResources().getColor(R.color.home1),
			getResources().getColor(R.color.home2),
			getResources().getColor(R.color.home3),
			getResources().getColor(R.color.home4),
			getResources().getColor(R.color.home5) };

	private Bitmap[] bitmaps = {
			BitmapFactory.decodeResource(getResources(), R.drawable.home1),
			BitmapFactory.decodeResource(getResources(), R.drawable.home2),
			BitmapFactory.decodeResource(getResources(), R.drawable.home3),
			BitmapFactory.decodeResource(getResources(), R.drawable.home4),
			BitmapFactory.decodeResource(getResources(), R.drawable.home5) };

	public HomeButton(Context context) {
		super(context);
	}

	public HomeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.HomeButton);
		color = typedArray.getInt(R.styleable.HomeButton_backcolor, 0);
		textsize = typedArray.getDimension(R.styleable.HomeButton_textSize, 24);
		vertical = typedArray.getBoolean(R.styleable.HomeButton_vertical, true);
		home = typedArray.getInt(R.styleable.HomeButton_home, 0);
		text = typedArray.getString(R.styleable.HomeButton_text);
		System.out.println("color:" + color + " textsize:" + textsize
				+ " vertical:" + vertical + " home:" + home);
		home_icon = bitmaps[home];
		// 获取屏幕的大小，坑爹找了老半天
		screenW = ((Activity) context).getWindow().getWindowManager()
				.getDefaultDisplay().getWidth() / 3 - 8;
		switch (home) {
		case 0:
			screenH = 2 * screenW;
			break;
		case 1:
			screenH = screenW - 4;
			screenW = 2 * screenW;
			break;
		case 2:
		case 3:
			screenH = screenW;
			screenW = screenW - 2;
			break;
		case 4:
			screenH = screenW;
			screenW = screenW * 3 + 4;
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(colors[color]);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(40);
		if (vertical) {
			Matrix matrix = new Matrix();
			matrix.postTranslate(
					this.getWidth() / 2 - home_icon.getWidth() / 2,
					this.getHeight() / 2 - home_icon.getHeight() / 2 - 20);
			canvas.drawText(text, this.getWidth() / 2 - home_icon.getWidth()
					/ 2 - 38, this.getHeight() / 2 + home_icon.getHeight() / 2
					+ 20, paint);
			canvas.drawBitmap(home_icon, matrix, paint);
		} else {
			Matrix matrix = new Matrix();
			if (home == 2 || home == 3) {
				matrix.postTranslate(this.getWidth() / 2 - home_icon.getWidth()
						/ 2, this.getHeight() / 2 - home_icon.getHeight() / 2
						- 20);
				canvas.drawBitmap(home_icon, matrix, new Paint());
				paint.setTextSize(32);
				if (home == 2) {
					canvas.drawText(
							text,
							this.getWidth() / 2 - home_icon.getWidth() / 2 - 55,
							this.getHeight() / 2 + home_icon.getHeight() / 2
									+ 25, paint);
				} else {
					canvas.drawText(
							text,
							this.getWidth() / 2 - home_icon.getWidth() / 2 - 25,
							this.getHeight() / 2 + home_icon.getHeight() / 2
									+ 25, paint);
				}

			} else {
				matrix.postTranslate(this.getWidth() / 4, this.getHeight() / 2
						- home_icon.getHeight() / 2);
				canvas.drawBitmap(home_icon, matrix, new Paint());

				if (home == 4) {
					paint.setColor(getResources().getColor(
							R.color.dark_grey_text));
				}
				canvas.drawText(text, home_icon.getWidth() + this.getWidth()
						/ 4 + 20, this.getHeight() / 2 + 15, paint);
			}

		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 重新设置屏幕大小
		setMeasuredDimension(screenW, screenH);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float start = 1.0f;
		float end = 0.95f;
		Animation scaleAnimation = new ScaleAnimation(start, end, start, end,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		Animation endAnimation = new ScaleAnimation(end, start, end, start,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(200);
		scaleAnimation.setFillAfter(true);
		endAnimation.setDuration(200);
		endAnimation.setFillAfter(true);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.startAnimation(scaleAnimation);
			state = 1;
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			this.startAnimation(endAnimation);
			state = 0;
			invalidate();
			if (listener != null) {
				listener.onclick();
			}
			break;
		// 滑动出去不会调用action_up,调用action_cancel
		case MotionEvent.ACTION_CANCEL:
			this.startAnimation(endAnimation);
			state = 0;
			invalidate();
			break;
		}
		// 不返回true，Action_up就响应不了
		return true;
	}

	/**
	 * 加入响应事件
	 * 
	 * @param clickListener
	 */
	public void setOnHomeClick(HomeClickListener clickListener) {
		this.listener = clickListener;
	}

}
