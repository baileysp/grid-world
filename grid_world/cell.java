enum Status {
  INACTIVE, WALL, START, GOAL, OPEN, CLOSED, SOLUTION
}

class Cell implements Comparable<Cell> {

  private int row, col;
  private Status status;
  private long lastClicked;
  private double gCost; // distance from starting node
  private double hCost; // heuristic cost
  private double fCost; // gCost + hCost
  private Cell previous;
  private boolean diagonal;

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
    status = Status.INACTIVE;
    lastClicked = 0;
    previous = this;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public double getgCost() {
    return gCost;
  }

  public void setgCost(double gCost) {
    this.gCost = gCost;
    fCost = (this.gCost + hCost);
  }

  public double gethCost() {
    return hCost;
  }

  public void sethCost(double hCost) {
    this.hCost = hCost;
    fCost = (gCost + this.hCost);
  }

  public double getfCost() {
    return fCost;
  }

  public void setfCost(double fCost) {
    this.fCost = fCost;
  }

  public boolean isDiagonal() {
    return diagonal;
  }

  public void setDiagonal(boolean diagonal) {
    this.diagonal = diagonal;
  }

  public boolean isStart() {
    return (status == Status.START);
  }

  public void setStart() {
    status = Status.START;
  }

  public boolean isGoal() {
    return (status == Status.GOAL);
  }

  public boolean isWall() {
    return (status == Status.WALL);
  }

  public void setInactive() {
    status = Status.INACTIVE;
  }

  public void setOpen() {
    status = Status.OPEN;
  }

  public void setClosed() {
    status = Status.CLOSED;
  }

  public void setSolution() {
    status = Status.SOLUTION;
  }

  public void setPrevious(Cell previous) {
    this.previous = previous;
  }

  public Cell getPrevious() {
    return previous;
  }

  public int[] getColor() {
    int[] cellColor = new int[4];
    cellColor[3] = 255;

    switch (status) {
      case INACTIVE:
        cellColor[0] = 255;
        cellColor[1] = 255;
        cellColor[2] = 255;
        break;
      case WALL:
        cellColor[0] = 0;
        cellColor[1] = 0;
        cellColor[2] = 0;
        break;
      case GOAL:
        cellColor[0] = 255;
        cellColor[1] = 105;
        cellColor[2] = 97;
        cellColor[3] = 200;
        break;
      case START:
        cellColor[0] = 119;
        cellColor[1] = 221;
        cellColor[2] = 119;
        cellColor[3] = 200;
        break;
      case OPEN:
        cellColor[0] = 175;
        cellColor[1] = 221;
        cellColor[2] = 119;
        cellColor[3] = 200;
        break;
      case CLOSED:
        cellColor[0] = 19;
        cellColor[1] = 194;
        cellColor[2] = 237;
        cellColor[3] = 200;
        break;
      case SOLUTION:
        cellColor[0] = 200;
        cellColor[1] = 200;
        cellColor[2] = 0;
        // cellColor[3] = 200;
        break;
    }

    return cellColor;
  }

  public boolean clickTimerUp() {
    return (System.currentTimeMillis() - lastClicked > 1000);
  }

  private void resetTimer() {
    lastClicked = System.currentTimeMillis();
  }

  public void toggleWall() {
    if (clickTimerUp()) {
      switch (status) {
        case WALL:
          status = Status.INACTIVE;
          break;
        default:
          status = Status.WALL;
      }
      resetTimer();
    }
  }

  public void cycleGoal() {
    if (clickTimerUp()) {
      switch (status) {
        case GOAL:
          status = Status.START;
          break;
        case START:
          status = Status.INACTIVE;
          break;
        default:
          status = Status.GOAL;
      }
      resetTimer();
    }
  }

  public int compareTo(Cell cell) {
    return (int) (1000 * (fCost - cell.fCost));
    //return (int) ((cell.fCost - fCost));
  }

  @Override
  public String toString() {
    
    return row + ", " + col + "\nCost from start: " + gCost + "\nCost to goal: " + hCost + "\nTotal Cost: " + fCost + "\nPrevious Cell: " + previous.getRow() + ", " + previous.getCol() + "\n";
  }

  @Override
  public boolean equals(Object other) {
    boolean retVal = false;
    if (other instanceof Cell) {
      Cell cell = (Cell) other;
      retVal = (cell.getRow() == this.row && cell.getCol() == this.col);
    }
    return retVal;
  }
}
