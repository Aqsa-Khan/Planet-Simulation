/**
 * Keeps track of the array of planets of a universe object, the file name containing information for the planet,  radius of the universe, end time of the simulation, and time step of the simulation.
 * @author Aqsa Khan
 * @version March 8
 */
public class Universe
{
    /**
     * end time of simulation (in milliseconds)
     */
    private double endT;
    /**
     * time step of simulation (in milliseconds)
     */
    private double deltaT;
    /**
     * file name of file that contains information about planet objects
     */
    private String universeFile;
    /**
     * array of planet objects
     */
    private Planet[] planets;
    /**
     * radius of the universe objects
     */
    private double radius;
    /**
     * x position of planet object
     */
    private double posX;
    /**
     * y position of planet object
     */
    private double posY;
    /**
     * x velocity of planet object
     */
    private double velX;
    /**
     * y velocity of planet object
     */
    private double velY;
    /**
     * mass of planet object
     */
    private double pmass;
    /**
     * name of file containing image of planet object
     */
    private String filename;
    
    /**
     * Main method that creates a universe object
     */
    public static void main(String[] args){
        Universe u = new Universe(157788000.0, 25000.0, "planets.txt");
        u.readUniverse();
        u.displayUniverse();
        u.runSimulation();
    }
    
    /**
     * Constructor for universe object
     * 
     * @param endT_init Initial value of the end time of the simulation
     * @param deltaT_init Initial value of the time step of the simulation
     * @param universeFile_init Initial file name of the file containing information about the planet objects
     */
    public Universe(double endT_init, double deltaT_init, String universeFile_init){
       endT = endT_init;
       deltaT = deltaT_init;
       universeFile = universeFile_init;
    }
    
    /**
     * Runs simulation
     */
    public void runSimulation(){
        for(double t = 0; t<endT; t = t + deltaT){
            updatePhysics();
            displayUniverse();
        }
    }
    
    /**
     * Reads the text file that contains information about the universe and planet objects
     */
    public void readUniverse(){
        In in = new In(universeFile);
        int numPlanets = in.readInt();
        planets = new Planet[numPlanets];
        System.out.println(numPlanets);
        radius = in.readDouble();
        System.out.println(radius);
        for(int i = 0; i<planets.length; i++){
            posX = in.readDouble();
            posY = in.readDouble();
            velX = in.readDouble();
            velY = in.readDouble();
            pmass = in.readDouble();
            filename = in.readString();
            planets[i] = new Planet(posX,posY,velX,velY,pmass,filename);
        }
    }
    
    /**
     * Displays the initial frame of the simulation that includes the planet objects
     */
    public void displayUniverse(){
        StdDraw.setCanvasSize(600,600);
        //instead of pixels, scaling image to arbitrary numbers on x, y axis
        StdDraw.setXscale(-radius*2,radius*2);
        StdDraw.setYscale(-radius*2,radius*2);
        StdDraw.enableDoubleBuffering();
        for(int i = 0; i < endT; i += deltaT) {
            StdDraw.clear();
            StdDraw.picture(0, 0, "starfield.jpg", radius * 2, radius * 2);
            for(int j = 0; j < planets.length; j++) {
                Planet p = planets[j];
                updatePhysics();
                p.display();
            }
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
    
    /**
     * Goes through each planet object, calculates force on the object and updates the planet object's positions and velocities. 
     */
    public void updatePhysics(){
        double fx;
        double fy;
        for(int i = 0; i<planets.length; i++){
            Planet p = planets[i];
            fx = 0;
            fy = 0;
            for(int j = 0; j<planets.length; j++){
                if(i != j){
                    Planet other = planets[j];
                    double fx_other = p.calculateFx(other);
                    double fy_other = p.calculateFy(other);
                    fx += fx_other;
                    fy += fy_other;
                }
            }
            p.updatePosition(fx,fy,deltaT);
        } 
    }
}

