package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("Start");
        Direction[] directions = new Direction[args.length];
        fillDirectionArr(directions, args);
        run(directions);
        out.println("Stop");
    }

    private static void fillDirectionArr(Direction[] directions, String[] args) {
        for (int i = 0; i < args.length; i++) {
            directions[i] = convertStringToEnum(args[i]);
        }
    }

    private static Direction convertStringToEnum(String arg) {
        switch (arg) {
            case "f":
                return Direction.FORWARD;
            case "b":
                return Direction.BACKWARD;
            case "r":
                return Direction.RIGHT;
            case "l":
                return Direction.LEFT;
            default:
                return null;
        }
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
