#include <stdio.h>
#include <assert.h>

// 将四个有符号字节封装成一个32位unsinged，一个字中的字节从0编号到3，为一个使用补码运算的机器编写函数

typedef unsigned packet_t;

// Extract byte form word,Return as signed integer
// 提取其中的一个byte位，用有符号的int表示

// 错误的实现
int xbyte_old(packet_t word,int bytenum){

   return (word >> (bytenum << 3)) & 0xFF;
}

int xbyte_new(packet_t word,int bytenum){
 
   // 要确认获取的byte是否为负值
  // 已知unsigned为32位
  int max_offset = 3;
  
  int shift_left_val = (max_offset - bytenum) << 3;
  int shift_right_val = max_offset << 3;

  return (int) word << shift_left_val >> shift_right_val;
}

int main (int argc, const char* argv[]) {

    assert(xbyte_old(0x00112233,2) == 0x11);
    // 老的方法无法处理x为负值的情况
    assert(xbyte_old(0xAABBCCDD,1) != 0xFFFFFFCC);     

    assert(xbyte_new(0x00112233,2) == 0x11);
    assert(xbyte_new(0xAABBCCDD,1) == 0xFFFFFFCC);

    return 0;
}
