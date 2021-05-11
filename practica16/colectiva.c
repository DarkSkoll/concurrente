#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>

int numeroDeRanks;
int orden = 10;

void llenarVector(int vector[orden]);
void imprimirVector(int vector[orden]);
void copiarRenglonesPorRank(int rank,int origen[orden],int destino[orden]);
void sumaVectorPorRank(int rank,int resultante[orden],int A[orden],int B[orden]);

int main(int argc, char *argv[]){
  int A[orden],B[orden],C[orden];
  int rank;
  int tag=1;
  int temporal[orden];
  double start,end;
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  start = MPI_Wtime();
  llenarVector(A);
  llenarVector(B);
  /*Envio y recepcion de mensajes*/
  if(rank == 0){
    sumaVectorPorRank(rank,temporal,A,B);
    copiarRenglonesPorRank(rank,temporal,C);
    if(numeroDeRanks > 1) MPI_Send(C,orden,MPI_INT,rank+1,tag,MPI_COMM_WORLD);
  }
  if(rank == numeroDeRanks - 1){
    if(numeroDeRanks > 1){
      MPI_Recv(C,orden,MPI_INT,rank-1,tag,MPI_COMM_WORLD,&status);
      sumaVectorPorRank(rank,temporal,A,B);
      copiarRenglonesPorRank(rank,temporal,C);
    }
    end = MPI_Wtime();

    imprimirVector(A);
    printf("\n");
    imprimirVector(B);
    printf("\nSuma de vectores terminada. rank: %d\n\n",rank);
    imprimirVector(C);
    printf("\n");
    printf("Tiempo: %lf rank: %d\n",end-start,rank);
    printf("\n");
  }
  if(rank != 0 && rank != numeroDeRanks - 1){
    MPI_Recv(C,orden,MPI_INT,rank-1,tag,MPI_COMM_WORLD,&status);
    sumaVectorPorRank(rank,temporal,A,B);
    copiarRenglonesPorRank(rank,temporal,C);
    MPI_Send(C,orden,MPI_INT,rank+1,tag,MPI_COMM_WORLD);
  }
  MPI_Finalize();
}





void llenarVector(int vector[orden]){
  for(int j = 0; j < orden; j++){
    vector[j] = rand()%20;
  }
}

void imprimirVector(int vector[orden]){
  for(int i = 0; i < orden; i++){
    printf("\t|%5d|\n",vector[i]);
  }
}

void copiarRenglonesPorRank(int rank,int origen[orden],int destino[orden]){
  int from = (rank * orden)/numeroDeRanks;
  int to = ((rank+1) * orden)/numeroDeRanks;
  int j;
  for (j = from; j < to; j++) {
    destino[j] = origen[j];
  }
}

void sumaVectorPorRank(int rank,int resultante[orden],int A[orden],int B[orden]){
  int from = (rank * orden)/numeroDeRanks;
  int to = ((rank+1) * orden)/numeroDeRanks;
  int i;

  printf("computing rank %d (from row %d to %d)\n", rank, from, to-1);
  for (i = from; i < to; i++){
      resultante[i] = A[i]+B[i];
  }
  printf("finished rank %d\n", rank);
}
