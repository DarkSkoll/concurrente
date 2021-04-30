public class Tabaco implements Runnable {
  Fabrica mc;

  public Tabaco(Fabrica mc) {
    this.mc = mc;
  }

  public void run() {
    while(true){
      try {
        Thread.sleep(500);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
      mc.Tabaco();
    }
  }
}
