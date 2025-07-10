import React, { useEffect, useState } from 'react';
import axios from '../utils/axios';

function AdminDashboard() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    axios.get('/bookings/admin')
      .then(res => setBookings(res.data))
      .catch(console.error);
  }, []);

  const updateStatus = (id, status) => {
    axios.put(`/bookings/admin/status?bookingId=${id}&status=${status}`)
      .then(() => {
        setBookings(bookings.map(b => b.id === id ? {...b, status} : b));
      })
      .catch(console.error);
  };

  return (
    <div className="container mt-4">
      <h2>Admin Booking Management</h2>
      {bookings.length === 0 && <p>No bookings.</p>}
      <table className="table">
        <thead>
          <tr>
            <th>Student</th>
            <th>Room</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map(b => (
            <tr key={b.id}>
              <td>{b.student.username}</td>
              <td>{b.room.type}</td>
              <td>{b.status}</td>
              <td>
                {b.status === 'PENDING' && (
                  <>
                    <button 
                      className="btn btn-success btn-sm me-2"
                      onClick={() => updateStatus(b.id, 'APPROVED')}
                    >
                      Approve
                    </button>
                    <button 
                      className="btn btn-danger btn-sm"
                      onClick={() => updateStatus(b.id, 'REJECTED')}
                    >
                      Reject
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminDashboard;
