public class Hidrogeno extends MoleculaAgua implements Runnable {
  MonMol mc;

  public Hidrogeno(MonMol mc) {
    this.mc = mc;
  }

  public void run() {
    while(true){
      try {
        Thread.sleep(500);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
      mc.Hidrogeno();
    }
  }
}
