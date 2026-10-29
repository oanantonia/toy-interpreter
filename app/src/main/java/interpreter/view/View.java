package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.*;
import interpreter.model.stmt.*;
import interpreter.model.type.*;
import interpreter.model.value.*;
import interpreter.repository.IRepository;
import interpreter.repository.Repository;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final List<IStmt> PROGRAMS = List.of(Examples.EX1, Examples.EX2, Examples.EX3);

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("- MENU -");
            System.out.println("1. Example 1: " + PROGRAMS.get(0));
            System.out.println("2. Example 2: " + PROGRAMS.get(1));
            System.out.println("3. Example 3: " + PROGRAMS.get(2));
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            String option = scanner.nextLine();

            if (option.equals("0")) {
                break;
            } else if (option.equals("1")) {
                runProgram(PROGRAMS.get(0));
            } else if (option.equals("2")) {
                runProgram(PROGRAMS.get(1));
            } else if (option.equals("3")) {
                runProgram(PROGRAMS.get(2));
            } else {
                System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private void runProgram(IStmt program) {
        try {
            PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), program);
            IRepository repo = new Repository(state);
            Controller ctrl = new Controller(repo, true);

            ctrl.allStep();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}