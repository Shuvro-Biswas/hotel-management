import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Login from './components/Auth/Login';
import Register from './components/Auth/Register';
import RoomList from './components/Rooms/RoomList';
import BookingForm from './components/Booking/BookingForm';
import BookingHistory from './components/Booking/BookingHistory';
import AdminDashboard from './components/Admin/AdminDashboard';
import PrivateRoute from './components/PrivateRoute';
import Navbar from './components/Navbar';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<RoomList />} />
        <Route path="/rooms" element={<RoomList />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/booking" element={
          <PrivateRoute>
            <BookingForm />
          </PrivateRoute>
        } />
        <Route path="/history" element={
          <PrivateRoute>
            <BookingHistory />
          </PrivateRoute>
        } />
        <Route path="/admin" element={
          <PrivateRoute>
            <AdminDashboard />
          </PrivateRoute>
        } />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

// import React from 'react';
// import { BrowserRouter, Routes, Route } from 'react-router-dom';
// import Register from './components/Auth/Register';

// function App() {
//   return (
//     <BrowserRouter>
//       <Routes>
//         <Route path="/" element={<Register />} />
//       </Routes>
//     </BrowserRouter>
//   );
// }

// export default App;

