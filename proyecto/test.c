#include <stdlib.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){
  int orden = 3;
  int matriz[3][3] = {
    {12,2,7},
    {8,32,11},
    {1,4,3}
  };
  int diagonalNegativa = 0;
  int diagonalPositiva = 0;
  int temporalNegativa;
  int temporalPositiva;
  int i,j;
  int determinante;
  int fila;

  for(i = 0; i < orden; i++){
    temporalNegativa = 1;
    temporalPositiva = 1;
    for(j = 0; j < orden; j++){
      fila = i+j;
      if(fila >= orden){
        fila -= orden;
      }
      temporalPositiva *= matriz[fila][j];
      //printf("Pos1: A[%d][%d]\n",(i+j)-(orden),j);
      temporalNegativa *= matriz[fila][(orden-1)-j];
      //printf("Neg2: A[%d][%d]\n",(i+j)-(orden),(orden-1)-j);
    }
    diagonalNegativa += temporalNegativa;
    diagonalPositiva += temporalPositiva;
  }

  determinante = diagonalPositiva - diagonalNegativa;
  printf("%d \n",determinante);

  return 0;
}
