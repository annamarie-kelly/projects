//recursive way to solve
#include <iostream>
#include <string>

using namespace std;

//CHANGE THESE NUMBERS LATER
int maze[10][10]; //THE MAZE
bool wasHere[10][10];
bool correctWay[10][10]; //THE MAZE SOLUTION

int startX, startY, endX, endY;

bool recursiveSolve(int x, int y);

void solve() {
	//make a function to generate maze

	for (int r = 0; r < 10; r++) //sets up values
	{
		for (int c = 0; c < 10; c++)
		{
			wasHere[r][c] = false;
			correctWay[r][c] = false;
		}
	}
	bool b = recursiveSolve(startX, startY);
	//if b = false, then maze is unsolvable
	if (b == true) {
		cout << "THE INVISIBLE MAZE WORKED!!" << endl;
	}
}

bool recursiveSolve(int x, int y) 
{
	if (x == endX && y == endY)
		return true;
	if (maze[x][y] == 2 || wasHere[x][y])
		return false;
	wasHere[x][y] = true;
	if (x != 0) { // Checks if not on left edge
		if (recursiveSolve(x - 1, y)) { // Recalls method one to the left
			correctWay[x][y] = true;
			return true;
		}
	}
	if (x != 10 - 1) { // Checks if not on right edge
		if (recursiveSolve(x + 1, y)) {// Recalls method one to the right
			correctWay[x][y] = true;
			return true;
		}
	}
	if (y != 0) { // Checks if not on top edge
		if (recursiveSolve(x, y - 1)) { // Recalls method one up
			correctWay[x][y] = true;
			return true;
		}
	}
	if (y != 10 - 1) { // Checks if not on bottom edge
		if (recursiveSolve(x, y + 1)) { // Recalls method one down
			correctWay[x][y] = true;
			return true;
		}
	}
	return false;
}
int main()
{
	solve();
}