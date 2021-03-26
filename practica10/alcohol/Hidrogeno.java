public class Hidrogeno extends Molecula implements Runnable{
  public void run(){
    while(true){
      try {
        Thread.sleep(500);
        H.release();
        System.out.println("H: "+H.availablePermits());
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
