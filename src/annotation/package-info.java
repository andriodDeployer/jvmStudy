/**
 * Created by DELL on 2018/10/17.
 *
 * 注解是java5引入的，用来为类，方法，字段，参数等java结构提供额外信息的机制，
 * 注解就像是给java对应的结构上打上了标签一样，而对这些注解的解析，根据解析的结果做一定的处理，
 * 所做的处理，可以理解为对注解语义的诠释。
 *
 * 注解的作用范围：RetentionPolicy决定，
 *      RetentionPolicy.SOURCE：表示注解的信息只存在于源码阶段，在编译出来的字节码上没有注解的的信息，这类的注解只能为前端编译器(源码->字节码)所利用，
 *      通过编写"注解处理器"完成在前端编译阶段对注解的解析和处理。实现了人为干预前端编译器的效果，是前段编译器更加的灵活弹性。
 *      RetentionPolicy.CLASS：表示主机的信息只存在于源码阶段和字节码阶段，在真正运行的机器码上，没有注解的信息，这类注解可以使用在前端编译器和后端编译器(字节码->机器码)
 *      主要为jit编译提供一定的信息.
 *      RetentionPolicy.RUNTIME：表示运行时可用，就是在机器码中会获取注解的信息，主要方便应用程序使用，如Sping中提供的一系列主机。
 *
 *    解析注解并对解析的结果进行一定的处理，来对注解的语义进行诠释，
 *    通常，在编译阶段起作用的的注解处理逻辑称为注解处理器，可以在前端编译器进行一定的任务干预：如规范约束，生成源码代码(确切的说，注解处理器并不能真正的修改已有源代码，这里说的修改源代码是修改源代码生成抽象语法树，
 *    主要就是修改语法树节点或者增加语法树节点，是语法树发生变化，然后编译器会发现语法树发生了变化，对变化的语法书，也就是新增的代码进行重新编译，产生新的字节码)。java中提供了修改抽象语法树的api.可以实现像cglib之类字节码操作工具实现的功能，
 *    只不过这个是在编译器完成。基准测试框架JMH中的注解的解析和处理就是利用注解处理器来完成的。
 *    编译源码时，将注解处理器绑定上去即可。
 *
 *
 *
 *
 *
 *
 *

 *
 *
 *
 *
 *
 */
package annotation;