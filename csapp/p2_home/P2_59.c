//
//  P2_59.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//


#include <stdio.h>

/**
 *  x 中的最低有效字节 与y中剩余自己组合
 */
int group_mask(int x,int y){
    int z = 0;
    // x 的掩码
    int x_mask = 0xFF;
    // y 的掩码
    int y_mask = ~0xFF;
    return (x&x_mask) | (y&y_mask);
}
