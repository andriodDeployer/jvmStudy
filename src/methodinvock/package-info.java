/**
 * Created by DELL on 2018/10/10.
 * 1.重载与重写：
 *  重载：在java程序中，如果一个类中，有多于一个方法名称相同，形式参数类型不同的方法的话，那么这几个方法之间就是重载关系，而在字节码中，重载的限制更少一些，在字节码文件中，
 *  方法名相同，形式参数或返回值不同就是重载。
 *  重载方法在编译期即可完成识别，所以重载方法的调用又称为静态绑定，java编译器会根据所传入的参数的声明类型(静态类型，而不是实际类型)，来选取重载方法，
 *  选取分为三个步骤：
 *      1.在不考虑自动装拆箱，不考虑变长参数的情况下选取重载方法，如果找不到适配方法，则进入第二步。如果找到多个进入第四步。
 *      2.考虑拆装箱，不考虑变长参数情况下选择重载方法，如果找不到适配方法，则进入第三步。如果找到多个进入第四步。
 *      3.同时考虑拆装箱和变长参数，如果找不到适配方法，则报错，如果找到多个进入第四步。
 *      4.在多个方法中找到一个最贴切的方法，贴切程度取决于形式参数的继承关系。如果父子类都适合的话，则选取子类型。
 *  在java语言中：如果子类中定义一个和父类中非私有方同名的方法，且方法的参数类型不同，那么在子类中，这两个方法同样构成重载。(对返回值类型不做限制)
 *
 *  在java语言中：如果子类中定义一个和父类中非私有方同名的方法，且方法的参数类型相同，如果方法是静态的，则子类覆盖了父类中的方法，
 *  如果不是静态的话，则构成重写。(对返回值类型不做限制)
 * 在jvm中确定一个方法的的条件主要包括：类名，方法名和方法描述符(方法的参数类型和方法的返回值类型)，在jvm进程中，每个方法都是唯一的。
 * 这样jvm可以根据三个条件能唯一确定一个方法。而且会在类加载过程验证阶段，对唯一性进行验证。
 *  在字节码中，如果子类定义了一个与父类中非私有，非静态的同名方法，且方法描述符相同，jvm认为是重写，比java程序要求更严格。
 *
 *
 *  分派：就是分配，确定一个方法。
 *  方法调用指令：
 *      invokestatic：调用静态方法：在解析阶段将符号引用解析为直接引用
 *      invokespecial：调用私有方法，构造方法，父类方法：在解析阶段完成解析。
 *      invokevirtual: 调用虚方法：在运行阶段完成解析
 *      invokeinterface: 调用接口方法：在运行阶段完成解析。
 *      invokedynamic: 调用动态方法
 *      对于上面的四种指令，jvm实现了分派逻辑(确定唯一方法的逻辑)，而对于invokedynamic的分派逻辑需要用户自己设定。让用户动态的决定使用上面四个中的那个一个。
 *      对于invokestatic和invokespecial指定方法在类加载的解析阶段就可以唯一确定调用的方法，又称为解析调用。
 *      因为在解析阶段就把方法的符号引用解析为直接引用。这两个指令调用的方法称为非虚方法。
 *      其实对于无法被重写的方法都称为非虚方法，都可以在解析阶段完成解析。虽然final修饰的方法，是由invokevirtual指令执行，但是它确实非虚方法。
 *
 *
 *  静态分派：根据静态类型完成方法的分派。分派逻辑作用于方法的参数类型，根据传入参数的静态类型确定分派。确定某个类中的具体方法。
 *  动态分派：根据动态类型完成方法的分派。分派逻辑作用于方法的接收者类型。根据接收者的动态类型确定分派。确定调用的方法在那个类中，子类还是父类。
 *  通常方法的分派：先根据动态分派，确定方法在那个类中，然后在根据静态分派，确定这个类中具体那个方法
 *
 *
 *  动态分派的具体过程：1.先确定方法接收者的动态类型。2.从方法接收者的具体类/接口中寻找合适的方法(静态绑定)。
 *  寻找的方式在大多数的jvm中采用空间换时间的方式，将方法按照类型存放到方法表中，(vtable,itable),更具动态类型，找到具体类，让后从具体类的方法表中找出合适的方法。
 *  当重复多次调用一个虚方法时，为了减少动态调用寻找方法的次数，在即时编译中有两种优化手段：
 *      1.内联缓存：将动态类型和动态类型的目标方法缓存起来。当动态调用时，如果动态类型已经缓存过了，直接从缓存中取出目标方法进行调用，不用在从动态类型中的方法表中查找了，减少了查找的次数。
 *      如重复多次执行animal.eat()，如果把cat和cat的eat()缓存起来的话，当动态类型为cat的话，就可以指定调用缓存起来的cat的eat方法了。
 *      通常的缓存实现为单态内敛缓存:所谓单态缓存，就是仅仅缓存一个动态类型。那么当一个动态类型在缓存中不存在时，就需要采用原始的方式从方法表中查找，那么对于缓存的内容怎么处理呢，
 *      第一种方式：进行替换，用新的类型替换旧的类型。这种方式有一个明显的确定，如果交替使用两种动态类型进行调用的话，那么内敛缓存的存在，经失去意义，只增加了写缓存的额外开销，而没有用缓存的性能提升。
 *      第二种方式：(目前jvm使用的方式)不对缓存的内容做任何修改，也就是缓存不中的动态类型，就采用原始的方法表查找。
 *
 *      2.方法内联：内联缓存，减少了从方法表中查找目标方法的的次数，可以从缓存中直接取出目标方法，但是多次执行目标方法同样很耗时。
 *
 *
 *
 *
 */
package methodinvock;