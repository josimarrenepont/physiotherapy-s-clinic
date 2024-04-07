import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="header-container"> {/* Container do cabeçalho */}
      <Link to="/" className="logo-link"> {/* Link para a homepage */}
        <img src="src/assets/fisioplat_logo.jpg" alt="Logo" className="logo-image" /> {/* Imagem da logo */}
      </Link>
    </header>
  );
};

export default Header;
