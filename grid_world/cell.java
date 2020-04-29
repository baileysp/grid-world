enum Status{
  INACTIVE, WALL
}

class Cell{

  int row, col;
  Status status;
  long lastClicked;
  //Color color;
  
  Cell(int row, int col){
    this.row = row;
    this.col = col;
    status = Status.INACTIVE;
    lastClicked = 0;
  }
  
 
  public int[] getColor(){
    int[] cell_color = new int[4]; 
    cell_color[3] = 255;
    
    switch (status){
      case INACTIVE:
        cell_color[0] = 255;
        cell_color[1] = 255;
        cell_color[2] = 255;
        break;
      case WALL:
        cell_color[0] = 0;
        cell_color[1] = 0;
        cell_color[2] = 0;
        break;
      default:
        int test = 6;
    }
    
    return cell_color;
  }
  
  public void changeWallStatus(){
    long currTime = System.currentTimeMillis();
    if(currTime - lastClicked > 1000){
      if(status == Status.WALL){
        status = Status.INACTIVE;
      } else if (status == Status.INACTIVE){
        status = Status.WALL;
      }
      lastClicked = currTime;
    }
       
    //status = Status.WALL;
  }
  
}
