 /**
 * Class represents an electromagnetic field with some modifications.
 * This electromagnetic field differs from the static field becuase
 * it has the added properties of a position variant magnetic field.
 *
 * This is a magnetic field which is confined to a specfic region.
 * It also has the property of having a electric field which
 * oscillates in time.
 *
 * @author Tehmoor Hussain
 * @version 1.5
 */

import java.lang.Math;

public class DynamicField extends EMField{

  /**
  * fieldReduction contains a number from 0 to n which defines by
  * what proportion the magnetic field is reduced/increased. divider
  * determines the x, y and z positions at which there is a magnetic
  * field change. savedMagnetic and savedElectric save the magnetic
  * and electric fields so that continuous multiplication does not
  * occur during the loop.
  */
  protected double fieldReduction;
  protected PhysicsVector divider, savedMagnetic, savedElectric;

  /**
  * Default constructor. Sets data members to zero.
  */
  public DynamicField()
  {
		super();
    savedMagnetic = new PhysicsVector();
    savedElectric = new PhysicsVector();
		divider = new PhysicsVector();
    fieldReduction = 0;
	}

  /**
  * Constructor that takes four arguments to create a DynamicField.
  * The electric field, magnetic field, magnetic field range, and
  * reduction of the magnetic field are then charge are then set
  * from these arguments.
  *
  * @param dividerIn user determined magnetic field domain
  * @param electricIn user determined electric field
  * @param magneticIn user determined magnetic field
  * @param reductionIn user determined magnetic field reduction
  **/
  public DynamicField(PhysicsVector dividerIn, PhysicsVector electricIn,
  PhysicsVector magneticIn, double reductionIn)
  {
    super(electricIn, magneticIn);
    fieldReduction = reductionIn;
    divider = new PhysicsVector(dividerIn);
  }

  /**
  * Return the magnetic field range
  *
  * @return The magnetic field range
  */
  public PhysicsVector getDivider()
  {
    return new PhysicsVector(divider);
  }

  /**
  * Set the divider
  *
  * @param dividerIn The divider
  */
  public void setDivider(PhysicsVector dividerIn)
	{
    divider = new PhysicsVector(dividerIn);
	}

  /**
  * Saves the initial value of magnetic field for later use.
  */
  public void initialiseMagnetic()
  {
    savedMagnetic = new PhysicsVector(magnetic);
  }

  /**
  * In the region determined by the x, y or z value of the
  * divider the magnetic field is reduced.
  *
  * @param positionIn The position of the particle
  */
  public void reduceX(PhysicsVector positionIn)
  {
    magnetic = new PhysicsVector(savedMagnetic);
    if (positionIn.getX() > divider.getX())
    {
      magnetic.scale(fieldReduction);
    }
    if (positionIn.getX() < divider.getX())
    {
      magnetic = new PhysicsVector(savedMagnetic);
    }
  }

  /**
  * Saves the initial value of electric field for later use.
  */
  public void initialiseElectric()
  {
    savedElectric = new PhysicsVector(electric);
  }

  /**
  * Generates an oscillating electric field of the form:
  * E = |E|Sin(wt) where w = angular frequency of oscillation
  * of the particle. This is so that the particle encounters
  * the positive part of the sine wave and is accelerated.
  *
  * @param timeIn The current time
  * @param periodIn The time taken for one orbit
  */
  public void oscillatingElectric(double timeIn, double periodIn)
  {
    electric = new PhysicsVector(savedElectric);
    electric.scale(Math.sin(2*Math.PI*timeIn/periodIn));
  }

}
