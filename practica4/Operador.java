public class Operador extends Thread{
  private String name;
  private String arrayName;
  private int[] array;

  public Operador(String name, String arrayName, int[] array){
    this.name = name;
    this.arrayName = arrayName;
    this.array = array;
  }

  public void run(){
    sumarElementos();
    sumarCuadrados();
    calcularMedia();
  }

  public void sumarElementos(){
    System.out.println(name + ": Imprimiendo la suma de los elementos del arreglo " + arrayName);
    int suma = 0;
    for(int i = 0; i < array.length; i++){
      suma += array[i];
      imprimir(suma);
    }
  }

  public void sumarCuadrados(){
    System.out.println(name + ": Imprimiendo la suma de los elementos del arreglo al cuadrado " + arrayName);
    int suma = 0;
    for(int i = 0; i < array.length; i++){
      suma += array[i]*array[i];
      imprimir(suma);
    }
  }

  public void calcularMedia(){
    float media = 0;
    for(int i = 0; i < array.length; i++){
      media += array[i];
    }
    media /= array.length;
    System.out.println(name + ": La media del arreglo es: " + media);
  }

  public void imprimir(int n){
    System.out.println(name + ": " + n);
  }
}
