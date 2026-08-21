package model;

public class TermLifePolicy extends Policy{


    public TermLifePolicy(String policyNo, String customerName, int basePremium, String status) {
        super(policyNo, customerName, basePremium, status);
    }

    @Override
    public String getPolicyType() {
        return "TERM";
    }
}
