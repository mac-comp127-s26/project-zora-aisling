# Snake Game

## Group Members
Aisling Li, Zora Shi

## Brief Description
Snake Game is a simple grid-based arcade game where the player controls a snake that moves continuously across the screen. The goal is to collect food items to grow longer while avoiding collisions with the walls. The game ends when the snake crashes, making it a balance between quick reactions and strategic movement.

## Game Overview / UI Concept
The game takes place in a 400 by 400 pixel window, structured as a grid so that all movement is aligned and predictable. The snake is represented as a series of connected green squares, starting from the center of the screen. Food appears as a red square at random positions on the grid.

The player uses the arrow keys to control the direction of the snake. The snake moves continuously, and each key press changes the direction of the next movement step. When the snake eats food, it grows longer and a new piece of food appears somewhere else on the screen.

The interface is intentionally minimal. There are no menus or complex UI elements, just the game window, the snake, the food, and a “GAME OVER” message that appears when the snake hits the wall.

## Why This Project Is Interesting
This project is interesting because it takes a very simple set of rules and turns it into an engaging and interactive system. Even though the mechanics are straightforward, the game requires careful coordination between input handling, animation, and collision detection.

From a programming perspective, it demonstrates how dynamic data structures like ArrayLists can represent changing objects such as a growing snake. It also highlights how real-time interaction works through event-driven programming and animation loops.

Additionally, the project is flexible and can be extended in many ways, such as adding self-collision detection, scoring systems, or more advanced movement mechanics.

## Minimum Viable Product (MVP)
The MVP of this project includes the core functionality needed for a playable Snake game:

- A snake that moves automatically in a grid-based system
- Keyboard controls to change the snake’s direction
- Food that appears at random positions on the grid
- The ability for the snake to grow when it eats food
- Collision detection with the walls
- A game over state that stops the game and displays a message

These features ensure that the game is fully functional and captures the essential gameplay experience, even without additional enhancements.
