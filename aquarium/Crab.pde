class Crab extends Animal{ 
  Crab(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
    super(xpos,ypos,xs,ys,xW,yH,t);
  }
  void display(){
  float xScaleFactor = float(xWidth)/170;
  float yScaleFactor = float(yHeight)/140;
  fill(255,0,0);
  pushMatrix();
  translate(x,y);
  beginShape();
  vertex(15*xScaleFactor,80*yScaleFactor);
  vertex(15*xScaleFactor,60*yScaleFactor);
  vertex(10*xScaleFactor,60*yScaleFactor);
  vertex(0*xScaleFactor,30*yScaleFactor);
  vertex(10*xScaleFactor,13*yScaleFactor);
  vertex(18*xScaleFactor,0*yScaleFactor);
  vertex(18*xScaleFactor,53*yScaleFactor);
  vertex(23*xScaleFactor,53*yScaleFactor);
  vertex(23*xScaleFactor,0*yScaleFactor);
  vertex(33*xScaleFactor,13*yScaleFactor);
  vertex(41*xScaleFactor,30*yScaleFactor);
  vertex(31*xScaleFactor,60*yScaleFactor);
  vertex(26*xScaleFactor,60*yScaleFactor);
  vertex(35*xScaleFactor,80*yScaleFactor);
  vertex(60*xScaleFactor,60*yScaleFactor);
  vertex(110*xScaleFactor,60*yScaleFactor);
  vertex(135*xScaleFactor,80*yScaleFactor);
  vertex(144*xScaleFactor,60*yScaleFactor);
  vertex(139*xScaleFactor,60*yScaleFactor);
  vertex(129*xScaleFactor,30*yScaleFactor);
  vertex(137*xScaleFactor,13*yScaleFactor);
  vertex(147*xScaleFactor,0*yScaleFactor);
  vertex(147*xScaleFactor,53*yScaleFactor);
  vertex(152*xScaleFactor,53*yScaleFactor);
  vertex(152*xScaleFactor,0*yScaleFactor);
  vertex(160*xScaleFactor,13*yScaleFactor);
  vertex(170*xScaleFactor,30*yScaleFactor);
  vertex(160*xScaleFactor,60*yScaleFactor);
  vertex(155*xScaleFactor,60*yScaleFactor);
  vertex(155*xScaleFactor,80*yScaleFactor);
  vertex(135*xScaleFactor,120*yScaleFactor);
  vertex(110*xScaleFactor,140*yScaleFactor);
  vertex(60*xScaleFactor,140*yScaleFactor);
  vertex(35*xScaleFactor,120*yScaleFactor);
  endShape(CLOSE);
  popMatrix();
  }
  void move(){
    x += xSpeed; //moves like an animal but with a bit of randomness (okay, maybe more than a bit)
    y += ySpeed;
    if(x + xWidth > tank.tankX + tank.tankW){
      x = tank.tankX + tank.tankW - xWidth;
      xSpeed = random(-2,0);
    }
    if(x < tank.tankX){
      x = tank.tankX;
      xSpeed = random(0,2);
    }
    if(y + yHeight > tank.tankY + tank.tankH){
      y = tank.tankY + tank.tankH - yHeight;
      ySpeed = random(-2,0);
    }
    if(y < tank.tankY + tank.tankH - tank.floorH){
      y = tank.tankY + tank.tankH - tank.floorH;
      ySpeed = random(0,2);
    }
    float randomVarianceX = random(-.3,.3);
    float randomVarianceY = random(-.3,.3);
    xSpeed += randomVarianceX;
    ySpeed += randomVarianceY;
    if(closestFood() != null){
      seek(closestFood());
    }
  }
  void update(){
    if(frameCount % 3 == 0){
      hunger--;
    }
    if(hunger <= 0){
      die();
    }
    if(closestFood() != null){
      checkEat(closestFood());
    }    
  }
  void seek(Animal a){
    if(a.x > this.x){
      if(xSpeed < 5){
        xSpeed += .1;
      }
    }
    if(a.x < this.x){
      if(xSpeed > -5){
        xSpeed -= .1;
      }
    }
    if(a.y > this.y){
      if(ySpeed < 5){
        ySpeed += .1;
      }
    }
    if(a.y < this.y){
      if(ySpeed > -5){
        ySpeed -= .1;
      }
    }
  }
  Crab closestFood(){
    Crab minFood = null;
    float minDist = 0;
    for(int i = 0; i < tank.animals.size(); i++){
      Animal currentAnimal = tank.animals.get(i);
      if(currentAnimal instanceof Crab){
        if(currentAnimal != this){
          if(minFood == null){
            minFood = (Crab) currentAnimal;
            minDist = dist(minFood.x,minFood.y,this.x,this.y);
          }
          if(dist(currentAnimal.x,currentAnimal.y,this.x,this.y) < minDist){
            minFood = (Crab) currentAnimal;
            minDist = dist(minFood.x,minFood.y,this.x,this.y);
          }
        }
      }
    }
    return minFood;
  }
}
