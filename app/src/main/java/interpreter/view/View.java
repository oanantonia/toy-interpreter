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

import java.util.Scanner;

public class View {
    private final IStmt ex1;
    private final IStmt ex2;
    private final IStmt ex3;

    public View() {
        this.ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        this.ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(ArithOp.ADD, new ValueExp(new IntValue(2)),
                                new ArithExp(ArithOp.MUL, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp(ArithOp.ADD, new VarExp("a"), new ValueExp(new IntValue(1)))),
                                        new PrintStmt(new VarExp("b"))))));

        this.ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("- MENU -");
            System.out.println("1. Example 1: " + ex1);
            System.out.println("2. Example 2: " + ex2);
            System.out.println("3. Example 3: " + ex3);
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            String option = scanner.nextLine();

            if (option.equals("0")) {
                break;
            } else if (option.equals("1")) {
                runProgram(ex1);
            } else if (option.equals("2")) {
                runProgram(ex2);
            } else if (option.equals("3")) {
                runProgram(ex3);
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
            Controller ctrl = new Controller(repo);

            ctrl.allStep();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}