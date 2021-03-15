public class HiloS1 extends Thread{
  private String name;
  private int a;

  public HiloS1(String name, int a){
    this.name = name;
    this.a = a;
  }

  public void run(){
    a *= 2;
    System.out.println(name + ": a = x * 2 = " + a);
  }

  public int getA(){
    return a;
  }
}
