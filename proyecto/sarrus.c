#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>

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
  int tag=1;
  long double Diagonales[2];
  long double tmp[2];
  double start,end;
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  llenarMatriz(A);
  start = MPI_Wtime();
  /*Envio y recepcion de mensajes*/
  if(rank == 0){
    sarrusPorRank(rank,tmp,A);
    Diagonales[0] = tmp[0];
    Diagonales[1] = tmp[1];
    if(numeroDeRanks > 1) MPI_Send(Diagonales,2,MPI_LONG_DOUBLE,rank+1,tag,MPI_COMM_WORLD);
    //else MPI_Send(Diagonales,2,MPI_LONG_DOUBLE,0,tag,MPI_COMM_WORLD);
  }
  if(rank == numeroDeRanks - 1){
    if(numeroDeRanks > 1){
      MPI_Recv(Diagonales,2,MPI_LONG_DOUBLE,rank-1,tag,MPI_COMM_WORLD,&status);
      sarrusPorRank(rank,tmp,A);
      Diagonales[0] += tmp[0];
      Diagonales[1] += tmp[1];
    }
    end = MPI_Wtime();
    if(orden < 21) imprimirMatriz(A);
    printf("Sarrus calculation done.\n\n");
    printf("Pos final: %Lf rank: %d\n",Diagonales[1],rank);
    printf("Neg final: %Lf rank: %d\n",Diagonales[0],rank);
    printf("Determinante = %Lf rank: %d\n",Diagonales[1]-Diagonales[0],rank);
    printf("Pos final: %Le rank: %d\n",Diagonales[1],rank);
    printf("Neg final: %Le rank: %d\n",Diagonales[0],rank);
    printf("Determinante = %Le rank: %d\n",Diagonales[1]-Diagonales[0],rank);
    printf("Tiempo: %lf rank: %d\n",end-start,rank);
    printf("\n");

    FILE *file1;
    file1 = fopen("datosTiempo.txt","a+");
    if(file1 != NULL){
      fprintf(file1,"%lf\n",end - start);
    }
    fflush(file1);
    fclose(file1);
  }
  if(rank != 0 && rank != numeroDeRanks - 1){
    MPI_Recv(Diagonales,2,MPI_LONG_DOUBLE,rank-1,tag,MPI_COMM_WORLD,&status);
    sarrusPorRank(rank,tmp,A);
    Diagonales[0] += tmp[0];
    Diagonales[1] += tmp[1];
    MPI_Send(Diagonales,2,MPI_LONG_DOUBLE,rank+1,tag,MPI_COMM_WORLD);
  }
  MPI_Finalize();
}

void llenarMatriz(int matriz[orden][orden]){
  for(int i = 0; i < orden; i++){
    for(int j = 0; j < orden; j++){
      matriz[i][j] = rand()%20+1;
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
}
