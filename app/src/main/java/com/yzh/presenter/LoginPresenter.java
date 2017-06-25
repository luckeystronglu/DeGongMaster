package com.yzh.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.HashMap;

import static com.yzh.base.MessageCode.LOGIN_MSGCODE_SUCCESS;


/**
 * Created by Vinice on 2016/1/19.
 */
public class LoginPresenter {
    private Context mContext;
    private Handler mHandler;
    public LoginPresenter(Context mContext, Handler mHandler){
        this.mContext = mContext;
        this.mHandler = mHandler;

    }

    public void login(final HashMap<String,String> params){
        new Thread(){
            @Override
            public void run() {
                super.run();

                Message message = Message.obtain();
                 message.obj = "";
                message.what = LOGIN_MSGCODE_SUCCESS;
                mHandler.sendMessage(message);

//                OkHttpUtils
//                        .getInstance()
//                        .post()
//                        .url(App.WEBSERVICE_URL + APIConstant.LOGIN)
//                        .addParams("LoginID", params.get("Username"))
//                        .addParams("Pwd", params.get("Password"))
//                        .addParams("IP", "")
//                        .addParams("MacAddr", "")
//                        .addParams("AppType", "MaxMES.APP")
//                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                        .addHeader("Content-Length", "length")
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int i) {
////                                logMessage("test onError");
//                                mHandler.sendEmptyMessage(MessageCode.LOGIN_MSGCODE_FAIL);
//
//                            }
//
//                            @Override
//                            public void onResponse(String s, int i) {
////                                logMessage("test onResponse" + s);
//                                Message message = Message.obtain();
//                                message.obj = s;
//                                message.what = LOGIN_MSGCODE_SUCCESS;
//                                mHandler.sendMessage(message);
//
//                            }
//                        });
            }
        }.start();
    }
}
