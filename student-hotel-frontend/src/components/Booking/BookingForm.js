import React, { useState, useEffect } from 'react';
import axios from '../utils/axios';

function BookingForm() {
  const [rooms, setRooms] = useState([]);
  const [selectedRoomId, setSelectedRoomId] = useState('');
  const [message, setMessage] = useState('');

  useEffect(() => {
    axios.get('/rooms')
      .then(res => setRooms(res.data.filter(r => r.available)))
      .catch(console.error);
  }, []);

  const handleBooking = async (e) => {
    e.preventDefault();
    const userId = 1; // TODO: decode JWT to get real user ID
    try {
      await axios.post(`/bookings/student?userId=${userId}&roomId=${selectedRoomId}`);
      setMessage('Booking successful!');
    } catch (err) {
      setMessage('Booking failed.');
    }
  };

  return (
    <div className="container col-md-6 mt-4">
      <h2>Book a Room</h2>
      {message && <div className="alert alert-info">{message}</div>}
      <form onSubmit={handleBooking}>
        <div className="mb-3">
          <label className="form-label">Select Room</label>
          <select 
            className="form-select" 
            value={selectedRoomId} 
            onChange={e => setSelectedRoomId(e.target.value)} 
            required
          >
            <option value="">Choose a room</option>
            {rooms.map(room => (
              <option key={room.id} value={room.id}>
                {room.type} - ${room.price}
              </option>
            ))}
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Book Now</button>
      </form>
    </div>
  );
}

export default BookingForm;
