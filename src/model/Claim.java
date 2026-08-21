package model;

public class Claim {
    private String policyNo;
    private double claimAmount;
    private Urgency urgency;
    private String hospitalName;
    private String remarks;
    private String status;

    public Claim(ClaimBuilder builder) {
        this.policyNo = builder.policyNo;
        this.claimAmount = builder.claimAmount;
        this.urgency = builder.urgency;
        this.hospitalName = builder.hospitalName;
        this.remarks = builder.remarks;
        this.status = "SUBMITTED";
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public static class ClaimBuilder{
        private String policyNo;
        private double claimAmount;
        private Urgency urgency;
        private String hospitalName;
        private String remarks;

        public ClaimBuilder(String policyNo, double claimAmount, Urgency urgency) {
            this.policyNo = policyNo;
            this.claimAmount = claimAmount;
            this.urgency = urgency;
        }

        public ClaimBuilder hospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
            return this;
        }

        public ClaimBuilder remarks(String remarks){
            this.remarks = remarks;
            return this;
        }

        public Claim build(){
            return new Claim(this);
        }
    }

    }

