package com.hsf.screenadaptation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hsf.screenadaptation.databinding.ActivityFirstBinding;

public class FirstActivity extends AppCompatActivity {

    ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GlobalUtils.INSTANCE.setCustomDensity(FirstActivity.this, GlobalUtils.INSTANCE.getApplication());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

        binding.btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUtils.INSTANCE.flexibleToast(FirstActivity.this, GlobalUtils.INSTANCE.getApplication());
            }
        });

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SmallestWidthActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        registerChange();
    }

    private void registerChange() {
        final DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics appDisplayMetrics = GlobalUtils.INSTANCE.getApplication().getResources().getDisplayMetrics();
        final DisplayMetrics activityDisplayMetrics = getResources().getDisplayMetrics();
        Log.d("Daisy", "监听前：" + systemDisplayMetrics.toString() + "\n / " +
                appDisplayMetrics.toString() + "\n / " + activityDisplayMetrics.toString());

        GlobalUtils.INSTANCE.getApplication().registerComponentCallbacks(new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(@NonNull Configuration newConfig) {
                final DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();
                final DisplayMetrics appDisplayMetrics = GlobalUtils.INSTANCE.getApplication().getResources().getDisplayMetrics();
                final DisplayMetrics activityDisplayMetrics = getResources().getDisplayMetrics();
                Log.d("Daisy", "变化后：" + systemDisplayMetrics.toString() + "\n / " +
                        appDisplayMetrics.toString() + "\n / " + activityDisplayMetrics.toString());
            }

            @Override
            public void onLowMemory() {

            }
        });


    }
}