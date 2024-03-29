// vite.config.jsx
export default {
    
  
    server: {
      proxy: {
        '/api': 'http://localhost:8080/plans', // Redireciona as solicitações que começam com '/api' para o back-end
      },
    },
  };