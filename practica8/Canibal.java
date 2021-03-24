import java.util.Random;

public class Canibal extends Cocina implements Runnable{
  private int numero;

  public Canibal(int numero){
    this.numero = numero;
  }

  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep((random.nextInt(5)+1)*100);
        System.out.println("El canibal "+numero+" toma 1 misionero");
        if(!olla.tryAcquire()){
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
