public class Molecula{
  protected static int H = 0;
  protected static int O = 0;
  protected static int H2O = 0;

  public static void main(String[] args) {
    Agua agua = new Agua();
    Thread tH2O = new Thread(agua);
    Thread tH = new Thread(new Hidrogeno(agua));
    Thread tO = new Thread(new Oxigeno(agua));

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

    System.out.println("Hay " + H2O + " moleculas de agua");
  }
}
