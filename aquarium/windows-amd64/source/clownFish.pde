class clownFish extends Animal{
  boolean hungry;
  clownFish(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
      super(xpos,ypos,xs,ys,xW,yH,t);
      hunger = 100;
    }
  void update(){
    if(frameCount % 5 == 0){
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
  void move(){
    this.x += xSpeed/2;
    this.y += ySpeed/2;
    if (x <= 0 || x+xWidth >= tankX+tankW){xSpeed = xSpeed * -1;}
    if (y <= tankY || y+yHeight >=tankY+tankH-floorH){ySpeed = ySpeed * -1;}
    if(closestFood() != null && hungry){
      seek(closestFood());
    }
  }
  void seek(Animal a){ //goes towards the food
    if(!withinX(a)){ //slows down when aligned with food
      if(a.x > this.x){
        if(xSpeed < 2){
          xSpeed += .1;
        }
      }
      if(a.x < this.x){
        if(xSpeed > -2){
          xSpeed -= .1;
        }
      }
    }
    else{
      if(xSpeed > 0){
        xSpeed -= .1;
      }
      if(xSpeed < 0){
        xSpeed += .1;
      }
    }
    if(!withinY(a)){
      if(a.y > this.y){
        if(ySpeed < 2){
          ySpeed += .1;
        }
      }
      if(a.y < this.y){
        if(ySpeed > -2){
          ySpeed -= .1;
        }
      }
    }
    else{
      if(ySpeed > 0){
        ySpeed -= .1;
      }
      if(ySpeed < 0){
        ySpeed += .1;
      }
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
  void display(){
    if(!hungry){
      fill(255,165,0);
    }
    else{
      fill(255,255,0);
    }
    rect(x,y,xWidth,yHeight);
    fill(255,255,255);
    rect(x + xWidth/2, y, xWidth/3, yHeight);
  }
}
