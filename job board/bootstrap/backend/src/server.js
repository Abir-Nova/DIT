// Load environment variables before importing the database module
require('dotenv').config();

const path = require('path');
const express = require('express');
const db = require('./db');
const adsRouter = require('./routes/ads');
const companiesRouter = require('./routes/companies');
const errorHandler = require('./middleware/errorHandler');

const app = express();
const PORT = Number(process.env.PORT || 3000);

// Parse incoming JSON request bodies so POST routes can read req.body
app.use(express.json());

// Serve the static front-end files from the frontend directory
app.use(express.static(path.join(__dirname, '../../frontend')));

// A small health endpoint helps confirm that the server is running
app.get('/api/health', (req, res) => {
  return res.status(200).json({ status: 'ok', service: 'HireLoop API' });
});

// Register API route groups
app.use('/api/ads', adsRouter);
app.use('/api/companies', companiesRouter);

// Return a JSON 404 for unknown API URLs
app.use('/api', (req, res) => {
  return res.status(404).json({ error: 'API route not found' });
});

// Convert unhandled errors into a safe JSON response
app.use(errorHandler);

// Start only after confirming the database connection is available
async function startServer() {
  try {
    const connection = await db.getConnection();
    await connection.ping();
    connection.release();

    app.listen(PORT, () => {
      console.log(`HireLoop is running at http://localhost:${PORT}`);
      console.log(`API health check: http://localhost:${PORT}/api/health`);
      console.log('Database connection successful');
    });
  } catch (error) {
    console.error('Could not connect to MySQL');
    console.error('Check that MySQL is running and backend/.env has the correct settings');
    console.error(error.message);
    process.exit(1);
  }
}

startServer();
