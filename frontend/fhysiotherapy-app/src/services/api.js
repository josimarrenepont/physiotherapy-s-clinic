// api.ts
import axios from 'axios';

const API_URL = 'http://localhost:5173/auth/signing';

const login = async (username, password) => {
  try {
    const response = await axios.post(`${API_URL}/login`, {
      username: username,
      password: password
    });
    return response.data;
  } catch (error) {
    throw new Error(`Erro ao fazer login: ${error.message}`);
  }
};

const registerClient = async (clientName) => {
  try {
    const response = await axios.post(`${API_URL}/client`, {
      name: clientName,
    });
    return response.data;
  } catch (error) {
    throw new Error(`Erro ao registrar cliente: ${error.message}`);
  }
};

const registerDependent = async (dependentName) => {
  try {
     const response = await axios.post(`${API_URL}/dependent`, {
       name: dependentName,
     });
     return response.data;
  } catch (error) {
     throw new Error(`Erro ao registrar dependente: ${error.message}`);
  }
 };

export { login, registerClient, registerDependent };

