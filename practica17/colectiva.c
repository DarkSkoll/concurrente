#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>

int numeroDeRanks;
int orden = 10;

void llenarVector(int vector[orden]);
void imprimirVector(int vector[orden]);
void operacionesVectorPorRank(int rank,int results[3],int vector[orden]);

int main(int argc, char *argv[]){
  int A[orden];
  int rank;
  int tag=1;
  int resultados[3] = {0,0,0};
  double start,end;
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  start = MPI_Wtime();
  llenarVector(A);
  /*Envio y recepcion de mensajes*/
  if(rank == 0){
    resultados[1] = A[0];
    operacionesVectorPorRank(rank,resultados,A);
    if(numeroDeRanks > 1) MPI_Send(resultados,3,MPI_INT,rank+1,tag,MPI_COMM_WORLD);
  }
  if(rank == numeroDeRanks - 1){
    if(numeroDeRanks > 1){
      MPI_Recv(resultados,3,MPI_INT,rank-1,tag,MPI_COMM_WORLD,&status);
      operacionesVectorPorRank(rank,resultados,A);
    }
    end = MPI_Wtime();

    imprimirVector(A);
    double tmp = resultados[0]/orden;
    printf("Operaciones sobre el vector terminadas. rank: %d\n",rank);
    printf("Suma: %d rank: %d\n",resultados[0],rank);
    printf("Minimo: %d rank: %d\n",resultados[1],rank);
    printf("Maximo: %d rank: %d\n",resultados[2],rank);
    printf("promedio: %lf rank: %d\n",tmp,rank);
    printf("Tiempo: %lf rank: %d\n",end-start,rank);
  }
  if(rank != 0 && rank != numeroDeRanks - 1){
    MPI_Recv(resultados,3,MPI_INT,rank-1,tag,MPI_COMM_WORLD,&status);
    operacionesVectorPorRank(rank,resultados,A);
    MPI_Send(resultados,3,MPI_INT,rank+1,tag,MPI_COMM_WORLD);
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

void operacionesVectorPorRank(int rank,int results[3],int vector[orden]){
  int from = (rank * orden)/numeroDeRanks;
  int to = ((rank+1) * orden)/numeroDeRanks;
  int i;

  printf("computing rank %d (from row %d to %d)\n", rank, from, to-1);
  for (i = from; i < to; i++){
    results[0] += vector[i];
    if(vector[i] < results[1]) results[1] = vector[i];
    if(vector[i] > results[2]) results[2] = vector[i];
  }
  printf("finished rank %d\n", rank);
}
