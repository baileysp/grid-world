enum Status{
  INACTIVE, WALL, START, GOAL, FRONTIER, VISITED, SOLUTION
}

class Cell implements Comparable<Cell>{

  public int row, col;
  private Status status;
  private long lastClicked;
  private double totalCost;
  private double distToStart;
  private double distToGoal;
  private Cell previous;
  
  public int compareTo(Cell cell){
    return (int) (this.distToGoal + this.distToStart - (cell.distToGoal + cell.distToStart));
  }
     
  public Cell(int row, int col){
    this.row = row;
    this.col = col;
    status = Status.INACTIVE;
    lastClicked = 0;
    distToStart = 0;
  }
  
  
  public boolean isStart(){ return (status == Status.START); }
  public void setStart(){ status = Status.START; }
  public boolean isGoal(){ return (status == Status.GOAL); }
  public boolean isWall(){ return (status == Status.WALL); }
  public void setInactive(){ status = Status.INACTIVE; }
  public void setFrontier(){ status = Status.FRONTIER; }
  public void setVisited(){ status = Status.VISITED; }
  public void setSolution(){ status = Status.SOLUTION; }
  
  public void setPrevious(Cell prev){ previous = prev; }
  public Cell getPrevious(){ return previous; }
  public void setDistToStart(double distance){ distToStart = distance; }
  public double getDistToStart(){ return (distToStart); }
  public void setDistToGoal(double distance){ distToGoal = distance; }
  
  public int[] getColor(){
    int[] cellColor = new int[4]; 
    cellColor[3] = 255;
    
    switch (status){
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
      case FRONTIER:
        cellColor[0] = 175;
        cellColor[1] = 221;
        cellColor[2] = 119;
        cellColor[3] = 200;
        break;
      case VISITED:
        cellColor[0] = 19;
        cellColor[1] = 194;
        cellColor[2] = 237;
        cellColor[3] = 200;
        break;
      case SOLUTION:
        cellColor[0] = 200;
        cellColor[1] = 200;
        cellColor[2] = 0;
        //cellColor[3] = 200;
        break;
    }
    
    return cellColor;
  }
  
  public boolean clickTimerUp(){
    return(System.currentTimeMillis() - lastClicked > 1000);
  }
  
  private void resetTimer(){
    lastClicked = System.currentTimeMillis();
  }     
  
  public void toggleWall(){
    if (clickTimerUp()){
      switch(status){
       case WALL:
         status = Status.INACTIVE;
         break;
       default:
         status = Status.WALL;
      }
      resetTimer();
    }
  }
  
  public void cycleGoal(){
    if(clickTimerUp()){
      switch(status){
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
  
  @Override
  public boolean equals(Object other){
    boolean retVal = false;

    if (other instanceof Cell){
      Cell cell = (Cell) other;
      retVal = (cell.row == this.row && cell.col == this.col);
    }
    return retVal;
    
    
  } 
}
