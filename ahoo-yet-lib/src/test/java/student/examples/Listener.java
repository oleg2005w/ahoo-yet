package student.examples;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        System.out.println("=========Test started!!!=========");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("=======Test finished!!!========");
    }
}
