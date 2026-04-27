import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.*;

public class Snake {
    private ArrayList<Rectangle> body;
    private Direction direction;
    private CanvasWindow canvas;

    public Snake(CanvasWindow canvas) {
        this.canvas = canvas;
        body = new ArrayList<>();
        direction = Direction.RIGHT;

        Rectangle head = new Rectangle(200, 200, 20, 20);
        head.setFillColor(Color.GREEN);
        body.add(head);
        canvas.add(head);
    }

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public void move() {
        Rectangle head = body.get(body.size() - 1);

        double x = head.getX();
        double y = head.getY();

        switch (direction) {
            case UP: y -= 20; break;
            case DOWN: y += 20; break;
            case LEFT: x -= 20; break;
            case RIGHT: x += 20; break;
        }

        Rectangle newHead = new Rectangle(x, y, 20, 20);
        newHead.setFillColor(Color.GREEN);
        body.add(newHead);
        canvas.add(newHead);

        // remove tail
        Rectangle tail = body.remove(0);
        canvas.remove(tail);
    }

    public void grow() {
        Rectangle tail = body.get(0);

        Rectangle newPart = new Rectangle(tail.getX(), tail.getY(), 20, 20);
        newPart.setFillColor(Color.GREEN);
        body.add(0, newPart);
        canvas.add(newPart);
    }

    public boolean isColliding(GraphicsObject other) {
        Rectangle head = body.get(body.size() - 1);

        return head.getX() < other.getX() + other.getWidth() &&
               head.getX() + head.getWidth() > other.getX() &&
               head.getY() < other.getY() + other.getHeight() &&
               head.getY() + head.getHeight() > other.getY();
    }

    public boolean hitWall() {
        Rectangle head = body.get(body.size() - 1);

        return head.getX() < 0 || head.getX() >= 400 ||
               head.getY() < 0 || head.getY() >= 400;
    }
}