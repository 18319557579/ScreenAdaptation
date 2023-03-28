package com.hsf.screenadaptation;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

public enum GlobalUtils {
    INSTANCE;

    private Application mApplication;

    public Application getApplication() {
        if (mApplication == null) {
            try {
                mApplication = (Application) (Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mApplication;
    }

    public void setCustomDensity(Activity activity, Application application) {
        final DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        final float targetDensity = systemDisplayMetrics.widthPixels / 360f;
        final float targetScaledDensity = targetDensity * (systemDisplayMetrics.scaledDensity / systemDisplayMetrics.density);
        final int targetDensityDpi = (int) (160 * targetDensity);

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    public void cancelCustom(Activity activity, Application application) {
        final DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();

        //如果Toast用的是application的话，那么就取消application的就行了
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        appDisplayMetrics.density = systemDisplayMetrics.density;
        appDisplayMetrics.scaledDensity = systemDisplayMetrics.scaledDensity;
        appDisplayMetrics.densityDpi = systemDisplayMetrics.densityDpi;

        //如果Toast用的是activity的话，那么就取消application的就行了
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = systemDisplayMetrics.density;
        activityDisplayMetrics.scaledDensity = systemDisplayMetrics.scaledDensity;
        activityDisplayMetrics.densityDpi = systemDisplayMetrics.densityDpi;
    }

    public void flexibleToast(Activity activity, Application application) {
        cancelCustom(activity, application);
        Toast.makeText(activity, "Hello大家好123", Toast.LENGTH_SHORT).show();
        setCustomDensity(activity, application);
    }

    public void setCustomDensityFont(Activity activity, Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        final float targetDensity = 6;
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.scaledDensity = targetDensity;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.scaledDensity = targetDensity;
    }
}
