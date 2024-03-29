// PlanList.js
import React, { useState } from 'react';
import PlanListItem from './PlanListItem';
import PlanUpdateForm from './PlanUpdateForm';


const PlanList = ({ plans }) => {
  const [selectedPlanId, setSelectedPlanId] = useState(null);

  const handleEditPlan = (id) => {
    setSelectedPlanId(id);
  };

  return (
    <div>
      <h2>Lista de Planos</h2>
      {plans.map((plan) => (
        <PlanListItem key={plan.id} plan={plan} onEdit={handleEditPlan} />
      ))}
      {selectedPlanId && <PlanUpdateForm planId={selectedPlanId} />}
    </div>
  );
};

export default PlanList;
