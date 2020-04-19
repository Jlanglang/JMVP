package com.baozi.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public class StartFactory {
    /**
     * 跳转fragment
     *
     * @param tofragment
     */

    public static void startFragment(AppCompatActivity activity, Fragment tofragment) {
        startFragment(activity, tofragment, null);
    }

    /**
     * @param activity
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */

    public static void startFragment(AppCompatActivity activity, Fragment fragment, String tag) {
        startFragment(activity, fragment, tag, true);
    }

    /**
     * @param activity
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    public static void startFragment(AppCompatActivity activity, Fragment fragment, String tag, boolean isAdd) {
        startFragment(activity, fragment, tag,
                MVPManager.getEnterAnim(),
                MVPManager.getExitAnim(),
                MVPManager.getEnterPopAnim(),
                MVPManager.getExitPopAnim(), isAdd);
    }


    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    public static void startFragment(AppCompatActivity activity, Fragment fragment, String tag, int enter, int popExit) {
        startFragment(activity, fragment, tag, enter, popExit, true);
    }

    public static void startFragment(AppCompatActivity activity, Fragment fragment, String tag, int enter, int popExit, boolean isAddBack) {
        startFragment(activity, fragment, tag,
                enter,
                0,
                0,
                popExit, isAddBack);
    }

    public static void startFragment(AppCompatActivity activity, Fragment fragment, String tag, int enterAnim, int exitAnim, int popEnter, int popExit, boolean isAddBack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);
        fragmentTransaction.add(android.R.id.content, fragment, tag);
        if (isAddBack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * @param rootFragment Activity内部fragment
     * @param containerId  fragment父容器
     */
    public static void replaceFragment(AppCompatActivity context, Fragment rootFragment, int containerId) {
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, rootFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 跳转Activity
     */
    public static void startActivity(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public static void startActivity(Context context, Class aClass, Bundle bundle) {
        Intent intent = new Intent(context, aClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public static void startActivity(Context context, Class aClass, Bundle bundle, int flag) {
        Intent intent = new Intent(context, aClass);
        intent.putExtras(bundle);
        intent.addFlags(flag);
        context.startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public static void startActivity(Context context, Class aClass, int flag) {
        Intent intent = new Intent(context, aClass);
        intent.addFlags(flag);
        context.startActivity(intent);
    }

}
