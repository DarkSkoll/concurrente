#include <math.h>
#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main(int argc,char*argv[]){
  int n,rank,size;
  //double PI25DT = 3.141592653589793238462643;
  double totalCorrecto = 0.25;
  double mypi,pi,h,sum;
  n = atoi(argv[1]);
  if(n <= 0) return 1;
  MPI_Init(&argc,&argv);
  MPI_Comm_size(MPI_COMM_WORLD,&size);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  printf("Rank = %d ",rank);
  printf("Total = %d ",size);
  MPI_Bcast(&n,1,MPI_INT,0,MPI_COMM_WORLD);
  h = 1.0/(double)n;
  sum = 0.0;
  int i = rank+1;
  double x;
  while(i <= n){
    x = h*((double)i-0.5);
    sum += pow(x,3);
    i += size;
  }
  mypi = h*sum;
  printf("rank %d h = %f\n",rank,mypi);
  MPI_Reduce(&mypi,&pi,1,MPI_DOUBLE,MPI_SUM,0,MPI_COMM_WORLD);
  if(rank == 0){
    printf("El valor aproximado de la integral de x^3 de 0 a 1 es: %f", pi);
    printf(", con un error de %f\n",pi-totalCorrecto);
  }
  MPI_Finalize();
}
