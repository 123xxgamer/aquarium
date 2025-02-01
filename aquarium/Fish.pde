class Fish extends Animal{ 
  boolean hungry;
  Fish(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
    super(xpos,ypos,xs,ys,xW,yH,t);
    hungry = false;
  }
  void display(){
    pushMatrix();
    float xScaleFactor; //multiplies x and y offsets so the fish can still fit in the width and height
    float yScaleFactor;
    xScaleFactor = float(xWidth)/75;
    yScaleFactor = float(yHeight)/35;
    stroke(255);
    if(!hungry){
      fill(255,150,0);
    }
    else{
      fill(100,0,200);
    }
    translate(x,y);
    if(xSpeed < 0){
      scale(-1,1);
    }
      beginShape();
      vertex(0,5*yScaleFactor);
      vertex(0,30*yScaleFactor);
      vertex(25*xScaleFactor,18*yScaleFactor);
      vertex(30*xScaleFactor,30*yScaleFactor);
      vertex(45*xScaleFactor,35*yScaleFactor);
      vertex(60*xScaleFactor,30*yScaleFactor);
      vertex(75*xScaleFactor,22*yScaleFactor);
      vertex(65*xScaleFactor,18*yScaleFactor);
      vertex(75*xScaleFactor,17*yScaleFactor);
      vertex(60*xScaleFactor,5*yScaleFactor);
      vertex(45*xScaleFactor,0);
      vertex(30*xScaleFactor,5*yScaleFactor);
      vertex(25*xScaleFactor,17*yScaleFactor);
      endShape(CLOSE);
      popMatrix();
  }
  void move(){
    x += xSpeed; //moves in a sine wave
    y += ySpeed * sin(radians(frameCount));
    if(x + xWidth > tank.tankX + tank.tankW && xSpeed > 0){
      xSpeed *= -1;
      x = tank.tankX + tank.tankW;
    }
    if(x - xWidth < tank.tankX && xSpeed < 0){
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
    if(closestFood() != null && hungry){
      seek(closestFood());
    }
    else{ //speeds up when not hungry
      if(xSpeed < 0){
        if(xSpeed > -2){ //caps
          xSpeed -= .1;
        }
      }
        else{
          if(xSpeed < 2){ //caps
            xSpeed += .1;
          }
        }
       if(ySpeed < 0){
         if(ySpeed > -2){
           ySpeed -= .1;
         }
       }
         else{
           if(ySpeed < 2){
             ySpeed += .1;
           }
         }  
      }
  }
  void seek(Animal a){
    if(x > a.x){
      if(xSpeed > -2){
        xSpeed -= .1;
      }
    }
    if(x < a.x){
      if(xSpeed < 2){
        xSpeed += .1;
      }
    }
    if(y > a.y){
      if(ySpeed > -2){
        ySpeed -= .1;
      }
    }
    if(y < a.y){
      if(ySpeed < 2){
        ySpeed += .1;
      }
    }
  }
  void update(){
    if(frameCount % 7 == 0){
      hunger--;
    }
    if(hunger < 50){
      hungry = true;
    }
    else{
      hungry = false;
    }
    if(hunger <= 0){
      die();
    }
    if(closestFood() != null && hungry){
      checkEat(closestFood());
    }
  }
    FishFood closestFood(){
    FishFood minFood = null;
    float minDist = 0;
    for(int i = 0; i < tank.animals.size(); i++){
      Animal currentAnimal = tank.animals.get(i);
      if(currentAnimal instanceof FishFood){
        if(minFood == null){
          minFood = (FishFood) currentAnimal;
          minDist = dist(minFood.x,minFood.y,this.x,this.y);
        }
        if(dist(currentAnimal.x,currentAnimal.y,this.x,this.y) < minDist){
          minFood = (FishFood) currentAnimal;
          minDist = dist(minFood.x,minFood.y,this.x,this.y);
        }
      }
    }
    return minFood;
  }
}
