import java.util.Scanner;

public class Launcher{
  public static void main(String[] args) {
    int cotaSuperior;
    int opc;
    Scanner lector = new Scanner(System.in);
    System.out.println("Ingrese la cota superior, un numero positivo mayor que 1");
    do{
      cotaSuperior = lector.nextInt();
    }while(cotaSuperior < 2);
    OperadorHer hilo1;
    OperadorInt hilo2;
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
      hilo1 = new OperadorHer(cotaSuperior,opc);
      hilo2 = new OperadorInt(cotaSuperior,opc);
      switch(opc){
        case 1: case 2: case 3: case 4:
          hilo1.start();
          hilo2.start();
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
      hilo1 = null;
      hilo2 = null;
    }while(opc != 6);
  }
}
