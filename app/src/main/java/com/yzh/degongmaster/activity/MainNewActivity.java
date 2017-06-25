package com.yzh.degongmaster.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.customview.selfheader.HeaderRightImageView;
import com.customview.view.SelfListview;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yzh.base.BaseActivity;
import com.yzh.degongmaster.R;
import com.yzh.degongmaster.adapter.HomeLvAdapter;
import com.yzh.model.LoginItemEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by appadmin on 2017/6/22.
 */

public class MainNewActivity extends BaseActivity {
    private HeaderRightImageView rightImageView;
    private String loginString;
    private List<LoginItemEntity> itemList;

    private SelfListview selfListview;
    private HomeLvAdapter homeLvAdapter;

    @Override
    protected int getActivityContentId() {
        return R.layout.activity_main_new;
    }

    protected void initView() {
        rightImageView = findViewByIds(R.id.header_main_new);
        Intent intent = getIntent();
        loginString = intent.getStringExtra("LoginString");

        selfListview = findViewByIds(R.id.self_lv_home);
        homeLvAdapter = new HomeLvAdapter(this);
        selfListview.setAdapter(homeLvAdapter);

    }

    @Override
    protected void initEvent() {
        rightImageView.setonclickListener(new HeaderRightImageView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                startActivity(new Intent(MainNewActivity.this,MainActivity.class));
            }

            @Override
            public void onClickRightIcon(View view) {

            }
        });
    }


    @Override
    protected void loadDatas() {
        if (loginString != null && !loginString.equals("")) {
            //删除Xml标题头,并去掉首尾空格
            if (loginString.contains("<?xml version=\"1.0\" encoding=\"utf-8\"?>")) {
                loginString = loginString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
            }
            if (loginString.contains("<string xmlns=\"http://tempuri.org/\">")) {
                loginString = loginString.replace("<string xmlns=\"http://tempuri.org/\">", "");
            }

            if (loginString != null && !loginString.equals("")) {
                loginString = loginString.trim();
                try {
                    JSONObject jobj = new JSONObject(loginString);
                    JSONArray itemLists = jobj.optJSONArray("Menu");
                    if (itemLists != null) {
                        TypeToken<List<LoginItemEntity>> tt = new TypeToken<List<LoginItemEntity>>() {
                        };
                        itemList = new Gson().fromJson(itemLists.toString(), tt.getType());
//                        int size = itemList.size();
                        homeLvAdapter.setDatas(itemList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private long pressTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - pressTime < 2000)) {
                this.finish();
            }else {
                Toast.makeText(MainNewActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                pressTime = System.currentTimeMillis();
            }

        }
        return true;
    }
}
