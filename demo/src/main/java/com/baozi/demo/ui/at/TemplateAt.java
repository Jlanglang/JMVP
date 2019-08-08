package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.baozi.mvp.tempalet.options.ToolbarOptions;

@JView(p = DemoPresenter.class, layout = R.layout.at_template)
public class TemplateAt extends TemplateActivity<DemoPresenter> {
    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getToolbarHelper().setTitle("我是模板Activity")
                .setLeading("关闭");
        RecyclerView view = findView(R.id.rv_content);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false);
                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(inflate) {
                };

                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public int getItemCount() {
                return 1000;
            }
        });
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        return super.getToolbarOptions()
                .setNoBack(true)
                .setToolbarColor(0)
                .setElevation(0);
    }
}
