//  P2_62.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>
#include <assert.h>
/*
 如果int使用算术右移 返回1
 其他返回0
 */
int int_shifts_arithmetic(){
    int num = -1;
    return !(num ^ (num >> 1));
}

int main(int argc,char* argv[] ){
   int a = int_shifts_arithmetic();
   printf("%d",a);
   assert(int_shifts_arithmetic());
   return 0;
}
