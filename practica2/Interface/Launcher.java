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
    Operador hilo1 = new Operador("Hilo 1", cotaSuperior);
    Operador hilo2 = new Operador("Hilo 2", cotaSuperior);
    Operador hilo3 = new Operador("Hilo 3", cotaSuperior);
    Operador hilo4 = new Operador("Hilo 4", cotaSuperior);
    Operador hilo5 = new Operador("Hilo 5", cotaSuperior);
    Operador hilo6 = new Operador("Hilo 6", cotaSuperior);
    Operador hilo7 = new Operador("Hilo 7", cotaSuperior);
    Operador hilo8 = new Operador("Hilo 8", cotaSuperior);
    hilo1.start();
    hilo2.start();
    hilo3.start();
    hilo4.start();
    hilo5.start();
    hilo6.start();
    hilo7.start();
    hilo8.start();
  }
}
