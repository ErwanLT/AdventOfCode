import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Jour11 {

    private static final String input = "3,8,1005,8,301,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,102,1,8,28,1006,0,98,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,1,10,4,10,101,0,8,54,2,1001,6,10,1,108,1,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,0,10,4,10,1002,8,1,84,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,101,0,8,105,1006,0,94,2,7,20,10,2,5,7,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,0,10,4,10,102,1,8,139,1006,0,58,2,1003,16,10,1,6,10,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,0,10,4,10,102,1,8,172,2,107,12,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,101,0,8,197,1006,0,34,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,1,10,4,10,102,1,8,223,1006,0,62,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,1,10,4,10,1001,8,0,248,1,7,7,10,1006,0,64,2,1008,5,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,280,101,1,9,9,1007,9,997,10,1005,10,15,99,109,623,104,0,104,1,21102,1,387508351636,1,21101,318,0,0,1106,0,422,21102,1,838480007948,1,21101,0,329,0,1106,0,422,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,0,235190525123,1,21101,0,376,0,1105,1,422,21101,0,106505084123,1,21101,0,387,0,1106,0,422,3,10,104,0,104,0,3,10,104,0,104,0,21101,0,838324605292,1,21102,1,410,0,1105,1,422,21102,709496668940,1,1,21102,421,1,0,1105,1,422,99,109,2,22101,0,-1,1,21102,1,40,2,21101,0,453,3,21102,443,1,0,1106,0,486,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,448,449,464,4,0,1001,448,1,448,108,4,448,10,1006,10,480,1102,1,0,448,109,-2,2106,0,0,0,109,4,2101,0,-1,485,1207,-3,0,10,1006,10,503,21102,0,1,-3,22102,1,-3,1,21201,-2,0,2,21101,1,0,3,21102,1,522,0,1106,0,527,109,-4,2105,1,0,109,5,1207,-3,1,10,1006,10,550,2207,-4,-2,10,1006,10,550,21202,-4,1,-4,1106,0,618,22102,1,-4,1,21201,-3,-1,2,21202,-2,2,3,21102,569,1,0,1106,0,527,21202,1,1,-4,21101,0,1,-1,2207,-4,-2,10,1006,10,588,21101,0,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,610,22101,0,-1,1,21101,0,610,0,106,0,485,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2106,0,0";

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        IntcodeComputer computer = new IntcodeComputer(input, true);
        computer.setInputNumber(0);
        computer.compute();
        ArrayList<Position> positions = new ArrayList<>();
        int outputPosition = 0;
        ArrayList<Position> white = new ArrayList<>();
        int color = 0, directions = 0, x = 0, y = 0;
        while (computer.isRunning()) {
            List<Long> outputs = computer.getOutputs();
            for (; outputPosition < outputs.size(); outputPosition += 2) {
                color = Integer.parseInt(outputs.get(outputPosition) + "");
                Position position = new Position(x, y);
                if (white.contains(position) && color == 0) {
                    white.remove(position);
                } else if (!white.contains(position) && color == 1) {
                    white.add(position);
                    if (!positions.contains(position)) {
                        positions.add(position);
                    }
                }
                int turn = Integer.parseInt(outputs.get(outputPosition + 1) + "");
                if (turn == 0) {
                    directions--;
                } else {
                    directions++;
                }
                if (directions == -1) directions += 4;
                if (directions == 4) directions -= 4;
                switch (directions) {
                    case 0: { // UP
                        y--;
                        break;
                    }
                    case 1: { // RIGHT
                        x--;
                        break;
                    }
                    case 2: { // DOWN
                        y++;
                        break;
                    }
                    case 3: { // LEFT
                        x++;
                        break;
                    }
                }
            }
            Position position = new Position(x, y);
            int positionColor = 0;
            if (white.contains(position)) {
                positionColor = 1;
            }
            computer.setInputNumber(positionColor);
            computer.compute();
        }
        System.out.printf("Number of panel : " + positions.size() + "\n");
    }

    private static void part2(){
        IntcodeComputer computer = new IntcodeComputer(input, true);
        computer.setInputNumber(1);
        computer.compute();
        int outputPosition = 0;
        ArrayList<Position> white = new ArrayList<>();
        int color = 0, directions = 0, x = 0, y = 0;
        while (computer.isRunning()) {
            List<Long> outputs = computer.getOutputs();
            for (; outputPosition < outputs.size(); outputPosition += 2) {
                color = Integer.parseInt(outputs.get(outputPosition) + "");
                Position position = new Position(x, y);
                if (white.contains(position) && color == 0) {
                    white.remove(position);
                } else if (!white.contains(position) && color == 1) {
                    white.add(position);
                }
                int turn = Integer.parseInt(outputs.get(outputPosition + 1) + "");
                if (turn == 0) {
                    directions--;
                } else {
                    directions++;
                }
                if (directions == -1) directions += 4;
                if (directions == 4) directions -= 4;
                switch (directions) {
                    case 0: { // UP
                        y--;
                        break;
                    }
                    case 1: { // RIGHT
                        x--;
                        break;
                    }
                    case 2: { // DOWN
                        y++;
                        break;
                    }
                    case 3: { // LEFT
                        x++;
                        break;
                    }
                }
            }
            Position position = new Position(x, y);
            int positionColor = 0;
            if (white.contains(position)) {
                positionColor = 1;
            }
            computer.setInputNumber(positionColor);
            computer.compute();
        }
        int minX = 10000;
        int minY = 10000;
        int maxX = -10000;
        int maxY = -10000;
        for (Position pos : white) {
            if (minX > pos.getX()) minX = pos.getX();
            if (minY > pos.getY()) minY = pos.getY();
            if (maxX < pos.getX()) maxX = pos.getX();
            if (maxY < pos.getY()) maxY = pos.getY();
        }

        for (int j = minY; j < maxY + 1; j++) {
            for (int i = maxX; i > minX - 1; i--) {
                Position pos = new Position(i, j);
                if (white.contains(pos)) {
                    System.out.print("\u2588");
                } else {
                    System.out.print("\u2591");
                }
            }
            System.out.print("\n");
        }
    }

    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    private static class Position {

        private int x = 0;

        private int y = 0;

    }
}
