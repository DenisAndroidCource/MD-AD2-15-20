package by.it.academy.solid.singleresponsibility;

public class SalaryCalculator {

    public int calculateSalary(AccountantData accountantData) {
        return accountantData.getWorkerType() + 100;
    }
}
