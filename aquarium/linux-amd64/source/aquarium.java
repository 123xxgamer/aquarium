/* autogenerated by Processing revision 1295 on 2025-02-01 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class aquarium extends PApplet {

Tank t;
Fish f0;
Crab c0;
int tankX = 0;
int tankY = 50;
int floorH = 100;
int tankW;
int tankH;


public void setup() {
  /* size commented out by preprocessor */;
  tankW = width;
  tankH = height - 50;
  t = new Tank(tankX, tankY, tankW, tankH, floorH);
  t.display();
}//setup


public void draw() {
  background(150);
  t.moveAnimals();
  t.display();
  t.update();
  textAlign(RIGHT,TOP);
  textSize(20);
  fill(0);
  text("Click in the water for fish/animal\nClick on the sand for crabs",width,0);
}

public void mousePressed() {
  if(t.clickedOnRock(mouseX,mouseY)){
    t.petRocks(mouseX,mouseY);
  }
  else{
    t.addFishFood(mouseX, mouseY);
  }
}
public void keyPressed(){
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
class Animal {
  boolean alive;
  int hunger;
  float x;
  float y;
  float xSpeed;
  float ySpeed;
  int xWidth;
  int yHeight;
  int cc;

  float ratio; 

  Tank tank;
  Animal(int xpos, int ypos, float xs, float ys, int xW, int yH, Tank t) {
    x = xpos;
    y = ypos;
    xSpeed = xs;
    ySpeed = ys;
    xWidth = xW;
    yHeight = yH;
    tank = t; //tank is t specified
    alive = true;
    hunger = 100;
    cc = color(255, 0, 255);
  }
  Animal(int xx, int yy) {
    x = xx;
    y = yy;
    tank = t; //assuming tank is t
    xSpeed = PApplet.parseInt(random(-2, 2));
    ySpeed = PApplet.parseInt(random(-1, 2));
    xWidth = 40;
    yHeight = 20;
    alive = true;
    hunger = 100;
    cc = color(255, 0, 255);
  }
  public void display() {
    fill(cc);
    rect(x, y, xWidth, yHeight);
  }
  public void die(){
    tank.animals.remove(this);
  }
  public void update(){
    hunger--;
    if(hunger < 0){
      die();
    }
    for(int i = 0; i < tank.animals.size(); i++){
      checkEat(tank.animals.get(i));
    }
  }
  public void checkEat(Animal food){
    if(food != this){
      if(collisionCheck(food)){
        hunger += 100;
        food.die();
      }
    }
  }
        
  public boolean collisionCheck(Animal food){
    return (withinX(food) && withinY(food));
  }
  public boolean withinX(Animal food){
    return (x <= food.x + food.xWidth) && (x + xWidth >= food.x);
  }
  public boolean withinY(Animal food){
    return (y <= food.y + food.yHeight) && (y + yHeight >= food.y);
  }
  public void move() {
    x += xSpeed;
    y += ySpeed;
    if (x + xWidth > tank.tankX + tank.tankW) {
      xSpeed *= -1;
      x = tank.tankX + tank.tankW - xWidth;
    }
    if (x < tank.tankX) {
      xSpeed *= -1;
      x = tank.tankX;
    }
    if (y + yHeight > tank.tankY + tank.tankH) {
      ySpeed *= -1;
      y = tank.tankY + tank.tankH - yHeight;
    }
    if (y < tank.tankY) {
      ySpeed *= -1;
      y = tank.tankY;
    }
  }
}
class Crab extends Animal{ 
  Crab(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
    super(xpos,ypos,xs,ys,xW,yH,t);
  }
  public void display(){
  float xScaleFactor = PApplet.parseFloat(xWidth)/170;
  float yScaleFactor = PApplet.parseFloat(yHeight)/140;
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
  public void move(){
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
    float randomVarianceX = random(-.3f,.3f);
    float randomVarianceY = random(-.3f,.3f);
    xSpeed += randomVarianceX;
    ySpeed += randomVarianceY;
    if(closestFood() != null){
      seek(closestFood());
    }
  }
  public void update(){
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
  public void seek(Animal a){
    if(a.x > this.x){
      if(xSpeed < 5){
        xSpeed += .1f;
      }
    }
    if(a.x < this.x){
      if(xSpeed > -5){
        xSpeed -= .1f;
      }
    }
    if(a.y > this.y){
      if(ySpeed < 5){
        ySpeed += .1f;
      }
    }
    if(a.y < this.y){
      if(ySpeed > -5){
        ySpeed -= .1f;
      }
    }
  }
  public Crab closestFood(){
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
class Fish extends Animal{ 
  boolean hungry;
  Fish(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
    super(xpos,ypos,xs,ys,xW,yH,t);
    hungry = false;
  }
  public void display(){
    pushMatrix();
    float xScaleFactor; //multiplies x and y offsets so the fish can still fit in the width and height
    float yScaleFactor;
    xScaleFactor = PApplet.parseFloat(xWidth)/75;
    yScaleFactor = PApplet.parseFloat(yHeight)/35;
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
  public void move(){
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
          xSpeed -= .1f;
        }
      }
        else{
          if(xSpeed < 2){ //caps
            xSpeed += .1f;
          }
        }
       if(ySpeed < 0){
         if(ySpeed > -2){
           ySpeed -= .1f;
         }
       }
         else{
           if(ySpeed < 2){
             ySpeed += .1f;
           }
         }  
      }
  }
  public void seek(Animal a){
    if(x > a.x){
      if(xSpeed > -2){
        xSpeed -= .1f;
      }
    }
    if(x < a.x){
      if(xSpeed < 2){
        xSpeed += .1f;
      }
    }
    if(y > a.y){
      if(ySpeed > -2){
        ySpeed -= .1f;
      }
    }
    if(y < a.y){
      if(ySpeed < 2){
        ySpeed += .1f;
      }
    }
  }
  public void update(){
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
    public FishFood closestFood(){
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
class FishFood extends Animal{
  FishFood(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
    super(xpos,ypos,xs,ys,xW,yH,t);
    cc = color(200,200,0);
  }
  public void update(){
  }
  public void eat(){
  }
  public void move(){
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
class Goldfish extends Animal { 
  boolean perished;
  boolean STOP;
  float hunger;
  int cx;
  int cy;
  float fsize;
  Goldfish(int x, int y) {
    super(x, y);
    xWidth = PApplet.parseInt(random(15,100));
    yHeight = PApplet.parseInt(random(10,100));
    cx = x + xWidth/2;
    cy = y + yHeight/2;
    xSpeed = random(0,1.5f) * PApplet.parseInt(pow(-1,PApplet.parseInt(random(0,2))));
    ySpeed = random(0,1) * PApplet.parseInt(pow(-1,PApplet.parseInt(random(0,2))));
    alive = true;
    hunger = 10;
    t.addAnimal(this);
    fsize = xWidth * yHeight * PI;
    ratio = PApplet.parseFloat(yHeight)/PApplet.parseFloat(xWidth);
  }
  Goldfish(int x, int y, int w, int h) {
    this(x, y);
    xWidth = w;
    yHeight = h;
    cx = x + xWidth/2;
    cy = y + yHeight/2;
    fsize = xWidth * yHeight * PI; //recompute size and ratio
    ratio = PApplet.parseFloat(yHeight)/PApplet.parseFloat(xWidth);
  }
  public void update(){
  }
  public void display() {
    if(!STOP){
    hunger-= .1f;
    //xywh
    if (perished) {
      ySpeed = 1;
      xSpeed = 0;
      fill(0xFF808080);
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
        fill(0xFF000000);
        circle(cx - xWidth /4, cy - yHeight/5, sqrt(log(fsize)));
      }
      if (xSpeed>0) {
        triangle(cx - xWidth/2, cy, cx - xWidth, cy + yHeight/2, cx - xWidth, cy - yHeight/2);
        fill(0xFF000000);
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
  public void checkEat(Goldfish other) {
    if (fsize > other.fsize && collisionCheck(other) && other.alive) {
      other.die();
      fsize += other.fsize;
      hunger += log(other.fsize);
      xWidth = PApplet.parseInt(sqrt(fsize/ratio/PI));
      yHeight = PApplet.parseInt(sqrt(fsize/ratio/PI)*ratio);
    }
  }
  public boolean collisionCheck(Goldfish other) {
    if (other == this) {
      return false;
    } else {
      return(dist(this.cx, this.cy, other.cx, other.cy)
        <= (this.xWidth/2 + other.xWidth/2));
    }
  }//collisionCheck
  public void die() {
    alive = false;
  }
  public void perish() {
    alive = false;
    perished = true;
  }
  public void move(){
    cx = PApplet.parseInt(x + xWidth/2);
    cy = PApplet.parseInt(y + yHeight/2);
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
  public void display(){
    strokeWeight(0);
    fill(0,255,255);
    rect(tankX,tankY,tankW,tankH);
    fill(194,178,128);
    rect(tankX,tankY+tankH,tankW,-floorH);
    for(int i = 0; i < animals.size(); i++){
      animals.get(i).display();
    }
  }
  public void moveAnimals(){
    for(int i = 0; i < animals.size(); i++){
      animals.get(i).move();
    }
  }
  public void update(){
    for(int i = 0; i < animals.size(); i++){
      animals.get(i).update();
    }
  }
  public void addAnimal(int x, int y){
    float xSpeed = random(-2,2);
    float ySpeed = random(-2,2);
    int xSize = 50 + PApplet.parseInt(random(-10,10));
    int ySize = 25 + PApplet.parseInt(random(-5,5));
    Animal animal;
    if(y < tankY + tankH - floorH){
    animal = new Animal(x,y,xSpeed,ySpeed,xSize,ySize,this);
    animals.add(animal); 
    }
  }
  public void addAnimal(Animal a){
    animals.add(a);
  }
    public void addFish(int x, int y){
    float xSpeed = random(-2,2);
    float ySpeed = random(-2,2);
    int xSize = 50 + PApplet.parseInt(random(-10,10));
    int ySize = 25 + PApplet.parseInt(random(-5,5));
    Animal animal = new Fish(x,y,xSpeed,ySpeed,xSize,ySize,this);
    animals.add(animal);
  }
  public void addCrab(int x, int y){
    float xSpeed = random(-2,2);
    float ySpeed = random(-2,2);
    int xSize = 50 + PApplet.parseInt(random(-10,10));
    int ySize = 25 + PApplet.parseInt(random(-5,5));
    Animal animal = new Crab(x,y,xSpeed,ySpeed,xSize,ySize,this);
    animals.add(animal);
  }
  public void addClownFish(int x, int y){
    int xWidth = PApplet.parseInt(random(10,30));
    int yHeight = PApplet.parseInt (random(5,10));
    float xSpeed = PApplet.parseInt(random(-5,5));
    float ySpeed = PApplet.parseInt(random(-5,5));
    Animal animal = new clownFish(x,y,xSpeed,ySpeed,xWidth,yHeight,this);
    animals.add(animal);
  }
  public void addRock(int x, int y){
    Animal animal = new rock(x,y);
    animals.add(animal);
  }
  public void addGoldFish(int x, int y){
    Goldfish animal = new Goldfish(x,y);
  }
  public void addTurtle(int x, int y){
    Animal animal = new Turtle(x,y);
    animals.add(animal);
  }
  public void addFishFood(int x, int y){
    int xWidth = PApplet.parseInt(random(7,10));
    int yHeight = PApplet.parseInt(random(5,7));
    float xSpeed = random(-.2f,.2f);
    float ySpeed = random(-.2f,.2f);
    Animal animal = new FishFood(x,y,xSpeed,ySpeed,xWidth,yHeight,this);
    animals.add(animal);
  }
  public boolean clickedOnRock(int mx, int my){
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
  public void petRocks(int mx, int my){
    for(int i = 0; i < animals.size(); i++){
      if(animals.get(i) instanceof rock){
        rock fedrock = (rock) animals.get(i);
        fedrock.pet(mx,my);
      }
    }
  }
}
class Turtle extends Animal {
  Turtle(int x, int y) {
    super(x, y);
    xSpeed = random(.25f, .75f) * PApplet.parseInt(pow(-1, PApplet.parseInt(random(0, 2))));
    ySpeed = 2;
    xWidth = PApplet.parseInt(random(60, 80));
    yHeight = PApplet.parseInt(random(50, 55));
  }
  public void display() {
    if(alive){
      fill(0, 48, 32);
      arc(x + xWidth/3, y + yHeight/2, xWidth/1.5f, yHeight, PI, 2*PI, CHORD);
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
      arc(x + xWidth/3, y + yHeight/2, xWidth/1.5f, yHeight, 0, PI, CHORD);
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
  public boolean collisionCheck(Goldfish other) {
    return (dist(x + 5 * xWidth/6, y + yHeight/2, other.cx, other.cy)<other.xWidth/4);
  }
  public void eat(Goldfish other) {
    if (other.perished && !other.STOP && collisionCheck(other)) {
      other.STOP = true;
      yHeight += log(other.fsize) * ratio;
      xWidth += log(other.fsize);
    }
  }
  public void move(){
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
  public void update(){
    if(frameCount % 15 == 0){
      hunger--;
    }
    if(hunger <= 0){
      die();
    }
  }
  public void die(){
    alive = false;
  }
}
class clownFish extends Animal{
  boolean hungry;
  clownFish(int xpos, int ypos, float xs, float ys,int xW, int yH, Tank t){
      super(xpos,ypos,xs,ys,xW,yH,t);
      hunger = 100;
    }
  public void update(){
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
  public void move(){
    this.x += xSpeed/2;
    this.y += ySpeed/2;
    if (x <= 0 || x+xWidth >= tankX+tankW){xSpeed = xSpeed * -1;}
    if (y <= tankY || y+yHeight >=tankY+tankH-floorH){ySpeed = ySpeed * -1;}
    if(closestFood() != null && hungry){
      seek(closestFood());
    }
  }
  public void seek(Animal a){ //goes towards the food
    if(!withinX(a)){ //slows down when aligned with food
      if(a.x > this.x){
        if(xSpeed < 2){
          xSpeed += .1f;
        }
      }
      if(a.x < this.x){
        if(xSpeed > -2){
          xSpeed -= .1f;
        }
      }
    }
    else{
      if(xSpeed > 0){
        xSpeed -= .1f;
      }
      if(xSpeed < 0){
        xSpeed += .1f;
      }
    }
    if(!withinY(a)){
      if(a.y > this.y){
        if(ySpeed < 2){
          ySpeed += .1f;
        }
      }
      if(a.y < this.y){
        if(ySpeed > -2){
          ySpeed -= .1f;
        }
      }
    }
    else{
      if(ySpeed > 0){
        ySpeed -= .1f;
      }
      if(ySpeed < 0){
        ySpeed += .1f;
      }
    }
  }
  public FishFood closestFood(){
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
  public void display(){
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
class rock extends Animal {
 boolean hungry;
 rock(int xx, int yy) { //rocks must be petted to stay alive
  super(xx, yy);
  ySpeed = 2; 
  cc = 0xFF636363;
  hungry = false;
 }
 public void update(){
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
 public void move() {
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
  public void display() {
    if(hungry){
      cc = 0xFF6363CC;
    }
    else{
      cc = 0xFF636363;
    }
    fill(cc);
    noStroke();
    rect(x, y, xWidth, yHeight);
  }
  public void pet(int mx, int my){
    if(clickedOn(mx, my)){
      hunger = 100;
    }
  }
  public boolean clickedOn(int mx, int my){
    return (mx >= x && mx <= x + xWidth) && (my >= y && my <= y + yHeight);
  }
}


  public void settings() { size(600, 600); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "aquarium" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
