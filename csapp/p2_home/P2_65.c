#include <stdio.h>
#include <assert.h>


// 2.65 如果32位的x 各bit中1的数量加起来为奇数，就返回1，否则返回0
// 思路，使用对折，消消乐的形式处理
int odd_ones_v1(unsigned x){

 // 第一次对折
 x = (x & 0x0000FFFF) ^ (x >> 16);
 // 第二次对折 长度只有8位有效位
 x = (x & 0x000000FF) ^ (x >> 8);
 x = (x & 0x0000000F) ^ (x >> 4);
 x = (x & 0x00000003) ^ (x >> 2);
 x = (x & 0x00000001) ^ (x >> 1);

  return x;
}

int odd_ones_v2(unsigned x){
  
  x ^= x >> 16;
  x ^= x >> 8;
  x ^= x >> 4;
  x ^= x >> 2;
  x ^= x >> 1;
  x &= 1;
  return x;
}





int main (int argc,const char * argv[]){
 
  assert(odd_ones_v1(0x10101011));
  assert(!odd_ones_v1(0x11111111));
  assert(odd_ones_v2(0x10101011));
  assert(!odd_ones_v2(0x11111111));

  return 0;
}
