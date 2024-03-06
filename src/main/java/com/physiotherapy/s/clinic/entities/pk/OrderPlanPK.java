package com.physiotherapy.s.clinic.entities.pk;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Plans;

import java.io.Serializable;
import java.util.Objects;

public class OrderPlanPK implements Serializable {

    private Client client;
    private Plans plans;

    public Client getClient(){
        return client;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public Plans getPlans(){
        return plans;
    }
    public void setPlans(Plans plans){
        this.plans = plans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPlanPK that)) return false;
        return Objects.equals(getClient(), that.getClient()) && Objects.equals(getPlans(), that.getPlans());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient(), getPlans());
    }
}
