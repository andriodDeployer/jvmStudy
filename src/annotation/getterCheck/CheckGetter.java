package annotation.getterCheck;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DELL on 2018/10/17.
 * 因为起到语法检查/约束，仅仅在编译器起租用就行了，将Retention设置为SOURCE即可。
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface CheckGetter {
}
