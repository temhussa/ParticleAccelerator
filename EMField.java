/**
 * Class represents an electromagnetic field. There is a constant
 * electric and magnetic field which comprise it. It also has
 * the ability of calculate the acceleration of particles
 * within it.
 *
 * @author Tehmoor Hussain
 * @version 1.2
 */
public class EMField{

  /**
  Define the vectors which comprise the electromagnetic field,
  electric and magnetic. Also the vector which determines the
  effects of this on a charged particle, acceleration.
  */
 	protected PhysicsVector electric, magnetic, acceleration;

  /**
  * Default constructor. Sets data members to zero.
  */
  public EMField()
  {
    electric = new PhysicsVector();
    magnetic = new PhysicsVector();
  }

 	/**
  * Constructor which sets two arguments to the the electric
  * field strength and magnetic flux density.
  *
  * @param electricIn The electric field strength
  * @param magneticIn The magnetic flux density
  */
  public EMField(PhysicsVector electricIn, PhysicsVector magneticIn)
  {
    electric = new PhysicsVector(electricIn);
    magnetic = new PhysicsVector(magneticIn);
  }

  /**
  * Set the electric field strength
  *
  * @param electricIn The electric field strength
  */
  public void setElectric(PhysicsVector electricIn)
  {
    electric = new PhysicsVector(electricIn);
  }

  /**
  * Set the magnetic flux density
  *
  * @param magneticIn The magnetic flux density
  */
  public void setMagnetic(PhysicsVector magneticIn)
  {
    magnetic = new PhysicsVector(magneticIn);
  }

  /**
  * Set the acceleration
  *
  * @param accelerationIn The current value of acceleration
  */
  public void setAcceleration(PhysicsVector accelerationIn)
  {
    acceleration = new PhysicsVector(accelerationIn);
  }

  /**
  * Return the electric field strength
  *
  * @return The current value of the electric field strength
  */
  public PhysicsVector getElectric()
  {
    return new PhysicsVector(electric);
  }

  /**
  * Return the magnetic flux density
  *
  * @return The current value of the magnetic flux density
  */
  public PhysicsVector getMagnetic()
  {
    return new PhysicsVector(magnetic);
  }

  /**
  * Return the acceleration
  *
  * @return The current value of the acceleration
  */
  public PhysicsVector getAcceleration()
  {
    return new PhysicsVector(acceleration);
  }

  /**
  * Calculates the acceleration experienced by a charged particle
  * according to the Lorentz force law (non-relativistic):
  * a = (q/m)*(E + vxB)
  *
  * @param theParticle - the charged particle moving in the field
  */
  public void calculateAcceleration(ChargedParticle particleIn)
  {
    acceleration = new PhysicsVector(electric);
    acceleration.increaseBy(PhysicsVector.cross(particleIn.getVelocity(),
    magnetic));
    acceleration.scale(particleIn.getCharge()/particleIn.getMass());
  }
}
