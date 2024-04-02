import React, { useEffect, useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import ClientFilter from './components/ClientFilter';
import ClientRegistrationForm from './components/ClientRegistrationForm';
import DependentRegistrationForm from './components/DependentRegistrationForm';
import PlanUpdateForm from './components/PlanUpdateForm';
import UpcomingPaymentsList from './components/UpcomingPaymentsList';
import './styles/buttons.css';
import './styles/clientRegistrationForm.css';

const App: React.FC = () => {
  const [registered, setRegistered] = useState(false);
  const [planUpdated, setPlanUpdated] = useState(false);
  

  useEffect(() => {
    if (registered || planUpdated) {
      // Recarrega a página após 2 segundos
      const timer = setTimeout(() => {
        window.location.reload();
      }, 2000);

      return () => clearTimeout(timer); // Limpa o timer se o componente for desmontado antes do tempo limite
    }
  }, [registered, planUpdated]);

  const handleRegistration = () => {
    // Lógica de registro aqui
    // Após o registro, atualiza o estado para true
    setRegistered(true);
  };

  const handlePlanUpdate = () => {
    // Lógica de atualização do plano aqui
    // Após a atualização, atualiza o estado para true
    setPlanUpdated(true);
  };

  return (
    <Router>
      <div>
        <nav>
          <ul>
            {!registered && !planUpdated && (
              <>
                <li>
                  <Link to="/client-registration">Registrar Cliente</Link>
                </li>
                <li>
                  <Link to="/dependent-registration">Registrar Dependente</Link>
                </li>
              </>
            )}
            <li>
              <Link to="/plan-update">Atualizar Plano</Link>
            </li>
            <li>
              <Link to="/client-filter">Filtrar Cliente</Link>
            </li>
            <li>
              <Link to="/upcoming-payments">Pagamentos Futuros</Link>
            </li>
          </ul>
        </nav>

        <div>
          <Routes>
            <Route
              path="/client-registration"
              element={<ClientRegistrationForm onRegister={handleRegistration} />}
            />
            <Route
              path="/dependent-registration"
              element={<DependentRegistrationForm onRegister={handleRegistration} />}
            />
            <Route
              path="/plan-update"
              element={<PlanUpdateForm onUpdate={handlePlanUpdate} />}
            />
            <Route path="/client-filter" element={<ClientFilter />} />
            <Route path="/upcoming-payments" element={<UpcomingPaymentsList />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;
