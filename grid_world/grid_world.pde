int NUM_CELL = 40;
int SCREEN_SIZE = 800;
float CELL_DIMEN = SCREEN_SIZE/NUM_CELL;
Grid gridWorld;
Search agent;

void setup(){
  size(800,800);
  this.gridWorld = new Grid(40);
  agent = new Search(this.gridWorld);
}

void draw(){
  drawCells();
  if (mousePressed) updateCells();
  if (agent.searching) agent.stepA();  
  
}

void updateCells(){
  if(mouseY >= 0 && mouseY <= SCREEN_SIZE && mouseX >= 0 && mouseY <= SCREEN_SIZE){
    int row = floor(mouseY / CELL_DIMEN);
    int col = floor(mouseX / CELL_DIMEN);
    if (mouseButton == LEFT){
      gridWorld.toggleWall(row, col);
    } else if (mouseButton == RIGHT){
      gridWorld.cycleGoal(row, col);
    }
  }
}

void keyPressed(){
  if (key == ' ') agent.startA();
}


void drawCells(){
  for(int row = 0; row < NUM_CELL; row++){
    for(int col = 0; col < NUM_CELL; col++){
      Cell cell = gridWorld.getCell(row, col);
      int[] cellColor = cell.getColor();
      fill(cellColor[0], cellColor[1], cellColor[2], cellColor[3]);
      square(col * CELL_DIMEN, row * CELL_DIMEN, CELL_DIMEN);
    }
  }
}
