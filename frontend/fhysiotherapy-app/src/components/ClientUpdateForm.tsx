import axios from 'axios';
import React, { useState } from 'react';

const ClientUpdateByNameForm: React.FC = () => {
    const [name, setName] = useState('');
    const [clientId, setClientId] = useState('');
    const [client, setClient] = useState({
        id: '',
        name: '',
        telephone: '',
        email: '',
        maritalStatus: '',
        profession: '',
    });

    const handleSearch = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/clients/findByName?name=${name}`);
            setClientId(response.data.id);
            setClient(response.data);
        } catch (error) {
            console.error('Erro ao buscar cliente:', error);
        }
    };

    const handleUpdate = async () => {
        try {
            await axios.put(`http://localhost:8080/clients/${clientId}`, client);
            console.log('Cliente atualizado com sucesso');
        } catch (error) {
            console.error('Erro ao atualizar cliente:', error);
        }
    };

    return (
        <div>
            <h2>Atualizar Cliente por Nome</h2>
            <label htmlFor="name">Nome do Cliente:</label>
            <input
                id="name"
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />
            <button onClick={handleSearch}>Buscar</button>

            {clientId && (
                <div>
                    <label htmlFor="id">ID:</label>
                    <input
                        id="id"
                        type="text"
                        value={clientId}
                        disabled
                    />
                    <label htmlFor="name">Nome:</label>
                    <input
                        id="name"
                        type="text"
                        value={client.name}
                        onChange={(e) => setClient({ ...client, name: e.target.value })}
                    />
                    <label htmlFor="telephone">Telefone:</label>
                    <input
                        id="telephone"
                        type="text"
                        value={client.telephone}
                        onChange={(e) => setClient({ ...client, telephone: e.target.value })}
                    />
                    <label htmlFor="email">Email:</label>
                    <input
                        id="email"
                        type="text"
                        value={client.email}
                        onChange={(e) => setClient({ ...client, email: e.target.value })}
                    />
                    <label htmlFor="maritalStatus">Estado Civil:</label>
                    <input
                        id="maritalStatus"
                        type="text"
                        value={client.maritalStatus}
                        onChange={(e) => setClient({ ...client, maritalStatus: e.target.value })}
                    />
                    <label htmlFor="profession">Profissão:</label>
                    <input
                        id="profession"
                        type="text"
                        value={client.profession}
                        onChange={(e) => setClient({ ...client, profession: e.target.value })}
                    />
                    <button onClick={handleUpdate}>Atualizar</button>
                </div>
            )}
        </div>
    );
};

export default ClientUpdateByNameForm;
