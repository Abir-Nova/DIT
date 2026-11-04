const express = require('express');
const db = require('../db');

const router = express.Router();

// GET /api/companies/:id/ads lists the ads belonging to one company
router.get('/:id/ads', async (req, res, next) => {
  try {
    // Route parameters arrive as strings, so validate the company ID first
    const companyId = Number(req.params.id);

    if (!Number.isInteger(companyId) || companyId <= 0) {
      return res.status(400).json({ error: 'Company ID must be a positive integer' });
    }

    // Check whether the company exists before querying its ads
    const [companies] = await db.execute(
      'SELECT id_company, name_company FROM company WHERE id_company = ?',
      [companyId]
    );

    if (companies.length === 0) {
      return res.status(404).json({ error: 'Company not found' });
    }

    // Join category to return its readable name instead of only the category ID
    const [ads] = await db.execute(
      `SELECT
         j.id_jobAD,
         j.title,
         j.position,
         j.description_jobAD,
         j.id_company,
         j.id_category,
         cat.name_category
       FROM jobAD AS j
       JOIN category AS cat ON j.id_category = cat.id_category
       WHERE j.id_company = ?
       ORDER BY j.id_jobAD`,
      [companyId]
    );

    // An existing company with no ads correctly receives an empty array
    return res.status(200).json(ads);
  } catch (error) {
    return next(error);
  }
});

module.exports = router;
