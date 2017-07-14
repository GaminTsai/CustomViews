package com.zzt8888.materialdesign.main_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zzt8888.beans.TypeDataBean;
import com.zzt8888.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareHolder> {


    private List<TypeDataBean.ResultsEntity> source;

    private Context context;

    public WelfareAdapter(Context context) {
        this.context = context;
        source = new ArrayList<>();
    }

    public void addAll(List<TypeDataBean.ResultsEntity> entities) {
        source.addAll(entities);
        notifyDataSetChanged();
    }

    @Override
    public WelfareHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_welfare, parent, false);
        WelfareHolder holder = new WelfareHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WelfareHolder holder, int position) {
        TypeDataBean.ResultsEntity entity = source.get(position);
        String url = entity.getUrl();
        Glide.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class WelfareHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view)
        ImageView imageView;

        public WelfareHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
