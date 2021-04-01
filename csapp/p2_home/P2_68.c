#include <stdio.h>
#include <assert.h>



// 2.68 Mask with least signficant n bits set to 1
// Example n = 6 --> 0x3F ，n = 17 --> 0x1FFFF

// Assum 1 <= n <=w

// 题没咋看懂，但从样例上弄明白了，根据n的值，从低位开始n个bit都设置为1
// n = 6 就是最低位的byte为  0011 1111
int lower_one_mask(int n) {

   int w = sizeof(int) << 3;
   
   int offset =  w - n;

   return (unsigned)-1 >> offset;
}

int main(int argc,const char* argv[]) {

    assert(lower_one_mask(6) == 0x3F);
    assert(lower_one_mask(17) == 0x1FFFF);
    assert(lower_one_mask(32) == 0xFFFFFFFF);
    assert(lower_one_mask(1)==0x1);
 
    return 0;
}
