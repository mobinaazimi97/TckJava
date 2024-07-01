package tck.test;

import tck.model.bl.PersonBl;

public class PersonTest {
    public static void main(String[] args) throws Exception {
        System.out.println(PersonBl.getPersonBl().findAll());
    }
}
