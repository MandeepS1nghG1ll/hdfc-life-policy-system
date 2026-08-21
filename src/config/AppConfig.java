package config;

public enum AppConfig {
    INSTANCE;

    private String companyName = "HDFCLife";
    private double maxClaim = 500000;

    public String getCompanyName() {
        return companyName;
    }

    public double getMaxClaim() {
        return maxClaim;
    }
}
