import axios from 'axios';
import React, { useState } from 'react';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const PlanUpdateForm: React.FC = () => {
  const currentDate = new Date();
  const formattedDate = `${currentDate.getDate().toString().padStart(2, '0')}/${(currentDate.getMonth() + 1).toString().padStart(2, '0')}/${currentDate.getFullYear()}`;

  const [id, setId] = useState('');
  const [moment, setMoment] = useState(formattedDate);
  const [additionalPricePerson, setAdditionalPricePerson] = useState('');
  const [price, setPrice] = useState('');

  const getRandomPlan = async () => {
    try {
      const response = await axios.get('http://localhost:8080/plans/random');
      const planData = response.data;
      setId(planData.id.toString());
      setMoment(planData.moment);
      setAdditionalPricePerson(planData.additionalPricePerson.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }));
      setPrice(planData.price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }));
    } catch (error) {
      console.error('Erro ao buscar plano:', error);
    }
  };

  const handlePlanUpdate = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      await axios.put(`http://localhost:8080/plans/${id}`, {
        moment: moment,
        additionalPricePerson: additionalPricePerson.replace(',', '.'),
        price: price.replace(',', '.')
      });
      console.log('Plano atualizado com sucesso');
      toast.success('Plano atualizado com sucesso!', { autoClose: 2000 });
     //setTimeout(() => {
       // window.location.href = '/'; // redireciona para a página inicial após 3 segundos
      //}, 3000);
    } catch (error) {
      console.error('Erro ao atualizar plano:', error);
    }
  };

  return (
    <div>
      <h2>Atualizar Plano</h2>
      <button onClick={getRandomPlan}>Buscar Plano</button>
      <form onSubmit={handlePlanUpdate}>
        <label htmlFor="id">ID do Plano:</label>
        <input
          id="id"
          type="long"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
        <label htmlFor="moment">Data:</label>
        <input
          type="text"
          value={moment}
          onChange={(e) => setMoment(e.target.value)}
        />
        <label htmlFor="additionalPricePerson">Preço Adicional por Pessoa:</label>
        <input
          id="additionalPricePerson"
          type="text"
          value={additionalPricePerson}
          onChange={(e) => setAdditionalPricePerson(e.target.value)}
        />
        <label htmlFor="price">Preço:</label>
        <input
          id="price"
          type="text"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />
        <button type="submit">Atualizar</button>
      </form>
      <ToastContainer />
    </div>
  );
};

export default PlanUpdateForm;