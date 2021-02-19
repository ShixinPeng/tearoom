//
//  main.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>

int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, World!\n");
    // float 不能准确描述的正整数值 2^(n+1) + 1
    int int_float_post_int = (int)(pow(2,(23+1)));
//    test_show_bytes(65537);
    test_show_bytes(1);
    return 0;
}
