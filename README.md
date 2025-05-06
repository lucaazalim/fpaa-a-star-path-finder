# Pathfinding in a 2D Maze with A* Algorithm

This project was developed as an assignment for the Foundations of Algorithm Design and Analysis course at PUC Minas University by:

- Fernando Ibrahim ([@FernandoIbrahim](https://github.com/FernandoIbrahim))
- Jhonata Dias ([@jhonatasdias](https://github.com/jhonatasdias))
- Pedro Braga ([@bragap](https://github.com/bragap))
- Luca Azalim ([@lucaazalim](https://github.com/lucaazalim))

## Project Description

This project implements a pathfinding solution for a 2D maze using the [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm). The application reads a maze
represented as a grid, where each cell has a specific cost or represents an obstacle. The goal is to find the shortest
path from a start point (`S`) to an end point (`E`) while considering the movement costs and obstacles.

## Problem Introduction

The problem addressed in this project is finding the optimal path in a 2D maze. The maze is represented as a grid where:

- `S` marks the starting point.
- `E` marks the ending point.
- `0`, `1`, ..., `8` represent the cost of traversing a cell.
- `9` represents an obstacle that cannot be traversed.

The solution uses the A* algorithm, which is a popular pathfinding algorithm that combines the cost of the path
traversed so far and a heuristic to estimate the remaining cost to the goal.

## How the A* Algorithm Works

The A* algorithm is a graph traversal and pathfinding algorithm that uses the following components:

1. **Cost from Start (g):** The actual cost of the path from the start node to the current node.
2. **Heuristic Cost to Goal (h):** An estimate of the cost to reach the goal from the current node. This implementation
   uses the Manhattan distance as the heuristic.
3. **Total Estimated Cost (f):** The sum of `g` and `h` (`f = g + h`).

The algorithm maintains two sets of nodes:

- **Open Set:** Nodes to be evaluated.
- **Closed Set:** Nodes already evaluated.

Steps of the algorithm:

1. Start with the initial node (`S`) and add it to the open set.
2. While the open set is not empty:
    - Select the node with the lowest `f` value.
    - If the node is the goal (`E`), reconstruct and return the path.
    - Otherwise, move the node to the closed set and evaluate its neighbors.
    - For each neighbor:
        - Skip if it is an obstacle or already in the closed set.
        - Calculate the tentative `g` value.
        - If the neighbor is not in the open set or the tentative `g` is better, update the neighbor's values and add it
          to the open set.
3. If the open set is empty and the goal is not reached, return that no path exists.

## Project Setup and Execution

### Prerequisites

- Java 17 or higher
- Maven

### Steps to Run the Project

1. Clone the repository or download the source code.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   java -cp target/pathfinder-1.0-SNAPSHOT.jar br.pucminas.fpaa.pathfinder.Main
   ```

### Running Tests

To run the tests:

```
mvn test
```

## Example

Given the following maze:

```
S 0 9 0 0 0 0 9 0 0
9 1 9 1 1 9 0 9 9 0
0 1 0 0 1 0 0 1 9 0
0 9 0 9 9 9 1 9 0 0
0 2 0 1 0 0 1 1 1 0
9 9 9 1 9 1 9 9 9 0
0 0 1 1 0 1 0 0 0 0
0 1 9 9 9 1 9 9 1 0
0 0 0 1 0 1 0 1 1 0
0 9 0 1 0 0 0 0 9 E
```

The application finds the shortest path and outputs the maze with the path marked as `*`.

```
S * 9 0 0 0 0 9 0 0
9 * 9 1 1 9 0 9 9 0
0 1 * 0 1 0 0 1 9 0
0 9 * 9 9 9 1 9 0 0
0 2 * 1 0 0 1 1 1 0
9 9 9 * 9 1 9 9 9 0
0 0 1 1 * 1 0 0 0 0
0 1 9 9 9 * 9 9 1 0
0 0 0 1 0 1 * 1 * 0
0 9 0 1 0 0 0 * 9 E
```

More examples are available in the [tests file](/src/test/java/br/pucminas/fpaa/MazeTest.java).