import java.util.ArrayList;

public class Finder {
    private void colorHorizontals(Cell[][] _fieldArray, int i, int j) {
        Colors playerColor = _fieldArray[i][j].getColor();
        ArrayList<Cell> cellsToColor = new ArrayList<Cell>();
        int counter = 0;
        for (int n = i - 1; n >= 0; n--) {
            if (!_fieldArray[n][j].isFree()) {
                if (_fieldArray[n][j].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[n][j]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int k = 1; k <= counter; k++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        counter = 0;
        for (int n = i + 1; n < 8; n++) {
            if (!_fieldArray[n][j].isFree()) {
                if (_fieldArray[n][j].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[n][j]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int k = 1; k <= counter; k++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        counter = 0;
        for (int m = j - 1; m >= 0; m--) {
            if (!_fieldArray[i][m].isFree()) {
                if (_fieldArray[i][m].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[i][m]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int k = 1; k <= counter; k++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        counter = 0;
        for (int m = j + 1; m < 8; m++) {
            if (!_fieldArray[i][m].isFree()) {
                if (_fieldArray[i][m].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[i][m]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int k = 1; k <= counter; k++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        for (Cell item : cellsToColor) {
            item.changeColor(playerColor);
        }
    }
    private void colorDiagonals(Cell[][] _fieldArray, int i, int j) {
        Colors playerColor = _fieldArray[i][j].getColor();
        ArrayList<Cell> cellsToColor = new ArrayList<Cell>();
        int counter = 0;
        for (int k = 1; (i - k) >= 0 && (j + k) < 8; k++) {
            if (!_fieldArray[i - k][j + k].isFree()) {
                if (_fieldArray[i - k][j + k].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[i - k][j + k]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int m = 1; m <= counter; m++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        counter = 0;
        for (int k = 1; (i + k) < 8 && (j + k) < 8; k++) {
            if (!_fieldArray[i + k][j + k].isFree()) {
                if (_fieldArray[i + k][j + k].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[i + k][j + k]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int m = 1; m <= counter; m++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        counter = 0;
        for (int k = 1; (i + k) < 8 && (j - k) >= 0; k++) {
            if (!_fieldArray[i + k][j - k].isFree()) {
                if (_fieldArray[i + k][j - k].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[i + k][j - k]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int m = 1; m <= counter; m++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        counter = 0;
        for (int k = 1; (i - k) >= 0 && (j - k) >= 0; k++) {
            if (!_fieldArray[i - k][j - k].isFree()) {
                if (_fieldArray[i - k][j - k].getColor() != playerColor) {
                    cellsToColor.add(_fieldArray[i - k][j - k]);
                    ++counter;
                } else {
                    break;
                }
            } else {
                for (int m = 1; m <= counter; m++) {
                    cellsToColor.remove(cellsToColor.size() - 1);
                }
                break;
            }
        }
        for (Cell item : cellsToColor) {
            item.changeColor(playerColor);
        }
    }
    private boolean checkHorizontals(Cell[][] _fieldArray, Colors playerColor, int i, int j) {
        boolean flag = false;
        for (int n = i - 1; n >= 0; n--) {
            if (!_fieldArray[n][j].isFree()) {
                if (_fieldArray[n][j].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        flag = false;
        for (int n = i + 1; n < 8; n++) {
            if (!_fieldArray[n][j].isFree()) {
                if (_fieldArray[n][j].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        flag = false;
        for (int m = j - 1; m >= 0; m--) {
            if (!_fieldArray[i][m].isFree()) {
                if (_fieldArray[i][m].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        flag = false;
        for (int m = j + 1; m < 8; m++) {
            if (!_fieldArray[i][m].isFree()) {
                if (_fieldArray[i][m].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return false;
    }
    public boolean checkDiagonals(Cell[][] _fieldArray, Colors playerColor, int i, int j) {
        Colors color = _fieldArray[i][j].getColor();
        boolean flag = false;
        for (int k = 1; (i - k) >= 0 && (j + k) < 8; k++) {
            if (!_fieldArray[i - k][j + k].isFree()) {
                if (_fieldArray[i - k][j + k].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        flag = false;
        for (int k = 1; (i + k) < 8 && (j + k) < 8; k++) {
            if (!_fieldArray[i + k][j + k].isFree()) {
                if (_fieldArray[i + k][j + k].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        flag = false;
        for (int k = 1; (i + k) < 8 && (j - k) >= 0; k++) {
            if (!_fieldArray[i + k][j - k].isFree()) {
                if (_fieldArray[i + k][j - k].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        flag = false;
        for (int k = 1; (i - k) >= 0 && (j - k) >= 0; k++) {
            if (!_fieldArray[i - k][j - k].isFree()) {
                if (_fieldArray[i - k][j - k].getColor() != playerColor) {
                    flag = true;
                } else if (flag){
                    return true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return false;
    }
    public Finder() {};
    public ArrayList<Cell> findAvailableCells(Cell[][] _fieldArray, Colors playerColor) {
        ArrayList<Cell> availableCells = new ArrayList<Cell>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (_fieldArray[i][j].isFree()
                        && (checkHorizontals(_fieldArray, playerColor, i , j)
                        || checkDiagonals(_fieldArray, playerColor, i, j))) {
                    availableCells.add(_fieldArray[i][j]);
                    _fieldArray[i][j].isAvailable = true;
                } else {
                    _fieldArray[i][j].isAvailable = false;
                }
            }
        }
        return availableCells;
    }
    public void colorAllEatenCells(Cell[][] _fieldArray, int i, int j) {
        colorHorizontals(_fieldArray, i, j);
        colorDiagonals(_fieldArray, i, j);
    }
}