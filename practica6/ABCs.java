import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class ABCs{
  protected static Semaphore B = new Semaphore(0);
  protected static Semaphore C = new Semaphore(1);
  protected static Semaphore sum = new Semaphore(0);
  protected static ArrayList<Character> cadena = new ArrayList<Character>();

  public static void main(String[] args) {
    Thread pA = new Thread(new Pa());
    Thread pB = new Thread(new Pb());
    Thread pC = new Thread(new Pc());

    pA.start();
    pB.start();
    pC.start();

    try{
      Thread.sleep(9000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }

    pA.stop();
    pB.stop();
    pC.stop();

    System.out.println(cadena);
  }
}
