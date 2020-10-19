import Core.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client implements Observer {
  List<Activity> active = new ArrayList<>();
  Printer printer = null;

  @Override
  public void update(Observable o, Object arg) {
    for (Activity a : active) {
      System.out.println(a);
    }
    if (this.printer != null) { printer.printTree();}
  }

  private void wait(int seconds) {
    try {
      Thread.sleep(1000 * seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    Clock c = Clock.getInstance();
    c.addObserver(this);
    c.start();

    Project root = new Project("root");
    Project p1 = new Project("P1");
    root.addActivity(p1);
    Project p2 = new Project("P2");
    root.addActivity(p2);
    Task t1 = new Task("T1");
    root.addActivity(t1);
    Task t2 = new Task("T2");
    p1.addActivity(t2);
    Task t3 = new Task("T3");
    p2.addActivity(t3);

    // fer l'objecte impressor
    printer = new Printer(root);

    // fer que aquest impresor imprimeixi l'arbre periodicament a partir d'ara
    // ...

    // provar-ho
    wait(4);
    // this will make some intervals
    t1.startTask();
    wait(4);
    t2.startTask();
    wait(2);
  }

  public static void main(String[] args) {
    Client client = new Client();
    client.start();
  }
}