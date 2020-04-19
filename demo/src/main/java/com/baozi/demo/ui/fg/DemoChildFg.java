package com.baozi.demo.ui.fg;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.demo.R;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.base.BaseFragment;
import com.baozi.mvp.presenter.EmptyPresenter;

@JView(layout = R.layout.fg_demo_child)
public class DemoChildFg extends BaseFragment<EmptyPresenter> {
    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        final RecyclerView view =  findView(R.id.rv_content);
        view.setLayoutManager(new LinearLayoutManager(mContext));
        view.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


                View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false);
                return new RecyclerView.ViewHolder(inflate) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }
}
