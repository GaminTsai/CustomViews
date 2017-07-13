package com.zzt8888.materialdesign.main_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zzt8888.materialdesign.R;
import com.zzt8888.tools.DrawableTool;

public class SimpleFragment extends Fragment {

    private static final String INDEX = "INDEX";

    private ImageView imageView;
    private LinearLayout mainLinearLayout;
    private int index;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt(INDEX);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        imageView = (ImageView) view.findViewById(R.id.image_view);
        mainLinearLayout = (LinearLayout) view.findViewById(R.id.main_layout);
        switch (index) {
            case 1:
                imageView.setImageResource(R.mipmap.landscape1);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.landscape2);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.landscape3);
                break;
            case 4:
                imageView.setImageResource(R.mipmap.landscape4);
                break;
            default:
                imageView.setImageResource(R.mipmap.landscape5);
                break;
        }

        changeBackgroundFromBitmap();
    }


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
    private void changeBackgroundFromBitmap() {
        Palette.from(DrawableTool.drawableToBitmap(imageView.getDrawable())).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //1.活力颜色
                Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                if (swatch == null) {
                    swatch = palette.getLightMutedSwatch();
                    if (swatch == null) {
                        swatch = palette.getVibrantSwatch();
                    }
                }
                int backGroundColor = swatch.getRgb();
                mainLinearLayout.setBackgroundColor(backGroundColor);

            }
        });
    }


    public static SimpleFragment newInstance(int index, String title) {
        SimpleFragment simpleFragment = new SimpleFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putString("TITLE", title);
        simpleFragment.setArguments(bundle);

        return simpleFragment;
    }

}
