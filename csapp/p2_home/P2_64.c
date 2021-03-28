#include <stdio.h>
#include <assert.h>
// x任意奇数位为1时，any_odd_one(x) 返回1，否则返回0；int的有32位
 
int any_odd_one(unsigned x){
   int mask = 0xAAAAAAAA;
   return !!(mask & x);
}

int main(int agrc,const char * argv[]){
   
   // …… 0010
   assert(any_odd_one(0x2));
   // …… 0100
   assert(!any_odd_one(0x4));

   int result = any_odd_one(0x3);
   
   printf(" any_odd(0x3)=%d \n",result);
   assert(any_odd_one(0x3));
   return 0;

}
