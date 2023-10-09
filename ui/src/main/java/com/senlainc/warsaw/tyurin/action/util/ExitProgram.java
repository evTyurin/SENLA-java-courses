package com.senlainc.warsaw.tyurin.action.util;

import com.senlainc.warsaw.tyurin.action.IAction;

public class ExitProgram implements IAction {
    @Override
    public void execute() {

        System.out.println("exit");
        System.exit(200);
    }
}