package com.zzt8888.materialdesign.main_ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzt8888.imageshow.ImageShowActivity;
import com.zzt8888.beans.TypeDataBean;
import com.zzt8888.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareHolder> {


    private List<TypeDataBean.ResultsEntity> source;

    private Activity context;

    public WelfareAdapter(Activity context) {
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

        Glide.with(context).load(url + "?imageView2/0/w/200").into(holder.imageView).onLoadStarted(context.getDrawable(R.mipmap.ic_launcher));
        holder.dateTime.setText(entity.getWho());
        holder.descContext.setText(entity.getDesc());
        holder.mainView.setOnClickListener(v -> {
            ImageShowActivity.start(v, context, url);
        });
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class WelfareHolder extends RecyclerView.ViewHolder {
        View mainView;
        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.desc_context)
        TextView descContext;
        @BindView(R.id.date_time)
        TextView dateTime;

        public WelfareHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mainView = view;
        }

    }
}
