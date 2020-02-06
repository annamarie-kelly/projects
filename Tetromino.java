import java.awt.Color;

enum Tetromino {
	NoShape(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },Color.BLACK),
	ZShape(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, Color.RED),
	SShape(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, Color.GREEN),
	LineShape(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, Color.CYAN),
	TShape(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, Color.MAGENTA),
	SquareShape(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, Color.YELLOW),
	LShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, Color.ORANGE.darker()),
	MirroredLShape(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, Color.BLUE);


	public int[][] coords;
	public Color color;

	private Tetromino(int[][] coords, Color c) {
		this.coords = coords;
		color = c;
	}
}