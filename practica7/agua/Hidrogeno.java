import java.util.Random;

public class Hidrogeno extends Molecula implements Runnable{
  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep(random.nextInt(500));
        H.release();
        System.out.println("H: "+H.availablePermits());
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
