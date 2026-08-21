package observer;

import model.Claim;

public interface ClaimObserver {
   void onClaimUpdate(Claim claim);
}
