package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.FailedSignInException;
import tck.model.da.SignInDa;
import tck.model.entity.Admin;
import tck.model.entity.Person;
import tck.model.entity.SignIn;
import tck.model.tool.CRUD;

import java.util.List;

public class SignInBl implements CRUD<SignIn> {
    @Getter
    private static SignInBl signInBl = new SignInBl();
    private SignInBl(){}

    @Override
    public SignIn save(SignIn signIn) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            signInDa.save(signIn);
            return signIn;
        }
    }

    @Override
    public SignIn edit(SignIn signIn) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            if (signInDa.findById(signIn.getId()) != null) {
             signInDa.edit(signIn);
                return signIn;
            } else {
                throw new FailedSignInException();
            }
        }
    }

    @Override
    public SignIn remove(int id) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
           SignIn signIn= signInDa.findById(id);
            if (signIn != null) {
               signInDa.remove(id);
                return signIn;
            } else {
                throw new FailedSignInException();
            }
        }
    }

    @Override
    public List<SignIn> findAll() throws Exception {
        try (SignInDa signInDa=new SignInDa()) {
            List<SignIn> signInList =signInDa.findAll();
            if (!signInList.isEmpty()) {
                for (SignIn signIn : signInList) {
                    signIn.setPerson(PersonBl.getPersonBl().findById(signIn.getPerson().getId()));
                }
                return signInList;
            } else {
                throw new FailedSignInException() ;
            }
        }
    }

    @Override
    public SignIn findById(int id) throws Exception {
        try (SignInDa signInDa=new SignInDa()) {
          SignIn signIn=signInDa.findById(id);
            if (signIn != null) {
                int personId =signIn.getPerson().getId();
                Person person = PersonBl.getPersonBl().findById(id);
             signIn.setPerson(person);
                return signIn;
            } else {
                throw new FailedSignInException();
            }
        }
    }
    public SignIn findByPersonId(int id) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            Person person = PersonBl.getPersonBl().findById(id);
            SignIn signIn = signInDa.findByPersonId(person.getId());
            if (signIn != null) {
                int personId = signIn.getPerson().getId();
                signIn.setPerson(PersonBl.getPersonBl().findById(signIn.getPerson().getId()));
                return signIn;
            } else {
                throw new FailedSignInException();
            }
        }
    }
    public SignIn findByAdminId(int id) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
           Admin admin = AdminBl.getAdminBl().findById(id);
            SignIn signIn = signInDa.findByAdminId(admin.getId());
            if (signIn != null) {
                int adminId = signIn.getAdmin().getId();
                //           Person person = PersonBl.getPersonBl().findById(id);
                signIn.setAdmin(AdminBl.getAdminBl().findById(signIn.getAdmin().getId()));
                return signIn;
            } else {
                throw new FailedSignInException();
            }
        }
    }
    public SignIn findByPersonUsername(String username) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            Person person = PersonBl.getPersonBl().findByUsername(username);
            SignIn signIn = signInDa.findByPersonId(person.getId());
            signIn.setPerson(PersonBl.getPersonBl().findById(signIn.getPerson().getId()));
            return signIn;
        }
    }
    public SignIn findByPersonPassword(String password) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            Person person = PersonBl.getPersonBl().findByPassword(password);
            SignIn signIn = signInDa.findByPersonId(person.getId());
            signIn.setPerson(PersonBl.getPersonBl().findById(signIn.getPerson().getId()));
            return signIn;
        }
    }
    public SignIn findByPersonEmail(String email) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            Person person = PersonBl.getPersonBl().findByEmail(email);
            SignIn signIn = signInDa.findByPersonId(person.getId());
            signIn.setPerson(PersonBl.getPersonBl().findById(signIn.getPerson().getId()));
            return signIn;
        }
    }
    public SignIn findByPersonPhoneNumber(String phoneNumber) throws Exception {
        try (SignInDa signInDa = new SignInDa()) {
            Person person = PersonBl.getPersonBl().findByPhoneNumber(phoneNumber);
            SignIn signIn = signInDa.findByPersonId(person.getId());
            signIn.setPerson(PersonBl.getPersonBl().findById(signIn.getPerson().getId()));
            return signIn;
        }
    }
}
