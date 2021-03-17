import java.util.Random;

public class Oxigeno extends Molecula implements Runnable{
  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep(random.nextInt(500));
        O.release();
        System.out.println("O: "+O.availablePermits());
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
