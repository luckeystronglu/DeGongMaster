package com.yzh.degongmaster.fragment.fragdiscover;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.customview.selfheader.HeaderRightTextView;
import com.libraryutils.BaiduMapUtils;
import com.libraryutils.ToastUtils;
import com.yzh.base.BaseFragment;
import com.yzh.degongmaster.R;
import com.yzh.degongmaster.activity.LoginActivity;
import com.yzh.eventbus.EventBusMsg;
import com.yzh.service.SmackService;


public class FragmentDisCover extends BaseFragment {
    private HeaderRightTextView headerView;
    private Button btn_login,btn_cancel;


    @Override
    protected int getContentView() {
        return R.layout.frag_discover;
    }


    @Override
    protected void initView(View view) {
        headerView = findViewByIds(R.id.header_frag_discover);
        headerView.setTitle("发现");
        btn_login = findViewByIds(R.id.btn_login);
        btn_cancel = findViewByIds(R.id.btn_cancel);

    }



    @Override
    protected void initEventListener() {
        headerView.setonclickListener(new HeaderRightTextView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                ToastUtils.showTextToast(context,"点击了发现页返回按钮");
            }

            @Override
            public void onClickRightText(View view) {


                double v = BaiduMapUtils.DistanceOfTwoPoints(22.609155, 114.066070, 22.6237,114.0646);
                ToastUtils.showTextToast(context,"距离"+v);

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SmackService.getInstance().stopSelf();
                }catch (Exception e) {

                }
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, LoginActivity.class));
            }
        });



    }

    @Override
    public void onEventMainThread(EventBusMsg eventPackage) {
        super.onEventMainThread(eventPackage);
        if (eventPackage.getEventMsgType().equals(EventBusMsg.EventMsgType.MSG_REFRESH)) {
            ToastUtils.showTextToast(context,"跳转过来的");
        }
    }


}