import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Client, Dependent } from '../types/types'; // Certifique-se de ajustar o caminho do import conforme a estrutura do seu projeto

const UpcomingPaymentsList: React.FC = () => {
  const [clients, setClients] = useState<Client[]>([]);

  useEffect(() => {
    const fetchClients = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/clients');
        setClients(response.data);
      } catch (error) {
        console.error('Erro ao buscar clientes:', error);
      }
    };
    fetchClients();
  }, []);

  return (
    <div>
      <h2>Clientes com Dependentes</h2>
      {clients.map((client: Client) => (
        <div key={client.id}>
          <h3>{client.name}</h3>
          <ul>
            {client.dependents.map((dependent: Dependent) => (
              <li key={dependent.id}>{dependent.name}</li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default UpcomingPaymentsList;
