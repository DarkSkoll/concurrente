import java.util.Random;

public class Productor extends Coordinador implements Runnable{
  private char producto;

  public Productor(char producto){
    this.producto = producto;
  }

  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep((random.nextInt(5)+1)*100);
        semaforo.acquire();
        System.out.println("El productor de "+producto+"'s agrego al buffer");
        buffer.add(producto);
        System.out.println(buffer);
        semaforo.release();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
