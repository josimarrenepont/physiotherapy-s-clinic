// PlanListItem.js
import React from 'react';


const PlanListItem = ({ plan, onEdit }) => {
  const handleEdit = () => {
    onEdit(plan.id);
  };

  return (
    <div>
      <h3>{plan.id}</h3>
      <p>{plan.name}</p>
      <button onClick={handleEdit}>Editar</button>
    </div>
  );
};

export default PlanListItem;
