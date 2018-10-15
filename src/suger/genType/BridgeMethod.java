package suger.genType;/**
 * Created by DELL on 2018/10/13.
 */

/**
 * user is lwb
 * 泛型擦除：就是在编译出来的字节码中，除了一些特殊的字段(singture)记录了泛型信息外，其他可执行的字节码中看不到泛型的信息(也就是所谓的擦除)。
 * 主要目的是为了兼容引入泛型之前的代码。是带有泛型的代码和不带泛型的代码编译出来的字节码没有差异，在新版的jdk中同样可以执行，
 * 如果不做擦除操作的话，编译出的字节码和不在泛型的代码就不一样，那么jvm执行的时候，还要分别进行处理，增加了工作量。处理方式不统一。
 *
 * 具体怎么擦除呢？因为泛型，就是一个泛指的不确定的类型，所谓擦除，就是找一个能作为泛指的所有类的最低层次的父类。如果这个类型不是泛指的类，那么就不用擦除了，如子类继承父类时，肯定会将泛型T只当一个类型，那么定义的子类中，就不用在擦除了。
 *
 * 比如：当定义类：FX_Object<T>，对于泛指类型的T被擦除后，用Object代表T,编译的字节码中就是ArrayList<T extends Object>
 *      get()的返回值就是Object。add方法的入参类型就是Object。
 * 比如：当定义类：FX_Number<T extends Number>,对于泛指类型T被擦出后，用Number代表T,
 *      get()的返回值就是Number。add方法的入参类型就是Number。
 *  当我们使用FX_Object时，创建一个实例new FX_Object<String>() 使用这个类的实例方法get(),会返回一个Object对象，然后编译器会在个指令invokevirtual后添加一个指令checkcast，将object转转成String。
 *
 *   桥接方法可以理解成一个适配器,生成的适配器方法,满足方法的重写,而在方法的实现中,调用子类不满足方法重写的方法,桥接方法,相当于一个满足方法重写的子类方法包装器.
 *  处理泛型重写会生成桥接方法:主要原因在于子类重写的泛型方法和父类中的方法的方法描述符是不同的,而不同的原因是:父类定义的泛型方法,T的类型为Object或者可以作为T的父类的类,而在子类的定义是,泛型T不是一个泛指类型,而是一个具体类型,
 *  所以子类重写时的方法描述符就是这个具体类了.因为jvm定义的重写,需要重写的方法,方法名和方法描述符是相同的,然而生成字节码的方法描述符是不同,为了满足jvm定义的重写语义,所以要生成一个桥接方法.
 *  除了泛型重写会生成桥接方法外,如果子类重写父类方法是,返回值类型是父类方法返回值类型的子类的话,同样也会桥接方法,
 *
 *
 *
 *
 *
 *
 **/


public class BridgeMethod {

    class Custom{};
    class VIP extends Custom{}

    //定义一个类的使用可以使用T代表一个合适的类型。当在使用这个类的使用，比如创建一个对象，作为父类被继承时，就必须给T指定一个精确的类型。而不能是一个“泛”指的类型。
    class Merchant<T extends Custom> {
        public double actionPrice(T custom){// descriptor: (Lsuger/genType/BridgeMethod$Custom;)D
            return 0.0d;
        }
    }

    //Merchant<VIP>这里必须给Merchant指定一个精确的类型，不能是一个泛指的类型如T,C或者F extends Custom等，必须使用符合条件的精准的类型：VIP，Custom等。
    class VIPnlyMerchant extends Merchant<VIP>{//这个VIP不会被擦除，因为他是一个明确指定的类，不是泛指的类。
        @Override
        public double actionPrice(VIP vip){//descriptor: (Lsuger/genType/BridgeMethod$VIP;)D
            return 0.0d;
        }
    }

    public static void main(String[] args){

    }


    /**子类VIPnlyMerchant重写了父类Merchant的actionPrice方法，那么按照jvm规范来说，子类中的方法和父类中的方法的方法名和方法描述符应该是一样的。
     *但是现在很明显两者的方法描述符是不同的，不符合jvm重写的定义，为了保证编译而成字节码能够保留重写的语义，java编译器在子类中，额外增加了一个桥接方法，
     * 该方法的方法名和方法描述符和父类的完全一样，这样就满足了重写的语义，方法的实现了，第一强转了类型，将Custorm转换成VIP然后调用子类那个不满足重写语义的方法.
     * 桥接方法的access_flag为ACC_BRIDGE, ACC_SYNTHETIC表示这个方法是桥接的,合成的,在源码中是不显示的.
     **/



    //方法调用时：方法入参类型是父类，在调用的时候，可以传入子类，原理不是多态(int明显不是char的父类)，是静态绑定的分配策略的支持。
    //但是如果入参类型是子类，调用时不能传入父类，那是因为jvm提供的四个方法调用指令的分派策略不支持。当然我们可以使用动态调用指定，自定义方法分派策略，如果你传入的参数类型时什么，我想让它分派到那就就分派到哪里，有点像向代理的意思。
}
