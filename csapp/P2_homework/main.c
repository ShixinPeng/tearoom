//
//  main.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>

void test2_57(){
    // float 不能准确描述的正整数值 2^(n+1) + 1
        int int_float_post_int = (int)(pow(2,(23+1)));
        test_show_bytes(65537);
        test_show_bytes(1);
}
void test2_58(){
    // 测试大小端
    int r = is_little_endian();
    printf("r = %d \n",r);
}

int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, World!\n");
    
//    test2_57()
//    test2_58();
    
    return 0;
}

