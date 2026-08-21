package store;

import exception.PolicyNotFoundException;
import model.Policy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;

public class PolicyStore {
    private ArrayList<Policy> allPolicies = new ArrayList<>();
    private HashSet<String> uniqueCustomerNames = new HashSet<>();
    private HashMap<String, Policy> policiesByNumber = new HashMap<>();
    private TreeMap<String, Policy> sortedPolicies = new TreeMap<>();

    public void addPolicy(Policy policy){
        allPolicies.add(policy);
        uniqueCustomerNames.add(policy.getCustomerName());
        policiesByNumber.put(policy.getPolicyNo(), policy);
        sortedPolicies.put(policy.getPolicyNo(), policy);
    }

    public Policy findByPolicyNumber(String policyNo) {
        Policy policy = policiesByNumber.get(policyNo);
        if(policy == null){
            throw new PolicyNotFoundException("Policy not Found: "+policyNo);
        }
        return policy;
    }

    public ArrayList<Policy> getAllPolicies() {
        return allPolicies;
    }

    public HashSet<String> getUniqueCustomerNames() {
        return uniqueCustomerNames;
    }

    public HashMap<String, Policy> getPoliciesByNumber() {
        return policiesByNumber;
    }

    public TreeMap<String, Policy> getSortedPolicies() {
        return sortedPolicies;
    }
}
