Tank t;
Fish f0;
Crab c0;
int tankX = 0;
int tankY = 50;
int floorH = 100;
int tankW;
int tankH;


void setup() {
  size(600, 600);
  tankW = width;
  tankH = height - 50;
  t = new Tank(tankX, tankY, tankW, tankH, floorH);
  t.display();
}//setup


void draw() {
  background(150);
  t.moveAnimals();
  t.display();
  t.update();
  textAlign(RIGHT,TOP);
  textSize(20);
  fill(0);
  text("Click in the water for fish/animal\nClick on the sand for crabs",width,0);
}

void mousePressed() {
  if(t.clickedOnRock(mouseX,mouseY)){
    t.petRocks(mouseX,mouseY);
  }
  else{
    t.addFishFood(mouseX, mouseY);
  }
}
void keyPressed(){
  if(key == '0'){
    t.addAnimal(mouseX,mouseY);
  }
  if(key == '1'){
    t.addFish(mouseX,mouseY);
  }
  if(key == '2'){
    t.addCrab(mouseX,mouseY);
  }
  if(key == '3'){
    t.addClownFish(mouseX,mouseY);
  }
  if(key == '4'){
    t.addRock(mouseX,mouseY);
  }
  if(key == '5'){
    t.addGoldFish(mouseX,mouseY); 
  }
  if(key == '6'){
    t.addTurtle(mouseX,mouseY); 
  }
}
