import java.util.Random;
public class Mouse extends Creature {
    private int age;

    public Mouse(int x, int y, City city, Random rand) {
        super(x, y, city, rand);
        age = 0;
        lab = LAB_BLUE;

    }

    @Override
    public void step() {
        // A mouse dies after 22 steps
        if (age >= 22) {
            dead = true;
            return;
        }
        if(rand.nextInt(10)<2) randomTurn();
        super.step();
    }

    @Override
    public void takeAction() {
        // Check if it's time to produce a baby mouse
        age++;
        if (age == 20) {
            city.birthMouse(getX(), getY());
        }
    }
}