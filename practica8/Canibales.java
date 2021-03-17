import java.util.Random;

public class Canibales extends Cocina implements Runnable{
  public void run(){
    Random random = new Random();
    int tomados;
    while(true){
      try {
        Thread.sleep(500);
        tomados = random.nextInt(4)+1;
        System.out.println("Los canibales quieren comer "+tomados+" misioneros");
        if(!olla.tryAcquire(tomados)){
          System.out.println("No hay suficientes misioneros, despierten al cocinero");
          alarma.release();
        }else{
          System.out.println("Quedan "+olla.availablePermits()+" misioneros en la olla");
          if(olla.availablePermits() == 0){
            alarma.release();
            System.out.println("Se acabaron los misioneros hagan mas");
          }
        }
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
