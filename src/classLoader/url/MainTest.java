package classLoader.url;

import java.lang.reflect.Method;

public class MainTest {
    public static void main(String[] args) {
 
        try {                   
            String rootUrl = "file:///E:\\java\\lang\\";
            NetClassLoader networkClassLoader = new NetClassLoader(rootUrl);
            String classname = "Student";
 
            Class clazz = networkClassLoader.loadClass(classname);  
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String name = method.getName();
                 System.out.println(name);
                 Object newInstance = clazz.newInstance();
                 if(name.equals("test")){
                     method.invoke(newInstance);
                 }
            }
            System.out.println(clazz.getClassLoader());
            System.out.println(clazz.getSimpleName());

        } catch (Exception e) {
            e.printStackTrace();
        }
 
 
    }
}