//  P2_61.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>
/*
 不能使用常规操作了！！
 ；（
 只能用：
 位运算和逻辑运算
 左移和右移
 加法和减法
 整型常数
 强制类型转换
 
 */

// C表达式 以下情况返回1,其他情况等于0
// A. x的任何为都等于1
// B. x的任何位都等于0
// C. x的最低有效位字节中都等于1
// D. x的最高有效字节中位都等于0

// 思路：如果符合确认条件，判断值为0，否则判断为1。提示：只有逻辑运算，才能只输出1或者0
int check_x(int x){
    printf("x:0x%08X x=%d\n",x,x);
    
    
    //int x = 0xFFFFFFFF = -1  ; -1 + 1 = 0
    int check_a = 0x00 || x + 1;
    int check_b = 0x00 || x;
    int check_c = 0x00 || ((x & 0xff) - 0xff);
    int check_d = 0x00 ||  x >> 24;// 问题，没有适配不同字的计算机
    printf("check_a=%d check_b=%d check_c=%d check_d=%d\n",check_a,check_b,check_c,check_d);
    return 0x0 || !check_a || !check_b || !check_c || !check_d ;
}



// A. x的任何为都等于1
int check_a(int x){
    
    return !~x;
}
// B. x的任何位都等于0
int check_b(int x){
    
    return !x;
}
// C. x的最低有效位字节中都等于1
int check_c(int x){
    // 除最低有效字节外，都为1，所有如果最低有效字节都等于1，则!~ 为1
    return !~(x | ~0xFF);
}
// D. x的最高有效字节中位都等于0
int check_d(int x){
    int other_size = sizeof(int) - 1;
    int other_size_bits = other_size << 8;
    int highest_bits = x >> other_size_bits;
    return  !(highest_bits & 0xFF);
    
}

