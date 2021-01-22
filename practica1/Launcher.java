import java.util.Scanner;

public class Launcher{
  public static void main(String[] args) {
    Launcher main = new Launcher();
    int cotaSuperior;
    int opc;
    Scanner lector = new Scanner(System.in);
    System.out.println("Ingrese la cota superior, un numero positivo mayor que 1");
    do{
      cotaSuperior = lector.nextInt();
    }while(cotaSuperior < 2);
    do{
      opc = 0;
      System.out.println("Menu Principal");
      System.out.println("La cota superior es: " + cotaSuperior);
      System.out.println("Por favor seleccione una opcion");
      System.out.println("1> Imprimir numeros pares");
      System.out.println("2> Imprimir numeros impares");
      System.out.println("3> Imprimir los multiplos de un numero");
      System.out.println("4> Imprimir numeros primos");
      System.out.println("5> Cambiar la cota superior");
      System.out.println("6> Salir");
      opc = lector.nextInt();
      switch(opc){
        case 1:
          main.imprimirPares(cotaSuperior);
          break;
        case 2:
          main.imprimirImpares(cotaSuperior);
          break;
        case 3:
          System.out.println("Ingrese una base para los multiplos");
          int base = lector.nextInt();
          main.imprimirMultiplos(cotaSuperior, base);
          break;
        case 4:
          main.imprimirPrimos(cotaSuperior);
          break;
        case 5:
          System.out.println("Ingrese la cota superior, un numero positivo mayor que 1");
          do{
            cotaSuperior = lector.nextInt();
          }while(cotaSuperior < 2);
          break;
        case 6:
          System.out.println("Hasta luego");
          break;
        default:
          System.out.println("Opcion Invalida");
      }
    }while(opc != 6);
  }

  public void imprimirPares(int cotaSuperior){
    System.out.println("Imprimiendo los numeros pares entre 1 y " + cotaSuperior);
    for(int i = 0; i < cotaSuperior/2; i++){
      System.out.print((i+1)*2 + ",");
    }
    System.out.println("");
  }

  public void imprimirImpares(int cotaSuperior){
    System.out.println("Imprimiendo los numeros impares entre 1 y " + cotaSuperior);
    for(int i = 0; i < cotaSuperior/2; i++){
      System.out.print(i*2+1 + ",");
    }
    System.out.println("");
  }

  public void imprimirMultiplos(int cotaSuperior, int base){
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

  public void imprimirPrimos(int cotaSuperior){
    System.out.println("Imprimiendo los numeros primos entre 1 y " + cotaSuperior);
    for(int i = 2; i < cotaSuperior; i++){
      if(esPrimo(i)){
        System.out.print(i+" ");
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
