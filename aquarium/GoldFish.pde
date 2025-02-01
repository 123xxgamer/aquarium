class Goldfish extends Animal { 
  boolean perished;
  boolean STOP;
  float hunger;
  int cx;
  int cy;
  float fsize;
  Goldfish(int x, int y) {
    super(x, y);
    xWidth = int(random(15,100));
    yHeight = int(random(10,100));
    cx = x + xWidth/2;
    cy = y + yHeight/2;
    xSpeed = random(0,1.5) * int(pow(-1,int(random(0,2))));
    ySpeed = random(0,1) * int(pow(-1,int(random(0,2))));
    alive = true;
    hunger = 10;
    t.addAnimal(this);
    fsize = xWidth * yHeight * PI;
    ratio = float(yHeight)/float(xWidth);
  }
  Goldfish(int x, int y, int w, int h) {
    this(x, y);
    xWidth = w;
    yHeight = h;
    cx = x + xWidth/2;
    cy = y + yHeight/2;
    fsize = xWidth * yHeight * PI; //recompute size and ratio
    ratio = float(yHeight)/float(xWidth);
  }
  void update(){
  }
  void display() {
    if(!STOP){
    hunger-= .1;
    //xywh
    if (perished) {
      ySpeed = 1;
      xSpeed = 0;
      fill(#808080);
      ellipse(cx, cy, xWidth, yHeight);

     if (y >= height - t.floorH + random(00,20)) {
    ySpeed = 0;
  }
    }
    else if (alive) {
      fill(250, 200, 50);
      ellipse(cx, cy, xWidth, yHeight);
      if (xSpeed<0) {
        triangle(cx + xWidth/2, cy, cx + xWidth, cy + yHeight/2, cx + xWidth, cy - yHeight/2);
        fill(#000000);
        circle(cx - xWidth /4, cy - yHeight/5, sqrt(log(fsize)));
      }
      if (xSpeed>0) {
        triangle(cx - xWidth/2, cy, cx - xWidth, cy + yHeight/2, cx - xWidth, cy - yHeight/2);
        fill(#000000);
        circle(cx + xWidth /4, cy - yHeight/5, sqrt(log(fsize)));
      }
      if(hunger < 6){ //eats if hungry
        for(int i = 0; i < t.animals.size(); i++){
            if(t.animals.get(i) instanceof Goldfish){
              checkEat((Goldfish) t.animals.get(i));
            }
        }
      }
      if (hunger<0) {
        perish();
      }
      
    }
    }}
  void checkEat(Goldfish other) {
    if (fsize > other.fsize && collisionCheck(other) && other.alive) {
      other.die();
      fsize += other.fsize;
      hunger += log(other.fsize);
      xWidth = int(sqrt(fsize/ratio/PI));
      yHeight = int(sqrt(fsize/ratio/PI)*ratio);
    }
  }
  boolean collisionCheck(Goldfish other) {
    if (other == this) {
      return false;
    } else {
      return(dist(this.cx, this.cy, other.cx, other.cy)
        <= (this.xWidth/2 + other.xWidth/2));
    }
  }//collisionCheck
  void die() {
    alive = false;
  }
  void perish() {
    alive = false;
    perished = true;
  }
  void move(){
    cx = int(x + xWidth/2);
    cy = int(y + yHeight/2);
    if(alive){
    
    if (cx >= width - xWidth/2 ||
        cx <= xWidth/2) {
        xSpeed*= -1;
     }
     if (cy >= height - t.floorH -yHeight/2||
         cy <= tankY + yHeight/2 ) {
         ySpeed*= -1;
  }}
  x += xSpeed;
  y += ySpeed;
}
}
