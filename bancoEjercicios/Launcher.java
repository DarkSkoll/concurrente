import java.util.Random;

public class Launcher{
  public static void main(String[] args) {
    if(args.length < 2){
      System.out.println("La forma de ejecutar el programa es:");
      System.out.println("java Launcher numero1 numero2");
      return;
    }
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    HiloS1 s1 = new HiloS1("Hilo S1",a,b);
    s1.start();
    try{
      s1.join();
    }catch(InterruptedException e){
      System.out.println("Error al sincronizar");
    }
    a = s1.getA();
    b = s1.getB();
    HiloS2 s2 = new HiloS2("Hilo S2",b);
    HiloS3 s3 = new HiloS3("Hilo S3",a);
    s2.start();
    s3.start();
    try{
      s2.join();
      s3.join();
    }catch(InterruptedException e){
      System.out.println("Error al sincronizar");
    }
    a = s3.getA();
    b = s2.getB();
    HiloS4 s4 = new HiloS4("Hilo S4",a,b);
    s4.start();
    try{
      s4.join();
    }catch(InterruptedException e){
      System.out.println("Error al sincronizar");
    }
    System.out.println("E = " + s4.getE());
    System.out.println("D = " + s4.getD());
  }
}
