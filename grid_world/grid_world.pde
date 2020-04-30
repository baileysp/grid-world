int NUM_CELL = 200;
int SCREEN_SIZE = 800;
float CELL_DIMEN = SCREEN_SIZE/NUM_CELL;
Grid gridWorld;
Search agent;
boolean DEBUG = false;
long cooldown;

void setup(){
  size(800,800);
  this.gridWorld = new Grid(NUM_CELL);
  agent = new Search(this.gridWorld);
  cooldown = System.currentTimeMillis();
}

void draw(){
  drawCells();
  if (mousePressed) {
    if (agent.searching) {
      printCell(); 
    } else {
      updateCells();
    }
  }
  if (agent.searching && !DEBUG) agent.stepA();  
  
}

void printCell(){
  if (clickWithinBounds() && timerUp()) {
    int row = floor(mouseY / CELL_DIMEN);
    int col = floor(mouseX / CELL_DIMEN);
    Cell cell = gridWorld.getCell(row, col);
    print(cell);
  }
}

void updateCells(){
  if (clickWithinBounds()){
    int row = floor(mouseY / CELL_DIMEN);
    int col = floor(mouseX / CELL_DIMEN);
    if (mouseButton == LEFT){
      gridWorld.toggleWall(row, col);
    } else if (mouseButton == RIGHT){
      gridWorld.cycleGoal(row, col);
    }
  }
}

boolean clickWithinBounds(){
  return (mouseY >= 0 && mouseY <= SCREEN_SIZE && mouseX >= 0 && mouseX <= SCREEN_SIZE);
}

void keyPressed(){
  if (key == ' ') agent.startA();
  if (key == '4') agent.stepA();
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

boolean timerUp(){
  if (System.currentTimeMillis() - cooldown > 1000){
   cooldown = System.currentTimeMillis();
   return true;
  }
  return false;
}
