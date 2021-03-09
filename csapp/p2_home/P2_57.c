//
//  P2_57.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>

typedef unsigned char *byte_pointer;

void show_bytes(byte_pointer start,size_t len){
    size_t i;
    for (i = 0; i < len; i++) {
        printf(" %.2x",start[i]);
    }
    printf("\n");
}

void show_short(short x){
    show_bytes((byte_pointer)&x, sizeof(short));
}
void show_int(int x){
    show_bytes((byte_pointer)&x, sizeof(int));
}
void show_long(long x){
    show_bytes((byte_pointer)&x, sizeof(long));
}
void show_float(float x){
    show_bytes((byte_pointer)&x, sizeof(float));
}
void show_double(double x){
    show_bytes((byte_pointer)&x, sizeof(double));
}
void show_pointer(void *x){
    show_bytes((byte_pointer)&x, sizeof(void *));
}

void test_show_bytes(int val) {
    int ival = val;
    float fval = (float) ival;
    int * pval = &ival;
    long lval = (long)ival;
    short sval = (short)ival;
    double dval = (double)ival;
    
    printf("show_short: \n");
    show_short(sval);
    printf("show_int: \n");
    show_int(ival);
    printf("show_long: \n");
    show_long(lval);
    printf("show_float: \n");
    show_float(fval);
    printf("show_double: \n");
    show_double(dval);
    printf("show_pointer: \n");
    show_pointer(pval);
    
}
