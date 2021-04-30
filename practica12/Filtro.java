public class Filtro implements Runnable {
  Fabrica mc;

  public Filtro(Fabrica mc) {
    this.mc = mc;
  }

  public void run() {
    while(true){
      try {
        Thread.sleep(500);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
      mc.Filtro();
    }
  }
}
