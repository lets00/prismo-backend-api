package com.api.prismo.seeds;

import com.api.prismo.models.OperationType;
import com.api.prismo.repository.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OperationTypeSeed implements CommandLineRunner {

    @Autowired
    OperationTypeRepository otr;

    @Override
    public void run(String... args) throws Exception {
        loadOperationType();
    }

    private void loadOperationType() {

//        if (otr.count() == 0) {
//            System.out.println("-----------Seeding operations type-----------");
//            OperationType operationType1 = new OperationType("COMPRA A VISTA");
//            OperationType operationType2 = new OperationType("COMPRA PARCELADA");
//            OperationType operationType3 = new OperationType("SAQUE");
//            OperationType operationType4 = new OperationType("PAGAMENTO");
//            otr.save(operationType1);
//            otr.save(operationType2);
//            otr.save(operationType3);
//            otr.save(operationType4);
//        }
    }
}
