import java.util.ArrayList;

public class Field {
    private final Cell[][] _fieldArray;
    private final Finder _finder;
    public Field() {
        _fieldArray = new Cell[8][8];
        _finder = new Finder();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                _fieldArray[i][j] = new Cell(Colors.EMPTY, j , i);
            }
        }

        _fieldArray[3][3].changeColor(Colors.BLUE);
        _fieldArray[3][4].changeColor(Colors.RED);
        _fieldArray[4][3].changeColor(Colors.RED);
        _fieldArray[4][4].changeColor(Colors.BLUE);
    }
    public ArrayList<Cell> findAvailableCells(Colors playerColor) {
        return _finder.findAvailableCells(_fieldArray, playerColor);
    }
    public void makeTurn(Colors color, int y, int x) {
        _fieldArray[y][x].changeColor(color);
        _finder.colorAllEatenCells(_fieldArray, y, x);
    }
    public int getAmountOfColor(Colors color) {
        int answer = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                answer += _fieldArray[i][j].getColor() == color ? 1 : 0;
            }
        }
        return answer;
    }
    public Field(Field field) {
        this._fieldArray = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this._fieldArray[i][j] = new Cell(field._fieldArray[i][j]);
            }
        }
        this._finder = new Finder();
    }
    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ans.append("\t");
                ans.append(_fieldArray[i][j]);
                ans.append("\t");
                ans.append("|");
            }
            ans.append("\n\t———————————————————————————————————————————————————" +
                       "——————————\n");
        }
        return ans.toString();
    }
}