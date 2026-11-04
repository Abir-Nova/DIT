// Load environment variables from the local .env file
require('dotenv').config();

const mysql = require('mysql2/promise');

// Create a connection pool so requests can reuse database connections
const pool = mysql.createPool({
  host: process.env.DB_HOST || 'localhost',
  port: Number(process.env.DB_PORT || 3306),
  user: process.env.DB_USER || 'root',
  password: process.env.DB_PASSWORD || '',
  database: process.env.DB_NAME || 'hireloop',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

// Export the pool so route files can execute SQL queries
module.exports = pool;
