public class Launcher {
  public static void main(String[] args) throws InterruptedException {
    Fabrica mc = new Fabrica();

    Thread papel = new Thread(new Papel(mc));
    Thread cigarro = new Thread(new Cigarro(mc));
    Thread filtro = new Thread(new Filtro(mc));
    Thread tabaco = new Thread(new Tabaco(mc));

    papel.start();
    filtro.start();
    cigarro.start();
    tabaco.start();

    Thread.sleep(9000);
    System.exit(0);
  }
}
