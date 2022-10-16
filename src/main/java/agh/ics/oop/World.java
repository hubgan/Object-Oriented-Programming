package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("Start");
//        Direction[] directions = new Direction[args.length];
//        fillDirectionArr(directions, args);
//        run(directions);
        Vector2d position1 = new Vector2d(1,2);
        out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        out.println(position2);
        out.println(position1.add(position2));
        out.println("Stop");
    }

    private static void fillDirectionArr(Direction[] directions, String[] args) {
        for (int i = 0; i < args.length; i++) {
            directions[i] = convertStringToEnum(args[i]);
        }
    }

    private static Direction convertStringToEnum(String arg) {
        return switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> null;
        };
    }

    private static void run(Direction[] args) {
        for (Direction argument: args) {
            printMove(argument);
        }
    }

    private static void printMove(Direction arg) {
        if (arg == null) {
            return;
        }

        switch (arg) {
            case FORWARD:
                out.println("Zwierzak idzie do przodu");
                break;
            case BACKWARD:
                out.println("Zwierzak idzie do tyłu");
                break;
            case LEFT:
                out.println("Zwierzak skręca w lewo");
                break;
            case RIGHT:
                out.println("Zwierzak skręca w prawo");
                break;
            default:
                break;
        }
    }
}
