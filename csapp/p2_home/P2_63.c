#include <stdio.h>

/* 实现算术右移的结果  */
unsigned srl(unsigned x, int k){
        /* Perform shift arithmetically */
        unsigned xsra = (int) x >> k;
        // 实现算术右移，就是高位要根据符号位进行补位；且当前为无符号的算术右移
        // 当前问题要解决问题是：x强转为int后，可能符号为1的情况导致输出结果不符合预期
        // 只需要处理最高位为1的情况下，发生算术唯一后的修正
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

/* 实现逻辑右移的结果  */
int sra(int x, int k){
  /* Perform shift logically */
  int xsrl = (unsigned) x >> k;
 return 0;
}


/*不使用右移或者除法 实现函数内部的功能  */
int main(int argc,const char * argv[]){

    	int s = -1;
    	unsigned x = (unsigned)s;

	printf("x = %u",x);
        int k = (sizeof(int) << 8) -1 ;
	unsigned y = srl(x,k);
    	printf("y=%u",y);
        unsigned y0 = srl0(x,k);
	printf("srl0=%u",y0);

}


