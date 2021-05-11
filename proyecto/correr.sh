#!/bin/bash

FILE=datosTiempo.txt
if [[ -f "$FILE" ]]; then
  rm datosTiempo.txt
fi

FILE=ordenes.txt
if [[ -f "$FILE" ]]; then
  rm ordenes.txt
fi

for i in {10..1400..10}
do
  echo "$i" >> ordenes.txt
  mpirun -np 4 ./sarrus $i;
done

paste ordenes.txt datosTiempo.txt > tiempoOrdenes.txt

#gnuplot graficaProcesos.ptl
