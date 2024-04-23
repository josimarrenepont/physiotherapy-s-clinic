import jwt, { JwtPayload } from 'jsonwebtoken';
import React from 'react';
import { Navigate, Route } from 'react-router-dom';

interface ProtectedRouteProps {
  path: string;
  element: React.ReactNode;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ path, element }) => {
  const isAuthenticated = verificarSeTokenEstaValido();

  return isAuthenticated ? (
    <Route path={path} element={element} />
  ) : (
    <Navigate to="/login" replace />
  );
};

const verificarSeTokenEstaValido = () => {
  const token = localStorage.getItem('token');
  if (token) {
    const decodedToken = jwt.decode(token);
    if (typeof decodedToken === 'object' && decodedToken !== null && 'exp' in decodedToken) {
      const jwtPayload = decodedToken as JwtPayload;
      if (jwtPayload.exp !== undefined && jwtPayload.exp * 1000 > Date.now()) {
        // O token é válido se ele existe e não expirou
        return true;
      }
    }
  }
  return false;
};

export default ProtectedRoute;
