package tck.test;

import tck.model.bl.SignInBl;

public class SignTest {
    public static void main(String[] args) throws Exception {
        SignInBl.getSignInBl().findAll();
    }
}
