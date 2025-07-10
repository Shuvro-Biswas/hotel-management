import React, { useEffect, useState } from 'react';
import axios from '../utils/axios';

function BookingHistory() {
  const [bookings, setBookings] = useState([]);
  const userId = 1; // TODO: get real userId from decoded JWT

  useEffect(() => {
    axios.get(`/bookings/student/${userId}`)
      .then(res => setBookings(res.data))
      .catch(console.error);
  }, []);

  return (
    <div className="container mt-4">
      <h2>Booking History</h2>
      {bookings.length === 0 ? (
        <p>No bookings found.</p>
      ) : (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Room</th>
              <th>Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {bookings.map(b => (
              <tr key={b.id}>
                <td>{b.room.type}</td>
                <td>{b.bookingDate}</td>
                <td>{b.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default BookingHistory;
