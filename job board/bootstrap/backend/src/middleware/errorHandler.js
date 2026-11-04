// Handle errors that were not handled inside an individual route
function errorHandler(error, req, res, next) {
  // Log the technical details in the server terminal for debugging
  console.error(error);

  // Avoid sending a second response if headers have already been sent
  if (res.headersSent) {
    return next(error);
  }

  // Return a generic message instead of exposing database details to clients
  return res.status(500).json({
    error: 'Internal server error'
  });
}

module.exports = errorHandler;
