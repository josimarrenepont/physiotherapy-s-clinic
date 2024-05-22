import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { validateCPF } from './ValidateCpfRg'; // Importe a função de validação de CPF

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
  const [cpfValid, setCpfValid] = useState(true); // Adicione um estado para controlar a validade do CPF

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

  const formatCPF = (value: string) => {
    // Remove qualquer caracter que não seja número
    let formattedCPF = value.replace(/\D/g, '');

    // Adiciona pontos e traço conforme o formato do CPF (###.###.###-##)
    formattedCPF = formattedCPF.replace(/(\d{3})(\d)/, '$1.$2');
    formattedCPF = formattedCPF.replace(/(\d{3})(\d)/, '$1.$2');
    formattedCPF = formattedCPF.replace(/(\d{3})(\d{1,2})$/, '$1-$2');

    return formattedCPF;
  };

  const handleDependentSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const cpfSemFormato = cpf.replace(/[.-]/g, '');

  // Verifica se o CPF é válido
  if (!validateCPF(cpfSemFormato)) {
    setCpfValid(false);
    return;
  }

    try {
      const response = await axios.post(`http://localhost:8080/dependents/${clientId}`, {
        name: name,
        telephone: telephone,
        kinship: kinship,
        cpf: cpf
      });

      console.log('Dependente registrado com sucesso:', response.data);
      toast.success('Dependente registrado com sucesso!');
      setName('');
      setTelephone('');
      setKinship('');
      setCpf('');
      setCpfValid(true); // Reseta o estado de validade do CPF
      setTimeout(() => {
        window.location.href = '/'; // redireciona para a página inicial após 3 segundos
      }, 3000);
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
            value={formatCPF(cpf)}
            onChange={(e) => {
              setCpf(e.target.value);
              setCpfValid(true); // Reseta o estado de validade do CPF ao modificar o valor
            }}
            className={!cpfValid ? 'invalid' : ''}
          />
          {!cpfValid && <p className="error-message">CPF inválido</p>}
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
      {dependents.length > 0 && (
        <div className="image-container">
          {/* Renderize aqui o conteúdo da imagem */}
        </div>
      )}
      <ToastContainer />
    </div>
  );
};

export default DependentRegistrationForm;