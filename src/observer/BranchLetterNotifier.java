package observer;

import model.Claim;

public class BranchLetterNotifier implements ClaimObserver{
    @Override
    public void onClaimUpdate(Claim claim) {
        System.out.println("Letter notified for claim "+claim.getPolicyNo()+" status "+claim.getStatus());
    }
}
