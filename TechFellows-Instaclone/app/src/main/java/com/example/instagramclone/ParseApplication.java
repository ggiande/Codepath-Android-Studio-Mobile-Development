package com.example.instagramclone;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("gZJT0sklccbOmHDSDtM7uAqWVXvmarZRAAQvGuSm")
                .clientKey("9Cb0lHK7O34LMLjjEfgp3vUQPvOV3viJ3QMQhZcs")
                .server("https://parseapi.back4app.com")
                .build()
        );

        // OLD USING HEROKU
//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId("gian-parstagram") // should correspond to APP_ID env variable
//                .clientKey("CPMKOVERPARSEG485645gvyfvg_$^#")  // set explicitly unless clientKey is explicitly configured on Parse server
//                .server("https://gian-parstagram.herokuapp.com/parse")
//                .build());
    }
}
