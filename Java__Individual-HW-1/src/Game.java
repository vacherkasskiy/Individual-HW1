import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Field _field;
    private Field _backupField;
    private Scanner _input;
    private Random _random;
    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public Game() {
        _input = new Scanner(System.in);
        _random = new Random();
    }
    public boolean startGame() {
        String mode;
        do {
            System.out.print("\033[H\033[2J");
            System.out.println("Выберите режим:");
            System.out.println("1. Игра с другом");
            System.out.println("2. Игра с ИИ");
            System.out.print(">>>  ");
            mode = _input.nextLine();
            System.out.println("\n");
        } while (!tryParseInt(mode) ||
                Integer.parseInt(mode) > 2 ||
                Integer.parseInt(mode) < 1);

        if (Integer.parseInt(mode) == 1) {
            return gameWithSomebody(true);
        } else {
            return gameWithSomebody(false);
        }
    }
    private boolean gameWithSomebody(boolean withFriend) {
        _field = new Field();
        int turnsAmount = 1;
        while (true) {
            Colors currentColor = turnsAmount % 2 == 0 ? Colors.BLUE : Colors.RED;
            ArrayList<Cell> availableCells = _field.findAvailableCells(currentColor);
            if (checkEndOfTheGame(availableCells)) {
                System.out.println("\n\nПобедили " + (getWinner() == Colors.RED ? "красные" : "синие")
                        + " cо счётом " + _field.getAmountOfColor(getWinner()) + "!");
                System.out.print("Начать новую игру? (y/<...>):" );
                return (Objects.equals(_input.nextLine(), "y"));
            }
            if (withFriend || currentColor == Colors.RED) {
                System.out.println(_field);
                System.out.println("\n\nХод " + (turnsAmount % 2 == 0 ? "синих" : "красных"));
                System.out.println("Введите \"c\", чтобы отменить предыдущий ход (если хотите)");
                System.out.println("Введите \"q\", чтобы сдаться (это необязательно)");
                System.out.println("Доступные ходы:");
                for (int i = 0; i < availableCells.size(); i++) {
                    System.out.println((i + 1) + ". " + (availableCells.get(i).Y + 1) + " " + (availableCells.get(i).X + 1));
                }
                String chosenTurn;
                do {
                    System.out.print("Ваш ход (введите номер хода из списка выше):");
                    chosenTurn = _input.nextLine();
                    if (Objects.equals(chosenTurn, "q")) {
                        System.out.println("\n\nПобедили " + (currentColor == Colors.RED ? "синие" : "красные")
                                + " cо счётом "
                                + _field.getAmountOfColor(currentColor == Colors.RED ? Colors.BLUE : Colors.RED) + "!");
                        System.out.print("Начать новую игру? (y/<...>):" );
                        return (Objects.equals(_input.nextLine(), "y"));
                    }
                    if (Objects.equals(chosenTurn, "c")) {
                        if (cancelPreviousTurn()) {
                            --turnsAmount;
                            if (!withFriend) {
                                --turnsAmount;
                            }
                            break;
                        }
                    }
                } while(!tryParseInt(chosenTurn)
                        || Integer.parseInt(chosenTurn) < 1
                        || Integer.parseInt(chosenTurn) > availableCells.size());
                if (chosenTurn.equals("c")) {
                    continue;
                }
                _backupField = new Field(_field);
                int x = availableCells.get(Integer.parseInt(chosenTurn) - 1).X;
                int y = availableCells.get(Integer.parseInt(chosenTurn) - 1).Y;
                _field.makeTurn(currentColor, y, x);
                System.out.println("\n");
            } else {
                makeRandomTurn(availableCells);
            }
            ++turnsAmount;
        }
    }
    private boolean checkEndOfTheGame(ArrayList<Cell> availableCells) {
        return availableCells.size() == 0;
    }
    private Colors getWinner() {
        return (_field.getAmountOfColor(Colors.RED) >
                _field.getAmountOfColor(Colors.BLUE) ?
                Colors.RED : Colors.BLUE);
    }
    private boolean cancelPreviousTurn() {
        if (_backupField == null ||
                _backupField.getAmountOfColor(Colors.RED) == _field.getAmountOfColor(Colors.RED)) {
            System.out.println("Вы уже отменяли предыдущий ход!");
            return false;
        }
        _field = new Field(_backupField);
        return true;
    }
    private void makeRandomTurn(ArrayList<Cell> availableCells) {
        int randomTurn = _random.nextInt(availableCells.size());
        Cell randomCell = availableCells.get(randomTurn);
        int x = randomCell.X;
        int y = randomCell.Y;
        _field.makeTurn(Colors.BLUE, y, x);
    }
}
