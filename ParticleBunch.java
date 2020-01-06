 /**
 * Class which represents a bunch of particles. Each of the particles
 * has the properties of a charged particle. The initial positions
 * of each can be randomised in x, y and z. This is so as to resemble
 * a beam of particles.
 *
 * @author Tehmoor Hussain
 * @version 1.8
 */

import java.lang.Math;

public class ParticleBunch extends ChargedParticle{

  /**
	* numberParticles defines the number of particles in a bunch.
	* choice determines which algorithm is used to calculate the
	* position of a proton. averagePosition gives the average position
	* of particles within the bunch. newPosition is created to hold
	* position so that the value of the original position isn't
	* modified. randomSpread contains the randomised spread.
	* ChargedParticles is an array of charged particle objects which
	* is meant to represent a bunch of particles.
	*/
	protected int numberParticles, choice;
	protected PhysicsVector averagePosition, newPosition, randomSpread;
	protected ChargedParticle[] ChargedParticles;

	/**
  * Default constructor. Sets data members to zero.
  */
	public ParticleBunch(){
		numberParticles = 0;
		averagePosition = new PhysicsVector();
		newPosition = new PhysicsVector();
		randomSpread = new PhysicsVector();
	}

	/**
  * Constructor that takes six arguments to create a ParticleBunch.
  * The mass, charge, position, velocity, number of particles and
	* algorithm choice are are then set from these arguments. The
	* array length is set to the number of particles. The array is
	* then populated with the number of particles of entries.
  *
  * @param massIn user determined mass
  * @param chargeIn user determined charge
  * @param postionIn user determined position
  * @param velocityIn user determined velocity
	* @param numberIn user determined number of particles
	* @param choiceIn user determined algorithm choice
  **/
	public ParticleBunch(double massIn, double chargeIn,
	PhysicsVector positionIn, PhysicsVector velocityIn, int numberIn,
	int choiceIn){
		super(massIn, chargeIn, positionIn, velocityIn);
		numberParticles = numberIn;
		choice = choiceIn;
		ChargedParticles = new ChargedParticle[numberParticles];
		for (int i = 0; i < numberParticles; i++){
			ChargedParticles[i] = new ChargedParticle(massIn, chargeIn, positionIn,
			velocityIn);
		}
	}

	/**
  * Set the average position
  *
  * @param averageIn The average position
  */
	public void setAverage(PhysicsVector averageIn){
		averagePosition =  new PhysicsVector(averageIn);
	}

	/**
  * Return the average position
  *
  * @return The average position
  */
	public PhysicsVector getAverage(){
		return new PhysicsVector(averagePosition);
	}

	/**
  * A different spread value from +Spread -> -Spread is
	* generated for each particle in the bunch. This is then
	* applied to the position of each particle.
  *
  * @param spreadIn The spread of particles
  */
	public void randomisePositions(PhysicsVector spreadIn){
		for(int i = 0; i < numberParticles; i++){
       randomSpread = new PhysicsVector(spreadIn);
			 randomSpread.scale(2*Math.random()-1);
			 newPosition = new PhysicsVector(position);
			 newPosition.increaseBy(randomSpread);
			 ChargedParticles[i].setPosition(newPosition);
		}
	}

	/**
  * According to the algorithm chosen each particle in the bunch is
	* moved due the force exerted on it by the electromagnetic field.
	* The force produces an acceleration which causes a velocity and
	* position change of each particle. The first choice is the euler
	* algorithm and the second choice is the Euler Cromer algorithm.
  *
  * @param deltaTime The time interval used in the simulation
	* @param fieldIn The user defined EM field
  */
	public void updateBunch(double deltaTime, DynamicField fieldIn){
		for (int i = 0; i < numberParticles; i++){
			 fieldIn.calculateAcceleration(ChargedParticles[i]);
			 if (choice == 1){
				 ChargedParticles[i].euler(deltaTime, fieldIn.getAcceleration());
			 }
			 else if (choice == 2){
				 ChargedParticles[i].eulerCromer(deltaTime, fieldIn.getAcceleration());
			 }
		}
	}

	/**
	* The average position of a particle in the bunch is calculated.
	* This is done by adding all the values of position at that instance
	* of time and dividing by the total number of particles in the bunch.
	*/
	public void calculateAverage(){
		averagePosition = new PhysicsVector(ChargedParticles[0].getPosition());
		for (int i = 1; i < numberParticles; i++){
			averagePosition.increaseBy(ChargedParticles[i].getPosition());
		}
		averagePosition.scale(1.00/numberParticles);
	}

}
