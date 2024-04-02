
const Buttons = () => {
  return (
    <div style={{ display: 'flex', justifyContent: 'space-around', marginTop: '20px' }}>
      <a href="/clientes" style={{ padding: '10px 20px', borderRadius: '5px', backgroundColor: '#007bff', color: 'white', cursor: 'pointer', textDecoration: 'none' }}>Clientes</a>
      <a href="/registrar-dependente" style={{ padding: '10px 20px', borderRadius: '5px', backgroundColor: '#007bff', color: 'white', cursor: 'pointer', textDecoration: 'none' }}>Registrar Dependente</a>
      <a href="/atualizar-plano" style={{ padding: '10px 20px', borderRadius: '5px', backgroundColor: '#007bff', color: 'white', cursor: 'pointer', textDecoration: 'none' }}>Atualizar Valor do Plano</a>
      <a href="/filtrar-clientes" style={{ padding: '10px 20px', borderRadius: '5px', backgroundColor: '#007bff', color: 'white', cursor: 'pointer', textDecoration: 'none' }}>Filtrar Clientes</a>
      <a href="/listar-clientes" style={{ padding: '10px 20px', borderRadius: '5px', backgroundColor: '#007bff', color: 'white', cursor: 'pointer', textDecoration: 'none' }}>Listar Clientes com Pagamento Pendente</a>
    </div>
  );
};

export default Buttons;

