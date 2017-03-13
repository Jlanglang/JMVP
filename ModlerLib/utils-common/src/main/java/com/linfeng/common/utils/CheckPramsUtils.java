package com.linfeng.common.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * @author jlanglang  2016/11/15 15:37
 * @版本 2.0
 * @Change
 * @des ${TODO}
 */
public class CheckPramsUtils {
    public static boolean checkParams(@Nullable String... parmas) {
        if (parmas == null) {
            return true;
        }
        int length = parmas.length;
        for (int i = 0; i < length; i++) {
            if (TextUtils.isEmpty(parmas[i])) {
                return true;
            }
        }
        return false;
    }
}
