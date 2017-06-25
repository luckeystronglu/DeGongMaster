package com.yzh.degongmaster.fragment.fragdata;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.customview.selfheader.HeaderRightImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libraryutils.ListViewRefreshUtils;
import com.libraryutils.ToastUtils;
import com.yzh.base.BaseFragment;
import com.yzh.degongmaster.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentData extends BaseFragment {
    private HeaderRightImageView headerView;
    private PullToRefreshListView data_test_lv;
    private List<String> datas;
    private ArrayAdapter<String> arrayAdapter;
    private int datasize;
    private boolean isLastPage = false;



    @Override
    protected int getContentView() {
        return R.layout.frag_data;
    }


    @Override
    protected void initView(View view) {
        headerView = findViewByIds(R.id.header_frag_data);
        headerView.setTitle("数据");
        data_test_lv = findViewByIds(R.id.data_test_lv);
        data_test_lv.setMode(PullToRefreshBase.Mode.BOTH);// 同时支持上拉下拉
        datas = new ArrayList<>();
        for (int i = 1;i < 21;i++){
            datas.add("广东深圳市" + i);
        }
        datasize = datas.size() + 1;
        arrayAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,datas);
        data_test_lv.setAdapter(arrayAdapter);
    }

    @Override
    protected void initEventListener() {
        headerView.setonclickListener(new HeaderRightImageView.clickHeaderListener() {
            @Override
            public void onClickLeftIcon() {
                ToastUtils.showTextToast(context,"点击了返回按钮");
            }

            @Override
            public void onClickRightIcon(View view) {
                ToastUtils.showTextToast(context,"点击了分享");
            }
        });
        data_test_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                ListViewRefreshUtils.setUpdateTime(refreshView);  //ListView更新刷新时间
                data_test_lv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data_test_lv.onRefreshComplete();
                    }
                }, 1000);

                if (datas.size() > 0) {
                    datas.clear();
                }
                datasize = 1;
                isLastPage = false;

                for (int i = datasize;i < datasize + 20;i++){
                    datas.add("广东深圳市" + i);
                }
                datasize = datas.size() + 1;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                ListViewRefreshUtils.setUpdateTime(refreshView); //ListView更新刷新时间
                if (datasize >= 60) {
                    isLastPage = true;
                }else {
                    isLastPage = false;
                }

                if (isLastPage) {
                    ToastUtils.showTextToast(getContext(), getResources().getString(R.string.this_is_last_page));
                    data_test_lv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            data_test_lv.onRefreshComplete();
                        }
                    }, 600);
                }else {
                    data_test_lv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            data_test_lv.onRefreshComplete();
                        }
                    }, 600);

                    for (int i = datasize;i < datasize + 10;i++){
                        datas.add("广东深圳市" + i);
                    }
                    datasize = datas.size() + 1;
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        });

        initFragmentListViewTipText(data_test_lv,isLastPage);  //上拉加载、下拉刷新的状态提示文字
    }



}