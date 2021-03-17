public class Oxigeno extends Molecula implements Runnable{
  public void run(){
    while(true){
      try {
        Thread.sleep(500);
        O.release();
        System.out.println("O: "+O.availablePermits());
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
