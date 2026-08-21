package model;

public abstract class Policy {
    private String policyNo;
    private String customerName;
    private int basePremium;
    private String status;

    public Policy(String policyNo, String customerName, int basePremium, String status) {
        this.policyNo = policyNo;
        this.customerName = customerName;
        this.basePremium = basePremium;
        this.status = status;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getBasePremium() {
        return basePremium;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "policyNo='" + policyNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", basePremium=" + basePremium +
                ", status='" + status + '\''
                ;
    }

    public abstract String getPolicyType();
}
