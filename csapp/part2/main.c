//
//  main.c
//  part2
//
//  Created by shixinPeng on 2021/2/17.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>

size_t strlen(const char *s);
int strlonger(char *s ,char *t){
     strlen(s);
    strlen(t);
    return 0;
}

// 关于浮点数准确表述正整数的范围
void test2_49(int fval,long dval){
    // 单精度的正整数表述
    float f = (float)fval;
    printf("正整数值=%d,单精度值=%f \n",fval,f);
    
    // 双精度的正整数表述
    double d = (double)dval;
    printf("正整数值=%ld,双精度值=%f \n",dval,d);
    
}

int main(int argc, const char * argv[]) {

    // 测试2.30 相加溢出判断
//    test2_30();
//    test2_44();
    
    test2_49( 2,pow(2,(23+1)) + 5);
    return 0;
}

// 2.30
// 如果参数x和y相加不会产生溢出，这个函数就返回1
int tadd_ok(int x,int y){
    int sum = x = y;

    return 1;
}

void test2_30(){
    
}
// 2.44
// 无符号后的结果判断
int test2_44(){
    int x = -1;
    int y = -1;
    
    unsigned ux = x;
    unsigned uy = y;
    int z = x+y;
    unsigned uz = ux + uy;
    if ( x+y == ux+uy) {
        printf("equal!\n");
    }
    
    printf(" z = %d ,uz = %u   \n",z,uz);
    
    return 1;
}

