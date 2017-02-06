package com.eicky;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * @author Eicky
 * @Description: 辅助服务检测工具类
 * @date: 2017/1/16 16:08
 * @version: V1.0
 */
public class AccessibilityUtil {
    public static boolean checkAccessibilityEnable(Context context) {
        //判断辅助功能是否开启
        if (!isAccessibilitySettingsOn(context)){
            // 引导至辅助功能设置页面
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity(intent);
            Toast.makeText(context, "请先开启 \"AndroidRocket\" 的辅助功能", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i(TAG, e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure
                    .ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }

        return false;
    }
}
