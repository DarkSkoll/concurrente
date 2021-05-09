#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>

int numeroDeRanks;
int size = 10;

void llenarMatriz(int matriz[size][size]);
void imprimirMatriz(int matriz[size][size]);
void fill_mmult(int rank,int R[size][size],int C[size][size]);
void mmult(int rank,int m[size][size],int A[size][size],int B[size][size]);

int main(int argc, char *argv[]){
  int A[size][size], B[size][size], C[size][size];
  int rank;
  int tag=1,i;
  struct timeval inicio, fin;
  float runtime, mips;
  int R[size][size];
  MPI_Status status;

  /*Inicia MPI e identifica tu id (rank) */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numeroDeRanks);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  llenarMatriz(A);
  sleep(1);
  llenarMatriz(B);
  /*Envio y recepcion de mensajes*/
  if(rank==0){

    gettimeofday( &inicio, (struct timezone *)0 );
    mmult(0,C,A,B);
    for(i=1; i<numeroDeRanks;i++){
      MPI_Recv(R, size*size, MPI_INT, i, tag, MPI_COMM_WORLD, &status);
      fill_mmult(i,R,C);
    }
  }
  else{
    mmult(rank,R,A,B);
    MPI_Send(R, size*size, MPI_INT, 0, tag, MPI_COMM_WORLD);
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
    imprimirMatriz(R);


    runtime = (float )(fin.tv_sec - inicio.tv_sec)*1000000 +
      (float )(fin.tv_usec - inicio.tv_usec);
    runtime /= 1000000.0;
    mips = (float )size*(float )size*(float )size;
    mips = (float )size*(float )size*(float )size;
    mips /= runtime;
    mips /= 1000000.0;
    printf("Execution time: %f secs. %f MIPS\n", runtime, mips );
  }

  MPI_Finalize();
}





void llenarMatriz(int matriz[size][size]){
  srand(time(NULL));
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++){
      matriz[i][j] = rand()%20;
    }
  }
}

void imprimirMatriz(int matriz[size][size]){
  printf("\n");
  for(int i = 0; i < size; i++){
    printf("\t| ");
    for(int j = 0; j < size; j++){
      printf("%5d|",matriz[i][j]);
    }
    printf("\n");
  }
}

void fill_mmult(int rank,int R[size][size],int C[size][size]){
  int from = (rank * size)/numeroDeRanks;
  int to = ((rank+1) * size)/numeroDeRanks;
  int i,j;
  for (i = from; i < to; i++)
    for (j = 0; j < size; j++) {
      C[i][j] = R[i][j];
    }
  printf("finished fill rank 0\n");
}

void mmult(int rank,int m[size][size],int A[size][size],int B[size][size]){
  int from = (rank * size)/numeroDeRanks;      /* note that this 'slicing' works fine */
  int to = ((rank+1) * size)/numeroDeRanks;    /* even if size is not divisible by numeroDeRanks */
  int i,j,k;

  printf("computing rank %d (from row %d to %d)\n", rank, from, to-1);
  for (i = from; i < to; i++)
    for (j = 0; j < size; j++) {
      m[i][j] = 0;
      for (k = 0; k < size; k++)
        m[i][j] += A[i][k]*B[k][j];
    }
  printf("finished rank %d\n", rank);
}
