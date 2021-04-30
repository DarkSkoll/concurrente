public class Agua extends MoleculaAgua implements Runnable {
  MonMol mc;

  public Agua(MonMol mc) {
    this.mc = mc;
  }

  public void run() {
    while(true){
      try {
        Thread.sleep(500);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
      mc.Agua();
    }
  }
}
