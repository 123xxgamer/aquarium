class Tank{
  int tankX;
  int tankY;
  int floorH;
  int tankW;
  int tankH;
  ArrayList<Animal> animals;
  Tank(int tx,int ty,int tw,int th,int fh){
    tankX = tx;
    tankY = ty;
    tankW = tw;
    tankH = th;
    floorH = fh;
    animals = new ArrayList<Animal>();
  }
  void display(){
    strokeWeight(0);
    fill(0,255,255);
    rect(tankX,tankY,tankW,tankH);
    fill(194,178,128);
    rect(tankX,tankY+tankH,tankW,-floorH);
    for(int i = 0; i < animals.size(); i++){
      animals.get(i).display();
    }
  }
  void moveAnimals(){
    for(int i = 0; i < animals.size(); i++){
      animals.get(i).move();
    }
  }
  void update(){
    for(int i = 0; i < animals.size(); i++){
      animals.get(i).update();
    }
  }
  void addAnimal(int x, int y){
    float xSpeed = random(-2,2);
    float ySpeed = random(-2,2);
    int xSize = 50 + int(random(-10,10));
    int ySize = 25 + int(random(-5,5));
    Animal animal;
    if(y < tankY + tankH - floorH){
    animal = new Animal(x,y,xSpeed,ySpeed,xSize,ySize,this);
    animals.add(animal); 
    }
  }
  void addAnimal(Animal a){
    animals.add(a);
  }
    void addFish(int x, int y){
    float xSpeed = random(-2,2);
    float ySpeed = random(-2,2);
    int xSize = 50 + int(random(-10,10));
    int ySize = 25 + int(random(-5,5));
    Animal animal = new Fish(x,y,xSpeed,ySpeed,xSize,ySize,this);
    animals.add(animal);
  }
  void addCrab(int x, int y){
    float xSpeed = random(-2,2);
    float ySpeed = random(-2,2);
    int xSize = 50 + int(random(-10,10));
    int ySize = 25 + int(random(-5,5));
    Animal animal = new Crab(x,y,xSpeed,ySpeed,xSize,ySize,this);
    animals.add(animal);
  }
  void addClownFish(int x, int y){
    int xWidth = int(random(10,30));
    int yHeight = int (random(5,10));
    float xSpeed = int(random(-5,5));
    float ySpeed = int(random(-5,5));
    Animal animal = new clownFish(x,y,xSpeed,ySpeed,xWidth,yHeight,this);
    animals.add(animal);
  }
  void addRock(int x, int y){
    Animal animal = new rock(x,y);
    animals.add(animal);
  }
  void addGoldFish(int x, int y){
    Goldfish animal = new Goldfish(x,y);
  }
  void addTurtle(int x, int y){
    Animal animal = new Turtle(x,y);
    animals.add(animal);
  }
  void addFishFood(int x, int y){
    int xWidth = int(random(7,10));
    int yHeight = int(random(5,7));
    float xSpeed = random(-.2,.2);
    float ySpeed = random(-.2,.2);
    Animal animal = new FishFood(x,y,xSpeed,ySpeed,xWidth,yHeight,this);
    animals.add(animal);
  }
  boolean clickedOnRock(int mx, int my){
    for(int i = 0; i < animals.size(); i++){
      if(animals.get(i) instanceof rock){
        rock testrock = (rock) animals.get(i);
        if(testrock.clickedOn(mx,my)){
          return true;
        }
      }
    }
    return false;
  }
  void petRocks(int mx, int my){
    for(int i = 0; i < animals.size(); i++){
      if(animals.get(i) instanceof rock){
        rock fedrock = (rock) animals.get(i);
        fedrock.pet(mx,my);
      }
    }
  }
}
