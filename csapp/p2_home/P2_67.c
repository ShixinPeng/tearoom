#include <stdio.h>
#include <assert.h>

/*warning: shift count >= width of type [-Wshift-count-overflow]*/
/*
int int_size_is_32_bad(){

 int set_msb = 1 << 31;
 int beyond_msb = 1 << 32;
 return set_msb && !beyond_msb;
}
*/
int int_size_is_32_good(){
    int set_msb = 1 << 31;
    int beyond_msb = set_msb << 1;

    return set_msb && !beyond_msb;
}

int int_size_is_32_for_16bit() {

    int set_msb = 1 << 15 << 15 << 1;
    int beyond_msb = set_msb << 1;
    return set_msb && !beyond_msb;
}


int main(int argc,const char* argv[])
{

//   assert(int_size_is_32_bad());
   assert(int_size_is_32_good());
   assert(int_size_is_32_for_16bit());

    return 0;
}
