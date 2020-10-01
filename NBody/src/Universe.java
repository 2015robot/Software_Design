import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac Universe.java
 *  Execution:    java Universe dt input.txt
 *  Dependencies: Body.java Vector.java StdIn.java StdDraw.java
 *  Datafiles:    http://www.cs.princeton.edu/introcs/34nbody/2body.txt
 *                http://www.cs.princeton.edu/introcs/34nbody/3body.txt
 *                http://www.cs.princeton.edu/introcs/34nbody/4body.txt
 *                http://www.cs.princeton.edu/introcs/34nbody/2bodyTiny.txt
 *
 *  This data-driven program simulates motion in the universe defined
 *  by the standard input stream, increasing time at the rate on the
 *  command line.
 *
 *  %  java Universe 25000 4body.txt
 *
 *
 ******************************************************************************/

public class Universe {
    public  Body[] bodies;     // array of n bodies
    public double radius;
    public int numBodies;

    final double def_penRadius = 0.025;
    // read universe from standard input
    public Universe(String fname) {
        try {
            Scanner in = new Scanner(new FileReader(fname));
            numBodies = Integer.parseInt(in.next());
            // number of bodies
            this.numBodies = numBodies;
            System.out.println("n=" + numBodies);

            // the set scale for drawing on screen
            radius = Double.parseDouble(in.next());
            System.out.println("radius=" + radius);
            StdDraw.setXscale(-radius, +radius);
            StdDraw.setYscale(-radius, +radius);

            // read in the n bodies
            bodies = new Body[numBodies];
            for (int i = 0; i < numBodies; i++) {
                System.out.println("i=" + i);
                double rx = Double.parseDouble(in.next());
                double ry = Double.parseDouble(in.next());
                double vx = Double.parseDouble(in.next());
                double vy = Double.parseDouble(in.next());
                double mass = Double.parseDouble(in.next());
                double[] position = {rx, ry};
                double[] velocity = {vx, vy};
                Vector r = new Vector(position);
                Vector v = new Vector(velocity);
                bodies[i] = new Body(r, v, mass);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //if the user initialize only with numBodies argument, we won't need to read bodies info from a file
    //it'll be random, this constructo has to initialize them randomly
    public Universe(int numBodies) {
        //numBodies --> guardamos el número de cuerpos que tenemos
        this.numBodies = numBodies;
        // number of bodies
        System.out.println("n=" + numBodies);

        // the set scale for drawing on screen
        radius = numBodies;
        System.out.println("radius=" + radius);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        bodies = new Body[numBodies];
        for (int i=0; i<numBodies; i++)
        {
            System.out.println("i=" + i);
            double rx = (int) (Math.random() * (5.0E30 - 0.0E0 )) + 0.0E0;
            double ry = (int) (Math.random() * (5.0E30 - 0.0E0 )) + 0.0E0;
            double vx = (int) (Math.random() * (5.0E30 - 0.0E0 )) + 0.0E0;
            double vy = (int) (Math.random() * (5.0E30 - 0.0E0 )) + 0.0E0;
            double mass = (int) (Math.random() * (5.0E30 - 0.0E0 )) + 0.0E0;
            double[] position = {rx, ry};
            double[] velocity = {vx, vy};

            Vector r = new Vector(position);
            Vector v = new Vector(velocity);

            bodies[i] = new Body(r, v, mass);

        }
    }

    // increment time by dt units, assume forces are constant in given interval
    public void increaseTime(double dt) {

        // initialize the forces to zero
        Vector[] f = new Vector[numBodies];
        for (int i = 0; i < numBodies; i++) {
            f[i] = new Vector(new double[2]);
        }

        // compute the forces
        for (int i = 0; i < numBodies; i++) {
            for (int j = 0; j < numBodies; j++) {
                if (i != j) {
                    f[i] = f[i].plus(bodies[i].forceFrom(bodies[j]));
                }
            }
        }

        // move the bodies
        for (int i = 0; i < numBodies; i++) {
            bodies[i].move(f[i], dt);
        }
    }

    // draw the n bodies
    public void draw() {
        for (int i = 0; i < numBodies; i++) {
            StdDraw.setPenRadius(def_penRadius);
            StdDraw.point(bodies[i].getPosition().cartesian(0), bodies[i].getPosition().cartesian(1));
        }
    }

    // draw the n bodies with trace
    public void draw(double penRadius) {
        for (int i = 0; i < numBodies; i++) {
            StdDraw.setPenRadius(penRadius);
            StdDraw.point(bodies[i].getPosition().cartesian(0), bodies[i].getPosition().cartesian(1));
        }
    }
}
