/**
 * Created by DELL on 2018/10/15.
 *
 *  即时编译是一项用来提升运行效率的技术，通常来说字节码会被jvm解释执行，也就是解释一句(将字节码解释成机器码)，执行一句，
 *  但是如果如果某些字节码，如果反复被执行，如循环体中的代码，被多次重复调用的方法体。此时就有必要，将这些重复执行的字节码
 *  直接一次性全部编译成机器码，去执行。减少了解释执行中解释的准备工作。即时编译的对象主要针对方法和循环体。
 *
 *  如何触发即时编译，主要就是根据方法调用次数和循环回边的次数，通常编译器，会在方法调用结束和循环回边的位置插入方法调用计数器和循环回边计数器，
 *  用来统计方法调用的次数和循环回边的次数，然后与相应的阈值进行比较来判断是否开始触发即时编译。
 *
 *  选择何种执行方式，是解释执行还是即时编译，以及是c1还是c2,要依据程序的执行状态，其中最为基础的便是方法的调用次数和循环回边执行次数。
 *  profiling是指在程序执行过程中，收集能够反应程序运行状态数据，这里所收集的数据称为程序的profile。
 *  程序状态数据的收集是一项额外工作，这些数据的收集工作会耗费一定的性能。
 *  即时编译，就是在在程序执行过程中不断的收集程序的状态数据，然后，根据这些状态数据，动态的决策出更加合适当前场景的编译方式。
 *  有点像神经网络，程序执行就是前向传播，在执行的过程，收集程序的执行状态，就像损失函数，而反向传播修改权值的过程，就是使用收集的状态数据做一定的决策，也就是改变编译的方式。
 *  profile更像是埋点。
 *  当获取到了足够的profile后，那么怎么具体优化呢？
 *      基于分支profile优化：在选择结构中，会统计一个选择结构的分支跳转(判断不成立的情况下，会跳转)次数，在收集了大量的profile后，
 *      如果发现大部分的情况下都没有跳转的话，那么就假设以后都不会跳转，那么触发jit编译器，就会生成这个方法的机器码，且机器码中不包含跳转的那部分逻辑。
 *      这样的操作称为剪枝。
 *      基于类型的profile优化：如果一个选择结构的判断条件是基于类型做判断的(A instanceof B)，在收集大量的状态信息后，发现大部分情况下，A都是Integer类型，
 *      那么我们就可以将判断条件转换成(A.getClass == Integer.class)很显然这种判断比(A instanceof B)的效率高很多。
 *      由此可见优化的前提，是先做假设，优化是按照假设成立的情况下进行的。
 *   那么当假设不成立怎么处理呢？
 *
 *
 *  解释执行和即时编译这两种方式那个好？两种方式各有各自的使用场景，对于非热点代码，解释执行好，对于热点代码即时编译好。
 *  即时编译中将字节码编译成机器码时很耗时的，如果编译完成之后，这段机器码使用的次数很少的话，很显然时不值得的。
 *  就是不是每个农民都买一台收割机进行收割一样。
 *  即时编译器编译的机器码执行越快，编译的过程就越耗时。针对不同的情况，对编译器进行了分层，分为c1和c2,分层的主要原因就是起到了折中的作用，
 *  时不那么热的代码能够在短时间内编译完成，很热的代码，情愿耗费大量时间编译出执行效率更高效的机器码。
 *  分层让jit更具有灵性，或者说更智能。
 *
 *
 *  目前的编译重要采用两段式是编译，前端和后端，前端：将源码经过语法词法以及语义分析，生成中间表达式。后端：对中间表达式进行优化，生成最终的目标代码。
 *  在java中在不考虑解释执行的话，前端编译，就是前端编译器要做的事情，将java代码编译成java字节码，jit编译就是后端编译器，将java字节码编译成机器码。
 *  因为存在两端的编译，在不改变程序业务逻辑的基础上，会使，我们写的java代码和真正执行的机器码之间会存在很大的差异，比如执行顺序，多余代码去除等。
 *  编译器的工作原理有点像代理模式一样，在原本的代码上增加了很多的额外的可以优化程序执行的代码。
 *  在前端的编译器中，常见的优化方式就是语法糖的使用，可以使我们在写程序时用一些很花哨的写法，前端编译器中的解语法糖步骤，会将这些花哨的语法，转化成普通的字节码。
 *  后端编译器中，在java字节码中添加一些埋点，收集程序执行过程中状态信息，对字节码进行重新优化，然后将运行的程序切换到优化后的机器码上。
 *
 *
 *  逃逸分析：判断一个对象是否会逃逸。对一个对象是否逃逸进行分析，也是一个额外工作，也就是jit编译器在java字节码中添加一段额外逻辑代码，就是收集用来判断
 *  对象是否存在逃逸的程序状态信息。
 *  jit是如何进行逃逸分析的呢，也就是jit在字节码中增加的逃逸分析的逻辑是什么呢？
 *    1.判断一个对象是否被存入堆中，也就是是否被赋值给静态字段或者是否被赋值给堆中对象的实例字段。
 *    2.对象是否作为调用者调用了一个方法，或者对象是否作为参数传入一个一个方法中，因为对于没有进行jit优化的方法来说，
 *    jit根本不知道这个方法内部的具体逻辑，也就是说jit不知道这个方法对调用者或者参数做了什么操作，是否做了1中指定的那些操作。
 *    所以针对1和2中是的情况，那么就任务这个对象逃逸了，逃到方法作用域的外面去了。
 *
 *  jit可以根据逃逸分析的结果，进行诸如锁消除、栈上分配以及标量替换的优化。
 *
 *
 *
 *  jit编译形式/招式：
 *      1.方法内联：在编译过程中遇到方法调用时，将目标方法的方法体纳入编译范围之中，并取代原方法调用的优化手段。
 *      2.基于profile进行优化。
 *      3.intrinsic：一些执行逻辑可能需要多条指令完成，由于处理器提供了一些高效的指令序列支持，将原本的逻辑实现替换成高效的处理器指令的实现，
 *      这个主要依靠处理器的支持，实现的过程通常情况下，后端编译中完成切换。
 *      4.锁消除：如果证明锁对象不会逃逸(不会逃逸出这个方法)，那么即时编译后的机器码中就会去掉关于这个对象锁的机器码。其实锁消除的条件可以更宽松一点，只要不从线程中逃逸，就可以将锁的逻辑消除，但是即时编译中的逃逸分析，是针对方法的。
 *      5.栈上分配：在我们的理解中，new字节码指令会在堆上为对象分配空间，但是分配空间的操作，并不是new字节码指令完成的，而是new指令对应的机器码完成的，
 *          如果jit将new指令编译成的机器码，是在栈上分配的化，那么new字节码指令就是在栈上分配的内存了。new指令只是一个IR(中间表达式)，真正做什么操作，还要取决于被最终编译成机器码。
 *          但是为了达到new字节码指令的语义一致性，统称不会这么做。那不然new指令就会有很多语义了。
 *          栈上分配是一种优化的思想，增加了对象管理的便利性，当new所在的方法退出了，new创建的对象所占的内存也就回收了，而不是用GC来回收占用的堆内存，
 *          但是Hotspot并没有那么做，如果那么做就改变了new语义的一致性，为了达到栈上分配的效果，采用了另外一种实现方式：标量替换。
 *      6.标量替换：标量是指一个数应景无法再分解成更小的数据来表示了，也就是jvm中的额原始数据类型，聚合量：就像java中的对象。标量替换，指的是将原本连续分配的对象拆散为一个个单独的字段，分布在栈上或者寄存器中。
 *                  从而达到了栈上分配的效果。
 *                  部分逃逸分析是一种附带了控制流信息的逃逸分析，通常存在于选择结构中，如果程序执行第一个分支的话，不存在逃逸，如果执行第二个分支的话，就存在逃逸。
 *                  它将判断新建对象真正逃逸的分支，并且支持将新建操作推延至逃逸分支
 *
 *
 *
 *  向量化计算：在数学中，利用向量可以同时计算多个值，有批量计算的效果，在jit优化中，也利用这种批量计算的方式，
 *  具体实现就是使用特殊的指令，可以同时操作很多位的指令来替换每次只能计算一位的指令，如可以用计算int的指令来代替执行四次计算byte
 *  的指令。指令的实现，需要相应的寄存器的依赖。
 *  其实intrinsic方法的实现，一部分也是使用了采用这种思想的指令作为高效实现。
 *
 *  因为jit的优化,需要经过多次运行进行收集profile,然后才能做出正确的优化编译,所以在进行性能测试时,要进行预热,目的就是让jit在预热的过程中,完成profile的收集,因为profiling的也是很耗时的,为了减少这部分性能消耗的影响
 *  在真正测试时,程序已经出于jit优化过的状态,和真实运行环境时相同的,否则如果没有进行预热的话,测试出来的性能要低于正常性能.
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
package jit;