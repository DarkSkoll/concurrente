import java.util.concurrent.Semaphore;

public class Cocina{
  protected static Semaphore misioneros = new Semaphore(100);
  protected static Semaphore olla = new Semaphore(0);
  protected static Semaphore alarma = new Semaphore(1);

  public static void main(String[] args) {
    Thread cocinero = new Thread(new Cocinero());
    Thread canibal1 = new Thread(new Canibal(1));
    Thread canibal2 = new Thread(new Canibal(2));
    Thread canibal3 = new Thread(new Canibal(3));
    Thread canibal4 = new Thread(new Canibal(4));

    cocinero.start();
    canibal1.start();
    canibal2.start();
    canibal3.start();
    canibal4.start();

    while(true){
      try {
        Thread.sleep(50);
      } catch(Exception e){
        e.printStackTrace();
      }
      if(misioneros.availablePermits() == 0 && olla.availablePermits() == 0){
        cocinero.stop();
        canibal1.stop();
        canibal2.stop();
        canibal3.stop();
        canibal4.stop();
        break;
      }
    }
  }
}
