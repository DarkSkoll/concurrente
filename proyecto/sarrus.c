#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/time.h>

int numeroDeRanks;
int orden;

void llenarMatriz(int matriz[orden][orden]);
void imprimirMatriz(int matriz[orden][orden]);
void sarrusPorRank(int rank,long double DiagArray[2],int matriz[orden][orden]);

int main(int argc, char *argv[]){
  if(argc != 2) return 0;
  sscanf(argv[1], "%d", &orden);

  int A[orden][orden];
  int rank;
  int tag=1,i;
  long double tmpDiag[2];
  long double DiagNeg = 0,DiagPos = 0;
  long double tmp[2];
  struct timeval inicio, fin;
  float runtime, mips;
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  llenarMatriz(A);
  /*Envio y recepcion de mensajes*/
  if(rank==0){

    gettimeofday( &inicio, (struct timezone *)0 );
    sarrusPorRank(0,tmp,A);
    DiagNeg += tmp[0];
    DiagPos += tmp[1];
    for(i=1; i<numeroDeRanks;i++){
      MPI_Recv(tmp,2,MPI_LONG_DOUBLE,i,tag,MPI_COMM_WORLD,&status);
      DiagNeg += tmp[0];
      DiagPos += tmp[1];
    }
  }
  else{
    sarrusPorRank(rank,tmpDiag,A);
    tmp[0] = tmpDiag[0];
    tmp[1] = tmpDiag[1];
    MPI_Send(tmp,2,MPI_LONG_DOUBLE,0,tag,MPI_COMM_WORLD);
  }

  if(rank==0){
    /*Muestro resultados y el tiempo de ejecución*/
    gettimeofday( &fin, (struct timezone *)0 );

    //imprimirMatriz(A);
    printf("\n\nSarrus calculation done.\n\n");
    printf("Pos final: %Lf rank: %d\n",DiagPos,rank);
    printf("Neg final: %Lf rank: %d\n",DiagNeg,rank);
    printf("Determinante = %Lf\n",DiagPos-DiagNeg);
    printf("Pos final: %Le rank: %d\n",DiagPos,rank);
    printf("Neg final: %Le rank: %d\n",DiagNeg,rank);
    printf("Determinante = %Le\n",DiagPos-DiagNeg);
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
      matriz[i][j] = rand()%5+1;
    }
  }
}

void imprimirMatriz(int matriz[orden][orden]){
  printf("\n");
  for(int i = 0; i < orden; i++){
    printf("\t|");
    for(int j = 0; j < orden; j++){
      printf("%2d|",matriz[i][j]);
    }
    printf("\n");
  }
}

void sarrusPorRank(int rank,long double DiagArray[2],int matriz[orden][orden]){
  int from = (rank * orden)/numeroDeRanks;
  int to = ((rank+1) * orden)/numeroDeRanks;
  long double temporalNegativa;
  long double temporalPositiva;
  int i,j;
  int fila;
  DiagArray[0] = 0;
  DiagArray[1] = 0;

  printf("computing rank %d (from row %d to %d)\n", rank, from, to-1);
  for(i = from; i < to; i++){
    temporalNegativa = 1;
    temporalPositiva = 1;
    for(j = 0; j < orden; j++){
      fila = i+j;
      if(fila >= orden){
        fila -= orden;
      }
      temporalPositiva *= matriz[fila][j];
      //printf("Pos: A[%d][%d]\n",fila,j);
      temporalNegativa *= matriz[fila][(orden-1)-j];
      //printf("Neg: A[%d][%d]\n",fila,(orden-1)-j);
    }
    DiagArray[0] += temporalNegativa;
    DiagArray[1] += temporalPositiva;
  }
  //printf("Pos final: %Lf rank: %d\n",DiagArray[1],rank);
  //printf("Neg final: %Lf rank: %d\n",DiagArray[0],rank);
  printf("finished rank %d\n", rank);
  printf("\n");
}
