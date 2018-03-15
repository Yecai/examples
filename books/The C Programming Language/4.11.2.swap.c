#include <stdio.h>

#define swap(t,x,y) {t temp;temp=x;x=y;y=temp;}

int
main()
{
  int x,y;
  x=1;
  y=2;
  
  printf("before swap:%d %d\n", x, y);
  swap(int, x, y);
  printf("after swap:%d %d\n", x, y);
}
