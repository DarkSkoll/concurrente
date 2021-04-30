public class Fabrica {
  private int papel;
  private int filtro;
  private int tabaco;
  private int cigarro;
  private boolean permiso;

  public Fabrica() {
    this.papel = 0;
    this.filtro = 0;
    this.cigarro = 0;
    this.tabaco = 0;
    permiso = false;
  }

  public synchronized void Tabaco() {
    if(!permiso){
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    tabaco++;
    System.out.println("Tabaco: " + tabaco);
    permiso = false;
  }

  public synchronized void Papel() {
    if(!permiso){
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    papel++;
    System.out.println("Papel: " + papel);
    permiso = false;
  }

  public synchronized void Filtro() {
    filtro++;
    System.out.println("Filtro: " + filtro);
    permiso = true;
    notify();
  }

  public synchronized void Cigarro() {
    while (tabaco < 1 || papel < 1 || filtro < 1) {
      try {
        wait();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    papel--;
    filtro--;
    tabaco--;
    cigarro++;
    System.out.println("Cigarros = " + cigarro + ", Papel = " + papel + ", Filtro = " + filtro+ ", Tabaco = " + tabaco);
  }
}
