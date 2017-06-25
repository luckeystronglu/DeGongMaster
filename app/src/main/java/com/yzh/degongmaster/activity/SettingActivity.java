/**
 *
 */
package com.yzh.degongmaster.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.customview.selfheader.HeaderRightImageView;
import com.customview.switchbutton.SwitchButton;
import com.libraryutils.ApkUtils;
import com.libraryutils.ToastUtils;
import com.yzh.base.BaseActivity;
import com.yzh.degongmaster.R;


/**
 * @author WT00111
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private HeaderRightImageView settingHeader;
    private RelativeLayout rl_personal_info, rl_modify_password;
    private RelativeLayout rl_msg_center, rl_version_update, rl_cancel_login;
    private TextView tv_app_version;
    private SwitchButton safety_swb;
    private ImageView iv_safety_right;

    @Override
    protected int getActivityContentId() {
        return R.layout.activity_settings;
    }

    protected void initView() {

        settingHeader = findViewByIds(R.id.header_setting_activity);
        rl_personal_info = findViewByIds(R.id.rl_personal_info);
        rl_modify_password = findViewByIds(R.id.rl_modify_password);
        rl_msg_center = findViewByIds(R.id.rl_msg_center);
        rl_version_update = findViewByIds(R.id.rl_version_update);
        rl_cancel_login = findViewByIds(R.id.rl_cancel_login);

        rl_personal_info.setOnClickListener(this);
        rl_modify_password.setOnClickListener(this);
        rl_msg_center.setOnClickListener(this);
        rl_version_update.setOnClickListener(this);
        rl_cancel_login.setOnClickListener(this);

        tv_app_version = findViewByIds(R.id.tv_app_version);
        tv_app_version.setText(ApkUtils.getAppVersionName(this));

        safety_swb = findViewByIds(R.id.switch_safety_swb);
        iv_safety_right = findViewByIds(R.id.iv_safety_right);

        safety_swb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    ToastUtils.showTextToast(context,"车辆安防已打开");
                }else {

                    ToastUtils.showTextToast(context,"车辆安防已关闭");
                }
            }
        });
    }


    protected void initEvent() {
        settingHeader.setonclickListener(new HeaderRightImageView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                startActivity(new Intent(context,MainActivity.class));
            }

            @Override
            public void onClickRightIcon(View view) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_info:
//                startActivity(new Intent(SettingActivity.this, PersonalInfoActivity.class));
                break;

            case R.id.rl_modify_password:
//                startActivity(new Intent(SettingActivity.this, UpdatePasswordActivity.class));
                break;


            case R.id.rl_msg_center:
//                startActivity(new Intent(SettingActivity.this, ActivityMessageCenter.class));
                break;

            case R.id.rl_version_update:

                break;

            case R.id.rl_cancel_login:
                //退出登陆

                break;
        }
    }






    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
