// LoginScreen.tsx

import React, { useState } from 'react';

const LoginScreen: React.FC<{ onLogin: () => void }> = ({ onLogin }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    // Aqui você pode fazer a lógica de autenticação, por exemplo, verificar o nome de usuário e senha.
    // Se a autenticação for bem-sucedida, defina loggedIn como true.
    setLoggedIn(true);
    onLogin(); // Chama a função fornecida pelo componente pai (App) para informá-lo que o login foi bem-sucedido.
  };

  return (
    <div>
      {loggedIn ? (
        <p>Você já está logado!</p>
      ) : (
        <div>
          <input
            type="text"
            placeholder="Nome de usuário"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            placeholder="Senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button onClick={handleLogin}>Login</button>
        </div>
      )}
    </div>
  );
};

export default LoginScreen;
