package com.baozi.demo.ui.at;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.mvp.StartFactory;


public class TestAt extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.at_test);
        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartFactory.startActivity(v.getContext(), BaseAt.class);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(this, TestAt.class);
        startActivity(intent);
    }
}
