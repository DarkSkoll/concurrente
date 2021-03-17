import java.util.concurrent.Semaphore;

public class Cocina{
  protected static Semaphore misioneros = new Semaphore(100);
  protected static Semaphore olla = new Semaphore(0);
  protected static Semaphore alarma = new Semaphore(1);

  public static void main(String[] args) {
    Thread cocinero = new Thread(new Cocinero());
    Thread canibales = new Thread(new Canibales());

    cocinero.start();
    canibales.start();

    try {
      Thread.sleep(9000);
    } catch(Exception e){
      e.printStackTrace();
    }

    cocinero.stop();
    canibales.stop();
  }
}
