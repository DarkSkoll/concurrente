import java.util.Random;

public class Pb extends ABCs implements Runnable{
  public void run(){
    Random random = new Random();
    while(true){
      try{
        //Thread.sleep(500);
        C.acquire();
        sum.acquire(random.nextInt(1)+1);
        cadena.add('B');
        B.release();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
