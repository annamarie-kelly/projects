import java.util.Random;

public class Shape {
  private Tetromino currShape;
  private int[][] coords;
 
  public Shape() {
    coords = new int[4][2];
    setShape(Tetromino.NoShape);
  }
 
  public void setShape(Tetromino shape) {
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 2; ++j) {
        coords[i][j] = shape.coords[i][j];
      }
    }
    currShape = shape;
  }
 
  private void setX(int index, int x) {
    coords[index][0] = x;
  }
 
  private void setY(int index, int y) {
    coords[index][1] = y;
  }
 
  public int x(int index) {
    return coords[index][0];
  }
 
  public int y(int index) {
    return coords[index][1];
  }
 
  public Tetromino getShape() {
    return currShape;
  }
 
  public void setRandomShape() {
    Random r = new Random();
    Tetromino[] values = Tetromino.values();
    setShape(values[Math.abs(r.nextInt()) % 7 + 1]);
  }
 
  public int minX() {
    int min = coords[0][0];
    for(int i = 0; i < 4; i++)
      min = Math.min(min, coords[i][0]);
    return min;
  }
 
  public int minY() {
    int min = coords[0][1];
    for(int i = 0; i < 4; i++)
      min = Math.min(min, coords[i][1]);
    return min;
  }
 
  public Shape rotateLeft() {
    if(currShape == Tetromino.SquareShape) //cannot rotate
      return this;
 
    Shape newShape = new Shape();
    newShape.currShape = currShape;
 
    for(int i = 0; i < 4; i++) { 
      newShape.setX(i, y(i));
      newShape.setY(i, -x(i));
    }
    
    if(newShape.equals(currShape))
    	rotateRight();
 
    return newShape;
  }
 
  public Shape rotateRight() {
    if (currShape == Tetromino.SquareShape) //cannot rotate
      return this;
 
    Shape result = new Shape();
    result.currShape = currShape;
 
    for (int i = 0; i < 4; i++) {
      result.setX(i, -y(i));
      result.setY(i, x(i));
    }
 
    return result;
  }
  
 
}