#include <stdio.h>
#include <assert.h>

int leftmost_one(unsigned x) {

  x |= x >> 1;
  x |= x >> 2;
  x |= x >> 4;
  x |= x >> 8;
  x |= x >> 16;

  return (x >> 1) + (x && 1);
}

int main(int argc,const char * argv[]) {
  assert(leftmost_one(0xFFFF) == 0x8000);
  assert(leftmost_one(0x6000) == 0x4000);
  assert(leftmost_one(0x0) == 0x0);
  assert(leftmost_one(0x8000) == 0x8000);
  return 0;

}
