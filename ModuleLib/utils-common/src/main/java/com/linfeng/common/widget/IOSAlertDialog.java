package com.linfeng.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;

import com.linfeng.common.R;

import static android.R.string.cancel;


public class IOSAlertDialog {
    private Context context;
    private Dialog dialog;

    private View contentView;
    private DrawableTextView txt_title;
    private DrawableTextView txt_msg;
    private DrawableTextView btn_neg;
    private DrawableTextView btn_pos;
    private View line;
    private View btLine;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public IOSAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public IOSAlertDialog builder() {
        // 获取Dialog布局
        contentView = LayoutInflater.from(context).inflate(
                R.layout.view_alertdialog, null);
        // 获取自定义Dialog布局中的控件
        txt_title = (DrawableTextView) contentView.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (DrawableTextView) contentView.findViewById(R.id.tv_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (DrawableTextView) contentView.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (DrawableTextView) contentView.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        line = contentView.findViewById(R.id.msg_line);
        line.setVisibility(View.GONE);
        btLine = contentView.findViewById(R.id.bt_lint);
        btLine.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(contentView);

        // 调整dialog背景大小
        contentView.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.8), LayoutParams.WRAP_CONTENT));
        return this;
    }

    public IOSAlertDialog setTitle(String title) {
        setTitle(title, 0);
        return this;
    }

    public IOSAlertDialog setTitle(String title, @ColorRes int color) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        if (color != 0) {
            txt_title.setTextColor(ContextCompat.getColor(context, color));
        }
        return this;
    }

    public IOSAlertDialog setMsg(String msg) {
        setMsg(msg, 0);
        return this;
    }

    public IOSAlertDialog setMsg(String msg, @ColorRes int color) {
        showMsg = true;
        if (color != 0) {
            txt_msg.setTextColor(ContextCompat.getColor(context, color));
        }
        if ("".equals(msg)) {
            txt_msg.setText("内容");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public IOSAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public IOSAlertDialog setOnDissMissListner(DialogInterface.OnDismissListener dissMissListner) {
        dialog.setOnDismissListener(dissMissListner);
        return this;
    }

    public IOSAlertDialog setPositiveButton(String text, @ColorRes int color,
                                            final OnClickListener listener) {
        showPosBtn = true;
        if (!TextUtils.isEmpty(text)) {
            btn_pos.setText(text);
        }
        if (color != 0) {
            btn_pos.setTextColor(ContextCompat.getColor(context, color));
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public IOSAlertDialog setNegativeButton(String text, @ColorRes int color,
                                            final OnClickListener listener) {
        showNegBtn = true;
        if (!TextUtils.isEmpty(text)) {
            btn_neg.setText(text);
        }
        if (color != 0) {
            btn_neg.setTextColor(ContextCompat.getColor(context, color));
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_neg.setVisibility(View.VISIBLE);
            btLine.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
        }
    }

    public View getContentView() {
        return contentView;
    }

    public IOSAlertDialog create() {
        setLayout();
        return this;
    }

    public void show() {
        create();
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
