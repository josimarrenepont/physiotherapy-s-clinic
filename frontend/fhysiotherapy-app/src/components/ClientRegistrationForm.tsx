import axios from 'axios';
import React, { useState } from 'react';


const ClientRegistrationForm: React.FC = () => {
  const [clientName, setClientName] = useState('');
  const [clientCPF, setClientCPF] = useState('');
  const [clientRG, setClientRG] = useState('');
  const [clientDateOfBirth, setClientDateOfBirth] = useState('');
  const [clientSex, setClientSex] = useState('');
  const [clientMaritalStatus, setClientMaritalStatus] = useState('');
  const [clientEmail, setClientEmail] = useState('');
  const [clientTelephone, setClientTelephone] = useState('');
  const [clientProfession, setClientProfession] = useState('');

  const handleClientSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/clients', {
        name: clientName,
        cpf: clientCPF,
        rg: clientRG,
        dateOfBirth: clientDateOfBirth,
        sex: clientSex,
        maritalStatus: clientMaritalStatus,
        email: clientEmail,
        telephone: clientTelephone,
        profession: clientProfession
      });
      console.log('Cliente registrado com sucesso:', response.data);
    } catch (error) {
      console.error('Erro ao registrar cliente:', error);
    }
  };

  return (
    <div>
      <h2>Registrar Cliente</h2>
      <form onSubmit={handleClientSubmit}>
      <label htmlFor="clientName">Nome do Cliente:</label>
  <input
    id="clientName"
    type="text"
    value={clientName}
    onChange={(e) => setClientName(e.target.value)}
  />
  <label htmlFor="clientCPF">CPF:</label>
  <input
    id="clientCPF"
    type="text"
    value={clientCPF}
    onChange={(e) => setClientCPF(e.target.value)}
  />
  <label htmlFor="clientRG">RG:</label>
  <input
    id="clientRG"
    type="text"
    value={clientRG}
    onChange={(e) => setClientRG(e.target.value)}
  />
  <label htmlFor="clientDateOfBirth">Data de Nascimento:</label>
  <input
    id="clientDateOfBirth"
    type="text"
    value={clientDateOfBirth}
    onChange={(e) => setClientDateOfBirth(e.target.value)}
  />
  <label htmlFor="clientSex">Sexo:</label>
  <input
    id="clientSex"
    type="text"
    value={clientSex}
    onChange={(e) => setClientSex(e.target.value)}
  />
  <label htmlFor="clientMaritalStatus">Estado Civil:</label>
  <input
    id="clientMaritalStatus"
    type="text"
    value={clientMaritalStatus}
    onChange={(e) => setClientMaritalStatus(e.target.value)}
  />
  <label htmlFor="clientEmail">Email:</label>
  <input
    id="clientEmail"
    type="text"
    value={clientEmail}
    onChange={(e) => setClientEmail(e.target.value)}
  />
  <label htmlFor="clientTelephone">Telefone:</label>
  <input
    id="clientTelephone"
    type="text"
    value={clientTelephone}
    onChange={(e) => setClientTelephone(e.target.value)}
  />
  <label htmlFor="clientProfession">Profissão:</label>
  <input
    id="clientProfession"
    type="text"
    value={clientProfession}
    onChange={(e) => setClientProfession(e.target.value)}
  />
        {/* Adicione os demais campos do cliente aqui */}
        <button className="custom-button" type="submit">Registrar Cliente</button>
      </form>
    </div>
  );
};

export default ClientRegistrationForm;
