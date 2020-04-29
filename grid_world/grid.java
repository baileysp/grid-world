import java.util.ArrayList;

class Grid{
  
  public Cell[][] grid;
  public Cell start;
  private Cell defaultStart;
  private Cell goal;
  private int numCells;
  
  public Grid(int numCells){
    this.numCells = numCells;
    this.grid = new Cell[numCells][numCells];
    for(int row = 0; row < numCells; row++){
      for(int col = 0; col < numCells; col++){
        this.grid[row][col] = new Cell(row, col);
      }
    }
    this.defaultStart = start = this.grid[0][0];
    this.grid[0][0].setStart();
  }
  
  public Cell getCell(int row, int col){
    return grid[row][col];
  }
  
  public Cell getGoal(){
    return goal;
  }
  
  public Cell getStart(){
    return start;
  }
  
  public boolean isGoalCell(Cell cell){
    return (goal == cell);
  }
  
  public ArrayList<Cell> successorCells(Cell cell){
    ArrayList<Cell> successors = new ArrayList();
    int row = cell.row; 
    int col = cell.col;
    if (row >= 0 && row < numCells - 1 && !grid[row + 1][col].isWall()){
      successors.add(grid[row + 1][col]);
    }
    if (row < numCells && row > 0 && !grid[row - 1][col].isWall()){
      successors.add(grid[row - 1][col]);
    }
    if (col >= 0 && col < numCells - 1 && !grid[row][col + 1].isWall()){
      successors.add(grid[row][col + 1]);
    }
    if (col < numCells && col > 0 && !grid[row][col - 1].isWall()){
      successors.add(grid[row][col - 1]);
    }
    System.out.println("row: " + cell.row + ", col: " + cell.col + "| "+ successors.size());
    //System.out.println(successors.size());
    return successors;
    
  }
  
  
  public void cycleGoal(int row, int col){
    Cell cell = grid[row][col];
    if (cell.clickTimerUp()){
      if (cell.isStart()){
        defaultStart.setStart();
        start = defaultStart;
        //goal = cell;
        //System.out.println(goal);
      } else if (cell.isGoal()){
        start.setInactive();
        start = cell;
      } else {
        goal = cell;
      }
      //System.out.println(cell);
      cell.cycleGoal();
    }    
  }
  
  public void toggleWall(int row, int col){
    Cell cell = grid[row][col];
    if (cell.clickTimerUp() && cell.isStart() && cell != defaultStart){
      defaultStart.setStart();
      start = defaultStart;
      cell.toggleWall();
    } else {
        cell.toggleWall();
      }
  }    
}
  
  
 
