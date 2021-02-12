public class HiloS2 extends Thread{
  private String name;
  private int b;

  public HiloS2(String name, int b){
    this.name = name;
    this.b = b;
  }

  public void run(){
    b *= b;
    System.out.println(name + ": b * b = " + b);
  }

  public int getB(){
    return b;
  }
}
