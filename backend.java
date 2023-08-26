const express = require('express');
const axios = require('axios');

const app = express();
app.use(express.json());

// Registration and Authentication
const clientID = 'your-client-id';
const clientSecret = 'your-client-secret';
const accessToken = 'your-access-token';

// Fetch train data from John Doe Railway Server
app.get('/trains', async (req, res) => {
  try {
    const response = await axios.get('http://20.244.56.144/train/trains', {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });

    // Process and filter train data based on your requirements

    res.status(200).json(processedTrainData);
  } catch (error) {
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.listen(3001, () => {
  console.log('API server is running on port 3001');
});