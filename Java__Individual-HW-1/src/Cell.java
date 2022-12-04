public class Cell {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private Colors _color;
    public int X;
    public int Y;
    public Cell(Colors color, int x, int y) {
        _color = color;
        X = x;
        Y = y;
    }
    public boolean isAvailable;
    public Colors getColor() {
        return _color;
    }
    public void changeColor(Colors color) {
        _color = color;
    }
    public boolean isFree() {
        return (_color == Colors.EMPTY);
    }
    public Cell (Cell cell) {
        this._color = cell._color;
        this.X = cell.X;
        this.Y = cell.Y;
        this.isAvailable = cell.isAvailable;
    }
    @Override
    public String toString() {
        if (_color == Colors.EMPTY) {
            if (isAvailable) {
                return (ANSI_GREEN + "○" + ANSI_RESET);
            }
            return "○";
        }
        if (_color == Colors.RED) {
            return (ANSI_RED + "●" + ANSI_RESET);
        }
        return (ANSI_BLUE + "●" + ANSI_RESET);
    }
}
