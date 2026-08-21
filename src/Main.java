import config.AppConfig;
import exception.InvalidClaimException;
import exception.PolicyNotFoundException;
import exception.PolicyServiceException;
import exception.UnknownPolicyTypeException;
import factory.PolicyFactory;
import model.Claim;
import model.Policy;
import model.Urgency;
import observer.BranchLetterNotifier;
import observer.ClaimEventPublisher;
import observer.ClaimObserver;
import observer.InAppNotifier;
import service.AuditLogger;
import service.ClaimService;
import store.PolicyStore;
import strategy.PremiumCalculator;
import strategy.PremiumStrategy;
import strategy.UlipPremiumStrategy;

import java.util.Iterator;

public class Main{
    static void main(String[] args) {

        Policy[] policies = {
                PolicyFactory.create("term", "HDFC-LIFE-1001", "Anita Sharma", 18500, "Active"),
                PolicyFactory.create("ULIP", "HDFC-LIFE-1002", "Rahul Mehta", 42000, "Active"),
                PolicyFactory.create("ENDOWMENT", "HDFC-LIFE-1003", "Priya Nair", 27000, "Lapsed"),
                PolicyFactory.create("TERM", "HDFC-LIFE-1004", "Vikram Singh", 15200, "Active"),
                PolicyFactory.create("ULIP", "HDFC-LIFE-1005", "Sneha Patel", 36000, "Active"),
                PolicyFactory.create("ENDOWMENT", "HDFC-LIFE-1006", "Anita Sharma", 22000, "Pending")
        };

        PolicyStore store = new PolicyStore();

        for(Policy policy : policies){
            store.addPolicy(policy);
        }

        System.out.println("====================================================================================================================================");
        System.out.println("Company: "+ AppConfig.INSTANCE.getCompanyName());
        System.out.println("====================================================================================================================================");

        System.out.println("ALL POLICIES:");
        Iterator<Policy> it = store.getAllPolicies().iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("====================================================================================================================================");
        System.out.println("Unique Customer count: ");
        System.out.println(store.getUniqueCustomerNames().size());

        System.out.println("====================================================================================================================================");
        System.out.println("Policy Number HDFC-LIFE-1004 is taken by:");
        System.out.println(store.findByPolicyNumber("HDFC-LIFE-1004").getCustomerName());

        System.out.println("====================================================================================================================================");
        System.out.println("TreeMap keys");
        for (String key: store.getSortedPolicies().keySet()){
            System.out.println(key);
        }

        System.out.println("====================================================================================================================================");
        PremiumStrategy strat = new UlipPremiumStrategy();
        PremiumCalculator calculator = new PremiumCalculator(strat);
        int calculatedPremium = calculator.calculate(store.findByPolicyNumber("HDFC-LIFE-1002").getBasePremium());
        System.out.println("ULIP Premium for HDFC-LIFE-1002 is: "+calculatedPremium);

        System.out.println("====================================================================================================================================");
        ClaimEventPublisher publisher = new ClaimEventPublisher();
        publisher.register(new BranchLetterNotifier());
        publisher.register(new InAppNotifier());

        ClaimService claimService = new ClaimService(store, publisher);

        try (AuditLogger logger = new AuditLogger("audit.log")) {

            Claim high = new Claim.ClaimBuilder("HDFC-LIFE-1001", 20000, Urgency.HIGH)
                    .hospitalName("Apollo")
                    .remarks("Hopitalised")
                    .build();

            Claim medium = new Claim.ClaimBuilder("HDFC-LIFE-1002", 56000, Urgency.MEDIUM)
                    .remarks("Policy claim")
                    .build();

            Claim low = new Claim.ClaimBuilder("HDFC-LIFE-1004", 65000, Urgency.LOW)
                    .remarks("Medical issue")
                    .build();

            claimService.fileClaim(high);
            claimService.fileClaim(medium);
            claimService.fileClaim(low);

            claimService.updateStatus(high, "APPROVED");


            System.out.println("====================================================================================================================================");
            System.out.println("Priority Queue Order:");
            while (!claimService.getClaimQueue().isEmpty()) {
                System.out.println(claimService.getClaimQueue().poll().getUrgency());
            }

            logger.log("Claim filed: " + high.getPolicyNo() + " status: " + high.getStatus());
        }
        catch (PolicyServiceException e){
            System.out.println("Claim service error, "+e.getMessage());
        }

            System.out.println("====================================================================================================================================");
        try {
            store.findByPolicyNumber("HDFC-LIFE-9999");
        }
        catch (PolicyServiceException e){
            System.out.println(e.getMessage());
        }

        try {
            Claim invalidclaim = new Claim.ClaimBuilder("HDFC-LIFE-1001", 6000000, Urgency.HIGH)
                    .remarks("Invalid Policy claim")
                    .build();
            claimService.fileClaim(invalidclaim);
        } catch (InvalidClaimException e) {
            System.out.println(e.getMessage());
        }

        try{
            PolicyFactory.create("ULTRA PLAN", "HDFC-LIFE-0000","JOHN DIE", 10, "Passive");
        } catch (UnknownPolicyTypeException e) {
            System.out.println(e.getMessage());
        }



    }

}