import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;

import java.awt.Color;
import java.util.ArrayList;

public class SnakeGame {
    private CanvasWindow canvas;
    private Snake snake;
    private Food food;
    private boolean gameOver = false;
    private int frameCount = 0;
    private int speed = 15; 
    private int level = 1;
    private int score = 0; 
    private GraphicsText scoreText;
    private GraphicsText levelText;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    public SnakeGame() {
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

            frameCount++;

            if (frameCount % speed == 0) {

                snake.move();

                if (snake.isColliding(food.getShape())) {
                    snake.grow();
                    food.spawn(canvas);
                    score++; 
                }

                updateLevel();

                if (snake.hitWall()) {
                    endGame();
                }

                for (Obstacle ob : obstacles) {
                    if (snake.isColliding(ob.getShape())) {
                        endGame();
                    }
                }
            }
        });
    }

    private void endGame() {
        gameOver = true;

        GraphicsText text = new GraphicsText("GAME OVER");
        text.setPosition(120, 200);
        text.setFontSize(30);
        text.setFillColor(Color.RED);
        canvas.add(text);
    }

    private void updateLevel() {
        if (score >= 5 && level < 2) {
            level = 2;
            speed = 8;
        }

        if (score >= 10 && level < 3) {
            level = 3;
            speed = 5;

            if (obstacles.isEmpty()) {
                obstacles.add(new Obstacle(canvas));
                obstacles.add(new Obstacle(canvas));
            }
        }
    }

    

    public static void main(String[] args) {
        new SnakeGame();
    }
}