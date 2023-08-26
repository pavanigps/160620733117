import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  const [trains, setTrains] = useState([]);

  useEffect(() => {
    async function fetchTrains() {
      try {
        const response = await axios.get('http://localhost:3001/trains');
        setTrains(response.data);
      } catch (error) {
        console.error('Error fetching train data', error);
      }
    }

    fetchTrains();
  }, []);

  return (
    <div className="App">
      <h1>Train Schedule</h1>
      <ul>
        {trains.map(train => (
          <li key={train.trainNumber}>
            Train: {train.trainName}, Departure Time: {train.departureTime.Hours}:{train.departureTime.Minutes}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;