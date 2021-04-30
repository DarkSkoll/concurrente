public class MonMol {
  private int contT;
  private int contF;
  private int contC;
  private boolean permiso;

  public MonMol(int contT, int contF, int contC) {
    this.contT = contT;
    this.contF = contF;
    this.contC = contC;
    permiso = false;
  }

  public synchronized void Oxigeno() {
    if(!permiso){
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    contT++;
    System.out.println("Oxigeno: " + contT);
    permiso = false;
  }


  public synchronized void Hidrogeno() {
    contF++;
    System.out.println("Hidrogeno: " + contF);
    permiso = true;
    notify();
  }

  public synchronized void Agua() {
    while (contF < 2) {
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    contT--;
    contF-=2;
    contC++;
    System.out.println("Molecula = " + contC + ", Oxigeno = " + contT + ", Hidrogeno = " + contF);
  }
}
