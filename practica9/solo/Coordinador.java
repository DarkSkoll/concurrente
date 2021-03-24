import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Coordinador{
  protected static Semaphore semaforo = new Semaphore(1);
  protected static ArrayList<Character> buffer = new ArrayList<Character>();

  public static void main(String[] args){
    Thread productorA = new Thread(new Productor('A'));
    Thread consumidorA = new Thread(new Consumidor('A'));

    productorA.start();
    try {
      Thread.sleep(1000);
    } catch(Exception e){
      e.printStackTrace();
    }
    consumidorA.start();
    try {
      Thread.sleep(10000);
    } catch(Exception e){
      e.printStackTrace();
    }
    productorA.stop();
    consumidorA.stop();
    System.out.println(buffer);
  }
}
