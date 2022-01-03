package PageObject.Action;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;

import java.util.concurrent.ConcurrentHashMap;

public class facade  {
    private static volatile page_1 instance_1;
    private static volatile page_2 instance_2;

    static ConcurrentHashMap<String , Object> map = new ConcurrentHashMap<>();
    private synchronized static  <T> void create(Class<T> Clazz){
        try {
            map.put(Clazz.getName(),Clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private static <T> Object get(Class<T> Clazz) {
        String className = Clazz.getName();
        if (!map.containsKey(className))
            create(Clazz);
        return map.get(className);
    }

   public static page_1 get_1(){
        return (page_1) get(page_1.class);
   }
    public static page_2 get_2(){
        return (page_2) get(page_2.class);
    }
    public static page_1 getInstance_1() {

        if (instance_1 == null) {
            synchronized (page_1.class) {
                if (instance_1 == null) {
                    instance_1 = new page_1();
                }
            }
        }
        return instance_1;
    }
    public static page_2 getInstance_2() {

        if (instance_2 == null) {
            synchronized (page_2.class) {
                if (instance_2 == null) {
                    instance_2 = new page_2();
                }
            }
        }
        return instance_2;
    }
}
