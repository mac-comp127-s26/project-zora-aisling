import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.Random;

public class Obstacle {
    private Rectangle shape;

    public Obstacle(CanvasWindow canvas) {
        Random rand = new Random();

        double x = rand.nextInt(20) * 20;
        double y = rand.nextInt(20) * 20;

        shape = new Rectangle(x, y, 20, 20);
        shape.setFillColor(new Color(139, 69, 19)); 
        canvas.add(shape);
    }

    public Rectangle getShape() {
        return shape;
    }
}