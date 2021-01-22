import java.util.Scanner;

public class OperadorHer extends Thread{
  private int cotaSuperior;
  private int opc;

  public OperadorHer(int cotaSuperior, int opc){
    this.cotaSuperior = cotaSuperior;
    this.opc = opc;
  }

  public void run(){
    System.out.println("Hilo de Herencia:");
    switch(opc){
      case 1:
        imprimirPares();
        break;
      case 2:
        imprimirImpares();
        break;
      case 3:
        System.out.println("Ingrese una base para los multiplos");
        Scanner lector = new Scanner(System.in);
        imprimirMultiplos(lector.nextInt());
        break;
      case 4:
        imprimirPrimos();
        break;
    }
  }

  public void imprimirPares(){
    System.out.println("Imprimiendo los numeros pares entre 1 y " + cotaSuperior);
    for(int i = 0; i < cotaSuperior/2; i++){
      System.out.print((i+1)*2 + ",");
    }
    System.out.println("");
  }

  public void imprimirImpares(){
    System.out.println("Imprimiendo los numeros impares entre 1 y " + cotaSuperior);
    for(int i = 0; i < cotaSuperior/2; i++){
      System.out.print(i*2+1 + ",");
    }
    System.out.println("");
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
      System.out.print(tmp + ",");
    }
    System.out.println("");
  }

  public void imprimirPrimos(){
    System.out.println("Imprimiendo los numeros primos entre 1 y " + cotaSuperior);
    for(int i = 2; i < cotaSuperior; i++){
      if(esPrimo(i)){
        System.out.print(i + " ");
      }
    }
    System.out.println("");
  }

  public boolean esPrimo(int n){
    if(n <= 1) return false;
    for(int i = 2; i < n/2+1; i++){
      if(n%i ==0) return false;
    }
    return true;
  }
}
