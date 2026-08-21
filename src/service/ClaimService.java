package service;

import config.AppConfig;
import exception.InvalidClaimException;
import model.Claim;
import observer.ClaimEventPublisher;
import store.PolicyStore;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ClaimService {
    private final PolicyStore policyStore;
    private final ClaimEventPublisher claimEventPublisher;
    private final PriorityQueue<Claim> claimQueue;

    public PriorityQueue<Claim> getClaimQueue() {
        return claimQueue;
    }

    public ClaimService(PolicyStore policyStore, ClaimEventPublisher claimEventPublisher) {
        this.policyStore = policyStore;
        this.claimEventPublisher = claimEventPublisher;
        this.claimQueue = new PriorityQueue<>(
                new Comparator<Claim>() {
                    @Override
                    public int compare(Claim o1, Claim o2) {
                        int result = Integer.compare(o1.getUrgency().getPriority(), o2.getUrgency().getPriority());
                            return result;
                    }
                }
        );
    }

    public void fileClaim(Claim claim){
        if(claim == null){
            throw new InvalidClaimException("Claim cannot be null");
        }
        policyStore.findByPolicyNumber(claim.getPolicyNo());

        if(claim.getClaimAmount() <= 0 || claim.getClaimAmount() > AppConfig.INSTANCE.getMaxClaim()){
            throw new InvalidClaimException("Invalid claim amount: "+claim.getClaimAmount());
        }

        claimQueue.add(claim);

    }

    public void updateStatus(Claim claim, String newStatus){
        if(claim==null || newStatus==null){
            throw new InvalidClaimException("Claim and status are required");
        }

        claim.updateStatus(newStatus);
        claimEventPublisher.notify(claim);
    }
}
