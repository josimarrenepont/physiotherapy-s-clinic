import { useParams } from 'react-router-dom';
import PlanUpdateForm from './PlanUpdateForm';

const PlanUpdatePage = () => {
  const { id } = useParams();

  return (
    <div>
      <h1>Atualizar Plano</h1>
      <PlanUpdateForm planId={id} />
    </div>
  );
};

export default PlanUpdatePage;
