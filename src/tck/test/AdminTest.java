package tck.test;

import tck.model.bl.AdminBl;

public class AdminTest {
    public static void main(String[] args) throws Exception {
        System.out.println(AdminBl.getAdminBl().findById(1));
    }
}
