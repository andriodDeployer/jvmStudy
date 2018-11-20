/**
 * Created by DELL on 2018/11/15.
 *
 * java中的异常类继承体系，其实很简单，根类为Throwable，这个类有两个子类Exception和Error。
 * 然后Exception和Error又有各自的子类。
 * 总的来说就三个类(连抽象类和接口都没有)
 *
 * 通过观察源码：各个类本身的结构也很简单，所有的状态字段信息和所有功能都定义在Throwable中，Exception和Error中都是重载的构造器。
 * 可以说，异常的类及类的继承体系结构都是异常的简单。
 * 之所以我们它陌生，是因为我们用的少，再加上这个类的对象，我们无法主动操作，他是被jvm使用的，我们最常用的就是打印出它的堆栈信息，再就是自定义一个异常。
 *
 *
 * Throwable类中的常用字段：
 *          private String detailMessage;   ：exception的详细信息，在初始化的时候完成对这个字段的初始化，只有这个一个地方可以完成初始化。
 *          private Throwable cause;        ：exception产生的原因，通常当捕获一个异常后，需要新抛出另外一个新的异常的时候，如果不将第一个异常的信息放入到新的异常，那么就看不到上一个异常的信息，这就导致第一个异常信息丢失，为了防止信息丢失，
 *                                          可以将上一个异常作为新异常的cause，通常用在catch代码块中生成新异常时使用这种方式，因为在catch中有上一层异常的引用。这样在打印新异常信息是，就将上一个异常做为cause by打印出来。这个字段的初始化，可以在构造异常对象时完成，也可调用initCause(cause)完成
 *          private StackTraceElement[] stackTrace; :异常的堆栈信息，StackTraceElement存放了一个栈帧的信息，也就是一个方法调用的信息，如这个方法名称，所在的类，以及在哪一行等，具体可以参看{@link java.lang.StackTraceElement},在调用fillTracestace()（无参）方法时
 *                                                  会将从当前栈顶到调用这个方法时栈帧，之间的所有栈帧全部获取到，并将每个栈帧封装成StaceTraceElement对象放到该字段指定的数组中。
 *          private List<Throwable> suppressedExceptions：压缩的异常信息。cause的作用可以防止上层信息丢失，但是这种方式需要获取上层异常的引用才可以，如果新异常发生在finally中的话，也就是没有上一层异常的引用，为了解决这个问题，java7中构造了一个名为try-with-resource的语法糖，在字节码层面将finally中抛出异常添加到该异常的该字段对应的list中
 *
 *          还有一些就是定义的常量了。
 *
 *Throwable类中常用的方法：
 *          public String getMessage():获取deatilMessage信息
 *          public String getLocalizedMessage()：内部调用getMessage();
 *          public synchronized Throwable getCause()：获取cause字段值
 *          public synchronized Throwable initCause(Throwable cause)：设置cause字段
 *          public void printStackTrace()：打印异常的的堆栈，打印顺序：
 *                                         1.打印出该异常的信息,异常名称+detaileMessage，具体看异常的tostring
 *                                         2.打印该异常的堆栈，栈顶到该异常的创建所在方法，之间的所有栈帧信息
 *                                         3.打印该异常的Suppressed中的所有异常信息，循环打印调用每个元素的printEncloseStacktrace()
 *                                         4.打印该异常的cause，调用cause的printEncloseStacktrace()
 *          public void printStackTrace(PrintStream s):和printStackTrace()方法功能类似，上述方法默认有System.err输出，而这个方法指定了输出形式。
 *          public synchronized Throwable fillInStackTrace()：有jvm调用将堆栈信息赋值给stackTrace字段。并返回当前对象。
 *          public StackTraceElement[] getStackTrace()：获取stackTrace字段的值
 *          public void setStackTrace(StackTraceElement[] stackTrace):给stackTrace字段进行设置。
 *          public final synchronized void addSuppressed(Throwable exception)：将异常添加到suppressedExceptions中,通常在try-with-resource中调用这个方法。
 *          public final synchronized Throwable[] getSuppressed() ：获取suppressedExceptions。
 *
 *
 *  每一个异常对象都是堆中的一个普通对象而已。
 *
 * StackTraceElement:
 *  private String declaringClass;
 *  private String methodName;
 *  private String fileName;
 *  private int    lineNumber;
 *
 */
package exception;