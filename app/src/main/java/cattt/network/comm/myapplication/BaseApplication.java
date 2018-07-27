package cattt.network.comm.myapplication;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BlurHelper.init(getApplicationContext());
    }
}
