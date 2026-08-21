package strategy;

public class PremiumCalculator {
    private PremiumStrategy strategy;

    public PremiumCalculator(PremiumStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(PremiumStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int basePremium){
        return strategy.calculate(basePremium);
    }
}
