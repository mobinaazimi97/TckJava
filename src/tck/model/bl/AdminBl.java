package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.AaccesssDeniedException;
import tck.controller.exceptions.NoAdminFoundException;
import tck.controller.exceptions.NoPersonFoundException;
import tck.model.da.AdminDa;
import tck.model.da.SignInDa;
import tck.model.entity.*;
import tck.model.tool.CRUD;

import java.util.List;


public class AdminBl implements CRUD<Admin> {
    @Getter
    private static AdminBl adminBl=new AdminBl();
    private AdminBl(){}

    @Override
    public Admin save(Admin admin) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
     //       if (adminDa.findByPersonId(admin.getPerson().getId()) != null) {            //TODO
                adminDa.save(admin);
                return admin;
//            }else {
//                throw new AaccesssDeniedException();
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
            List<Admin> personList = adminDa.findAll();
            if (!personList.isEmpty()) {
                return  personList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Admin findById(int id) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Admin admin = adminDa.findById(id);
            if (admin != null) {
                admin.setId(id);                            //TODO : TRUE ?
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }
    public Admin findByUser(String user) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Admin admin = adminDa.findByUser(user);
            if (admin != null) {
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }
    public Admin findByPass(String pass) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            Admin admin = adminDa.findByPass(pass);
            if (admin != null) {
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }
    public Admin findByPersonId(int id) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Person person = PersonBl.getPersonBl().findById(id);
            Admin admin= adminDa.findByPersonId(person.getId());
            if (admin != null) {
                int personId =admin.getPerson().getId();
 //               Person person = PersonBl.getPersonBl().findById(id);
               admin.setPerson(PersonBl.getPersonBl().findById(admin.getPerson().getId()));
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }
    public Admin findByPersonUser(String username) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Person person = PersonBl.getPersonBl().findByUsername(username);
        Admin admin = adminDa.findByPersonUser(person.getUsername());
            admin.setPerson(PersonBl.getPersonBl().findById(admin.getPerson().getId()));
            return admin;
        }
    }
    public Admin findByPersonPass(String password) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Person person = PersonBl.getPersonBl().findByPassword(password);
            Admin admin = adminDa.findByPersonPass(person.getPassword());
            admin.setPerson(PersonBl.getPersonBl().findById(admin.getPerson().getId()));
            return admin;
        }
    }
    public List<Admin> findByPersonFamily(String family) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            List<Person>personList=PersonBl.getPersonBl().findByFamily(family);
            List<Admin> adminList = adminDa.findByPersonFamily(family);
            if (!personList.isEmpty()) {
                return adminList;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }
    public Admin findByTicketId(int id) throws Exception {
        try (AdminDa adminDa=new AdminDa()) {
            Ticket ticket=TicketBl.getTicketBl().findById(id);
            Admin admin = adminDa.findByTicketId(ticket.getId());
            if (admin != null) {
                int ticketId =admin.getTicket().getId();
                //               Ticket ticket = TicketBl.getTicketBl().findById(ticketId);
               admin.setTicket(TicketBl.getTicketBl().findById(admin.getTicket().getId()));
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }
    public Admin findByResponseId(int id) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
           Response response =ResponseBl.getResponseBl().findById(id);
            Admin admin = adminDa.findByResponseId(response.getId());
            if (admin != null) {
                int responseId = admin.getResponse().getId();
                //               Ticket ticket = TicketBl.getTicketBl().findById(ticketId);
                admin.setResponse(ResponseBl.getResponseBl().findById(admin.getResponse().getId()));
                return admin;
            } else {
                throw new NoAdminFoundException();
            }
        }
    }
}
