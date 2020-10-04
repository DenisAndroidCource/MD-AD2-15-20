package by.it.academy.kotlinexample.ex;

public class Controller {

    BaseClass foo(int a, int b) {
        if (b != 0) {
            return new SuccessData();
        }

        return new ErrorData();
    }

}
