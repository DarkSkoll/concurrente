#!/bin/bash

FILE=datosTiempo.txt
if [[ -f "$FILE" ]]; then
  rm datosTiempo.txt
fi

FILE=ordenes.txt
if [[ -f "$FILE" ]]; then
  rm ordenes.txt
fi

FILE=procesos.txt
if [[ -f "$FILE" ]]; then
  rm procesos.txt
fi

function ordenes(){
  for i in {10..1400..10}
  do
    echo "$i" >> ordenes.txt
    mpirun -np 4 ./sarrus $i;
  done
}

function procesos(){
  for i in {1..4}
  do
    echo "$i" >> procesos.txt
    mpirun -np 4 ./sarrus $i;
  done
}

procesos
ordenes

paste ordenes.txt datosTiempo.txt > tiempoOrdenes.txt
paste procesos.txt datosTiempo.txt > tiempoProcesos.txt

gnuplot graficaProcesos.ptl
gnuplot graficaOrdenes.ptl
