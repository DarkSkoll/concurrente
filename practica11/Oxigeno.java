public class Oxigeno extends MoleculaAgua implements Runnable {
  MonMol mc;

  public Oxigeno(MonMol mc) {
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
