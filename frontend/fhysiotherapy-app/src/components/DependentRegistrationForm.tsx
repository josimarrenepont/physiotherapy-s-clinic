import axios from 'axios';
import React, { useEffect, useState } from 'react';

interface Client {
 id: string;
 name: string;
}

const DependentRegistrationForm: React.FC = () => {
 const [dependents, setDependents] = useState<Client[]>([]);
 const [dependentName, setDependentName] = useState('');
 const [dependentTelephone, setDependentTelephone] = useState('');
 const [dependentKinship, setDependentKinship] = useState('');
 const [dependentCPF, setDependentCPF] = useState('');
 const [clientId, setClientId] = useState('');

 useEffect(() => {
    const fetchClients = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/clients');
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
      const response = await axios.post('http://localhost:8080/api/dependent', {
        name: dependentName,
        telephone: dependentTelephone,
        kinship: dependentKinship,
        cpf: dependentCPF,
        clientId: clientId
      });
      console.log('Dependente registrado com sucesso:', response.data);
    } catch (error) {
      console.error('Erro ao registrar dependente:', error);
    }
 };

 return (
    <div>
      <h2>Registrar Dependente</h2>
      <form onSubmit={handleDependentSubmit}>
        <label htmlFor="dependentName">Nome do Dependente:</label>
        <input
          id="dependentName"
          type="text"
          value={dependentName}
          onChange={(e) => setDependentName(e.target.value)}
        />
        <label htmlFor="dependentTelephone">Telefone:</label>
        <input
          id="dependentTelephone"       
          type="text"
          value={dependentTelephone}
          onChange={(e) => setDependentTelephone(e.target.value)}
        />
        <label htmlFor="dependentKinships">Parentesco:</label>
        <input
          id="dependentKinships"
          type="text"
          value={dependentKinship}
          onChange={(e) => setDependentKinship(e.target.value)}
        />
        <label htmlFor="dependentCPF">CPF:</label>
        <input
          id="dependentCPF"
          type="text"
          value={dependentCPF}
          onChange={(e) => setDependentCPF(e.target.value)}
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
 );
};

export default DependentRegistrationForm;