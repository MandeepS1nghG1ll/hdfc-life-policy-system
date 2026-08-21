package observer;

import model.Claim;

import java.util.ArrayList;
import java.util.List;

public class ClaimEventPublisher {
    private List<ClaimObserver> observerList = new ArrayList<>();

    public void register(ClaimObserver observer){

        observerList.add(observer);
    }

    public void notify(Claim claim){
        for(ClaimObserver obs : observerList){
            obs.onClaimUpdate(claim);
        }
    }
}
