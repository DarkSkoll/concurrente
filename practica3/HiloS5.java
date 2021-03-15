public class HiloS5 extends Thread{
  private String name;
  private int d;
  private int f;

  public HiloS5(String name, int d){
    this.name = name;
    this.d = d;
  }

  public void run(){
    f = d/3;
    System.out.println(name + ": f = d / 3 = " + f);
  }

  public int getF(){
    return f;
  }
}
