package model;

public class EndowmentPolicy extends Policy{
    public EndowmentPolicy(String policyNo, String customerName, int basePremium, String status) {
        super(policyNo, customerName, basePremium, status);
    }

    @Override
    public String getPolicyType() {
        return "ENDOWMENT";
    }
}
