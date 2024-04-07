
import axios from 'axios';
import React, { useEffect, useState } from 'react';

interface Client {
  id: string;
  name: string;
}

const DependentRegistrationForm: React.FC = () => {
  const [dependents, setDependents] = useState<Client[]>([]);
  const [name, setName] = useState('');
  const [telephone, setTelephone] = useState('');
  const [kinship, setKinship] = useState('');
  const [cpf, setCpf] = useState('');
  const [clientId, setClientId] = useState('');

  useEffect(() => {
    const fetchClients = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/clients`);
        setDependents(response.data);
        
      } catch (error) {
        console.error('Erro ao buscar clientes:', error);
      }
    };
    fetchClients();
  }, []);

  const handleDependentSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.post(`http://localhost:8080/dependents/${clientId}`, {
        name: name,
        telephone: telephone,
        kinship: kinship,
        cpf: cpf
      });

      console.log('Dependente registrado com sucesso:', response.data);
    } catch (error) {
      console.error('Erro ao registrar dependente:', error);
    }
  };

  return (
    <div className="container">
      <div className="form-container">
        <h2>Registrar Dependente</h2>
        <form onSubmit={handleDependentSubmit}>
          <label htmlFor="name">Nome do Dependente:</label>
          <input
            id="name"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <label htmlFor="telephone">Telefone:</label>
          <input
            id="telephone"       
            type="text"
            value={telephone}
            onChange={(e) => setTelephone(e.target.value)}
          />
          <label htmlFor="kinship">Parentesco:</label>
          <input
            id="kinship"
            type="text"
            value={kinship}
            onChange={(e) => setKinship(e.target.value)}
          />
          <label htmlFor="cpf">CPF:</label>
          <input
            id="cpf"
            type="text"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
          />
          <label htmlFor="clientId">Cliente:</label>
          <select value={clientId} onChange={(e) => setClientId(e.target.value)}>
            {dependents.map((client) => (
              <option key={client.id} value={client.id}>
                {client.name}
              </option>
            ))}
          </select>
          <button type="submit">Registrar Dependente</button>
        </form>
      </div>
      <div className="image-container">
        {}
      </div>
    </div>
  );
};

export default DependentRegistrationForm;
