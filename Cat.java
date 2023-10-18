import java.util.List;
import java.util.Random;
public class Cat extends Creature {
    private static final int jump = 2;
    private static final int maxStarvation = 50;
    private int sinceLastMeal;
    private int eatCounter;

    public Cat(int x, int y, City city, Random rnd) {
        super(x, y, city, rnd);
        this.sinceLastMeal = 0;
        this.lab = LAB_YELLOW;
        this.stepLen = jump;
        this.eatCounter = 0;
    }

    private void chaseMouse(Mouse mouse) {
        setDirection(mouse.getGridPoint());
        this.lab = LAB_CYAN;
        step();
        if (this.getGridPoint().equals(mouse.getGridPoint())) {
            mouse.dead = true;
            this.sinceLastMeal = 0;
            this.lab = LAB_YELLOW;
        }
    }

    private Mouse closestMouse(){
        double minDistance = Double.MAX_VALUE;
        Mouse closestMouse = null;

        List<Creature> creatures = city.getCreatures();
        for (Creature c : creatures) {
            if (c instanceof Mouse) {
                Mouse mouse = (Mouse) c;
                int distance = this.getGridPoint().dist(mouse.getGridPoint());
                if (distance <= 20 && distance < minDistance) {
                    minDistance = distance;
                    closestMouse = mouse;
                }
            }
        }
        return closestMouse;
    }
    private void setDirection(GridPoint targetPoint) {
        int dx = targetPoint.x - this.getGridPoint().x;
        int dy = targetPoint.y - this.getGridPoint().y;

        // Check if it's faster to wrap around to the target
        if (Math.abs(dx) > City.WIDTH / 2) {
            if (dx < 0) dx += City.WIDTH;
            else dx -= City.WIDTH;
        }
        if (Math.abs(dy) > City.HEIGHT / 2) {
            if (dy < 0) dy += City.HEIGHT;
            else dy -= City.HEIGHT;
        }

        // Set the direction based on the differences
        if (Math.abs(dy) > Math.abs(dx)) {
            if (dy > 0) setDir(SOUTH);
            else setDir(NORTH);
        } else {
            if (dx > 0) setDir(EAST);
            else setDir(WEST);
        }
    }

    public void step(){
        if(eatCounter == maxStarvation){
            this.dead = true;
            return;
        }
        eatCounter++;
        if (rand.nextInt(100) < 5)
            randomTurn();
        super.step();
    }

    @Override
    public void takeAction() {
        Mouse targetMouse = closestMouse();
        if (targetMouse != null) chaseMouse(targetMouse);
        else step();
        this.sinceLastMeal++;
    }
}
