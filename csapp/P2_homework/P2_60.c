//
//  P2_60.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>

// 使用字节b替换x中的第i字节
unsigned replace_byte(unsigned x, int i,unsigned char b){
    // i 为必须是在有效范围内
    if (i < 0 || i > 3) {
        return NULL;
    }
    unsigned x_mask = 0xFF << (i<<3);
    printf("x_mask = 0x%08X\n",x_mask);
    unsigned y = (unsigned)(b << (i<<3));
    printf("y      = 0x%08X\n",y);
    return (x&~x_mask)|y;
}
