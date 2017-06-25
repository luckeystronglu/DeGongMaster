package com.yzh.degongmaster.activity;

import android.content.Intent;

import com.yzh.base.BaseActivity;
import com.yzh.base.PreferenceData;
import com.yzh.degongmaster.R;


public class LoadingActivity extends BaseActivity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_loading);
//		final long loadLoginAccount = PreferenceData.loadLoginAccount(this);
//		new Thread() {
//			public void run() {
//				try {
//					Thread.sleep(1 * 1000);
//					if (loadLoginAccount <= 0){
//						Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
//						startActivity(intent);
//						finish();
//					}else{
//						Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
//						startActivity(intent);
//						finish();
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}.start();
//	}

	@Override
	protected int getActivityContentId() {
		return R.layout.activity_loading;
	}


	@Override
	protected void initView() {
		final long loadLoginAccount = PreferenceData.loadLoginAccount(this);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1 * 1000);
					if (loadLoginAccount <= 0){
						Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}else{
						Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
