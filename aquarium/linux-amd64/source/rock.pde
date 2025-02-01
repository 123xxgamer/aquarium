class rock extends Animal {
 boolean hungry;
 rock(int xx, int yy) { //rocks must be petted to stay alive
  super(xx, yy);
  ySpeed = 2; 
  cc = #636363;
  hungry = false;
 }
 void update(){
   if(frameCount % 10 == 0){
      hunger--;
    }
    if(hunger < 25){
      hungry = true;
    }
    else{
      hungry = false;
    }
    if(hunger <= 0){
      die();
    }
 }
 void move() {
    if (x+xWidth>tankX+tankW || x <= tankX) {
        xSpeed*= -1;
     }
     if (y+yHeight >= tankY + tankH - floorH) {
         ySpeed*= 0;
         xSpeed = 0;
      }
     x+= xSpeed;
     y+= ySpeed;
  }
  void display() {
    if(hungry){
      cc = #6363CC;
    }
    else{
      cc = #636363;
    }
    fill(cc);
    noStroke();
    rect(x, y, xWidth, yHeight);
  }
  void pet(int mx, int my){
    if(clickedOn(mx, my)){
      hunger = 100;
    }
  }
  boolean clickedOn(int mx, int my){
    return (mx >= x && mx <= x + xWidth) && (my >= y && my <= y + yHeight);
  }
}
