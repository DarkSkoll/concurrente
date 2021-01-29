public class HiloS5 extends Thread{
  private String name;
  private int d;

  public HiloS5(String name, int d){
    this.name = name;
    this.d = d;
  }

  public void run(){
    d /= 3;
    System.out.println(name + ": d / 3 = " + d);
  }

  public int getD(){
    return d;
  }
}
