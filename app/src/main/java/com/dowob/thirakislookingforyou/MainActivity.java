package com.dowob.thirakislookingforyou;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dowob.thirakislookingforyou.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityVM vm = new MainActivityVM(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(vm);
    }
}
