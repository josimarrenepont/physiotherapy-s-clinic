import axios from 'axios';
import React, { useState } from 'react';

const PlanUpdateForm: React.FC = () => {
  const [moment, setMoment] = useState('');
  const [additionalPricePerson, setAdditionalPricePerson] = useState('');
  const [price, setPrice] = useState('');

  const handlePlanUpdate = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.put('http://localhost:8080/api/plan', {
        moment: moment,
        additionalPricePerson: additionalPricePerson,
        price: price
      });
      console.log('Plano atualizado com sucesso:', response.data);
    } catch (error) {
      console.error('Erro ao atualizar plano:', error);
    }
  };

  return (
    <div>
      <h2>Atualizar Plano</h2>
      <form onSubmit={handlePlanUpdate}>
        <label htmlFor="moment">Data do Plano (DD/MM/YYYY):</label>
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
        <label htmlFor="price">Preço Total:</label>
        <input
          id="price"
          type="text"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />
        <button type="submit">Atualizar</button>
      </form>
    </div>
  );
};

export default PlanUpdateForm;

