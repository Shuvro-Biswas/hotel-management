import React, { useEffect, useState } from 'react';
import axios from '../utils/axios';

function RoomList() {
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    axios.get('/rooms')
      .then(res => setRooms(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="container mt-4">
      <h2>Available Rooms</h2>
      <div className="row">
        {rooms.length === 0 && <p>No rooms found.</p>}
        {rooms.map(room => (
          <div key={room.id} className="col-md-4 mb-3">
            <div className={`card ${room.available ? '' : 'bg-light'}`}>
              <div className="card-body">
                <h5 className="card-title">{room.type}</h5>
                <p className="card-text">Price: ${room.price}</p>
                <p className="card-text">Available: {room.available ? 'Yes' : 'No'}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default RoomList;
