public class Consultorio {
  private boolean ocupado;
  private int sala;

  public Consultorio() {
    ocupado = false;
    sala = 0;
  }

  public synchronized void Paciente() {
    try {
      Thread.sleep(1000);
    } catch(InterruptedException e){
      e.printStackTrace();
    }
    if(!ocupado){
      ocupado = true;
      System.out.println("Paciente entra a consulta");
    }else if(sala < 10){
      System.out.println("El paciente va a la sala de espera");
      sala++;
      System.out.println("Hay: " + sala +" pacientes en la sala");
    }else{
      System.out.println("El paciente hace un cita para otro dia");
    }
    notify();
    try {
      wait();
    } catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  public synchronized void Doctor() {
    while(!ocupado){
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    System.out.println("El doctor atiende al paciente");
    try {
      Thread.sleep(500);
    } catch(InterruptedException e){
      e.printStackTrace();
    }
    sala--;
    ocupado = false;
    notify();
  }
}
