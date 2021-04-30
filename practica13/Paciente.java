public class Paciente implements Runnable {
  Consultorio mc;

  public Paciente(Consultorio mc) {
    this.mc = mc;
  }

  public void run() {
    while(true) mc.Paciente();
  }
}
