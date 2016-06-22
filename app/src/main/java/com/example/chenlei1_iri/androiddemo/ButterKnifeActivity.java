package com.example.chenlei1_iri.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.click)
    Button click;
    @BindView(R.id.reset)
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);

        ButterKnife.bind(ButterKnifeActivity.this);
    }

    @OnClick({R.id.click, R.id.reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click:
                Toast.makeText(ButterKnifeActivity.this, "Click Butter knife", Toast.LENGTH_SHORT).show();
                edit.setText("Butte Knife");
                break;
            case R.id.reset:
                Toast.makeText(ButterKnifeActivity.this, "Reset Butter knife", Toast.LENGTH_SHORT).show();
                edit.setText("");
                edit.setHint(getString(R.string.please_input_something));
                break;
        }
    }
}
