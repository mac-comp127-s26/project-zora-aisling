import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;

import java.awt.Color;

public class SnakeGame2 {
    private CanvasWindow canvas;
    private Snake snake;
    private Food food;
    private boolean gameOver = false;

    public SnakeGame2() {
        canvas = new CanvasWindow("Snake", 400, 400);

        snake = new Snake(canvas);
        food = new Food(canvas);

        setupControls();
        runGame();
    }

    private void setupControls() {
        canvas.onKeyDown(e -> {
            if (e.getKey() == Key.UP_ARROW) {
            snake.setDirection(Direction.UP);
        } else if (e.getKey() == Key.DOWN_ARROW) {
        snake.setDirection(Direction.DOWN);
        } else if (e.getKey() == Key.LEFT_ARROW) {
    snake.setDirection(Direction.LEFT);
        } else if (e.getKey() == Key.RIGHT_ARROW) {
        snake.setDirection(Direction.RIGHT);
        }
        });
    }

    private void runGame() {
        canvas.animate(() -> {
            if (gameOver) return;

            snake.move();

            if (snake.isColliding(food.getShape())) {
                snake.grow();
                food.spawn(canvas);
            }

            if (snake.hitWall()) {
                gameOver = true;

                GraphicsText text = new GraphicsText("GAME OVER");
                text.setPosition(120, 200);
                text.setFontSize(30);
                text.setFillColor(Color.RED);
                canvas.add(text);
            }
        });
    }

    public static void main(String[] args) {
        new SnakeGame2();
    }
}
