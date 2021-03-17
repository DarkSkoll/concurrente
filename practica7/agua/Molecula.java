import java.util.concurrent.Semaphore;

public class Molecula{
  protected static Semaphore H = new Semaphore(0);
  protected static Semaphore O = new Semaphore(0);
  protected static Semaphore H2O = new Semaphore(0);

  public static void main(String[] args) {
    Thread tH = new Thread(new Hidrogeno());
    Thread tO = new Thread(new Oxigeno());
    Thread tH2O = new Thread(new Agua());

    tH.start();
    tO.start();
    tH2O.start();

    try {
      Thread.sleep(9000);
    } catch(Exception e){
      e.printStackTrace();
    }

    tH.stop();
    tO.stop();
    tH2O.stop();

    System.out.println("Hay " + H2O.availablePermits() + " moleculas de agua");
  }
}
