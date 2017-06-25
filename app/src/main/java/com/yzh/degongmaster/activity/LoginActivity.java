package com.yzh.degongmaster.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.libraryutils.PreferencesUtils;
import com.libraryutils.ToastUtils;
import com.yzh.base.App;
import com.yzh.base.BaseActivity;
import com.yzh.degongmaster.R;
import com.yzh.presenter.LoginPresenter;
import com.yzh.xmpp.smackconnection.SmackConst;

import java.util.HashMap;

import static com.yzh.base.MessageCode.LOGIN_MSGCODE_FAIL;
import static com.yzh.base.MessageCode.LOGIN_MSGCODE_SUCCESS;


public class LoginActivity extends BaseActivity {

    private String mUserNameStr = "";
    private String mPasswordStr = "";

    private EditText et_username;
    private EditText et_password;

    private BroadcastReceiver receiver;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                switch (msg.what) {
                    case LOGIN_MSGCODE_SUCCESS:
                        if (dlgWaiting.isShowing()) {
                            dlgWaiting.dismiss();
                        }
                        mDlgWaitingHandler.removeMessages(MSG_cannt_get_data);

                        String strObj = (String) msg.obj;

                        PreferencesUtils.putSharePre(LoginActivity.this, "username", mUserNameStr);
                        PreferencesUtils.putSharePre(LoginActivity.this, "password", mPasswordStr);

                        Intent inte = new Intent(LoginActivity.this,MainNewActivity.class);
                        inte.putExtra("LoginString",strObj);
                        startActivity(inte);

                        LoginActivity.this.finish();

                        break;

                    case LOGIN_MSGCODE_FAIL:
                        if (dlgWaiting.isShowing()) {
                            dlgWaiting.dismiss();
                        }
                        mDlgWaitingHandler.removeMessages(MSG_cannt_get_data);
                        ToastUtils.showTextToast(context,"登陆失败");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected int getActivityContentId() {
        return R.layout.activity_login;
    }



    protected void initView() {
        et_username = findViewByIds(R.id.et_login_username);
        et_password = findViewByIds(R.id.et_login_password);

//		initReceiver();
    }



    //点击登陆
    public void loginClick(View view){
        attemptLogin();
    }




//	public void login(View v){
//		Log.i("XMPP", "login");
//		//启动SmackService
//		Intent intent=new Intent(LoginActivity.this,SmackService.class);
//		intent.putExtra("username", username.getText().toString());
//		intent.putExtra("password", password.getText().toString());
//		startService(intent);
//	}


    private void initReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(SmackConst.ACTION_IS_LOGIN_SUCCESS)) {
                    boolean isLoginSuccess = intent.getBooleanExtra("isLoginSuccess", false);


                    if (isLoginSuccess) {//登录成功
                        //默认开启声音和震动提醒
                        PreferencesUtils.putSharePre(LoginActivity.this, SmackConst.MSG_IS_VOICE, true);
                        PreferencesUtils.putSharePre(LoginActivity.this, SmackConst.MSG_IS_VIBRATE, true);
                        Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                        intent2.putExtra("index", 2);
//						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        finish();
                    } else {
                        ToastUtils.showTextToast(LoginActivity.this, "登录失败，请检您的网络是否正常以及用户名和密码是否正确");
                    }
                }
            }
        };
        //注册广播接收者
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(SmackConst.ACTION_IS_LOGIN_SUCCESS);
        registerReceiver(receiver, mFilter);
    }

    protected void onStart() {
        super.onStart();
        String un = PreferencesUtils.getSharePreStr(this, "username");//用户名
        String pwd = PreferencesUtils.getSharePreStr(this, "password");//密码
        if (!TextUtils.isEmpty(un)) {
            et_username.setText(un);
            et_username.setSelection(et_username.length());
        }
        if (!TextUtils.isEmpty(pwd)) {
            et_password.setText(pwd);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
    }


    private void attemptLogin() {

        mUserNameStr = et_username.getText().toString();
        mPasswordStr = et_password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(mUserNameStr)) {
            ToastUtils.showTextToast(LoginActivity.this, "用户名不能为空");
            focusView = et_username;
            cancel = true;

        }
//		else if (!VerifyUtils.identity(mUserNameStr)) {
//			ToastUtils.showTextToast(LoginActivity.this,"请输入正确的身份证号码");
//			focusView = username;
//			cancel = true;
//
//		}

        // Check for a valid password.
        if (TextUtils.isEmpty(mPasswordStr)) {
            ToastUtils.showTextToast(LoginActivity.this, "密码不能为空");
            focusView = et_password;
            cancel = true;

        } else if (mPasswordStr.length() < 6) {
            ToastUtils.showTextToast(LoginActivity.this, "密码不能少于6位");
            focusView = et_password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            dlgWaiting.show();
            mDlgWaitingHandler.sendEmptyMessageDelayed(MSG_cannt_get_data, App.WAITTING_SECOND);

            LoginPresenter loginPresenter = new LoginPresenter(this, mHandler);
            HashMap<String, String> map = new HashMap<>();
            map.put("Username",mUserNameStr);
            map.put("Password",mPasswordStr);
            loginPresenter.login(map);

        }
    }


}
