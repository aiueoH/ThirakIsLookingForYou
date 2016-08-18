package com.dowob.thirakislookingforyou;

import android.content.ContentResolver;
import android.content.Context;
import android.content.EntityIterator;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;

import com.code19.library.AppUtils;
import com.dowob.thirakislookingforyou.BR;

import java.util.Calendar;

/**
 * Created by Wei on 2016/7/14.
 */
public class MainActivityVM extends BaseObservable {
    private Context mContext;

    private String mConsole = "";
    private String mSendNotificationButtonText = "Send notification";

    public MainActivityVM(Context context) {
        mContext = context;
        if (!AppUtils.isServiceRunning(context, NLService.class.getName()))
            context.startService(new Intent(mContext, NLService.class));
    }

    public void onResume() {
        notifyPropertyChanged(BR.enableAccessNotification);
    }

    public void onClickIntentNotificationAccessSetting(View view) {
        mContext.startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
    }

    public void onClickSendNotificationButton(View view) {
        sendNotification();
    }

    private void sendNotification() {
        String title = "TestNotification " + Calendar.getInstance().getTime().getMinutes() + " " + Calendar.getInstance().getTime().getSeconds();
        String text = "Hello! This is test notification.";
        NotificationSender.send(mContext, title, text);
        updateConsole(title, text);
    }

    private boolean isNotificationServiceRunning() {
        ContentResolver contentResolver = mContext.getContentResolver();
        String enabledNotificationListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        String packageName = mContext.getPackageName();
        return enabledNotificationListeners != null && enabledNotificationListeners.contains(packageName);
    }

    private void updateConsole(String title, String text) {
        mConsole += "\n" + title + "\n" + text;
        notifyPropertyChanged(BR.console);
    }

    public String getSendNotificationButtonText() {
        return mSendNotificationButtonText;
    }

    @Bindable
    public String getConsole() {
        return mConsole;
    }

    @Bindable
    public boolean isEnableAccessNotification() {
       return isNotificationServiceRunning();
    }
}
