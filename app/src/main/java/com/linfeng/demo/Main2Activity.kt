package com.linfeng.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Main2Activity : AppCompatActivity() {
    public  val tag = this.javaClass.simpleName;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onStart() {
        super.onStart()
        resources.getColor(R.color.colorAccent);
    }

    fun get(a: Int, b: Int, c: Int) {
//        var i: Int = 1
//        var s: String? = ""
//        var c: Char = 'c'
//        var d: Double = 1.00003
//        var f: Float = 1.03f
//        var l: Long = 100000000L
//        var short: Short = 123
//
//        val i1 = 1
//        var s1 = ""
//        var c1 = 'c'
//        var d1 = 1.00003
//        var f1 = 1.03f
//        var l1 = 100000000L
//        var short1 = 123
//        val w: Int = 1
//        var q = if (w == 1) 1 else 2;
    }
}
