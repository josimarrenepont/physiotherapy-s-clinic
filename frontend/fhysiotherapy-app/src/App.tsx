import React from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import ClientRegistrationForm from './components/ClientRegistrationForm';
import ClientTotalPrice from './components/ClientTotalPrice';
import ClientUpdateForm from './components/ClientUpdateForm';
import DependentRegistrationForm from './components/DependentRegistrationForm';
import Footer from './components/Footer'; // Importe o componente Footer
import Header from './components/Header'; // Importe o componente Header
import PlanUpdateForm from './components/PlanUpdateForm';
import './components/ValidateCpfRg';
import './styles/buttons.css';
import './styles/clientRegistrationForm.css';
import './styles/totalPrice.css';


const App: React.FC = () => {
  return (
    <Router>
      <div className="app-container"> {/* Container principal */}
        <Header /> {/* Componente Header */}
        <nav className="nav-container"> {/* Container da navegação */}
          <ul className="nav-list"> {/* Lista de links */}
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
                <Link to="/client-total-price" className="nav-link">Valor Total do Plano</Link>
              </button>
            </li>
            
            <li>
              <button className="nav-button">
                <Link to="/update-client" className="nav-link">Atualizar Cliente</Link>
              </button>
            </li>
          </ul>
        </nav>

        <div className="content-container"> {/* Container do conteúdo */}
          <Routes>
            <Route path="/client-registration" element={<ClientRegistrationForm />} />
            <Route path="/dependent-registration" element={<DependentRegistrationForm />} />
            <Route path="/plan-update" element={<PlanUpdateForm />} />
            <Route path="/client-total-price" element={<ClientTotalPrice />} />
            <Route path="/update-client" element={<ClientUpdateForm />} />

          </Routes>
        </div>

        <Footer /> {/* Componente Footer */}
      </div>
    </Router>
  );
};

export default App;
