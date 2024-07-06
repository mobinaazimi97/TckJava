package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.AaccesssDeniedException;
import tck.model.da.OperateDa;
import tck.model.entity.Admin;
import tck.model.entity.Operator;
import tck.model.entity.SignIn;
import tck.model.entity.Ticket;
import tck.model.tool.CRUD;

import java.util.List;

public class OperateBl implements CRUD<Operator> {
    @Getter
    private static OperateBl operateBl = new OperateBl();

    private OperateBl() {
    }

    @Override
    public Operator save(Operator operator) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            operateDa.save(operator);
            return operator;
        }
    }

    @Override
    public Operator edit(Operator operator) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            if (operateDa.findById(operator.getId()) != null) {
                operateDa.edit(operator);
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    @Override
    public Operator remove(int id) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            Operator operator = operateDa.findById(id);
            if (operator != null) {
                operateDa.remove(id);
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    @Override
    public List<Operator> findAll() throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            List<Operator> operatorList = operateDa.findAll();
            if (!operatorList.isEmpty()) {
//                for (Operator operator : operatorList) {                                              //TODO
//                    operator.setSignIn(SignInBl.getSignInBl().findByPersonId(operator.getSignIn().getPerson().getId()));
//                }
                return operatorList;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    @Override
    public Operator findById(int id) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            Operator operator = operateDa.findById(id);
            if (operator != null) {
                operator.setId(id);                            //TODO : TRUE ?
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByPersonId(int id) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            SignIn signIn = SignInBl.getSignInBl().findByPersonId(id);
            Operator operator = operateDa.findByPersonId(signIn.getPerson().getId());
            if (operator != null) {
                int personId = operator.getSignIn().getPerson().getId();
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setSignIn(SignInBl.getSignInBl().findByPersonId(operator.getSignIn().getPerson().getId()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByPerUsername(String username) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            SignIn signIn = SignInBl.getSignInBl().findByPersonUsername(username);
            Operator operator = operateDa.findByPerUsername(signIn.getPerson().getUsername());
            if (operator != null) {
                //              operator.getSignIn().getPerson().getUsername();                            //TODO
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setSignIn(SignInBl.getSignInBl().findByPersonUsername(operator.getSignIn().getPerson().getUsername()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByPerPassword(String password) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            SignIn signIn = SignInBl.getSignInBl().findByPersonPassword(password);
            Operator operator = operateDa.findByPerPassword(signIn.getPerson().getPassword());
            if (operator != null) {
                //             operator.getSignIn().getPerson().getPassword();                            //TODO
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setSignIn(SignInBl.getSignInBl().findByPersonPassword(operator.getSignIn().getPerson().getPassword()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByPerPhone(String phoneNumber) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            SignIn signIn = SignInBl.getSignInBl().findByPersonPhoneNumber(phoneNumber);
            Operator operator = operateDa.findByPerPhone(signIn.getPerson().getPhoneNumber());
            if (operator != null) {
                //             operator.getSignIn().getPerson().getPassword();                            //TODO
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setSignIn(SignInBl.getSignInBl().findByPersonPhoneNumber(operator.getSignIn().getPerson().getPhoneNumber()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByPerEmail(String email) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            SignIn signIn = SignInBl.getSignInBl().findByPersonEmail(email);
            Operator operator = operateDa.findByPerEmail(signIn.getPerson().getEmail());
            if (operator != null) {
                //             operator.getSignIn().getPerson().getPassword();                            //TODO
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setSignIn(SignInBl.getSignInBl().findByPersonEmail(operator.getSignIn().getPerson().getEmail()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByAdminId(int id) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            Admin admin = AdminBl.getAdminBl().findById(id);
            Operator operator = operateDa.findByAdminId(admin.getId());
            if (operator != null) {
                int adminId = operator.getAdmin().getId();
                //           Person person = PersonBl.getPersonBl().findById(id);
                operator.setAdmin(AdminBl.getAdminBl().findById(operator.getAdmin().getId()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByAdminUsername(String user) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            Admin admin = AdminBl.getAdminBl().findByUser(user);
            Operator operator = operateDa.findByAdminUsername(admin.getUser());
            if (operator != null) {
                //              operator.getSignIn().getPerson().getUsername();                            //TODO
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setAdmin(AdminBl.getAdminBl().findByUser(operator.getAdmin().getUser()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }

    public Operator findByAdminPassword(String pass) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            //         Person person = PersonBl.getPersonBl().findById(id);
            Admin admin = AdminBl.getAdminBl().findByPass(pass);
            Operator operator = operateDa.findByAdminPassword(admin.getPass());
            if (operator != null) {
                //              operator.getSignIn().getPerson().getUsername();                            //TODO
                //               Person person = PersonBl.getPersonBl().findById(id);
                operator.setAdmin(AdminBl.getAdminBl().findByPass(operator.getAdmin().getPass()));
                return operator;
            } else {
                throw new AaccesssDeniedException();
            }
        }
    }
}
