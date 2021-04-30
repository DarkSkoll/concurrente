public class Oxigeno implements Runnable {
  Molecula mc;

  public Oxigeno(Molecula mc) {
    this.mc = mc;
  }

  public void run() {
    while(true){
      try {
        Thread.sleep(500);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
      mc.Oxigeno();
    }
  }
}
