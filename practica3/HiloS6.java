public class HiloS6 extends Thread{
  private String name;
  private int g;
  private int e;
  private int f;

  public HiloS6(String name, int f, int e){
    this.name = name;
    this.f = f;
    this.e = e;
  }

  public void run(){
    g = f + e;
    System.out.println(name + ": g = f + e  = " + g);
  }

  public int getG(){
    return g;
  }
}
