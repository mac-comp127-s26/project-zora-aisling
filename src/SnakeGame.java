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
    private int obstacleTimer = 0; 

    public SnakeGame() {
        canvas = new CanvasWindow("Snake", 400, 400);

        snake = new Snake(canvas);
        food = new Food(canvas);

        // UI
        scoreText = new GraphicsText("Score: 0");
        scoreText.setPosition(10, 20);
        canvas.add(scoreText);

        levelText = new GraphicsText("Level: 1");
        levelText.setPosition(300, 20);
        canvas.add(levelText);

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
            obstacleTimer++; // ⭐ 每帧增加

            if (frameCount % speed == 0) {

                snake.move();

                // 吃 food
                if (snake.isColliding(food.getShape())) {
                    snake.grow();
                    food.spawn(canvas);
                    score++;
                }

                updateLevel();
                updateUI();

                if (snake.hitWall()) {
                    endGame("GAME OVER");
                }

                for (Obstacle ob : obstacles) {
                    if (snake.isColliding(ob.getShape())) {
                        endGame("GAME OVER");
                    }
                }

                if (level == 3 && obstacleTimer % 25 == 0) {
                    resetObstacles();
                }

                if (score >= 15) {
                    endGame("YOU WIN!");
                }
            }
        });
    }

    private void updateUI() {
        scoreText.setText("Score: " + score);
        levelText.setText("Level: " + level);
    }

    private void updateLevel() {
        if (score >= 5 && level < 2) {
            level = 2;
            speed = 10;
            showLevelUp("LEVEL 2!");
        }

        if (score >= 10 && level < 3) {
            level = 3;
            speed = 7;
            showLevelUp("LEVEL 3!");

            if (obstacles.isEmpty()) {
                obstacles.add(new Obstacle(canvas));
                obstacles.add(new Obstacle(canvas));
            }
        }
    }

    private void resetObstacles() {
        for (Obstacle ob : obstacles) {
            canvas.remove(ob.getShape());
        }

        obstacles.clear();

        obstacles.add(new Obstacle(canvas));
        obstacles.add(new Obstacle(canvas));
    }

    private void showLevelUp(String textStr) {
        GraphicsText text = new GraphicsText(textStr);
        text.setFontSize(30);
        text.setFillColor(Color.BLUE);
        text.setPosition(120, 200);
        canvas.add(text);

        canvas.pause(1000);
        canvas.remove(text);
    }

    private void endGame(String message) {
        gameOver = true;

        GraphicsText text = new GraphicsText(message);
        text.setPosition(120, 200);
        text.setFontSize(30);
        text.setFillColor(Color.RED);
        canvas.add(text);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}