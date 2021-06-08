package liyihuan.app.android.lazyfragment;

import android.app.Application;

/**
 * @ClassName: AppCache
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 22:55
 */
public class AppCache {
    private static Application context;

    public static Application getContext() {
        return context;
    }

    public static void setContext(Application app) {
        if (app == null) {
            AppCache.context = null;
            return;
        }
        AppCache.context = app;
    }
}
