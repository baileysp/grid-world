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
        // while (currentCell != grid.getStart()) {
        //   //System.out.println("creating solution");
        //   //System.out.println("row: " + currentCell.getRow() + ", col: " + currentCell.getCol());
        //   //currentCell.setSolution();
        //   currentCell = currentCell.getPrevious();

        // }
        searching = false;
      }
      if (!closed.contains(currentCell)) {

        closed.add(currentCell);
        currentCell.setClosed();

        ArrayList<Cell> successors = grid.successorCells(currentCell);

        for (int i = 0; i < successors.size(); i++) {
          Cell successor = successors.get(i);
          
          if (!open.contains(successor)) {
            successor.setPrevious(currentCell);
            // TODO: Change this to make diagonal more expensive
            successor.setgCost(currentCell.getgCost() + 1);
            // TODO: Straight line distance makes more sense if using diagonals
            successor.sethCost(taxiCabToGoal(successor));
            System.out.println("Adding cell to open list: " + successor);
            open.add(successor);
            successor.setOpen();
          } else if (successor.getgCost() > currentCell.getgCost() + 1) {
            System.out.println("Updating cell: OLD " + successor);
            successor.setPrevious(currentCell);
            successor.setgCost(currentCell.getgCost() + 1);
            System.out.println("NEW " + successor);
          }
        }
      }
    }
  }

  private double taxiCabToGoal(Cell cell) {
    Cell goal = grid.getGoal();
    return Math.abs(cell.getRow() - goal.getRow()) + Math.abs(cell.getCol() - goal.getCol());
  }

}
