import React from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import ClientFilter from './components/ClientFilter';
import ClientRegistrationForm from './components/ClientRegistrationForm';
import DependentRegistrationForm from './components/DependentRegistrationForm';
import PlanUpdateForm from './components/PlanUpdateForm';
import UpcomingPaymentsList from './components/UpcomingPaymentsList';
import './styles/clientRegistrationForm.css';
const App: React.FC = () => {
 return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/client-registration">Registrar Cliente</Link>
            </li>
            <li>
              <Link to="/dependent-registration">Registrar Dependente</Link>
            </li>
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

        <Routes>
          <Route path="/client-registration" element={<ClientRegistrationForm />} />
          <Route path="/dependent-registration" element={<DependentRegistrationForm />} />
          <Route path="/plan-update" element={<PlanUpdateForm />} />
          <Route path="/client-filter" element={<ClientFilter />} />
          <Route path="/upcoming-payments" element={<UpcomingPaymentsList />} />
        </Routes>
      </div>
    </Router>
 );
};

export default App;
