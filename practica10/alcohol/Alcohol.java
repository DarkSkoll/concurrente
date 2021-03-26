public class Alcohol extends Molecula implements Runnable{
  public void run(){
    while(true){
      try {
        Thread.sleep(500);
        H.acquire(6);
        O.acquire();
        C.acquire(2);
        alcohol.release();
        System.out.println("Añadida molecula de alcohol");
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
