package action.util;

import action.IAction;

public class ExitProgram implements IAction {
    @Override
    public void execute() {

        System.out.println("exit");
        System.exit(200);
    }
}