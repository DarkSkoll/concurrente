#include <time.h>

#ifndef manejo
#define manejo

void llenarMatriz(int orden, int matriz[orden][orden]){
  srand(time(NULL));
  for(int i = 0; i < orden; i++){
    for(int j = 0; j < orden; j++){
      matriz[i][j] = rand()%99;
    }
  }
}

void imprimirMatriz(int orden, int matriz[orden][orden]){
  for(int i = 0; i < orden; i++){
    printf("|");
    for(int j = 0; j < orden; j++){
      printf("%2d|",matriz[i][j]);
    }
    printf("\n");
  }
}

#endif
