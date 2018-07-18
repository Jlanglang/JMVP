package com.baozi.kotlindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baozi.kotlindemo.presenter.DemoPresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
