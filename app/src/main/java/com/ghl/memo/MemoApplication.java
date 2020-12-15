package com.ghl.memo;

import android.app.Application;
import android.content.Context;

public class MemoApplication extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication(){
        return application;
    }
}
