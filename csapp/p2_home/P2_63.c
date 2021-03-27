#include <stdio.h>

/* 实现算术右移来完成逻辑右移  */
unsigned srl(unsigned x, int k){
        /* Perform shift arithmetically */
        unsigned xsra = (int) x >> k;
        // 实现算术右移，就是高位要根据符号位进行补位；且当前为无符号的算术右移
        // 当前问题要解决问题是：x强转为int后，可能符号为1的情况导致输出结果不符合预期
        // 只需要处理最高位为1的情况下，进行算术位移后，结果值就发生了变化，需要进行修正
        // 移动位数k，总位数 = sizeof（int）<< 3
        int int_bit_size = sizeof(int) << 3;
        // 为符号位被使用时的剩余位长度
        int lbit_size = int_bit_size - k;
        // 使用掩码，对除了剩余位的值 设置0
        int all_1 = -1;
        int mask = all_1 << lbit_size;
        return xsra & ~mask;
}
unsigned srl0(unsigned x, int k){
        /* Perform shift arithmetically */
        unsigned xsra = (int) x >> k;
       return xsra;
}

/* 函数sra用逻辑右移（已经由值xsr给出）来完成算术右移  */
int sra(int x, int k){
  /* Perform shift logically */
  int xsrl = (unsigned) x >> k;
  // 这道题目的难点是：结果是要逻辑右移的执行结果，但把int强转成了unsigned
  // ！！！和java不同，几乎所有的编译器/机器组、都对有符号数使用算术右移，另一方面，对于无符号数，右移必须是逻辑的,所以说，当int转为无符号时，就不会采用算术位移了。 就要解决，最高bit位为1时，算术右移要根据最高位来补偿的特性。

  int w = sizeof(int) << 3;
  // mask 为移动部分的掩码
  int mask = (int)-1 << (w-k);
  // 
  int m = 1 << (w-1);
 // ! 是not 是逻辑位运算，输出的只能是0x00或0x01；这里右边x是如果最高为1，就是0-1，=号右边就是0xFF~FF,如果x最高位为0，则为1-1=0 ，=号右边为0x00
  mask &= !(x & m) -1;
  // 最终处理就是，mask是否把右移补0的位改为1
  return xsrl | mask;
}


/*不使用右移或者除法 实现函数内部的功能  */
int main(int argc,const char * argv[]){

    	int s = -1;
    	unsigned x = (unsigned)s;

	printf("x = %u \n",x);
        int k = (sizeof(int) << 8) -1 ;
	unsigned y = srl(x,k);
    	printf("y=%u \n",y);
        unsigned y0 = srl0(x,k);
	printf("srl0=%u \n",y0);

        printf("使用算术右移来满足逻辑右移的效果\n");
	
        int result = sra(-1,16);
        printf("sra(-1,16)=%d \n",result);
        int result_check =  (unsigned)-1 >> 16;
        printf("(unsigned)-1 >> 16 = %d \n",result_check);
        int result_target = (int)-1 >> 16;
        printf("(int)-1 >> 16 = %d \n",result_target);
}


