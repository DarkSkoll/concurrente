public class Molecula {
  private int oxigeno;
  private int hidrogeno;
  private int agua;
  private boolean permiso;

  public Molecula(int oxigeno, int hidrogeno, int agua) {
    this.oxigeno = oxigeno;
    this.hidrogeno = hidrogeno;
    this.agua = agua;
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
    oxigeno++;
    System.out.println("Oxigeno: " + oxigeno);
    permiso = false;
  }


  public synchronized void Hidrogeno() {
    hidrogeno++;
    System.out.println("Hidrogeno: " + hidrogeno);
    permiso = true;
    notify();
  }

  public synchronized void Agua() {
    while (hidrogeno < 2) {
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    oxigeno--;
    hidrogeno-=2;
    agua++;
    System.out.println("Molecula Agua = " + agua + ", Oxigeno = " + oxigeno + ", Hidrogeno = " + hidrogeno);
  }
}
