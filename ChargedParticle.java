/**
 * Class designed to represent a charged particle. This class
 * has the properties of a particle but with the added
 * parameter of charge. It is also a test of the technnique
 * called inheritance, with the purpose of producing encapsulated
 * code.
 *
 * @author Tehmoor Hussain
 * @version 1.0
 */
public class ChargedParticle extends Particle{

  /**
	* Define the particle charge.
	**/
  protected double charge;

  /**
	* Default constructor that initialises all charged particle properties
  * to zero.
	**/
  public ChargedParticle()
  {
		super();
		charge=0;
	}

  /**
  * Constructor that takes two arguments to create a ChargedParticle.
  * The mass and charge are then set from these two arguments.
  *
  * @param massIn user determined mass of particle
  * @param chargeIn user determined charge of particle
  **/
  public ChargedParticle(double massIn, double chargeIn)
  {
    super(massIn);
    charge = chargeIn;
  }

  /**
  * Constructor that takes four arguments to create a ChargedParticle.
  * The mass, charge, velocity and position are then set from these
  * arguments.
  *
  * @param massIn user determined mass of particle
  * @param chargeIn user determined charge of particle
  * @param positionIn user determined position of particle
  * @param velocityIn user determined velocity of particle
  **/
  public ChargedParticle(double massIn, double chargeIn,
  PhysicsVector positionIn, PhysicsVector velocityIn)
  {
    super(massIn, positionIn, velocityIn);
    charge = chargeIn;
  }

  /**
	* Return the charge.
	* @return charge
	**/
  public double getCharge()
  {
    return(charge);
  }

  /**
  * Set the charge.
  *
  * @param chargeIn user defined charge.
  **/
  public void setCharge(double chargeIn)
	{
		charge=chargeIn;
	}

}
