/* eslint-disable react-refresh/only-export-components */
import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // URL do backend
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '') // Remove o prefixo '/api' antes de enviar para o backend
      }
    }
  }
});