/**
 * Planet position, x and y coordinates, file name and mass of planet are kept here
 * 
 * @author Aqsa Khan
 * @version March 8
 */

public class Planet
{
    //x position of the planet
    private double positionX;
    //y position of the planet
    private double positionY;
    //x velocity of the planet
    private double velocityX;
    //y velocity of the planet
    private double velocityY;
    //mass of the planet
    private double mass;
    //file name for the picture of the planet
    private String planetFile;
    
    public Planet(double px, double py, double vx, double vy, double m, String file){
        /**
         * This is the constructor for Planet object
         * @param px Initial value of x position of the object
         * @param py Initial value of y position of the object
         * @param vx Initial value of x velocity of the object
         * @param vy Initial value of y velocity of the object
         * @param m Initial value of the mass of the object
         * @param file Initial name of file for image representing object
         */
        positionX = px;
        positionY = py;
        velocityX = vx;
        velocityY = vy;
        mass = m;
        planetFile = file;
    }
    
    /**
     * Displays object on the screen
     */
    public void display(){
        StdDraw.picture(positionX, positionY, planetFile);
    }
    
    /**
     * Updates the object's position and velocity as well as caluclates acceleration of the object using the forces in the x and y and a time step.
     * @param fx Initial force in the x direction of an object 
     * @param fy Initial force in the y direction of an object
     * @param dt Initial time step
     */
    public void updatePosition(double fx, double fy, double dt){
        double accelerationX = fx/mass;
        double accelerationY = fy/mass;
        velocityX = velocityX + (dt*accelerationX);
        velocityY = velocityY + (dt*accelerationY);
        positionX = positionX + (velocityX*dt);
        positionY = positionY + (velocityY*dt);
    }
    
    /**
     * Calulates the force in the x position that is acting on one planet by another planet. 
     * @return Calculated force in the x position on one planet
     */
    public double calculateFx(Planet other){
        double deltaX = other.positionX-positionX;
        double deltaY = other.positionY-positionY;
        double massTwo = other.mass * mass;
        double r = Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
        double f = (6.67E-11*massTwo)/(r*r);
        double fx = f*(deltaX/r);
        return fx;
    }
    
    /**
     * Calculates the force in the y position that is acting on one planet by another planet.
     * @return Calculated force in the y position on one planet
     */
    public double calculateFy(Planet other){
        double deltaX = other.positionX - positionX;
        double deltaY = other.positionY - positionY;
        double massTwo = other.mass*mass;
        double r = Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
        double f = (6.67E-11*massTwo)/(r*r);
        double fy = f*(deltaY/r);
        return fy;
    }
}
