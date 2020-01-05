/**
 * Class represents a particle. This particle has properties
 * velocity, mass, position and acceleration. It also has
 * two numerical algorithms by which it is moved; Euler
 * and Euler Cromer.
 *
 * @author Tehmoor Hussain
 * @version 1.2
 */
public class Particle{

	/**
	* Define the mass of the particle, the vector properties of
	* a particle ie. its position, velocity and acceleration.
	*/
	protected double mass;
	protected PhysicsVector position, velocity, acceleration;

	/**
	* The Default Constructor. Sets everything to zero.
	*/
	public Particle(){
		mass = 0;
		position = new PhysicsVector();
		velocity = new PhysicsVector();
		acceleration = new PhysicsVector();
	}

	/**
	* Constructor with one input, the mass of the particle.
	* Set everything else to zero.
	*
	* @param massIn mass of the particle
	*/
	public Particle(double massIn){
		mass = massIn;
		position = new PhysicsVector();
		velocity = new PhysicsVector();
		acceleration = new PhysicsVector();
	}

	/**
	*  Constructor that sets mass, position and velocity
	*  @param massIn mass of the particle
	*  @param positionIn initial position of particle
	*  @param velocityIn initial velocity of particle
	*/
	public Particle(double massIn, PhysicsVector positionIn,
	PhysicsVector velocityIn)
	{
		mass = massIn;
		position = new PhysicsVector(positionIn);
		velocity = new PhysicsVector(velocityIn);
		acceleration = new PhysicsVector();
	}

	/**
	* Return the position
	*
	* @return position
	*/
	public PhysicsVector getPosition()
	{
		return new PhysicsVector(position);
	}

	/**
	* Return the velocity
	*
	* @return velocity
	*/
	public PhysicsVector getVelocity()
	{
		return new PhysicsVector(velocity);
	}

	/**
	* Return the acceleration
	*
	* @return acceleration
	*/
	public PhysicsVector getAcceleration()
	{
		return new PhysicsVector(acceleration);
	}

	/**
	* Return the mass
	*
	* @return mass
	*/
	public double getMass()
	{
		return mass;
	}

	/**
	* Set the mass
	*
	* @param massIn The new mass
	*/
	public void setMass(double massIn)
	{
		mass=massIn;
	}

	/**
	* Set the position
	*
	* @param positionIn The new position
	*/
	public void setPosition(PhysicsVector positionIn)
	{
		position = new PhysicsVector(positionIn);
	}

	/**
	* Set the velocity
	*
	* @param veclocityIn The new velocity
	*/
	public void setVelocity(PhysicsVector velocityIn)
	{
		velocity = new PhysicsVector(velocityIn);
	}

	/**
	* Set the acceleration
	*
	* @param accelerationIn The new acceleration
	*/
	public void setAcceleration(PhysicsVector accelerationIn)
	{
		acceleration= new PhysicsVector(accelerationIn);
	}

	/**
	* Implement the Euler algorithm which operates as follows:
	* r_(n+1) = r_(0) + v_(n)*t
	* v_(n+1) = v_(0) + a_(n)*t
	*
	* Where n is the current value index, 0 is the initial
	* index, t is the time interval, r is the position vector
	* and v is the velocity.
	*
	* @param deltaTime The time interval
	* @param accelerationIn The current acceleration
	*/
	public void euler(double deltaTime, PhysicsVector accelerationIn)
	{
		acceleration = new PhysicsVector(accelerationIn);
		position.increaseBy(PhysicsVector.scale(deltaTime, velocity));
		velocity.increaseBy(PhysicsVector.scale(deltaTime, acceleration));
	}

	/**
	* Implement the Euler Cromer algorithm which operates as follows:
	* v_(n+1) = v_(0) + a_(n)*t
	* r_(n+1) = r_(0) + v_(n)*t
	*
	* Where n is the current value index, 0 is the initial
	* index, t is the time interval, r is the position vector
	* and v is the velocity.
	*
	* @param deltaTime The time interval
	* @param accelerationIn The current acceleration
	*/
	public void eulerCromer(double deltaTime, PhysicsVector accelerationIn){
		acceleration = new PhysicsVector(accelerationIn);
		velocity.increaseBy(PhysicsVector.scale(deltaTime, acceleration));
		position.increaseBy(PhysicsVector.scale(deltaTime, velocity));
	}

	/**
	* Create a string containing the mass, position, velocity,
	* and acceleration of the particle. This method is called
	* automatically by System.out.println(someparticle)
	*
	* @return string with the format: " mass " + mass + " Position: "
	* + position + " Velocity: " + velocity + "Acceleration: " +
	* acceleration
	*/
	public String toString()
	{
		return " mass " + mass + " Position: " + position.returnSimpleString() +
		" Velocity: " + velocity.returnSimpleString() + " Acceleration: " +
		acceleration.returnSimpleString();
	}
}
