import java.util.Random;

public class Consumidor extends Coordinador implements Runnable{
  private char producto;

  public Consumidor(char producto){
    this.producto = producto;
  }

  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep((random.nextInt(5)+1)*100);
        semaforo.acquire();
        System.out.println("El consumidor de "+producto+"'s busca en el buffer");
        if(buffer.remove((Character)producto)){
          System.out.println("Encontro el producto y lo toma");
          System.out.println(buffer);
        }else{
          System.out.println("No encontro su producto y se retira");
        }
        semaforo.release();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
