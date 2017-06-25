package com.yzh.degongmaster.fragment.fraghome;

import android.content.Intent;
import android.view.View;

import com.customview.selfheader.HeaderRightImageView;
import com.libraryutils.ToastUtils;
import com.yzh.base.BaseFragment;
import com.yzh.degongmaster.R;
import com.yzh.degongmaster.activity.SettingActivity;


public class FragmentHome extends BaseFragment {
    private HeaderRightImageView headerView;

    @Override
    protected int getContentView() {
        return R.layout.frag_home;
    }


    @Override
    protected void initView(View view) {
        headerView = findViewByIds(R.id.header_frag_home);
        headerView.setTitle("首页");
    }

    @Override
    protected void initEventListener() {
        headerView.setonclickListener(new HeaderRightImageView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                startActivity(new Intent(context, SettingActivity.class));
//                ToastUtils.showTextToast(context,"点击了返回按钮");
            }

            @Override
            public void onClickRightIcon(View view) {
                ToastUtils.showTextToast(context,"点击了分享");
            }
        });
    }





}