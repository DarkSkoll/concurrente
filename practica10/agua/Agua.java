public class Agua extends Molecula implements Runnable{
  public synchronized void run(){
    while(true){
      try {
        Thread.sleep(500);
        wait();
        H -= 2;
        O--;
        H2O++;
        System.out.println("Añadida molecula de agua");
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
