package tck.model.bl;

import lombok.Getter;
import tck.model.da.AdminDa;
import tck.model.da.OperateDa;
import tck.model.entity.Operator;
import tck.model.tool.CRUD;

import java.util.List;

public class OperateBl implements CRUD<Operator> {
    @Getter
    private static OperateBl operateBl=new OperateBl();
    private OperateBl(){}
    @Override
    public Operator save(Operator operator) throws Exception {
        try (OperateDa operateDa = new OperateDa()) {
            operateDa.save(operator);
            return operator;
        }
    }

    @Override
    public Operator edit(Operator operator) throws Exception {
        return null;
    }

    @Override
    public Operator remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Operator> findAll() throws Exception {
        return null;
    }

    @Override
    public Operator findById(int id) throws Exception {
        return null;
    }
}
