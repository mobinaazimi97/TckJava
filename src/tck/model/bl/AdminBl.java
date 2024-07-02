package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.NoAdminFoundException;
import tck.model.da.AdminDa;
import tck.model.entity.Admin;
import tck.model.tool.CRUD;

import java.util.List;


public class AdminBl implements CRUD<Admin> {
    @Getter
    private static AdminBl adminBl=new AdminBl();
    private AdminBl(){}

    @Override
    public Admin save(Admin admin) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
           adminDa.save(admin);
            return admin;
        }
    }

    @Override
    public Admin edit(Admin admin) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            if (adminDa.findById(admin.getId()) != null) {
               adminDa.edit(admin);
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }

    @Override
    public Admin remove(int id) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Admin admin = adminDa.findById(id);
            if (admin != null) {
                adminDa.remove(id);
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }

    @Override
    public List<Admin> findAll() throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            List<Admin> adminList = adminDa.findAll();
            if (!adminList.isEmpty()) {
                return adminList;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }

    @Override
    public Admin findById(int id) throws Exception {
        return null;
    }
}
