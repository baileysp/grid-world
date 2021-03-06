import java.util.ArrayList;

class Grid {

  public Cell[][] grid;
  private Cell start;
  private Cell defaultStart;
  private Cell goal;
  private int numCells;

  public Grid(int numCells) {
    this.numCells = numCells;
    this.grid = new Cell[numCells][numCells];
    for (int row = 0; row < numCells; row++) {
      for (int col = 0; col < numCells; col++) {
        this.grid[row][col] = new Cell(row, col);
      }
    }
    this.defaultStart = start = this.grid[0][0];
    this.grid[0][0].setStart();
  }

  public Cell getCell(int row, int col) {
    return grid[row][col];
  }

  public Cell getGoal() {
    return goal;
  }

  public Cell getStart() {
    return start;
  }

  public boolean isGoalCell(Cell cell) {
    return goal == cell;
  }

  public ArrayList<Cell> successorCells(Cell cell) {
    ArrayList<Cell> successors = new ArrayList<Cell>();
    int row = cell.getRow();
    int col = cell.getCol();
    if (row >  0){
      if (col > 0){
        //Top left diagonal
        Cell topLeftDiag = grid[row - 1][col - 1];
        if (!topLeftDiag.isWall()){
          topLeftDiag.setDiagonal(true);
          successors.add(topLeftDiag);
        }
      }
      if (col < numCells - 1){
        //Top right diagonal
        Cell topRightDiag = grid[row - 1][col + 1];
        if (!topRightDiag.isWall()){
          topRightDiag.setDiagonal(true);
          successors.add(topRightDiag);
        }
      }
      if (!grid[row - 1][col].isWall()){
        //Top center
        successors.add(grid[row - 1][col]);
      }
    }
    if (row < numCells - 1){
      if (col > 0){
        //Bottom left diagonal
        Cell btmLeftDiag = grid[row + 1][col - 1];
        if (!btmLeftDiag.isWall()){
          btmLeftDiag.setDiagonal(true);
          successors.add(btmLeftDiag);
        }
      }
      if (col < numCells - 1){
        //Bottom right diagonal
        Cell btmRightDiag = grid[row + 1][col + 1];
        if (!btmRightDiag.isWall()){
          btmRightDiag.setDiagonal(true);
          successors.add(btmRightDiag);
        }
      }
      if (!grid[row + 1][col].isWall()){
        //Bottom center
        successors.add(grid[row + 1][col]);
      }
    }    
    if (col > 0 ){
      Cell centerLeft = grid[row][col - 1];
      if (!centerLeft.isWall() && !successors.contains(centerLeft)){
        successors.add(centerLeft);
      }
    }
    if (col < numCells - 1){
      Cell centerRight = grid[row][col + 1];
      if (!centerRight.isWall() && !successors.contains(centerRight)){
        successors.add(centerRight);
      }
    }
    return successors;
  }

  public void cycleGoal(int row, int col) {
    Cell cell = grid[row][col];
    if (cell.clickTimerUp()) {
      if (cell.isStart()) {
        defaultStart.setStart();
        start = defaultStart;
        // goal = cell;
        // System.out.println(goal);
      } else if (cell.isGoal()) {
        start.setInactive();
        start = cell;
      } else {
        goal = cell;
      }
      // System.out.println(cell);
      cell.cycleGoal();
    }
  }

  public void toggleWall(int row, int col) {
    Cell cell = grid[row][col];
    if (cell.clickTimerUp() && cell.isStart() && cell != defaultStart) {
      defaultStart.setStart();
      start = defaultStart;
      cell.toggleWall();
    } else {
      cell.toggleWall();
    }
  }
}
