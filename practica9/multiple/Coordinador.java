import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Coordinador{
  protected static Semaphore semaforo = new Semaphore(1);
  protected static ArrayList<Character> buffer = new ArrayList<Character>();

  public static void main(String[] args){
    Thread productorA = new Thread(new Productor('A'));
    Thread consumidorA = new Thread(new Consumidor('A'));
    Thread productorB = new Thread(new Productor('B'));
    Thread consumidorB = new Thread(new Consumidor('B'));
    Thread productorC = new Thread(new Productor('C'));
    Thread consumidorC = new Thread(new Consumidor('C'));
    Thread productorD = new Thread(new Productor('D'));
    Thread consumidorD = new Thread(new Consumidor('D'));

    productorA.start();
    productorB.start();
    productorC.start();
    productorD.start();
    try {
      Thread.sleep(1000);
    } catch(Exception e){
      e.printStackTrace();
    }
    consumidorA.start();
    consumidorB.start();
    consumidorC.start();
    consumidorD.start();
    try {
      Thread.sleep(10000);
    } catch(Exception e){
      e.printStackTrace();
    }
    productorA.stop();
    productorB.stop();
    productorC.stop();
    productorD.stop();
    consumidorA.stop();
    consumidorB.stop();
    consumidorC.stop();
    consumidorD.stop();
    System.out.println(buffer);
  }
}
