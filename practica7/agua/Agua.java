public class Agua extends Molecula implements Runnable{
  public void run(){
    while(true){
      try {
        Thread.sleep(500);
        H.acquire(2);
        O.acquire();
        H2O.release();
        System.out.println("Añadida molecula de agua");
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
