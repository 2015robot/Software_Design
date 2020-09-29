import java.util.concurrent.ThreadLocalRandom;

public class NBodySimulator {

  private static Universe __universe;

  private String __fname;
  private double __dt;
  private int __pauseTime;

  final double min_dt = 0.0001;
  final double max_dt = 10;
  final int min_pauseTime = 1;
  final int max_pauseTime = 20;

  public NBodySimulator(String fname, double dt, int pauseTime, boolean do_trace) {
    // client to simulate a universe
    // In IntelliJ : Run -> Run... -> Edit configurations -> Program arguments 10000 data/3body.txt
      __dt = dt;
      __pauseTime = pauseTime;
      System.out.println("dt=" + __dt);
      __universe = new Universe(__fname);
      simulate();
  }

  //PROBLEM: WE ARE USING ALMOST THE SAME CODE AS IN THE OTHER CONSTRUCTOR
  //only reads numBodies from command line and make some bodies with random mass
  public NBodySimulator(int numBodies) {
    //dt, pauseTime have to be random, this constructor has to initialize them
    //Otherwise, Universe's constructor has to initialize the bodies
    __dt = ThreadLocalRandom.current().nextDouble(min_dt, max_dt + 1);
    __pauseTime = ThreadLocalRandom.current().nextInt(min_pauseTime, max_pauseTime + 1);
    System.out.println("dt=" + __dt);
    __universe = new Universe(numBodies);
    simulate();
  }

  public void simulate() {
    //I think it has to contain the code of the main loop
    StdDraw.enableDoubleBuffering();
    while (true) {
      StdDraw.clear();
      __universe.increaseTime(__dt);
      __universe.draw();
      StdDraw.show();
      StdDraw.pause(__pauseTime);
    }
  }

  public static void main(String[] args) {
    NBodySimulator nbody;
    int numargs = args.length;
    if ((numargs == 3) || (numargs == 4)) {
      double dt = Double.parseDouble(args[0]);
      String fname = args[1];
      int pauseTime = Integer.parseInt(args[2]);
      boolean do_trace = false;
      if (args.length == 4) {
        do_trace = args[3].toLowerCase().equals("trace");
      }
      nbody = new NBodySimulator(fname, dt, pauseTime, do_trace);
      nbody.simulate();
    } else if (numargs == 1) {
      int numBodies = Integer.parseInt(args[0]);
      nbody = new NBodySimulator(numBodies);
      nbody.simulate();
    } else {
      assert false : "invalid number of aguments";
    }
  }


}
