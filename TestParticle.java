/**
 * Class which tests a how a group of charged particles in a
 * electromagnetic field behave. From here the physical properties
 * of the particles and various simulation parameters can be varied.
 *
 * The current configuration of the program is a cyclotron, this
 * has a constant magnetic field and an oscillating electric field.
 * Hence the expected trajectory of the particle group is a outward
 * spiral.
 *
 * @author Tehmoor Hussain
 * @version 2.2
 */

import java.lang.Math;
import java.util.*;
import java.io.*;

 public class TestParticle{

   public static void main (String[] args) throws FileNotFoundException{

     /**
     * timeStep is the interval which is used to calculate the
     * acceleration of a particle. currentTime is time at that
     * particular instance starting from time t = 0. percentageField
     * is by what proportion the field is scaled by when it has
     * regional variation.
     *
     * algorithmChoice = 1 for Euler algorithm and algorithmChoice = 2
     * for Euler Cromer algorithm. noParticle determines the number of
     * particles in a beam. noOrbits determines how many orbits are
     * completed.
     */
     double particleMass = 1.67e-27;
     double particleCharge = 1.60e-19;
     double timeStep = 1.00e-4;
     double currentTime = 0.00;
     double percentageField = 1;
     int algorithmChoice = 1;
     int noParticle = 100;
     int noOrbits = 5;

     /**
     * fieldDivider determines below/above which x, y and z positions
     * the magnetic field changes. Currently only x - position
     * dependent changes implemented. initialSpread determines the
     * maximum and minumum possible values of spread that the particles
     * start with.
     */
     PhysicsVector fieldDivider = new PhysicsVector(0, 0, 0);
     PhysicsVector initialSpread = new PhysicsVector(0.01, 0, 0);

     /**
     * electricMagnitude determines the magnitude of the electric
     * field, electricDirection determines its direction, these
     * are then combined into electricField which is the total field.
     */
     double electricMagnitude = 1.00e-7;
   	 PhysicsVector electricDirection = new PhysicsVector(0, 1, 0);
     PhysicsVector electricField = PhysicsVector.scale(electricMagnitude,
     electricDirection);

     /**
     * magneticMagnitude determines the magnitude of the magnetic
     * field, magneticDirection determines its direction, these
     * are then combined into magneticField which is the total field.
     */
     double magneticMagnitude = 1e-7;
   	 PhysicsVector magneticDirection = new PhysicsVector(0, 0, 1);
     PhysicsVector magneticField = PhysicsVector.scale(magneticMagnitude,
     magneticDirection);

     /**
     * initialSpeed determines the magnitude of the initial velocity,
     * velocityDirection determines its direction, these are then
     * combined into initialVelocity which is the total velocity.
     */
     double initialSpeed = 0.10;
     PhysicsVector velocityDirection = new PhysicsVector(1, 0, 0);
     PhysicsVector initialVelocity = PhysicsVector.scale(initialSpeed,
     velocityDirection);

     /**
     * positionMagnitude determines the magnitude of the initial
     * position, positionDirection determines its direction, these
     * are then combined into initialPosition which is the total
     * position.
     */
     double positionMagnitude = 0;
     PhysicsVector positionDirection = new PhysicsVector(0, 0, 0);
   	 PhysicsVector initialPosition = PhysicsVector.scale(positionMagnitude,
     positionDirection);

     /**
     * radialMagnitude calculates the magnitude of radius at which
     * the particle is orbiting by : r = Mv/|B|q , this is an analytic
     * formulation. orbitTime calculates the time taken for an orbit
     * by T = 2piM/|B|q, which is also an analytic formulation.
     * The total time of the simulation is determined by the time
     * taken for these orbits to complete. This is not meant to be
     * exact, just an order of magnitude estimate for simulation time.
     */
     double radialMagnitude = (particleMass*initialSpeed)/
     (magneticMagnitude*particleCharge);
     double orbitTime = (2*Math.PI*particleMass)/
     (magneticMagnitude*particleCharge);
     double endTime = orbitTime*noOrbits;

     /**
     * writer determines which file is to be written to. A new
     * DynamicField is then created in which the protonBunch ie.
     * beam of protons will travel.
     */
     PrintWriter writer = new PrintWriter("DataOut.txt");
     DynamicField Field = new DynamicField(fieldDivider, electricField,
     magneticField, percentageField);
     ParticleBunch protonBunch = new ParticleBunch(particleMass,
     particleCharge, initialPosition, initialVelocity, noParticle,
     algorithmChoice);

     /**
     * The positions of the particles in the bunch are set randomly
     * in accordance with the defined spread. Also the initial electric
     * field value is saved.
     */
     protonBunch.randomisePositions(initialSpread);
     Field.initialiseElectric();

     /**
     * At each instant of time each particle in the bunch are moved
     * according to how much force they feel in the electromagnetic
     * field. This is repeated until the specified time has elapsed.
     * Currently an oscillating electric field is set up in conjunction
     * with a perpendicular magnetic field. At each time step the x
     * and y positions are written to file as the z position is not
     * expected to change.
     */
		 while(currentTime <= endTime){
       protonBunch.calculateAverage();
       writer.println(protonBunch.getAverage().returnSimple2DString());
		   currentTime+=timeStep;

       Field.oscillatingElectric(currentTime, orbitTime);
       Field.getElectric();
		   protonBunch.updateBunch(timeStep, Field);
		 }
     writer.close();
	 }
 }
