package jit;/**
 * Created by DELL on 2018/10/16.
 * jit对循环的优化：
 *  1.循环无关代码外提：循环无关代码是指循环中值不变的表达式，因为这些表达式的值是不变的，只需要计算一次就可以了，无需放在循环体中多次计算，
 *  将这些循环无关的代码进行外提，从而达到性能提升的效果。
 *  2.循环展开：因为循环中会涉及到跳转，判断等，会打断cpu的指令流水，影响cup的并行度，展开循环就是为了减少循环的次数，全展开是循环展开的一种特殊情况，
 *  就是将循环结构变成顺序结构，是一种空间换时间的方式，同时由于，循环展开增加了循环体中的内容，增加了jit的profiling，有利于触发更多的优化。
 *
 *  3.判断外提：将循环体存在判断的结构变成判断中存在循环的结构，主要原因是判断中存在跳转，同样会打断cpu流水，那么循环中存在判断会使打断cpu流水的次数成倍增加，
 *  在不改变程序逻辑的前提下，将判断外提到循环外面。
 *
 *  4.循环剥离：主要就是为了让循环体中的逻辑更具有规律性，将循环头和循环尾部的特殊处理放到循环外面。
 *
 *  以上是jit对循环的优化，可见jit经过程序状态信息的收集，做出的优化还是很智能的。
 *  由此可见，我们写代码是，在满足可读性的基础上，也要尽可能想着编译器优化的方向努力，尽量减少jit优化的发生，要直到jit优化还是很耗时的。
 *  不要舍本逐末，以为的追求高效率。
 *
 *  通常做优化的宗旨就是：
 *      让做的快的做，不让做的慢的做。
 *      让做的快的做多，做的慢的少做。
 *      取巧，在目的能到达的基础上，减少要做的事情。
 *
 *
 *
 *
 */

/**
 * user is lwb
 **/


public class XunHuan {

    private int count = 10;
    public int getCount(){
        System.out.println("ddddd");
        return count;
    }

    public static void main(String[] arg){
        XunHuan xunHuan = new XunHuan();
        //
        for(int i=0;i<xunHuan.getCount();i++){
            System.out.println(i);
        }


        Integer[] ints = new Integer[10];//会开辟10个Integer的空间
        System.out.println(ints[0]);
        System.out.println(ints[1]);
        System.out.println(ints[2]);
    }




}
