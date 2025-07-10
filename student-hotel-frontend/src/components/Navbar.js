import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { removeToken, isLoggedIn } from './utils/auth';

function Navbar() {
  const navigate = useNavigate();

  const logout = () => {
    removeToken();
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
      <div className="container">
        <Link className="navbar-brand" to="/">HotelMgmt</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/rooms">Rooms</Link>
            </li>
            {isLoggedIn() && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/booking">Book</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/history">My Bookings</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/admin">Admin</Link>
                </li>
              </>
            )}
          </ul>
          <ul className="navbar-nav ms-auto">
            {isLoggedIn() ? (
              <li className="nav-item">
                <button className="btn btn-outline-light" onClick={logout}>Logout</button>
              </li>
            ) : (
              <>
                <li className="nav-item me-2">
                  <Link className="btn btn-outline-light" to="/login">Login</Link>
                </li>
                <li className="nav-item">
                  <Link className="btn btn-light" to="/register">Register</Link>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
