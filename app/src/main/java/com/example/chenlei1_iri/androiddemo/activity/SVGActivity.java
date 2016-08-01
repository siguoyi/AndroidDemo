package com.example.chenlei1_iri.androiddemo.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.chenlei1_iri.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SVGActivity extends AppCompatActivity {

    @BindView(R.id.imv_animate)
    ImageView imvAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imv_animate)
    public void onClick() {
        animate();
    }

    private void animate() {
        Drawable drawable = imvAnimate.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
