package br.com.kiev.mockito.staticwithparams;

public class MyUtils {
    public static String getWelcomeMessage(String userName, Boolean isCustomer) {
        if (isCustomer) {
            return "Dear " + userName;
        }
        return "Hello " + userName;
    }
}