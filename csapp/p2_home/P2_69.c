#include <stdio.h>
#include <assert.h>

/*
Do rotating left shift .Assume 0 <= n <= w
Examples when x =0x12345678 and w =32;
 n=4 -> 0x23456781 , n=20 -> 0x67812345

循环左移

*/
unsigned rotate_left(unsigned x, int n){

    // 找到移动部分，利用掩码获取，在左移，在补充原来的掩码获取值
    int w = sizeof(int) << 3 ;
    int offset = w - n;
    printf("offset=%d\n",offset);
   
    int left_mask = -1 << offset ;
   
    unsigned rotate_left = (unsigned)(left_mask & x) >> offset;
    printf("rotate_left=%x \n",rotate_left);
    return  (x << n ) | rotate_left;
}


int main(int argc,const char* argv[]) {

   assert(rotate_left((unsigned)0x12345678,4) == 0x23456781);
   assert(rotate_left((unsigned)0x12345678,20) == 0x67812345 );
   assert(rotate_left((unsigned)0x12345678,0) == 0x12345678 );
   return 0;
}
