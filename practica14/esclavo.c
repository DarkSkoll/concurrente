#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/time.h>

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
  struct timeval inicio, fin;
  float runtime, mips;
  int temporal[orden][orden];
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  llenarMatriz(A);
  llenarMatriz(B);
  /*Envio y recepcion de mensajes*/
  if(rank==0){

    gettimeofday( &inicio, (struct timezone *)0 );
    multiplicacionMatricesPorRank(0,C,A,B);
    for(i=1; i<numeroDeRanks;i++){
      MPI_Recv(temporal, orden*orden, MPI_INT, i, tag, MPI_COMM_WORLD, &status);
      copiarRenglonesPorRank(i,temporal,C);
    }
  }
  else{
    multiplicacionMatricesPorRank(rank,temporal,A,B);
    MPI_Send(temporal, orden*orden, MPI_INT, 0, tag, MPI_COMM_WORLD);
  }

  if(rank==0){
    /*Muestro resultados y el tiempo de ejecución*/
    gettimeofday( &fin, (struct timezone *)0 );

    imprimirMatriz(A);
    printf("\n");
    imprimirMatriz(B);
    printf("\n\nMatrix multiplication done.\n\n");
    imprimirMatriz(C);
    printf("\n");


    runtime = (float )(fin.tv_sec - inicio.tv_sec)*1000000 +
      (float )(fin.tv_usec - inicio.tv_usec);
    runtime /= 1000000.0;
    mips = (float )orden*(float )orden*(float )orden;
    mips = (float )orden*(float )orden*(float )orden;
    mips /= runtime;
    mips /= 1000000.0;
    printf("Execution time: %f secs. %f MIPS\n", runtime, mips );
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
