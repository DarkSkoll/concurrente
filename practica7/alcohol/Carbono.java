public class Carbono extends Molecula implements Runnable{
  public void run(){
    while(true){
      try {
        Thread.sleep(500);
        C.release();
        System.out.println("C: "+C.availablePermits());
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
