int NUM_CELL = 40;
int SCREEN_SIZE = 900;
float cell_dimen = SCREEN_SIZE / NUM_CELL;
Cell[][] grid = new Cell[NUM_CELL][NUM_CELL];

void setup(){
  size(900, 900);

  for(int row = 0; row < NUM_CELL; row++){
    for(int col = 0; col < NUM_CELL; col++){
      grid[row][col] = new Cell(row, col);
    }
  }
}

void draw(){
  drawCells();
  if(mousePressed){
    int row = floor(mouseY / cell_dimen);
    int col = floor(mouseX / cell_dimen);
    if(row > -1 && row < NUM_CELL && col > -1 && col < NUM_CELL){
      if(mouseButton == LEFT){
        grid[row][col].changeWallStatus();
      } else if (mouseButton == RIGHT){
        
      }
    }
    //grid[row][col].setWall();
  }
}

void drawCells(){
  for(int row = 0; row < NUM_CELL; row++){
    for(int col = 0; col < NUM_CELL; col++){
      Cell cell = grid[row][col];
      int[] cell_color = cell.getColor();
      fill(cell_color[0], cell_color[1], cell_color[2], cell_color[3]);
      square(col * cell_dimen, row * cell_dimen, cell_dimen);
      
      
    }
  }
}
