import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.Random;

public class Food {
    private Rectangle shape;
    private Random rand = new Random();

    public Food(CanvasWindow canvas) {
        spawn(canvas);
    }

    public void spawn(CanvasWindow canvas) {
        if (shape != null) {
            canvas.remove(shape);
        }

        double x = rand.nextInt(20) * 20;
        double y = rand.nextInt(20) * 20;

        shape = new Rectangle(x, y, 20, 20);
        shape.setFillColor(Color.RED);
        canvas.add(shape);
    }

    public Rectangle getShape() {
        return shape;
    }
}
