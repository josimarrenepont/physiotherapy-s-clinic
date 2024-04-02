import axios from 'axios';
import React, { useState } from 'react';

const ClientRegistrationForm: React.FC = () => {
  const [name, setName] = useState('');
  const [cpf, setCPF] = useState('');
  const [rg, setRG] = useState('');
  const [telephone, setTelephone] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [sex, setSex] = useState('');
  const [maritalStatus, setMaritalStatus] = useState('');
  const [email, setEmail] = useState('');
  const [profession, setProfession] = useState('');


  const handleClientSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/clients', {
        name: name,
        cpf: cpf,
        rg: rg,
        dateOfBirth: dateOfBirth,
        sex: sex,
        maritalStatus: maritalStatus,
        email: email,
        telephone: telephone,
        profession: profession // Enviar o ID do plano como parte do corpo da solicitação
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

        <label htmlFor="name">Nome do Cliente:</label>
        <input
          id="name"
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <label htmlFor="cpf">CPF:</label>
        <input
          id="cpf"
          type="text"
          value={cpf}
          onChange={(e) => setCPF(e.target.value)}
        />
        <label htmlFor="clientRG">RG:</label>
        <input
          id="rg"
          type="text"
          value={rg}
          onChange={(e) => setRG(e.target.value)}
        />
        <label htmlFor="DateOfBirth">Data de Nascimento:</label>
        <input
          id="dateOfBirth"
          type="text"
          value={dateOfBirth}
          onChange={(e) => setDateOfBirth(e.target.value)}
        />
        <label htmlFor="sex">Sexo:</label>
        <input
          id="sex"
          type="text"
          value={sex}
          onChange={(e) => setSex(e.target.value)}
        />
        <label htmlFor="maritalStatus">Estado Civil:</label>
        <input
          id="maritalStatus"
          type="text"
          value={maritalStatus}
          onChange={(e) => setMaritalStatus(e.target.value)}
        />
        <label htmlFor="email">Email:</label>
        <input
          id="email"
          type="text"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <label htmlFor="telephone">Telefone:</label>
        <input
          id="telephone"
          type="text"
          value={telephone}
          onChange={(e) => setTelephone(e.target.value)}
        />
        <label htmlFor="profession">Profissão:</label>
        <input
          id="profession"
          type="text"
          value={profession}
          onChange={(e) => setProfession(e.target.value)}
        />
        <button className="custom-button" type="submit">Registrar Cliente</button>
      </form>
    </div>
  );
};

export default ClientRegistrationForm;
