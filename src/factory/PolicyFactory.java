package factory;

import exception.InvalidClaimException;
import exception.UnknownPolicyTypeException;
import model.EndowmentPolicy;
import model.Policy;
import model.TermLifePolicy;
import model.UlipPolicy;

public class PolicyFactory {
    public PolicyFactory() {
    }

    public static Policy create(String type, String policyNo, String customer, int premium, String status){
        if ("TERM".equalsIgnoreCase(type)){
            return new TermLifePolicy(policyNo, customer, premium, status);
        }

        if ("ULIP".equalsIgnoreCase(type)){
            return new UlipPolicy(policyNo, customer, premium, status);
        }

        if ("ENDOWMENT".equalsIgnoreCase(type)){
            return new EndowmentPolicy(policyNo, customer, premium, status);
        }

        throw new UnknownPolicyTypeException("Claim Type is Invalid or Unknown: "+type+", please input TERM/ULIP/ENDOWMENT");
    }
}
