package corySandLab;

import java.awt.*;
import java.util.*;
import java.util.ArrayList;


public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  //add constants for particle types and toolshere
  
  //things to add
  //	FANS
  //	ants
  //	glass
  
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int STEAM = 4;
  public static final int FIRE = 5;
  public static final int WOOD = 6;
  public static final int CHARCOAL = 7;
  public static final int LAVA = 8;
  public static final int SNOW = 9;
  public static final int ICE = 10;
  public static final int BLACKHOLE = 11;
  public static final int BOUNCY_BALL = 12;
  public static final int FAN = 13;
  
  private static final int particleCount = 14;
  
  public static final int COOL = particleCount;
  public static final int SPARK = particleCount+1;
  public static final int HEATLENS= particleCount+2;
  public static final int DRAG= particleCount+3;
  
  
  private static final int toolCount = 4;
  
  
  public static boolean Heatview = false;
  
  int[] tools = {COOL,SPARK,HEATLENS,DRAG};
  int[] solidy = {METAL,SAND,WOOD,CHARCOAL,ICE,BLACKHOLE,BOUNCY_BALL,FAN};
  int[] sandy = {SAND,WOOD,CHARCOAL};
  int[] liquidy = {WATER,LAVA};
  int[] gassy = {STEAM,FIRE};
  
  //grid types here
  public static final int BASE = 0;
  public static final int VY = 1;
  public static final int VX = 2;
  public static final int TEMP = 3;
  
  
  //other constants
  public static final int UP = -1;
  public static final int DOWN = 1;
  
  
  ArrayList<Color> colors = new ArrayList<>();
  ArrayList<Integer> spawnTemps = new ArrayList<>();
  
  
  
  private int[][][] grid;
  private SandDisplay display;
  int numRows;
  int numCols;
  int GY;
  int GX;
  
  public SandLab(int numRows, int numCols){
    this.numRows=numRows;
    this.numCols=numCols;
    
	 
	String[] names;
    names = new String[particleCount+toolCount];
    
    names[EMPTY] = "Erase";
    colors.add(new Color(0,0,0));
    spawnTemps.add(50);
    
    names[METAL] = "Metal";
    colors.add(new Color(80,80,100));
    spawnTemps.add(50);
    
    names[SAND] = "Sand";
    colors.add(new Color(200,200,0));
    spawnTemps.add(50);
    
    names[WATER] = "Water";
    colors.add(new Color(0,0,255));
    spawnTemps.add(30);
    
    names[STEAM] = "Steam";
    colors.add(new Color(225,225,225));
    spawnTemps.add(50);
    
    names[FIRE] = "Fire";
    colors.add(new Color(255,0,0));
    spawnTemps.add(80);
    
    names[WOOD] = "Wood";
    colors.add(new Color(153,76,0));
    spawnTemps.add(50);
    
    names[CHARCOAL] = "Charcoal";
    colors.add(new Color(100,80,80));
    spawnTemps.add(50);
    
    names[LAVA] = "Lava";
    colors.add(new Color(150,0,0));
    spawnTemps.add(2000);
    
    names[SNOW] = "Snow";
    colors.add(new Color(175,175,175));
    spawnTemps.add(10);
    
    names[ICE] = "Ice";
    colors.add(new Color(100,100,200));
    spawnTemps.add(-60);
    
    
    names[BLACKHOLE] = "Black Hole";
    colors.add(new Color(100,0,100));
    spawnTemps.add(0);
    
    names[BOUNCY_BALL] = "Bouncy ball";
    colors.add(new Color(0,200,0));
    spawnTemps.add(50);
    
    names[FAN] = "Fan";
    colors.add(new Color(230,230,230));
    spawnTemps.add(50);
    
    //tools
    
    names[COOL] = "Cool";
    colors.add(new Color(0,255,0));
    
    names[SPARK] = "Spark";
    colors.add(new Color(255,0,0));
    
    names[HEATLENS] = "Heat Lens";
    colors.add(new Color(255,0,0));
    
    names[DRAG] = "Drag";
    colors.add(new Color(0,0,0));
    
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    grid = new int[4][numRows][numCols];
    
    GY=1;
    GX=0;
    
    for(int row = 0; row<numRows;row++) {
    	Arrays.fill(grid[VY][row], 0);
    	Arrays.fill(grid[VX][row], 0);
    	
    	Arrays.fill(grid[TEMP][row], 50);	
    }
    
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool){
	  boolean particality = true;
	  for(int notParticle : tools) {
		  if(tool == notParticle) particality = false;
	  }
	  if(particality) {
		  grid[TEMP][row][col] = spawnTemps.get(tool);  
		  grid[BASE][row][col] = tool;
	  }
	  switch(tool){
	  	case COOL:
	  		grid[TEMP][row][col]-=100;
	  		break;
	  	case SPARK:
	  		grid[TEMP][row][col]=250;
	  		break;
	  	case HEATLENS:
	  		Heatview = true;
//	  		System.out.println("TEMP: "+ grid[TEMP][row][col]);
	  		break;
	  	case DRAG:
	  		pull(col, row, 10);
	  		break;
	  }
  }

  //copies each element of grid into the display
  public void updateDisplay(){
	  float red;
	  float blue;
	  for(int row=0;row<grid[BASE].length;row++) {
		  for(int col=0;col<grid[BASE][row].length;col++) {
			  display.setColor(row, col, colors.get(grid[BASE][row][col]));
			  
			  if(Heatview) {
				  if(grid[TEMP][row][col]>1500)red = (float)1;
				  else if(grid[TEMP][row][col]<100)red =(float)0;
				  else red= (float) (grid[TEMP][row][col]/1500.0);
				  
				  if(grid[TEMP][row][col]>50)blue = (float)0;
				  else if(grid[TEMP][row][col]<-50)blue= (float)1;
				  else blue = (float) ((float) (50-grid[TEMP][row][col])/100.0);
				  
				  display.setColor(row, col, new Color(red, (float)0.2, blue, (float)0.6));
			  }
		  }
	  }
	  Heatview=false;
  }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step(){
	  int row = (int)(Math.random()*numRows);
	  int col = (int)(Math.random()*numCols);
	  for(int i =0; i<8;i++)heatDissipate(col, row);
	  
	  switch(grid[BASE][row][col]) {
	  	case SAND: 
	  		friction(col, row);
	  		sandTempLogic(col,row);
	  		gravity(col,row,SAND);
	  		break;
	  	case WOOD:
	  		friction(col, row);
	  		woodTempLogic(col,row);
	  		gravity(col,row,WOOD);
	  		break;
	  	case WATER: 
	  		friction(col, row);
	  		waterTempLogic(col, row);
	  		gravity(col, row, WATER);
	  		break;
	  	case STEAM: 
	  		friction(col, row);
	  		steamTempLogic(col,row);
	  		gravity(col,row,STEAM);
	  		break;
	  	case FIRE:
	  		friction(col, row);
	  		fireTempLogic(col,row);
	  		if(Math.random()<.6)gravity(col,row,FIRE);
	  		break;
	  	case CHARCOAL:
	  		friction(col, row);
	  		charcoalTempLogic(col,row);
	  		gravity(col,row,CHARCOAL);
	  		break;
	  	case LAVA:
	  		friction(col, row);
	  		lavaTempLogic(col,row);
	  		gravity(col,row,LAVA);
	  		break;
	  	case METAL:
	  		friction(col, row);
	  		metalTempLogic(col,row);
	  		break;
	  	case SNOW:
	  		friction(col, row);
	  		snowTempLogic(col,row);
	  		snow(col,row);
	  		break;
	  	case ICE:
	  		friction(col, row);
	  		iceTempLogic(col,row);
	  		break;
	  	case BLACKHOLE:
	  		friction(col, row);
	  		pull(col,row,20);
	  		break;
	  	case BOUNCY_BALL:
	  		bounce(col,row);
	  		break;
	  	case FAN:
	  		blow(col,row,10,5);
	  	default:
	  		friction(col, row);
	  		break;
	  }
  }
  
  private boolean isSolid(int x, int y) {
	  if(x<0 || numCols<=x || y<0 || numRows<=y) {
		  return true;
	  }
	  int currentMaterial = grid[BASE][y][x];
	  for(int solidMaterial : solidy) {
		  if(currentMaterial == solidMaterial) {
			  return true;
		  }
	  }
	  return false;
	  
  }

  private boolean isMaterial(int x, int y, int material) {
	  if(x<0 || numCols<=x || y<0 || numRows<=y) {
		  return false;
	  }
	  int currentMaterial = grid[BASE][y][x];
	  if(currentMaterial == material) {
		  return true;
	  }
	  return false;
	  
  }
  
  
  private void gravity(int x, int y, int material) {
	  if(grid[BASE][y][x] != material)return;
	  
	  for(int sandyMaterial : sandy) {
		  if(material == sandyMaterial) {
			  if(fall(x,y,material,DOWN,false))return;
			  friction(x, y);
			  if(pile(x,y,material,DOWN))return;
			  grid[VX][y][x]=0;
			  grid[VY][y][x]=0;
		  }
	  }
	  for(int liquidyMaterial : liquidy) {
		  if(material == liquidyMaterial) {
			  if(fall(x,y,material,DOWN,true))return;
			  friction(x, y);
			  if(pour(x,y,material,DOWN))return;
			  grid[VX][y][x]=0;
			  grid[VY][y][x]=0;
		  }
	  }
	  for(int gassyMaterial : gassy) {
		  if(material == gassyMaterial) {
			  if(fall(x,y,material,UP,true))return;
			  friction(x, y);
			  if(pour(x,y,material,UP))return;
			  grid[VX][y][x]=0;
			  grid[VY][y][x]=0;
		  }
	  }
	  
	  
  }
  
  private void move(int Xi,int Yi,int Xf,int Yf) {
	  int material = grid[BASE][Yi][Xi];
	  grid[BASE][Yi][Xi] = grid[BASE][Yf][Xf];
	  grid[BASE][Yf][Xf] = material;
		  
	  int tempTemp = grid[TEMP][Yi][Xi];
  	  grid[TEMP][Yi][Xi] = grid[TEMP][Yf][Xf];
  	  grid[TEMP][Yf][Xf]=tempTemp;
  	  
  	  int tempVY= grid[VY][Yi][Xi];
	  grid[VY][Yi][Xi] = grid[VY][Yf][Xf];
	  grid[VY][Yf][Xf]=tempVY;
	  
	  int tempVX= grid[VX][Yi][Xi];
	  grid[VX][Yi][Xi] = grid[VX][Yf][Xf];
	  grid[VX][Yf][Xf]=tempVX;
	  
  }
  
  private boolean pile(int x, int y, int material, int direction) {
	  int dir = 0;
	  
	  
	  int max_yv = grid[VY][y][x]+GY*direction;
	  int max_xv = grid[VX][y][x]+GX*direction;
	  int signy = (int)Math.signum(max_yv);
	  int signx = (int)Math.signum(max_xv);
	  
	  if(Math.random()>((Math.abs(max_yv)+0.0)/(Math.abs(max_xv)+Math.abs(max_yv)))) {
		  if(Math.random()<(1.0/3)) dir = signy;
		  else if(Math.random()<(1.0/3)) dir = 0;
		  else dir = -1*signy;
		  
		  if(!isSolid(x+signx, y+dir) && !isMaterial(x+signx, y+dir, material)) {
			  grid[VY][y][x]+=dir;
			  move(x,y,x+signx,y+dir);
	  		  return true;
		  }
		  else if(!isSolid(x+signx, y-dir) && !isMaterial(x+signx, y-dir, material)) {
			  grid[VY][y][x]-=dir;
			  move(x,y,x+signx,y-dir);
			  return true;
		  }
		  
	  }
	  else {
		  if(Math.random()<(1.0/3)) dir = signy;
		  else if(Math.random()<(1.0/3)) dir = 0;
		  else dir = -1*signy;
		  
		  if(!isSolid(x+dir, y+signy) && !isMaterial(x+dir, y+signy, material)) {
			  grid[VX][y][x]+=dir;
			  move(x, y, x+dir, y+signy);
			  return true;
		  }
		  else if(!isSolid(x-dir, y+signy)&& !isMaterial(x-dir, y+signy, material)) {
			  grid[VX][y][x]-=dir;
			  move(x, y, x-dir, y+signy);
			  return true;
		  }
		  
	  }
	  return false;
  }
  
  private boolean pour(int x, int y, int material, int direction) {
	  int dir = 0;
	  
	  
	  int max_yv = grid[VY][y][x]+GY*direction;
	  int max_xv = grid[VX][y][x]+GX*direction;
	  int signy = (int)Math.signum(max_yv);
	  int signx = (int)Math.signum(max_xv);
	  
	  if(Math.random()>((Math.abs(max_yv)+0.0)/(Math.abs(max_xv)+Math.abs(max_yv)))) {
		  if(Math.random()<(1.0/3)) dir = signx;
		  else if(Math.random()<(1.0/3)) dir = 0;
		  else dir = -1*signx;
		  
		  if(!isSolid(x+signx, y+dir) && isMaterial(x+signx, y+dir, EMPTY)) {
			  move(x, y, x+signx, y+dir);
	  		  return true;
		  }
		  else if(!isSolid(x+signx, y-dir) && isMaterial(x+signx, y-dir, EMPTY)) {
			  move(x, y, x+signx, y-dir);
	  		  return true;
		  }
		  else if(!isSolid(x, y-dir) && isMaterial(x, y-dir, EMPTY)) {
			  move(x, y, x, y-dir);
	  		  return true;
		  }
		  
	  }
	  else {
		  if(Math.random()<(1.0/3)) dir = signy;
		  else if(Math.random()<(1.0/3)) dir = 0;
		  else dir = -1*signy;
		  
		  if(!isSolid(x+dir, y+signy) && isMaterial(x+dir, y+signy, EMPTY)) {
			  move(x, y, x+dir, y+signy);
	  		  return true;
		  }
		  else if(!isSolid(x-dir, y+signy)&& isMaterial(x-dir, y+signy, EMPTY)) {
			  move(x, y, x-dir, y+signy);
			  return true;
		  }
		  else if(!isSolid(x+dir, y)&& isMaterial(x+dir, y, EMPTY)) {
			  move(x, y, x+dir, y);
	  		  return true;
		  }
	  }
	  return false;
  }
  
  private boolean fall(int x, int y, int material, int direction,boolean liquid) {
	int max_yv = grid[VY][y][x]+GY*direction;
	int max_xv = grid[VX][y][x]+GX*direction;
	int signy = (int)Math.signum(max_yv);
	int signx = (int)Math.signum(max_xv);
	
	if(!isSolid(x+max_xv,y+max_yv) && ((!liquid && !isMaterial(x+max_xv,y+max_yv, material)) || (liquid && !isMaterial(x+max_xv,y+max_yv, material)))) {
	  move(x, y, x+max_xv, y+max_yv);
	  return true;
	}
  
	if(!isSolid(x+signx,y+signy) && ((!liquid && !isMaterial(x+signx,y+signy, material) || (liquid && !isMaterial(x+signx, y+signy, material))))) {
		move(x, y, x+signx, y+signy);
		return true;
	}
	return false;
  }
  
  
  private void bounce(int x, int y) {
	double elasticity = .8;
	grid[VY][y][x] += GY;
	grid[VX][y][x] += GX;
	int signy = (int)Math.signum(grid[VY][y][x]);
	int signx = (int)Math.signum(grid[VX][y][x]);
	
	//fall
	if(!isSolid(x+signx,y+signy)) {
		move(x, y, x+signx, y+signy);
		return;
	}
	
	//diagonal bounce
	
	if(isSolid(x+1, y+signy) && !isSolid(x-1, y+signy))
		grid[VX][y][x]-=1;
	
	if(isSolid(x-1, y+signy) && !isSolid(x+1, y+signy))
		grid[VX][y][x]+=1;
	
	if(isSolid(x+signx, y+1) && !isSolid(x+signx, y-1))
		grid[VY][y][x]+=1;
	
	if(isSolid(x+signx, y-1) && !isSolid(x+signx, y+1))
		grid[VY][y][x]-=1;
	
//		grid[VY][y][x]=grid[VY][y][x]/2;
//		grid[VX][y][x]=grid[VY][y][x]/2;
	
	
	//normal bounce
	if(isSolid(x, y+signy)) {
		grid[VY][y][x] = (int) (-grid[VY][y][x]*elasticity);
		return;
	}
	if(isSolid(x+signx,y)) {
		grid[VX][y][x] = (int) (-grid[VX][y][x]*elasticity);
		return;
	}
	if(isSolid(x+signx,y+signy)) {
		grid[VY][y][x] = (int) (-grid[VY][y][x]*elasticity);
		grid[VX][y][x] = (int) (-grid[VX][y][x]*elasticity);
	}
	
	
  }
  
  private void blow(int x, int y,int distance, int power) {
	  for(int i = 1; i < distance;i++) {
		  if(x+i<numCols && power-i>1)grid[VX][y][x+i]+=power-i;
		  else if(x+i<numCols)grid[VX][y][x+i]+=2;
		  if(isSolid(x+i, y))break;
	  }
	  for(int i = 1; i < distance;i++) {
		  if(y+i<numRows && power-i>1)grid[VY][y+i][x]+=power-i;
		  else if(y+i<numRows)grid[VY][y+i][x]+=2;
		  if(isSolid(x, y+i))break;
	  }
	  for(int i = -distance; i < 0;i++) {
		  if(x+i>=0 && power-i<-1)grid[VX][y][x+i]+=-(i+power);
		  else if(x+i>=0)grid[VX][y][x+i]+=-2;
		  if(isSolid(x+i, y))break;
	  }
	  
	  for(int i = -distance; i < 0;i++) {
		  if(y+i>=0&& power-i<-1)grid[VY][y][x+i]+=-(i+power);
		  else if(y+i>=0)grid[VY][y][x+i]+=-2;
		  if(isSolid(x, y+i))break;
	  }
	  
	  
  }
  private void snow(int x, int y) {
	  if(Math.random()<.5)return;
	  if(grid[BASE][y][x] != SNOW)return;
	  
	  int max_yv = grid[VY][y][x]+GY*DOWN;
	  int max_xv = grid[VX][y][x]+GX*DOWN;
	  int signy = (int)Math.signum(max_yv);
	  int signx = (int)Math.signum(max_xv);
	  int dir;
	  
	  if(isMaterial(x+signx, y+signy, WATER))fall(x, y, SNOW, UP, false);
	  
	  
	  if((x+y)%2==1) {
		 if(fall(x,y,SNOW,DOWN,false))return;
	  }
	  
	  
	  
	  if(Math.random()>((Math.abs(max_yv)+0.0)/(Math.abs(max_xv)+Math.abs(max_yv)))) {
		  if(Math.random()<(1.0/2)) dir = signx;
		  else dir = -1*signx;
		  if(isMaterial(x+signx, y+dir,SNOW) || isMaterial(x+signx, y-dir,SNOW))return;
		  
		  if(!isSolid(x+signx, y+dir) && !isMaterial(x+signx, y+dir, SNOW)) {
			  move(x, y, x+signx, y+dir);
	  		  return;
		  }
		  
	  }
	  else {
		  if(Math.random()<(1.0/2)) dir = signy;
		  else dir = -1*signy;
		  
		  if(isMaterial(x+dir, y+signy,SNOW) || isMaterial(x-dir, y+signy,SNOW)) return;
		  
		  if(!isSolid(x+dir, y+signy) && !isMaterial(x+dir, y+signy, SNOW)) {
			  move(x, y, x+dir, y+signy);
	  		  return;
		  }
	  }
	  
  }
  
  private void heatDissipate(int x, int y) {
	  double thermalResistance = 40.0;
	  
	  switch((int)(Math.random()*12)) {
	  
	  	case 9:
	  		
	  		int ydir = (int) Math.signum(grid[VY][y][x]);
	  		int xdir = (int) Math.signum(grid[VX][y][x]);
	  		
	  		if(0<(x-xdir) && (x-xdir)<numCols && 0<(y-ydir) && (y-ydir)<numRows) {
		  		  if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y-ydir][x-xdir])/thermalResistance)) {
		  			  grid[TEMP][y][x]-=1;
		  			  grid[TEMP][y-ydir][x-xdir]+=1;
		  		  }
		  		}
	  		break;
	  	case 8: 
	  		if(y>0) {
	  		  if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y-1][x])/thermalResistance)) {
	  			  grid[TEMP][y][x]-=1;
	  			  grid[TEMP][y-1][x]+=1;
	  		  }
	  		}
	  		break;
	  	case 1:
	  		if(y<(numRows-1)) {
	  		  if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y+1][x])/thermalResistance)) {
	  			  grid[TEMP][y][x]-=1;
	  			  grid[TEMP][y+1][x]+=1;
	  		  }
	  		}
	  		break;
	  	case 2:
	  		if(x>0&&y>0) {
	  			if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y-1][x-1])/thermalResistance)) {
					  grid[TEMP][y][x]-=1;
					  grid[TEMP][y-1][x-1]+=1;
				  }
	  		}
	  		break;
	  	case 3:
	  		if(x>0) {
	  			if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y][x-1])/thermalResistance)) {
	  			  grid[TEMP][y][x]-=1;
	  			  grid[TEMP][y][x-1]+=1;
	  		  }
	  		}
	  		break;
	  	case 4:
	  		if(x>0 &&y<(numRows-1)) {
	  			if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y+1][x-1])/thermalResistance)) {
					  grid[TEMP][y][x]-=1;
					  grid[TEMP][y+1][x-1]+=1;
				  }
	  		}
	  		break;
	  	case 5:
	  		if(x<(numCols-1)&&y>0) {
	  			if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y-1][x+1])/thermalResistance)) {
					  grid[TEMP][y][x]-=1;
					  grid[TEMP][y-1][x+1]+=1;
				  }
	  		}
	  		break;
	  	case 6:
	  		if(x<(numCols-1)) {
	  			if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y][x+1])/thermalResistance)) {
	  			  grid[TEMP][y][x]-=1;
	  			  grid[TEMP][y][x+1]+=1;
	  			}
	  		}
	  		break;
	  	case 7:
	  		if(x<(numCols-1)&&y<(numRows-1)) {
	  			if(Math.random()<((grid[TEMP][y][x]-grid[TEMP][y+1][x+1])/thermalResistance)) {
					  grid[TEMP][y][x]-=1;
					  grid[TEMP][y+1][x+1]+=1;
				  }
	  		}
	  		break;
	  }
  }
  
  
  private void sandTempLogic(int x, int y) {
	  //stuff
  }

  private void waterTempLogic(int x,int y) {
	  int temp = grid[TEMP][y][x];
	  int signy = (int)Math.signum(grid[VY][y][x])+GY;
	  int signx = (int)Math.signum(grid[VX][y][x])+GX;
	  
	  if(temp >= 150) {
		  grid[BASE][y][x] = STEAM;
		  grid[TEMP][y][x] = 60;
		  if(0<=(x+signx) && numRows>(x+signx) && 0<=(y+signy) && numCols>(y+signy)) grid[TEMP][y+signy][x+signx] = 20;
	  }
	  
	  if(temp<30) {
//		  System.out.print("not solid: "+ !isSolid(x+signx, y+signy));
//		  System.out.print(", Not water: "+ !isMaterial(x+signx, y+signy, WATER));
//		  System.out.println(",not ice: "+ !isMaterial(x+signx, y+signy, ICE));
		  if(!isSolid(x+signx, y+signy) && !isMaterial(x+signx, y+signy, ICE)) grid[BASE][y][x] = SNOW;
		  else grid[BASE][y][x]=ICE;
	  }
	  
	  
  }
  
  private void iceTempLogic(int x, int y) {
	  int temp = grid[TEMP][y][x];
	  
	  if(temp>0) {
		  if(Math.random()<.1)grid[TEMP][y][x]-=1;
	  }
	  
	  if(temp >= 30) {
		  grid[BASE][y][x] = WATER;
	  }
  }
  private void snowTempLogic(int x,int y) {
	  int temp = grid[TEMP][y][x];
	  int signy = (int)Math.signum(grid[VY][y][x])+GY;
	  int signx = (int)Math.signum(grid[VX][y][x])+GX;
	  
	  if(temp<=5 && (isSolid(x+signx, y+signy) || isMaterial(x+signx, y+signy, WATER))) grid[BASE][y][x] = ICE;
	  
	  if(temp >= 30) {
		  grid[BASE][y][x] = WATER;
	  }
  }
  private void steamTempLogic(int x, int y) {
	  int temp = grid[TEMP][y][x];
	  if(Math.random()<.03)grid[TEMP][y][x]-= 1;
	  if(temp <= 30) {
		  grid[BASE][y][x] = WATER;
		  grid[TEMP][y][x]+= 70;
	  }
  }

  private void fireTempLogic(int x, int y) {
	  grid[TEMP][y][x]+= 3;
	  if(Math.random()<0.06 || isSolid(x, y-1)) {
		  grid[BASE][y][x] = EMPTY;
	  }
  }

  private void woodTempLogic(int x, int y) {
	  int temp = grid[TEMP][y][x];
	  if(temp>110) {
		  int signy = (int)Math.signum(grid[VY][y][x]);
		  int signx = (int)Math.signum(grid[VX][y][x]);
		  
//		  grid[TEMP][y][x]-=1;
		  if(!isSolid(x-signx,y-signy) && isMaterial(x-signx, y-signy,EMPTY)) {
			  grid[BASE][y-signy][x-signx] = FIRE;
			  grid[TEMP][y][x]+=5;
		  }
	  }
	  if(temp>200) {
		  grid[BASE][y][x] = CHARCOAL;
	  }
	  
  }
  private void charcoalTempLogic(int x, int y) {
	  int temp = grid[TEMP][y][x];
	  if(temp>170) {
		  int signy = (int)Math.signum(grid[VY][y][x]+GY);
		  int signx = (int)Math.signum(grid[VX][y][x]+GX);
		  
		  grid[TEMP][y][x]-=2;
		  if(!isSolid(x-signx,y-signy) && (isMaterial(x-signx, y-signy,EMPTY) || isMaterial(x-signx, y-signy,FIRE))) grid[BASE][y-signy][x-signx] = FIRE;
	  }
	  if(temp>300) {
		  grid[TEMP][y][x]=100;
		  grid[BASE][y][x] = EMPTY;
	  }
  }
  private void lavaTempLogic(int x, int y) {
	  int signy = (int)Math.signum(grid[VY][y][x]);
	  int signx = (int)Math.signum(grid[VX][y][x]);
	  
	  grid[TEMP][y][x]-=35;
	  if(0<=(x+signx) && numRows>(x+signx) && 0<=(y+signy) && numCols>(y+signy)) grid[TEMP][y+signy][x+signx] +=300;
	  else grid[TEMP][y][x]+=30;
	  
	  if(grid[TEMP][y][x]<800)grid[BASE][y][x] = METAL;
	  
  }

  private void metalTempLogic(int x, int y) {
	  
	  for(int i=0;i<30;i++)heatDissipate(x, y);
	  
	  if(grid[TEMP][y][x]>1000)grid[BASE][y][x] = LAVA;
	  
  }
  
  
  //particles go faster causing them to have less time to slow down on the other side, zipping past
  private void pull(int x, int y,int pull_distance) {
	  int ymax=pull_distance;
	  int xmax=pull_distance;
	  int ymin=-pull_distance;
	  int xmin=-pull_distance;
	  
	  if(y+pull_distance>=numRows)ymax = numRows-y-1;
	  if(y-pull_distance<0)ymin = -y;
	  if(x+pull_distance>=numCols)xmax = numCols-x-1;
	  if(x-pull_distance<0)xmin = -x;
	  
	  
	  for(int i = ymax;i>=ymin;i--) {
		  for(int j = xmax;j>=xmin;j--) {
			  if(Math.sqrt(i*i+j*j)<pull_distance) {				  
				  grid[VY][y+i][x+j] = -i;
				  grid[VX][y+i][x+j] = -j;
			  }
		  }
	  }
  }
  
  
  private void friction(int x, int y){
	  double friction_coef = 3.0;
	  int friction_y=(int) ((Math.abs(grid[VY][y][x])+friction_coef)/friction_coef);
	  int friction_x=(int) ((Math.abs(grid[VX][y][x])+friction_coef)/friction_coef);
	  
	  grid[VY][y][x]-=friction_y*Math.signum(grid[VY][y][x]);
	  grid[VX][y][x]-=friction_x*Math.signum(grid[VX][y][x]);
  }
  
  //do not modify
  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}