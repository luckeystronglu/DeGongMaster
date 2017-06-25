package com.yzh.degongmaster.activity;

import android.view.View;

import com.customview.selfheader.HeaderRightTextView;
import com.libraryutils.ToastUtils;
import com.yzh.base.BaseActivity;
import com.yzh.degongmaster.R;

/**
 * Created by appadmin on 2017/6/21.
 */

public class SlideTestActivity extends BaseActivity{
    private HeaderRightTextView header_slide;


//    private Toolbar toolbar;
//    private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mDrawerToggle;
//    private ListView lvLeftMenu;
//    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
//    private ArrayAdapter arrayAdapter;
//    private ImageView ivRunningMan;
//    private AnimationDrawable mAnimationDrawable;


    @Override
    protected int getActivityContentId() {
        return R.layout.activity_slidetest;
    }


//        //京东RunningMan动画效果，和本次Toolbar无关
//        mAnimationDrawable = (AnimationDrawable) ivRunningMan.getBackground();
//        mAnimationDrawable.start();
//        toolbar.setTitle("Toolbar");//设置Toolbar标题
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //创建返回键，并实现打开关/闭监听
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                mAnimationDrawable.stop();
//            }
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                mAnimationDrawable.start();
//            }
//        };
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        //设置菜单列表
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
//        lvLeftMenu.setAdapter(arrayAdapter);


    protected void initView() {
        header_slide = findViewByIds(R.id.header_slidetest);

    }

    protected void initEvent() {
        header_slide.setonclickListener(new HeaderRightTextView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                SlideTestActivity.this.finish();
            }

            @Override
            public void onClickRightText(View view) {
                ToastUtils.showTextToast(context,"点击了测试页右侧文字");
            }
        });
    }


}
