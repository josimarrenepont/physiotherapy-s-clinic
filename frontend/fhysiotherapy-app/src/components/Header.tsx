// Header.tsx
import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/fisioplatlogo.jpg'; // Importe a imagem

const Header: React.FC = () => {
  return (
    <header className="header-container">
      <Link to="/">
        <img src={logo} alt="Fisioplat Logo" className="logo" />
      </Link>
    </header>
  );
};

export default Header;
