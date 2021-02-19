//
//  main.c
//  P2_32
//
//  Created by shixinPeng on 2021/2/17.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>
#include <limits.h>


int tadd_ok(int x,int y){
    int sum = x+y;
    return (sum - x == y) && (sum - y == x);
}

int tsub_ok(int x,int y){
    return tadd_ok(x, -y);
}

int main(int argc, const char * argv[]) {

    
    int a = -2147483647 -1 ;
    int a2 = -2147483647;
    printf(" a = %d \n",a);
    int b = -a;
    
    int b2 = -a2;
    int c = b+a;
    
    printf("-a = %d \n",b);
    printf("-a2 = %d \n",b2);
    printf("sum a + -a = %d \n",c);
    printf("INT_MIN = %d \n",INT_MIN);
    
    int x = -2147483647;
    int y = 2147483647;
    int add = tadd_ok(x, y);
    printf("add = %d \n",add);
    int sub = tsub_ok(x, y);
    printf("sub = %d \n",sub);
    return 0;
}

