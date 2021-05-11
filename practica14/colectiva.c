#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>

int numeroDeRanks;
int orden = 10;

void llenarMatriz(int matriz[orden][orden]);
void imprimirMatriz(int matriz[orden][orden]);
void copiarRenglonesPorRank(int rank,int origen[orden][orden],int destino[orden][orden]);
void multiplicacionMatricesPorRank(int rank,int resultante[orden][orden],int A[orden][orden],int B[orden][orden]);

int main(int argc, char *argv[]){
  int A[orden][orden], B[orden][orden], C[orden][orden];
  int rank;
  int tag=1,i;
  int temporal[orden][orden];
  double start,end;
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  start = MPI_Wtime();
  llenarMatriz(A);
  llenarMatriz(B);
  /*Envio y recepcion de mensajes*/
  if(rank == 0){
    multiplicacionMatricesPorRank(rank,temporal,A,B);
    copiarRenglonesPorRank(rank,temporal,C);
    if(numeroDeRanks > 1) MPI_Send(C,orden*orden,MPI_INT,rank+1,tag,MPI_COMM_WORLD);
  }
  if(rank == numeroDeRanks - 1){
    if(numeroDeRanks > 1){
      MPI_Recv(C,orden*orden,MPI_INT,rank-1,tag,MPI_COMM_WORLD,&status);
      multiplicacionMatricesPorRank(rank,temporal,A,B);
      copiarRenglonesPorRank(rank,temporal,C);
    }
    end = MPI_Wtime();

    imprimirMatriz(A);
    printf("\n");
    imprimirMatriz(B);
    printf("\nMatrix multiplication done.\n\n");
    imprimirMatriz(C);
    printf("\n");
    printf("Tiempo: %lf rank: %d\n",end-start,rank);
    printf("\n");
  }
  if(rank != 0 && rank != numeroDeRanks - 1){
    MPI_Recv(C,orden*orden,MPI_INT,rank-1,tag,MPI_COMM_WORLD,&status);
    multiplicacionMatricesPorRank(rank,temporal,A,B);
    copiarRenglonesPorRank(rank,temporal,C);
    MPI_Send(C,orden*orden,MPI_INT,rank+1,tag,MPI_COMM_WORLD);
  }
  MPI_Finalize();
}





void llenarMatriz(int matriz[orden][orden]){
  for(int i = 0; i < orden; i++){
    for(int j = 0; j < orden; j++){
      matriz[i][j] = rand()%20;
    }
  }
}

void imprimirMatriz(int matriz[orden][orden]){
  printf("\n");
  for(int i = 0; i < orden; i++){
    printf("\t| ");
    for(int j = 0; j < orden; j++){
      printf("%5d|",matriz[i][j]);
    }
    printf("\n");
  }
}

void copiarRenglonesPorRank(int rank,int origen[orden][orden],int destino[orden][orden]){
  int from = (rank * orden)/numeroDeRanks;
  int to = ((rank+1) * orden)/numeroDeRanks;
  int i,j;
  for (i = from; i < to; i++)
    for (j = 0; j < orden; j++) {
      destino[i][j] = origen[i][j];
    }
}

void multiplicacionMatricesPorRank(int rank,int resultante[orden][orden],int A[orden][orden],int B[orden][orden]){
  int from = (rank * orden)/numeroDeRanks;
  int to = ((rank+1) * orden)/numeroDeRanks;
  int i,j,k;

  printf("computing rank %d (from row %d to %d)\n", rank, from, to-1);
  for (i = from; i < to; i++)
    for (j = 0; j < orden; j++) {
      resultante[i][j] = 0;
      for (k = 0; k < orden; k++)
        resultante[i][j] += A[i][k]*B[k][j];
    }
  printf("finished rank %d\n", rank);
}
