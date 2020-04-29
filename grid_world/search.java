import java.util.PriorityQueue;
import java.util.*;

class Search{
  
  public boolean searching;
  PriorityQueue<Cell> frontier = new PriorityQueue<Cell>();
  LinkedList<Cell> visited = new LinkedList<Cell>();
  private Grid grid;
  
  Search(Grid grid){
    this.grid = grid;
    searching = false;
  }
  
  public void startA(){
    System.out.print(grid.getGoal().row);
    searching = true;
    frontier.add(grid.start);
  }
  
  public void stepA(){
    System.out.println(frontier.size());
    if(frontier.isEmpty()){
      searching = false;
      System.out.println("no solution");
    } else{
      Cell currentCell = frontier.poll();
      System.out.println("row: " + currentCell.row + ", col: " + currentCell.col);
      
      System.out.println(!visited.contains(currentCell));
      if (!visited.isEmpty())
        System.out.println("element " + visited.get(0).row);
      
      if (grid.isGoalCell(currentCell)){
        while(currentCell != grid.getStart()){
          System.out.println("creating solution");
          System.out.println("row: " + currentCell.row + ", col: " + currentCell.col);
          currentCell.setSolution();
          currentCell = currentCell.getPrevious();
          
        }
        searching = false;
      }
      if (!visited.contains(currentCell)){
        
        visited.add(currentCell);
        currentCell.setVisited();
        
        ArrayList<Cell> successors = grid.successorCells(currentCell);
        
        for(int i = 0; i < successors.size(); i++){
          Cell successor = successors.get(i);
          //System.out.println(successor.row + " " + successor.col);
          
          if(!frontier.contains(successor)){
            //System.out.println("here");
            successor.setPrevious(currentCell);
            successor.setDistToStart(currentCell.getDistToStart() + 1);
            successor.setDistToGoal(taxiCabToGoal(successor));
            frontier.add(successor);
            successor.setFrontier();
          } else if(successor.getDistToStart() > currentCell.getDistToStart() + 1){
            successor.setPrevious(currentCell);            
            successor.setDistToStart(currentCell.getDistToStart() + 1);
          }          
        }         
      }      
    }
  }
  
  private double taxiCabToGoal(Cell cell){
    Cell goal = grid.getGoal();
    return Math.abs(cell.row - goal.row) + Math.abs(cell.col - goal.col);
  }
  
}
