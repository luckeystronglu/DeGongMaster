package com.yzh.degongmaster.fragment.fragme;

import android.content.Intent;
import android.view.View;

import com.customview.selfheader.HeaderRightTextView;
import com.libraryutils.ToastUtils;
import com.yzh.base.BaseFragment;
import com.yzh.degongmaster.R;
import com.yzh.degongmaster.activity.SlideTestActivity;


public class FragmentMy extends BaseFragment {
    private HeaderRightTextView headerView;

    @Override
    protected int getContentView() {
        return R.layout.frag_me;
    }


    @Override
    protected void initView(View view) {
        headerView = findViewByIds(R.id.header_frag_me);
        headerView.setTitle("我的");
    }

    @Override
    protected void initEventListener() {
        headerView.setonclickListener(new HeaderRightTextView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                startActivity(new Intent(context, SlideTestActivity.class));
//                ToastUtils.showTextToast(context,"点击了我的页面返回按钮");
            }

            @Override
            public void onClickRightText(View view) {
                ToastUtils.showTextToast(context,"点击了我的页面右键文字");
            }
        });
    }





}