import java.util.Random;

public class Pa extends ABCs implements Runnable{
  public void run(){
    Random random = new Random();
    while(true){
      try{
        Thread.sleep(500);
        cadena.add('A');
        System.out.println(cadena);
        if(random.nextInt(3)%2 == 0){
          sum.release();
        }
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
