package by.it.academy.layoutexample.patternadapter;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Operation {

    private int num1;
    private int num2;
    private int result;

    public Operation(int num1, int num2, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Operation) {
            Operation operationObj = (Operation) obj;
            return Objects.equals(num1, operationObj.num1) && Objects.equals(num2, operationObj.num2) &&
                    Objects.equals(result, operationObj.result);
        }
        return false;
    }
}
