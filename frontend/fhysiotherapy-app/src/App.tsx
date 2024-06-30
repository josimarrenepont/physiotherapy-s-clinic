// App.tsx

import React, { useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './assets/fisioplat.jpg';
import './assets/fisioplatlogo.jpg';
import ClientRegistrationForm from './components/ClientRegistrationForm';
import ClientTotalPrice from './components/ClientTotalPrice';
import ClientUpdateForm from './components/ClientUpdateForm';
import DependentRegistrationForm from './components/DependentRegistrationForm';
import Footer from './components/Footer';
import Header from './components/Header';
import LoginScreen from './components/LoginScreen'; // Importe o componente LoginScreen
import PlanUpdateForm from './components/PlanUpdateForm';
import './components/ValidateCpfRg';
import './styles/buttons.css';
import './styles/clientRegistrationForm.css';
import './styles/totalPrice.css';
const App: React.FC = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  return (
    <Router>
      <div className="app-container">
        <Header />
        <nav className="nav-container">
          <ul className="nav-list">
            {isLoggedIn ? (
              <>
                <li>
                  <button className="nav-button">
                    <Link to="/client-registration" className="nav-link">Registrar Cliente</Link>
                  </button>
                </li>
                <li>
                  <button className="nav-button">
                    <Link to="/dependent-registration" className="nav-link">Registrar Dependente</Link>
                  </button>
                </li>
                <li>
                  <button className="nav-button">
                    <Link to="/plan-update" className="nav-link">Atualizar Plano</Link>
                  </button>
                </li>
                <li>
                  <button className="nav-button">
                    <Link to="/client-total-price" className="nav-link">Pesquisar Cliente</Link>
                  </button>
                </li>
                <li>
                  <button className="nav-button">
                    <Link to="/update-client" className="nav-link">Atualizar Cliente</Link>
                  </button>
                </li>
              </>
            ) : null}
          </ul>
        </nav>

        <div className="content-container">
          <Routes>
            {!isLoggedIn && <Route path="/" element={<LoginScreen onLogin={handleLogin} />} />}
            {isLoggedIn && (
              <>
                <Route path="/client-registration" element={<ClientRegistrationForm />} />
                <Route path="/dependent-registration" element={<DependentRegistrationForm />} />
                <Route path="/plan-update" element={<PlanUpdateForm />} />
                <Route path="/client-total-price" element={<ClientTotalPrice />} />
                <Route path="/update-client" element={<ClientUpdateForm />} />
              </>
            )}
          </Routes>
        </div>

        <Footer />
      </div>
    </Router>
  );
};

export default App;