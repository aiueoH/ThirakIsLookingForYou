package com.dowob.thirakislookingforyou;

import android.content.Context;
import android.content.EntityIterator;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Environment;
import android.view.View;
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
        context.startService(new Intent(mContext, NLService.class));
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
}
