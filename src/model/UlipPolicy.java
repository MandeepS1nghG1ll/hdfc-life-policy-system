package model;

public class UlipPolicy extends Policy{
    public UlipPolicy(String policyNo, String customerName, int basePremium, String status) {
        super(policyNo, customerName, basePremium, status);
    }

    @Override
    public String getPolicyType() {
        return "ULIP";
    }
}
