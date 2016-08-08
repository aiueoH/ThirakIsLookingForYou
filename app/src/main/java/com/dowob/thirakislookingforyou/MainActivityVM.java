package com.dowob.thirakislookingforyou;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Wei on 2016/7/14.
 */
public class MainActivityVM extends BaseObservable {
    private Context mContext;

    private String mButtonText = "this is button text from VM";

    public MainActivityVM(Context context) {
        mContext = context;
        context.startService(new Intent(mContext, NLService.class));
    }

    public void onClickButton(View view) {
        sendNotification();
    }

    private void sendNotification() {
        String title = "TestNotification " + Calendar.getInstance().getTime().getMinutes() + " " + Calendar.getInstance().getTime().getSeconds();
        String text = "Hello! This is test notification.";
        NotificationSender.send(mContext, title, text);
    }

    public String getButtonText() {
        return mButtonText;
    }
}
