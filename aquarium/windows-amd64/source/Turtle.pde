class Turtle extends Animal {
  Turtle(int x, int y) {
    super(x, y);
    xSpeed = random(.25, .75) * int(pow(-1, int(random(0, 2))));
    ySpeed = 2;
    xWidth = int(random(60, 80));
    yHeight = int(random(50, 55));
  }
  void display() {
    if(alive){
      fill(0, 48, 32);
      arc(x + xWidth/3, y + yHeight/2, xWidth/1.5, yHeight, PI, 2*PI, CHORD);
      fill(0, 200, 0);
      rect(x, y + yHeight/2, xWidth/6, yHeight/2);
      rect(x + xWidth/2, y + yHeight/2, xWidth/6, yHeight/2);
      if (xSpeed>0) {
        circle(x + 5 * xWidth/6, y + yHeight/2, xWidth/3);
      }
      if (xSpeed<0) {
        circle(x - xWidth/6, y + yHeight/2, xWidth/3);
      }
   }
   else{
      fill(0, 48, 32);
      arc(x + xWidth/3, y + yHeight/2, xWidth/1.5, yHeight, 0, PI, CHORD);
      fill(0, 200, 0);
      rect(x, y + yHeight/2, xWidth/6, -yHeight/2);
      rect(x + xWidth/2, y + yHeight/2, xWidth/6, -yHeight/2);
      if (xSpeed>0) {
        circle(x + 5 * xWidth/6, y + yHeight/2, xWidth/3);
      }
      if (xSpeed<0) {
        circle(x - xWidth/6, y + yHeight/2, xWidth/3);
      }
   }
  }
  boolean collisionCheck(Goldfish other) {
    return (dist(x + 5 * xWidth/6, y + yHeight/2, other.cx, other.cy)<other.xWidth/4);
  }
  void eat(Goldfish other) {
    if (other.perished && !other.STOP && collisionCheck(other)) {
      other.STOP = true;
      yHeight += log(other.fsize) * ratio;
      xWidth += log(other.fsize);
    }
  }
  void move(){
    if (y+yHeight >= tankY + tankH - floorH) {
      ySpeed*= 0;
    }
    if (x >= width - xWidth||
        x <= 0) {
        xSpeed*= -1;
     }
     if(alive){
        x += xSpeed;
        y += ySpeed;
     }
  }
  void update(){
    if(frameCount % 15 == 0){
      hunger--;
    }
    if(hunger <= 0){
      die();
    }
  }
  void die(){
    alive = false;
  }
}
