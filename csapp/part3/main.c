//
//  main.c
//  part3
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>
void multstore(long,long,long *);

int main(int argc, const char * argv[]) {
    // insert code here...
    long d;
    multstore(2, 3, &d);
    printf("2 * 3 --> %ld \n",d);
    return 0;
}
long mult2(long a,long b){
    long s = a+b;
    return s;
}
