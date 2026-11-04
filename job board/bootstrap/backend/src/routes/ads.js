const express = require('express');
const db = require('../db');

const router = express.Router();

// Convert a value into a positive integer or return null when it is invalid
function positiveInteger(value) {
  const number = Number(value);
  return Number.isInteger(number) && number > 0 ? number : null;
}

// GET /api/ads lists all ads for the front-end
router.get('/', async (req, res, next) => {
  try {
    // Join the company and category tables so the page can show their names
    const [ads] = await db.execute(
      `SELECT
         j.id_jobAD,
         j.title,
         j.position,
         j.description_jobAD,
         j.id_company,
         j.id_category,
         c.name_company,
         cat.name_category
       FROM jobAD AS j
       JOIN company AS c ON j.id_company = c.id_company
       JOIN category AS cat ON j.id_category = cat.id_category
       ORDER BY j.id_jobAD DESC`
    );

    return res.status(200).json(ads);
  } catch (error) {
    return next(error);
  }
});

// GET /api/ads/:id returns full details for one ad
router.get('/:id', async (req, res, next) => {
  try {
    const adId = positiveInteger(req.params.id);

    if (!adId) {
      return res.status(400).json({ error: 'Ad ID must be a positive integer' });
    }

    // Get the ad together with its company and category information
    const [ads] = await db.execute(
      `SELECT
         j.id_jobAD,
         j.title,
         j.position,
         j.description_jobAD,
         j.id_company,
         j.id_category,
         c.name_company,
         c.address_company,
         c.description_company,
         cat.name_category
       FROM jobAD AS j
       JOIN company AS c ON j.id_company = c.id_company
       JOIN category AS cat ON j.id_category = cat.id_category
       WHERE j.id_jobAD = ?`,
      [adId]
    );

    if (ads.length === 0) {
      return res.status(404).json({ error: 'Job ad not found' });
    }

    return res.status(200).json(ads[0]);
  } catch (error) {
    return next(error);
  }
});

// POST /api/ads creates a new job ad
router.post('/', async (req, res, next) => {
  try {
    const { title, position, description_jobAD } = req.body;
    const companyId = positiveInteger(req.body.id_company);
    const categoryId = positiveInteger(req.body.id_category);

    // Validate the required title and foreign key IDs before touching the database
    if (typeof title !== 'string' || title.trim() === '') {
      return res.status(400).json({ error: 'title is required and must be a non-empty string' });
    }

    if (title.trim().length > 100) {
      return res.status(400).json({ error: 'title must contain at most 100 characters' });
    }

    if (!companyId) {
      return res.status(400).json({ error: 'id_company must be a positive integer' });
    }

    if (!categoryId) {
      return res.status(400).json({ error: 'id_category must be a positive integer' });
    }

    if (position != null && (typeof position !== 'string' || position.length > 50)) {
      return res.status(400).json({ error: 'position must be a string of at most 50 characters' });
    }

    if (description_jobAD != null &&
        (typeof description_jobAD !== 'string' || description_jobAD.length > 500)) {
      return res.status(400).json({
        error: 'description_jobAD must be a string of at most 500 characters'
      });
    }

    // Validate foreign key references to return helpful errors instead of raw SQL errors
    const [companies] = await db.execute(
      'SELECT id_company FROM company WHERE id_company = ?',
      [companyId]
    );

    if (companies.length === 0) {
      return res.status(404).json({ error: 'Company not found' });
    }

    const [categories] = await db.execute(
      'SELECT id_category FROM category WHERE id_category = ?',
      [categoryId]
    );

    if (categories.length === 0) {
      return res.status(404).json({ error: 'Category not found' });
    }

    // Use placeholders to safely pass user input into the INSERT query
    const [result] = await db.execute(
      `INSERT INTO jobAD
         (title, position, id_company, id_category, description_jobAD)
       VALUES (?, ?, ?, ?, ?)`,
      [
        title.trim(),
        position == null || position.trim() === '' ? null : position.trim(),
        companyId,
        categoryId,
        description_jobAD == null || description_jobAD.trim() === ''
          ? null
          : description_jobAD.trim()
      ]
    );

    // Return the newly created resource and its generated database ID
    const [createdAds] = await db.execute(
      `SELECT
         j.id_jobAD,
         j.title,
         j.position,
         j.description_jobAD,
         j.id_company,
         j.id_category,
         c.name_company,
         cat.name_category
       FROM jobAD AS j
       JOIN company AS c ON j.id_company = c.id_company
       JOIN category AS cat ON j.id_category = cat.id_category
       WHERE j.id_jobAD = ?`,
      [result.insertId]
    );

    return res.status(201).json(createdAds[0]);
  } catch (error) {
    return next(error);
  }
});

// POST /api/ads/:id/applications records an application for one ad
router.post('/:id/applications', async (req, res, next) => {
  try {
    const adId = positiveInteger(req.params.id);
    const applicantId = positiveInteger(req.body.id_applicant);
    const { message } = req.body;

    if (!adId) {
      return res.status(400).json({ error: 'Ad ID must be a positive integer' });
    }

    if (!applicantId) {
      return res.status(400).json({ error: 'id_applicant must be a positive integer' });
    }

    if (message != null && (typeof message !== 'string' || message.length > 500)) {
      return res.status(400).json({ error: 'message must be a string of at most 500 characters' });
    }

    // Make sure the job ad exists before inserting an application
    const [ads] = await db.execute(
      'SELECT id_jobAD FROM jobAD WHERE id_jobAD = ?',
      [adId]
    );

    if (ads.length === 0) {
      return res.status(404).json({ error: 'Job ad not found' });
    }

    // Make sure the applicant exists before inserting an application
    const [applicants] = await db.execute(
      'SELECT id_applicant, name_applicant FROM applicant WHERE id_applicant = ?',
      [applicantId]
    );

    if (applicants.length === 0) {
      return res.status(404).json({ error: 'Applicant not found' });
    }

    // Check for a duplicate application before attempting the INSERT
    const [existingApplications] = await db.execute(
      'SELECT id_application FROM application WHERE id_jobAD = ? AND id_applicant = ?',
      [adId, applicantId]
    );

    if (existingApplications.length > 0) {
      return res.status(409).json({ error: 'This applicant has already applied to this job' });
    }

    // The ad ID comes from the URL, so the client does not send it in the body
    const [result] = await db.execute(
      'INSERT INTO application (id_jobAD, id_applicant, message) VALUES (?, ?, ?)',
      [adId, applicantId, message == null || message.trim() === '' ? null : message.trim()]
    );

    // Read back the saved row so the response includes its generated ID and date
    const [createdApplications] = await db.execute(
      `SELECT
         a.id_application,
         a.id_jobAD,
         a.id_applicant,
         p.name_applicant,
         a.message,
         a.date_application
       FROM application AS a
       JOIN applicant AS p ON a.id_applicant = p.id_applicant
       WHERE a.id_application = ?`,
      [result.insertId]
    );

    return res.status(201).json(createdApplications[0]);
  } catch (error) {
    // MySQL's unique constraint is the final protection against simultaneous duplicate requests
    if (error.code === 'ER_DUP_ENTRY') {
      return res.status(409).json({ error: 'This applicant has already applied to this job' });
    }

    return next(error);
  }
});

module.exports = router;
