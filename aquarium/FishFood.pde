class FishFood extends Animal{
  FishFood(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
    super(xpos,ypos,xs,ys,xW,yH,t);
    cc = color(200,200,0);
  }
  void update(){
  }
  void eat(){
  }
  void move(){
    x += xSpeed;
    y += ySpeed;
    if(x + xWidth > tank.tankX + tank.tankW){
      xSpeed *= -1;
      x = tank.tankX + tank.tankW - xWidth;
    }
    if(x < tank.tankX){
      xSpeed *= -1;
      x = tank.tankX;
    }
    if(y + yHeight > tank.tankY + tank.tankH - tank.floorH){
      ySpeed *= -1;
      y = tank.tankY + tank.tankH - tank.floorH - yHeight;
    }
    if(y < tank.tankY){
      ySpeed *= -1;
      y = tank.tankY;
    }
  }
}
