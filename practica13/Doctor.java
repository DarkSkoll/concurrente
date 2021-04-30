public class Doctor implements Runnable {
  Consultorio mc;

  public Doctor(Consultorio mc) {
    this.mc = mc;
  }

  public void run() {
    while(true) mc.Doctor();
  }
}
