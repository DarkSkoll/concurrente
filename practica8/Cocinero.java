public class Cocinero extends Cocina implements Runnable{
  public void run(){
    int diferencia;
    while(true){
      try {
        alarma.acquire();
        System.out.println("El cocinero se despierta");
        diferencia = 10-olla.availablePermits();
        misioneros.acquire(diferencia);
        System.out.println("Misioneros vivos: "+misioneros.availablePermits());
        olla.release(diferencia);
        System.out.println("Misioneros cocinados: "+olla.availablePermits());
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
