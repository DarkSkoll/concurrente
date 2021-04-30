public class Launcher {
  public static void main(String[] args) throws InterruptedException {
    Consultorio mc = new Consultorio();
    int i = 0;

    Thread doctor = new Thread(new Doctor(mc));
    Thread paciente1 = new Thread(new Paciente(mc));
    Thread paciente2 = new Thread(new Paciente(mc));
    Thread paciente3 = new Thread(new Paciente(mc));
    Thread paciente4 = new Thread(new Paciente(mc));
    Thread paciente5 = new Thread(new Paciente(mc));

    doctor.start();
    paciente1.start();
    paciente2.start();
    paciente3.start();
    paciente4.start();
    paciente5.start();

    Thread.sleep(20000);
    System.exit(0);
  }
}
