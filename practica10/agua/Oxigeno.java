import java.util.Random;

public class Oxigeno extends Molecula implements Runnable{
  Molecula agua;

  public Oxigeno(Molecula agua){
    this.agua = agua;
  }

  public void run(){
    Random random = new Random();
    while(true){
      try {
        Thread.sleep(random.nextInt(500));
        O++;
        System.out.println("O: "+O);
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
