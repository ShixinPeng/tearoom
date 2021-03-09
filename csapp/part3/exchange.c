#inculde <stdio.h>

int exchange(long *xp,long y){
  long x = *xp;
  *xp = y;
  return x;
} 

