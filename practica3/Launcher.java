import java.util.Random;

public class Launcher{
  public static void main(String[] args) {
    if(args.length < 3){
      System.out.println("La forma de ejecutar el programa es:");
      System.out.println("java Launcher numero1 numero2 numero3");
      return;
    }
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    int c = Integer.parseInt(args[2]);
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("c = " + c);
    HiloS1 hiloS1 = new HiloS1("Hilo S1", a);
    HiloS2 hiloS2 = new HiloS2("Hilo S2", b);
    HiloS3 hiloS3 = new HiloS3("Hilo S3", c);
    hiloS1.start();
    hiloS2.start();
    hiloS3.start();
    try{
      hiloS1.join();
      hiloS2.join();
      hiloS3.join();
    }catch(InterruptedException e1){
      System.out.println("Error en el join de hilos 1,2,3");
    }
    a = hiloS1.getA();
    b = hiloS2.getB();
    c = hiloS3.getC();
    HiloS4 hiloS4 = new HiloS4("Hilo S4", a, b, c);
    hiloS4.start();
    try{
      hiloS4.join();
    }catch(InterruptedException e2){
      System.out.println("Error en el join de hilo 4");
    }
    int d = hiloS4.getD();
    int e = hiloS4.getE();
    System.out.println("d = " + d);
    System.out.println("e = " + e);
    HiloS5 hiloS5 = new HiloS5("Hilo S5", d);
    HiloS7 hiloS7 = new HiloS7("Hilo S7", e);
    hiloS5.start();
    hiloS7.start();
    try{
      hiloS5.join();
    }catch(InterruptedException e3){
      System.out.println("Error en el join de hilo 5");
    }
    d = hiloS5.getD();
    System.out.println("d = " + d);
    HiloS6 hiloS6 = new HiloS6("Hilo S6", d, e);
    hiloS6.start();
    try{
      hiloS6.join();
      hiloS7.join();
    }catch(InterruptedException e4){
      System.out.println("Error en el join de hilos 6,7");
    }
    int f = hiloS6.getF();
    int g = hiloS7.getG();
    System.out.println("f = " + f);
    System.out.println("g = " + g);
    HiloS8 hiloS8 = new HiloS8("Hilo S8", f,g);
    hiloS8.start();
  }
}
