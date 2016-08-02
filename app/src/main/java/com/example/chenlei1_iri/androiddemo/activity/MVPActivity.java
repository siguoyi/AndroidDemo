package com.example.chenlei1_iri.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.chenlei1_iri.androiddemo.R;
import com.example.chenlei1_iri.androiddemo.mvp.MainPresenter;
import com.example.chenlei1_iri.androiddemo.mvp.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MVPActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.tv_mvp)
    TextView tvMvp;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        loadData();
    }

    private void loadData() {
        mainPresenter = new MainPresenter();
        mainPresenter.addDataListener(this);
        mainPresenter.getString();
    }

    @Override
    public void onShowString(String str) {
        tvMvp.setText(str);
    }
}
