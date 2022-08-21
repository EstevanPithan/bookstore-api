import React from 'react'
import { Link } from 'react-router-dom'
const Header = () => {
  return (
    <header className="header-wrapper">
      <div className="header">
        <div className="checkbox-container">
          <div className="checkbox-wrapper">
            <input type="checkbox" id="toggle" />
            <label className="checkbox" for="toggle">
              <div className="trace"></div>
              <div className="trace"></div>
              <div className="trace"></div>
            </label>

            <div className="menu"></div>

            <nav className="menu-items">
              <ul>
                <li>
                  <Link to="/newBuyOrder"> Comprar Livros</Link>
                </li>
                <li>
                  <Link to="/newSellOrder"> Vender Livros</Link>
                </li>
              </ul>
              <ul>
                <li>CONSULTA</li>
                <li>
                  <Link to="/">Livros</Link>
                </li>
              </ul>
              <ul>
                <li>CADASTRO</li>
                <li>
                  <Link to="./newBook"> Livros</Link>
                </li>

                <li>
                  <Link to="/newClient"> Clientes</Link>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>

      <div className="brand-container">
        <h1>TQI BOOKSTORE</h1>
      </div>
    </header>
  )
}

export default Header
