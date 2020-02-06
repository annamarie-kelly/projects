package snake;

import java.util.LinkedList;
import java.util.Random;

import processing.core.PApplet;


public class Snake extends PApplet {

	public int red = 255;
	public int blue = 255;
	public int green = 255;
	public Random cg = new Random();
	public boolean moveRight = false;
	public boolean moveLeft = false;
	public boolean moveDown = false;
	public boolean moveUp = false;
	public boolean gameover = false;;
	public LinkedList<Food> foods;
	public int speed = 3;
	public SnakeBody snake;
	public int score;
	public ScoreBoard sd = new ScoreBoard();

	public class Coordinate
	{
		public int xPos;
		public int yPos;
		public Coordinate(int x, int y)
		{
			xPos = x;
			yPos = y;
		}
		public int getX()
		{
			return xPos;
		}
		public int getY()
		{
			return yPos;
		}
		public void setX(int x)
		{
			xPos = x;
		}
		public void setY(int y)
		{
			yPos = y;
		}
	}

	public class SnakeBody
	{
		LinkedList<Coordinate> body;
		private char direction;
		public SnakeBody()
		{
			body = new LinkedList<Coordinate>();
			direction = 'u';
			body.add(new Coordinate(400, 400));
			body.add(new Coordinate(350, 400));
		}

		public void drawSnake()
		{
			int headX = body.getFirst().getX();
			int headY = body.getFirst().getY();
			for(int i = 1; i < body.size(); i++)
			{
				int x = body.get(i).getX();
				int y = body.get(i).getY();
				stroke(255);
				fill(255);
				rect(x, y, 15, 15);
			}
			stroke(red, green, blue);
			fill(red, green, blue);
			rect(headX, headY, 15, 15);
		}
		public void setDirection()
		{
			if(moveRight)
				direction = 'r';
			if(moveLeft)
				direction = 'l';
			if(moveUp)
				direction = 'u';
			if(moveDown)
				direction = 'd';	
		}
		public void move()
		{
			//Coordinate last = body.getLast();
			Coordinate newC = new Coordinate(0, 0);
			body.removeLast();
			this.setDirection();
			switch(direction)
			{
			case 'u':
				newC.setY(body.getFirst().getY()-speed);
				newC.setX(body.getFirst().getX());
				break;
			case 'd':
				newC.setY(body.getFirst().getY()+speed);
				newC.setX(body.getFirst().getX());
				break;
			case 'l':
				newC.setY(body.getFirst().getY());
				newC.setX(body.getFirst().getX()-speed);
				break;
			case 'r':
				newC.setY(body.getFirst().getY());
				newC.setX(body.getFirst().getX()+speed);
				break;
			}
			body.add(0, newC);
			ate();
			
		}

		public LinkedList<Coordinate> getBody()
		{
			return body;
		}
		public void checkBounds() 
		{
			int headX = body.getFirst().getX();
			int headY = body.getFirst().getY();
			if(headX < 0 || headX > width - 25 || headY < 0 || headY > height - 25)
				gameover = true;
		}

		public void touchingBody()
		{
			int headX = body.getFirst().getX();
			int headY = body.getFirst().getY();
			for(int i = 1; i < body.size(); i++)
			{
				if(headX == body.get(i).getX() && headY == body.get(i).getY())
				{
					gameover = true;
				}
			}
			System.out.println("touching self?");
		}

		public void ate()
		{
			int headX = body.getFirst().getX();
			int headY = body.getFirst().getY();
			int foodX = foods.getFirst().getX();
			int foodY = foods.getFirst().getY();
			if(foodX + 10 > headX && foodX < headX + 25){
				if(foodY + 10 > headY && foodY < headY + 25){
					Coordinate newBody = new Coordinate(0, 0);
					int newX = 0;
					int newY = 0;
					Coordinate last = body.getLast();
					switch(direction)
					{
					case 'u':
						newX = last.getX();
						newY = last.getY() + 2 * speed;
						break;
					case 'd':
						newX = last.getX();
						newY = last.getY() - 2 * speed;
						break;
					case 'l':
						newX = last.getX() + 2 * speed;
						newY = last.getY();
						break;
					case 'r':
						newX = last.getX() - 2 * speed;
						newY = last.getY();
						break;
					}
					newBody.setX(newX);
					newBody.setY(newY);

					body.add(newBody);
					for(int i = 2; i < 6; i++)
					{
						Coordinate nB = new Coordinate(newBody.getX(), newBody.getY());
						switch(direction)
						{
						case 'u':
							nB.setX(newBody.getX());
							nB.setY(newBody.getY() + (i * speed));
							break;
						case 'd':
							nB.setX(newBody.getX());
							nB.setY(newBody.getY() - (i * speed));
							break;
						case 'l':
							nB.setX(newBody.getX() + (i * speed));
							nB.setY(newBody.getY());
							break;
						case 'r':
							nB.setX(newBody.getX() - (i * speed));
							nB.setY(newBody.getY());
							break;
						}
						body.add(nB);
					}
					foods.remove();
					Food newFood = new Food();
					foods.add(newFood);
					speed += .6;
					score += 10;
					red = cg.nextInt(200)+100;
					green = cg.nextInt(200)+100;
					blue = cg.nextInt(200)+100;
					
				}
			}
		}
	}

	public class Food
	{
		private int x;
		private int y;
		private Random r;

		public Food()
		{
			r = new Random();

			boolean needNewValue = false;
			do
			{
				x = r.nextInt(550)+ 200;
				y = r.nextInt(550) + 200;
				for(Coordinate i: snake.getBody())
				{
					if(x + 15 > i.getX() && x < i.getX() + 15){
						if(y + 15 > i.getY() && y < i.getY() + 15){
							needNewValue = true;
						}
					}else{
						needNewValue = false;
					}
				}

			}while(needNewValue);
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}

		public void drawFood()
		{
			stroke(255, 0, 0);
			fill(255, 0, 0);
			rect(x, y, 10, 10);
		}
	}


	public void keyPressed()
	{
		if(key == '8' && !moveDown)
		{
			moveUp = true;
			moveDown = false;
			moveLeft = false;
			moveRight = false;

		}
		if(key == '5' && !moveUp)
		{
			moveUp = false;
			moveDown = true;
			moveLeft = false;
			moveRight = false;
		}
		if(key == '4' && !moveRight)
		{
			moveUp = false;
			moveDown = false;
			moveLeft = true;
			moveRight = false;
		}
		if(key == '6' && !moveLeft)
		{
			moveUp = false;
			moveDown = false;
			moveLeft = false;
			moveRight = true;
		}
	}

	public void setup()
	{
		size(800, 800);
		snake = new SnakeBody();
		foods = new LinkedList<Food>();
		foods.add(new Food());
	}

	public void draw() 
	{
		if(!gameover)
		{
			background(0, 0, 0);
			snake.checkBounds();
			snake.touchingBody();
			snake.move();
			sd.DrawScoreBoard();
			snake.drawSnake();
			foods.getFirst().drawFood();
		}else{
			background(red, green, blue);
			textSize(20);
			fill(245, 27,89);
			text("Score: " + score, 276, 360);	
			textSize(42);
			fill(245, 27,89);
			text("G@Me 0vEr U $uX!", 250, 400);	
			textSize(20);
			fill(245, 27,89);
			text("Id wih u luck next time but ure gonna need a lot more than that with ur skill", 20, 425);		
		}
	}

	public class ScoreBoard
	{
		private String ScoreText = "";

		public ScoreBoard()
		{
			ScoreText = "Score " + score;
		}

		public void DrawScoreBoard()
		{
			fill(255);
			ScoreText = "Score: " + score;
			textSize(20);
			text(ScoreText, 5, 18);
		}
	}
}