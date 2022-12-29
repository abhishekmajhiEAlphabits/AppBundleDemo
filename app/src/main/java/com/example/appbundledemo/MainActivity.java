package com.example.appbundledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private SplitInstallManager splitInstallManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splitInstallManager =
                SplitInstallManagerFactory.create(getApplicationContext());

    }

    public void loadFeatureOne(View view) {
        SplitInstallRequest request = SplitInstallRequest.newBuilder().addModule("feature1").build();

        splitInstallManager.startInstall(request).addOnSuccessListener(new OnSuccessListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                Intent intent = new Intent().setClassName(getPackageName(), "com.example.feature1.FeatureOneActivity");
                startActivity(intent);
                Log.i("TAG", "Module download success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), "Couldn't download feature1: " + e.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("TAG", "Module download success");
            }
        });
    }
}