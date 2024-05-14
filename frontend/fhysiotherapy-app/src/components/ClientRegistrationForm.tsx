import axios from 'axios';
import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { validateCPF, validateRG } from './ValidateCpfRg'; // Importe as funções de validação

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
  const [rgValid, setRgValid] = useState(true);
  const [cpfValid, setCpfValid] = useState(true);

  const formatCPF = (value: string) => {
    // Remove qualquer caracter que não seja número
    let formattedValue = value.replace(/\D/g, '');

    // Insere os pontos e o traço no CPF
    formattedValue = formattedValue.replace(/(\d{3})(\d)/, '$1.$2');
    formattedValue = formattedValue.replace(/(\d{3})(\d)/, '$1.$2');
    formattedValue = formattedValue.replace(/(\d{3})(\d{1,2})$/, '$1-$2');

    return formattedValue;
  };

  const handleCPFChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    const formattedValue = formatCPF(value);
    setCPF(formattedValue);
    setCpfValid(true); // Reseta o estado de validade do CPF ao modificar o valor
  };

  const handleClientSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const currentDate = new Date().toISOString();
      const response = await axios.post('http://localhost:8080/clients', {
        name: name,
        cpf: cpf,
        rg: rg,
        dateOfBirth: dateOfBirth,
        sex: sex,
        maritalStatus: maritalStatus,
        email: email,
        telephone: telephone,
        profession: profession,
        register: currentDate
      }, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      console.log('Cliente registrado com sucesso:', response.data);
      toast.success('Cliente registrado com sucesso!');
      setName('');
      setCPF('');
      setRG('');
      setTelephone('');
      setDateOfBirth('');
      setSex('');
      setMaritalStatus('');
      setEmail('');
      setProfession('');
      setTimeout(() => {
        window.location.href = '/';
      }, 3000);
    } catch (error) {
      console.error('Erro ao registrar cliente:', error);
    }
  };

  const handleRgChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setRG(value);

    if (!validateCPF(value) && !validateRG(value)) {
      setRgValid(false);
    } else {
      setRgValid(true);
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
          onChange={handleCPFChange}
          className={!cpfValid ? 'invalid' : ''}
        />
        {!cpfValid && <p className="error-message">CPF inválido</p>}
        <label htmlFor="rg">RG:</label>
        <input
          id="rg"
          type="text"
          value={rg}
          onChange={handleRgChange}
          className={!rgValid ? 'invalid' : ''}
        />
        <label htmlFor="telephone">Telefone:</label>
        <input
          id="telephone"
          type="text"
          value={telephone}
          onChange={(e) => setTelephone(e.target.value)}
        />
        <label htmlFor="dateOfBirth">Data de Nascimento:</label>
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
        <label htmlFor="profession">Profissão:</label>
        <input
          id="profession"
          type="text"
          value={profession}
          onChange={(e) => setProfession(e.target.value)}
        />
        <button className="custom-button" type="submit">Registrar Cliente</button>
      </form>
      <ToastContainer />
    </div>
  );
};

export default ClientRegistrationForm;
