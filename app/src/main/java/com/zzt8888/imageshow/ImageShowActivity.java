package com.zzt8888.imageshow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zzt8888.base.BaseActivity;
import com.zzt8888.materialdesign.R;
import com.zzt8888.tools.DrawableTool;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 图片支持放大，支持查看下一张
 */
public class ImageShowActivity extends BaseActivity {

    private static final String IMAGE_URL = "IMAGE_URL";
    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.main_layout)
    LinearLayout mainLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ButterKnife.bind(this);

        String imageUrl = getIntent().getStringExtra(IMAGE_URL);
        Glide.with(this).load(imageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }



            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                /**
                 * Palette.Swatch s = p.getVibrantSwatch();       //获取到充满活力的这种色调
                 * Palette.Swatch s = p.getDarkVibrantSwatch();    //获取充满活力的黑
                 * Palette.Swatch s = p.getLightVibrantSwatch();   //获取充满活力的亮
                 * Palette.Swatch s = p.getMutedSwatch();           //获取柔和的色调
                 * Palette.Swatch s = p.getDarkMutedSwatch();      //获取柔和的暗
                 * Palette.Swatch s = p.getLightMutedSwatch();    //获取柔和的亮
                 * swatch对象对应的颜色方法
                 * <p>
                 * getPopulation(): 像素的数量
                 * getRgb(): RGB颜色
                 * getHsl(): HSL颜色
                 * getBodyTextColor(): 用于内容文本的颜色
                 * getTitleTextColor(): 标题文本的颜色
                 */

                Palette.from(DrawableTool.drawableToBitmap(resource)).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        //1.活力颜色
                        Palette.Swatch swatch = palette.getLightMutedSwatch();
                        if (swatch == null) {
                            swatch = palette.getDarkVibrantSwatch();
                            if (swatch == null) {
                                swatch = palette.getVibrantSwatch();
                            }
                        }
                        int backGroundColor = swatch.getRgb();
                        mainLayout.setBackgroundColor(backGroundColor);
                        getWindow().setStatusBarColor(backGroundColor);

                    }
                });

                return false;
            }
        }).into(imageView);


    }


    public static void start(View view, Activity activity, String imageUrl) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                Pair.create(view, activity.getString(R.string.transition_image)));
        Intent starter = new Intent(activity, ImageShowActivity.class);
        starter.putExtra("IMAGE_URL", imageUrl);
        ActivityCompat.startActivity(activity, starter, options.toBundle());
    }
}
