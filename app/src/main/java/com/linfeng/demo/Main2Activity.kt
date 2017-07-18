package com.linfeng.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import rx.Observable


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Observable.just("").map { s -> if (s == null) "" else "123" }
                .filter { s -> s == "" }
                .subscribe { s -> Toast.makeText(this, s, Toast.LENGTH_SHORT) }

    }
}
