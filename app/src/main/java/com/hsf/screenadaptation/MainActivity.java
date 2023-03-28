package com.hsf.screenadaptation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hsf.screenadaptation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application application = GlobalUtils.INSTANCE.getApplication();
                final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
                Log.d("Daisy", "参数：" + displayMetrics.toString() + "/ " + displayMetrics.widthPixels);

                Log.d("Daisy", "参数2：" + getResources().getDisplayMetrics().toString());
            }
        });

        binding.btnSkipFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSetAdapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUtils.INSTANCE.setCustomDensity(MainActivity.this, GlobalUtils.INSTANCE.getApplication());
            }
        });

        binding.btnSetAdaptSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUtils.INSTANCE.setCustomDensityFont(MainActivity.this, GlobalUtils.INSTANCE.getApplication());
            }
        });

        binding.btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUtils.INSTANCE.flexibleToast(MainActivity.this, GlobalUtils.INSTANCE.getApplication());
            }
        });




    }
}