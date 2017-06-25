package com.yzh.degongmaster.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.RadioGroup;

import com.libraryutils.ToastUtils;
import com.yzh.base.BaseActivity;
import com.yzh.degongmaster.R;
import com.yzh.degongmaster.fragment.fragdata.FragmentData;
import com.yzh.degongmaster.fragment.fragdiscover.FragmentDisCover;
import com.yzh.degongmaster.fragment.fraghome.FragmentHome;
import com.yzh.degongmaster.fragment.fragme.FragmentMy;
import com.yzh.eventbus.EventBusMsg;
import com.yzh.xmpp.smackconnection.SmackConst;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private SmackReciver SmackReciver;
    private int fragIndex = 0;


    @Override
    protected int getActivityContentId() {
        return R.layout.activity_main;
    }


    protected void initView() {
        Intent intent = getIntent();
        fragIndex = intent.getIntExtra("index",0);

        radioGroup = findViewByIds(R.id.rg_tab);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.getChildAt(fragIndex).performClick();


        SmackReciver=new SmackReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmackConst.ACTION_NEW_MESSAGE);
        intentFilter.addAction(SmackConst.ACTION_ERROR_DISCONNECTED);
        intentFilter.addAction(SmackConst.ACTION_RECONNECT_SUCCESS);
        registerReceiver(SmackReciver, intentFilter);
    }


//    protected void initEvent() {
//
//    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_frag_home:
                //点击"首页"
                fragmentManager(R.id.fl_fragment, new FragmentHome(), "fraghome");
                break;
            case R.id.rb_frag_data:
                //点击"数据"
                fragmentManager(R.id.fl_fragment, new FragmentData(), "fragdata");
                break;

            case R.id.rb_frag_discover:
                //点击"发现"
                fragmentManager(R.id.fl_fragment, new FragmentDisCover(), "fragdiscover");
                break;

            case R.id.rb_frag_my:
                //点击"我"
                fragmentManager(R.id.fl_fragment, new FragmentMy(), "fragmy");
                break;
        }
    }


//    private long pressTime;
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - pressTime < 2000)) {
//                this.finish();
//            }else {
//                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
//                pressTime = System.currentTimeMillis();
//            }
//
//        }
//        return true;
//    }


    public class SmackReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch(intent.getAction()){
                case SmackConst.ACTION_NEW_MESSAGE:
                    String from = intent.getStringExtra(SmackConst.BUNDLE_FROM_JID);
                    String body = intent.getStringExtra(SmackConst.BUNDLE_MESSAGE_BODY);
                    ToastUtils.showTextToast(context, "收到"+from+"消息："+body);
                    break;
                case SmackConst.ACTION_ERROR_DISCONNECTED:
                    ToastUtils.showTextToast(context, "连接断开，正在重连...");
                    break;
                case SmackConst.ACTION_RECONNECT_SUCCESS:
                    ToastUtils.showTextToast(context, "重连成功");
                    break;
            }
        }

    }

    @Override
    public void onEventMainThread(EventBusMsg eventPackage) {
        super.onEventMainThread(eventPackage);
        if (eventPackage.getEventMsgType().equals(EventBusMsg.EventMsgType.JUDGE_IS_LOGIN_ORNOT)) {
            ToastUtils.showTextToast(context,"eventPackage.getEventMsgType() = " + eventPackage.isJudgeLogin());
        }
    }
}
