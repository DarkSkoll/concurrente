import java.util.Random;

public class Pc extends ABCs implements Runnable{
  public void run(){
    Random random = new Random();
    while(true){
      try{
        //Thread.sleep(500);
        B.acquire();
        sum.acquire();
        cadena.add('C');
        System.out.println(cadena);
        C.release();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
