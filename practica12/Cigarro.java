public class Cigarro implements Runnable {
  Fabrica mc;

  public Cigarro(Fabrica mc) {
    this.mc = mc;
  }

  public void run() {
    while(true){
      try {
        Thread.sleep(500);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
      mc.Cigarro();
    }
  }
}
