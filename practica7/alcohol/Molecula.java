import java.util.concurrent.Semaphore;

public class Molecula{
  protected static Semaphore H = new Semaphore(0);
  protected static Semaphore O = new Semaphore(0);
  protected static Semaphore C = new Semaphore(0);
  protected static Semaphore alcohol = new Semaphore(0);

  public static void main(String[] args) {
    Thread tH = new Thread(new Hidrogeno());
    Thread tO = new Thread(new Oxigeno());
    Thread tC = new Thread(new Carbono());
    Thread tAlcohol = new Thread(new Alcohol());

    tH.start();
    tO.start();
    tC.start();
    tAlcohol.start();

    try {
      Thread.sleep(9000);
    } catch(Exception e){
      e.printStackTrace();
    }

    tH.stop();
    tO.stop();
    tC.stop();
    tAlcohol.stop();

    System.out.println("Hay " + alcohol.availablePermits() + " moleculas de alcohol");
  }
}
