public class Launcher {
  public static void main(String[] args) throws InterruptedException {
    Molecula mc = new Molecula(0,0,0);

    Thread t = new Thread(new Oxigeno(mc));
    Thread p = new Thread(new Hidrogeno(mc));
    Thread c = new Thread(new Agua(mc));

    t.start();
    p.start();
    c.start();

    Thread.sleep(9000);
    System.exit(0);
  }
}
