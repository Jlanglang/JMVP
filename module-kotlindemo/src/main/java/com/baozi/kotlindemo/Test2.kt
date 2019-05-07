package com.baozi.kotlindemo

class A {
    fun test() {
        funA();
        funB {
            print("123");
        }
    }

    fun funA(a: Int = 0, b: String = "") {

    }

    fun funB(a: () -> Unit) {

    }
}