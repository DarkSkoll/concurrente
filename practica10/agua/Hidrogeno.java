import java.util.Random;

public class Hidrogeno extends Molecula implements Runnable{
  Molecula agua;

  public Hidrogeno(Molecula agua){
    this.agua = agua;
  }

  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep(random.nextInt(500));
        H++;
        System.out.println("H: "+H);
        if(H == 2){
          agua.notify();
        }
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
