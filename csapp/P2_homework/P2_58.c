//
//  P2_58.c
//  P2_homework
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//

#include <stdio.h>
// 编写is_little_endian 小端时返回1，大端时返回0；兼容任何字长
typedef unsigned char *byte_pointer;

int is_little_endian() {
    int x = 0x01;
    int y = ((byte_pointer)&x)[0];
    return x==y;
}
