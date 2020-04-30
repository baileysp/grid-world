import java.util.PriorityQueue;
import java.util.*;

class Search {

  public boolean searching;
  private PriorityQueue<Cell> open = new PriorityQueue<Cell>();
  private LinkedList<Cell> closed = new LinkedList<Cell>();
  private Grid grid;

  public Search(Grid grid) {
    this.grid = grid;
    searching = false;
  }

  public void startA() {
    System.out.println("Goal: " + grid.getGoal());
    this.searching = true;
    open.add(grid.getStart());
  }

  public void stepA() {
    if (open.isEmpty()) {
      searching = false;
      System.out.println("no solution");
    } else {
      Cell currentCell = open.poll();

      if (grid.isGoalCell(currentCell)) {
        System.out.println("GOAL \n" + currentCell);
        while (currentCell != grid.getStart()) {
          currentCell.setSolution();
          currentCell = currentCell.getPrevious();
        }
        currentCell.setSolution();
        searching = false;
      }
      if (!closed.contains(currentCell)) {

        closed.add(currentCell);
        currentCell.setClosed();
        System.out.println("Adding to closed list:\n" + currentCell);

        ArrayList<Cell> successors = grid.successorCells(currentCell);
        for (int i = 0; i < successors.size(); i++) {
          Cell successor = successors.get(i);
          if (closed.contains(successor)) continue;
          if (!open.contains(successor)) {
            successor.setPrevious(currentCell);
            if (successor.isDiagonal()) {
              successor.setgCost(currentCell.getgCost() + 14);
              successor.setDiagonal(false);
            } else {
              successor.setgCost(currentCell.getgCost() + 10);
            }
            successor.sethCost(euclidian(successor));
            System.out.println("Adding cell to open list: " + successor);
            open.add(successor);
            successor.setOpen();
          } else {
            if (successor.isDiagonal() && successor.getgCost() > currentCell.getgCost() + 14) {
              successor.setPrevious(currentCell);
              successor.setgCost(currentCell.getgCost() + 14);
              System.out.println("NEW Diagonal" + successor);

            } else if (!successor.isDiagonal() && successor.getgCost() > currentCell.getgCost() + 10) {
              successor.setPrevious(currentCell);
              successor.setgCost(currentCell.getgCost() + 10);
              System.out.println("NEW Cardinal" + successor);
            }

            // System.out.println("Updating cell: OLD " + successor);
            // successor.setPrevious(currentCell);
            // successor.setgCost(currentCell.getgCost() + 1);
            // System.out.println("NEW " + successor);
          }
        }
      }
    }
  }

  private double taxiCabToGoal(Cell cell) {
    Cell goal = grid.getGoal();
    return 10 *Math.abs(cell.getRow() - goal.getRow()) + Math.abs(cell.getCol() - goal.getCol());
  }

  private double euclidian(Cell cell) {
    Cell goal = grid.getGoal();
    // System.out.println(goal);
    double distance = Math
        .sqrt(Math.pow(cell.getRow() - goal.getRow(), 2.0) + Math.pow(cell.getCol() - goal.getCol(), 2.0));
    // System.out.println(distance);
    return distance * 10;
  }

}
