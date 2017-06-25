package com.yzh.degongmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yzh.degongmaster.R;
import com.yzh.model.LoginItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/7.
 */
public class HomeGridAdapter extends BaseAdapter {
    private Context context;
    private List<LoginItemEntity.ChildMenuBean> gridlist;

    public HomeGridAdapter(Context context) {
        this.context = context;
        this.gridlist = new ArrayList<>();
    }


    //设置数据源
    public void setDatas(List<LoginItemEntity.ChildMenuBean> gridlist){
        this.gridlist = gridlist;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return gridlist.size();
    }

    @Override
    public Object getItem(int position) {
        return gridlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (ListViewHolder) convertView.getTag();
        } else {
            viewHolder = new ListViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_self_gv, null);
            viewHolder.grid_tv_gv = (TextView) convertView.findViewById(R.id.tv_item_home_gv);
            viewHolder.grid_img = (ImageView) convertView.findViewById(R.id.iv_item_home_gv);
            convertView.setTag(viewHolder);
        }

        viewHolder.grid_tv_gv.setText(gridlist.get(position).getMenuName());
        Glide.with(context)
                .load(gridlist.get(position).getProPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .crossFade(500)
                .into(viewHolder.grid_img);
        /* .placeholder(R.drawable.image_app)*/
        return convertView;
    }


    class ListViewHolder{
        protected TextView grid_tv_gv;
        protected ImageView grid_img;
    }
}
