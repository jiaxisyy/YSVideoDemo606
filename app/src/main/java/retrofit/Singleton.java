package retrofit;

/**
 * Created by shuang.xiang on 2017/3/28.
 */

public class Singleton {
    private static Singleton instance = new Singleton();

    public Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }

}
