import java.util.Random;

public class Operador implements Runnable{
  private String nombre;
  private int cotaSuperior;

  public Operador(String nombre, int cotaSuperior){
    this.nombre = nombre;
    this.cotaSuperior = cotaSuperior;
  }

  public void start(){
    (new Thread(this)).start();
  }

  public void run(){
    Random random = new Random();
    imprimirPares();
    imprimirImpares();
    imprimirMultiplos(random.nextInt(cotaSuperior/2));
    imprimirPrimos();
  }

  public void imprimirPares(){
    System.out.println("Imprimiendo los numeros pares entre 1 y " + cotaSuperior);
    for(int i = 1; i < cotaSuperior/2; i++){
      imprimir(i*2);
    }
  }

  public void imprimirImpares(){
    System.out.println("Imprimiendo los numeros impares entre 1 y " + cotaSuperior);
    for(int i = 0; i < cotaSuperior/2; i++){
      imprimir(i*2+1);
    }
  }

  public void imprimirMultiplos(int base){
    if(base <= 1 || base > cotaSuperior){
      System.out.println("Base invalida");
      return;
    }
    System.out.println("Imprimiendo los multiplos de " + base + " entre 1 y " + cotaSuperior);
    int tmp;
    for(int i = 1; i < cotaSuperior/2; i++){
      tmp = i*base;
      if(tmp > cotaSuperior) break;
      imprimir(tmp);
    }
  }

  public void imprimirPrimos(){
    System.out.println("Imprimiendo los numeros primos entre 1 y " + cotaSuperior);
    for(int i = 2; i < cotaSuperior; i++){
      if(esPrimo(i)){
        imprimir(i);
      }
    }
  }

  public void imprimir(int numero){
    System.out.println(nombre + ": " + numero);
  }

  public boolean esPrimo(int numero){
    if(numero <= 1) return false;
    for(int i = 2; i < numero/2+1; i++){
      if(numero%i ==0) return false;
    }
    return true;
  }
}
