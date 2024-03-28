


const Buttons = () => {
  return (
    <div>
      <button onClick={() => console.log('Navegar para Clientes')}>Clientes</button>
      <button onClick={() => console.log('Registrar Dependente')}>Registrar Dependente</button>
      <button onClick={() => console.log('Atualizar Valor do Plano')}>Atualizar Valor do Plano</button>
      <button onClick={() => console.log('Filtrar Clientes')}>Filtrar Clientes</button>
      <button onClick={() => console.log('Listar Clientes com Pagamento Pendente')}>Listar Clientes com Pagamento Pendente</button>
    </div>
  );
};

export default Buttons;
