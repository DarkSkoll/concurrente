public class HiloS7 extends Thread{
  private String name;
  private int e;
  private int g;

  public HiloS7(String name, int e){
    this.name = name;
    this.e = e;
  }

  public void run(){
    g = e + 5;
    System.out.println(name + ": g = e + 5 = " + g);
  }

  public int getG(){
    return g;
  }
}
