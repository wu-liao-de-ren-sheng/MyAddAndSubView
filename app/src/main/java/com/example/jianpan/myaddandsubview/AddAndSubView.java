package com.example.jianpan.myaddandsubview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 
 * @author 键盘
 *
 */
public class AddAndSubView extends RelativeLayout {

	private static int MIN_NUM = 1;

	private int num  = MIN_NUM;
	private int max = Integer.MAX_VALUE;
	private OnNumChangeListener onNumChangeListener;

	private TextView textView;
	private ImageView mAddImg;
	private ImageView mSubImg;

	public AddAndSubView(Context context) {
		this(context, null);
	}

	public AddAndSubView(Context context, AttributeSet attrs) {
		super(context, attrs);
		control();
	}

	private void control() {
		initView();          //实例化内部view
		setViewListener();
	}


	/**
	 * 实例化内部View
	 */
	private void initView() {
		View view = inflate(getContext(), R.layout.view_add_and_sub, this);
		textView = (TextView) view.findViewById(R.id.num_tv);
		mAddImg = (ImageView) view.findViewById(R.id.num_add_img);
		mSubImg = (ImageView) view.findViewById(R.id.num_reduction_img);

		textView.setText(String.valueOf(num));
	}

	/** 设置文本变化相关监听事件 */
	private void setViewListener() {
		mAddImg.setOnClickListener(new OnButtonClickListener());
		mSubImg.setOnClickListener(new OnButtonClickListener());
	}


	public void setNum(int num)
	{
		this.num = num;
		textView.setText(String.valueOf(num));
	}

	/** textView */
	public int getNum() {
		return Integer.parseInt(textView.getText().toString());
	}

	/** 设置EditText文本变化监听 */
	public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener) {
		this.onNumChangeListener = onNumChangeListener;
	}

	public void setMax(int max) {
		if (max > 0) {
			this.max = max;
		}
	}

	public void setMinNum(int minNum){
		MIN_NUM = minNum;
	}

	/** 加减按钮事件监听器 */
	class OnButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String numString = textView.getText().toString();
			if (numString.equals("")) {
				num = MIN_NUM;
				textView.setText(String.valueOf(MIN_NUM));
			} else {

				if (v.getId() == R.id.num_add_img) {
					if (++num > max)  //先加，再判断
					{
						num--;
						if (onNumChangeListener != null) {
							onNumChangeListener.onMax(AddAndSubView.this, num);
						}
					} else {
						textView.setText(String.valueOf(num));

						if (onNumChangeListener != null) {
							onNumChangeListener.onNumChange(AddAndSubView.this, num);
						}
					}
				} else if (v.getId() == R.id.num_reduction_img) {
					if (--num < MIN_NUM)  //先减，再判断
					{
						if (onNumChangeListener != null){
							onNumChangeListener.onMin(AddAndSubView.this, num);
						}
						num++;
					} else {
						textView.setText(String.valueOf(num));
						if (onNumChangeListener != null) {
							onNumChangeListener.onNumChange(AddAndSubView.this, num);
						}
					}
				}
			}
		}
	}

	public interface OnNumChangeListener {
		/**
		 * 输入框中的数值改变事件
		 *
		 * @param view 整个AddAndSubView
		 * @param num  输入框的数值
		 */
		void onNumChange(View view, int num);
		/**
		 * 当数值倒达最小数，再次点击减号时调用
		 *
		 * @param view 整个AddAndSubView
		 * @param num  输入框的数值
		 */
		void onMin(View view, int num);
		/**
		 * 当数值倒达最大数，再次点击加号时调用
		 *
		 * @param view 整个AddAndSubView
		 * @param num  输入框的数值
		 */
		void onMax(View view, int num);
	}
}
