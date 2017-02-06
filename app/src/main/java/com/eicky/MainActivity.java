package com.eicky;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAlerWindowPermission();
        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAlerWindowPermission())
                    openAlertWindow();
            }
        });
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrackerService.class);
                intent.putExtra(TrackerService.TYPE_KEY, TrackerService.Type.CLOSE.code);
                MainActivity.this.startService(intent);
            }
        });
    }

    private void openAlertWindow() {
        if (AccessibilityUtil.checkAccessibilityEnable(MainActivity.this)) {
            Intent intent = new Intent(MainActivity.this, TrackerService.class);
            intent.putExtra(TrackerService.TYPE_KEY, TrackerService.Type.OPEN.code);
            startService(intent);
            finish();
        }
    }

    private boolean checkAlerWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(MainActivity.this, "当前无悬浮窗权限，请授权！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" +
                        getPackageName()));
                startActivityForResult(intent, PERMISSION_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "权限授予成功！", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
