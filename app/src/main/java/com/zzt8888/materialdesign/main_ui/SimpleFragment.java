package com.zzt8888.materialdesign.main_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzt8888.materialdesign.R;

public class SimpleFragment extends Fragment {

    private static final String INDEX = "INDEX";

    private ImageView imageView;
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
    }


    public static SimpleFragment newInstance(int index) {
        SimpleFragment simpleFragment = new SimpleFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        simpleFragment.setArguments(bundle);

        return simpleFragment;
    }

}
