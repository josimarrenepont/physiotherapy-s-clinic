import axios from 'axios';
import React, { useState } from 'react';

interface Client {
    name: string;
    id: number;
    email: string;
    telephone: string;
    // Add other properties as needed
}

interface Plan {
    id: number;
    name: string;
}

const PlanTotalPrice: React.FC = () => {
    const [clientName, setClientName] = useState('');
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [plans, setPlans] = useState<Plan[]>([]);
    const [selectedPlanId, setSelectedPlanId] = useState<number | null>(null);
    const [totalPrice, setTotalPrice] = useState<number | null>(null);
    const [clientInfo, setClientInfo] = useState<Client | null>(null);

    const handleGetClient = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/clients?name=${clientName}`);
            const foundClient = response.data.find((c: Client) => c.name === clientName);

            if (foundClient) {
                setClientInfo(foundClient);

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
        } catch (error) {
            console.error('Erro ao buscar plano:', error);
        }
    };

    const handleGetTotalPrice = async () => {
        try {
            if (clientInfo && selectedPlanId !== null) {
                const responseTotalPrice = await axios.get(`http://localhost:8080/plans/${selectedPlanId}/totalPrice?clientId=${clientInfo.id}`);
                setTotalPrice(responseTotalPrice.data);
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
            {clientInfo && totalPrice !== null && (
                <table className="client-table">
                    <thead>
                    <h3>Informações do Cliente:</h3>
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
                            <td>{"R$ " + totalPrice}</td>
                        </tr>
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default PlanTotalPrice;
