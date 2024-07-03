import axios from 'axios';
import React, { useState } from 'react';

interface Client {
    name: string;
    id: number;
    email: string;
    telephone: string;
}

interface Plan {
    id: number;
    name: string;
}

interface Dependent {
    id: number;
    name: string;
    kinship: string;
}

const PlanTotalPrice: React.FC = () => {
    const [clientName, setClientName] = useState('');
    const [plans, setPlans] = useState<Plan[]>([]);
    const [dependents, setDependents] = useState<Dependent[]>([]);
    const [selectedPlanId, setSelectedPlanId] = useState<number | null>(null);
    const [totalPrice, setTotalPrice] = useState<number | null>(null);
    const [clientInfo, setClientInfo] = useState<Client | null>(null);

    const handleGetClient = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/clients/findByName?name=${clientName}`);
            const foundClient = response.data;
    
            if (foundClient) {
                setClientInfo(foundClient);
                setSelectedPlanId(null); // Clear the selected plan
                setTotalPrice(null); // Clear the total price
    
                // Fetching dependents
                const responseDependents = await axios.get(`http://localhost:8080/clients/${foundClient.id}/dependents`);
                setDependents(responseDependents.data);
    
                // Fetching plans related to the found client
                const responsePlans = await axios.get(`http://localhost:8080/clients/${foundClient.id}/plans`);
                setPlans(responsePlans.data);
            } else {
                console.error('Cliente não encontrado.');
            }
        } catch (error) {
            console.error('Erro ao obter o cliente:', error);
        }
    };

    const handleGetRandomPlan = async () => {
        try {
            const response = await axios.get('http://localhost:8080/plans/random');
            const planData = response.data;
            setSelectedPlanId(planData.id);
            console.log('Plano selecionado:', planData);
        } catch (error) {
            console.error('Erro ao buscar plano:', error);
        }
    };

    const handleGetTotalPrice = async () => {
        try {
            if (clientInfo && selectedPlanId !== null) {
                const responseTotalPrice = await axios.get(`http://localhost:8080/plans/${selectedPlanId}/totalPrice?clientId=${clientInfo.id}`);
                setTotalPrice(responseTotalPrice.data);
                console.log('Preço total do plano:', responseTotalPrice.data);
            } else {
                console.error('Cliente ou plano não selecionado.');
            }
        } catch (error) {
            console.error('Erro ao obter o preço total:', error);
        } 
    };
    

    return (
        <div>
            <input
                type="text"
                placeholder="Nome do Cliente"
                value={clientName}
                onChange={(e) => setClientName(e.target.value)}
                onBlur={handleGetClient}
            />

            <button onClick={handleGetRandomPlan}>Buscar Plano</button>
            <button onClick={handleGetTotalPrice}>Preço Total</button>

            {clientInfo && (
                <div>
                    <h3>Informações do Cliente:</h3>
                    <table className="client-table">
                        <thead>
                            <tr>
                                <th>Nome do Cliente</th>
                                <th>ID do Cliente</th>
                                <th>E-mail</th>
                                <th>Celular</th>
                                <th>Preço Total do Plano</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{clientInfo.name}</td>
                                <td>{clientInfo.id}</td>
                                <td>{clientInfo.email}</td>
                                <td>{clientInfo.telephone}</td>
                                <td>{'R$ ' + totalPrice}</td>
                            </tr>
                        </tbody>
                    </table>
                    <ul>
                        {plans.map((plan) => (
                            <li key={plan.id}>{plan.name}</li>
                        ))}
                    </ul>
                    <h3>Dependentes:</h3>
                    <table className="dependents-table">
                        <thead>
                            <tr>
                                <th>ID do Dependente</th>
                                <th>Nome do Dependente</th>
                                <th>Parentesco</th>
                            </tr>
                        </thead>
                        {dependents.length > 0 && (
                            <tbody>
                                {dependents.map((dependent) => (
                                    <tr key={dependent.id}>
                                        <td>{dependent.id}</td>
                                        <td>{dependent.name}</td>
                                        <td>{dependent.kinship}</td>
                                    </tr>
                                ))}
                            </tbody>
                        )}</table>
                </div>
            )}
        </div>
    );
};

export default PlanTotalPrice;
