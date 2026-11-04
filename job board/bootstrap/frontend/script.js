// Keep the API path in one place so it is easy to change later
const API_BASE_URL = '/api';

// Find the page elements that JavaScript needs to update
const jobsList = document.querySelector('#jobs-list');
const statusMessage = document.querySelector('#status-message');
const jobCount = document.querySelector('#job-count');
const detailsSection = document.querySelector('#details-section');
const detailsContent = document.querySelector('#details-content');
const closeDetailsButton = document.querySelector('#close-details');

// Load the job ads as soon as the browser has loaded this script
document.addEventListener('DOMContentLoaded', loadJobs);

// Request the list of ads from the API and render a card for each one
async function loadJobs() {
  statusMessage.textContent = 'Loading job ads…';
  statusMessage.classList.remove('error');

  try {
    const response = await fetch(`${API_BASE_URL}/ads`); // issue here 

    // fetch does not throw automatically for HTTP errors such as 404 or 500
    if (!response.ok) {
      throw new Error(`The server returned HTTP ${response.status}`);
    }

    const ads = await response.json();

    // Clear any previous content before displaying the latest result
    jobsList.replaceChildren();
    jobCount.textContent = `${ads.length} ${ads.length === 1 ? 'opportunity' : 'opportunities'}`;

    if (ads.length === 0) {
      statusMessage.textContent = 'There are no job ads yet';
      return;
    }

    statusMessage.textContent = '';

    // Create one card for each ad returned by the API
    ads.forEach(ad => {
      jobsList.appendChild(createJobCard(ad));
    });
  } catch (error) {
    statusMessage.textContent = `Could not load job ads: ${error.message}`;
    statusMessage.classList.add('error');
    jobCount.textContent = 'Unavailable';
  }
}

// Build a job card using DOM methods rather than inserting API text as HTML
function createJobCard(ad) {
  const card = document.createElement('article');
  card.className = 'job-card';

  const topRow = document.createElement('div');
  topRow.className = 'job-card-top';

  const category = document.createElement('span');
  category.className = 'category-tag';
  category.textContent = ad.name_category || 'Uncategorized';

  const jobId = document.createElement('span');
  jobId.className = 'job-id';
  jobId.textContent = `JOB #${ad.id_jobAD}`;

  topRow.append(category, jobId);

  const title = document.createElement('h3');
  title.textContent = ad.title;

  const company = document.createElement('p');
  company.className = 'company-name';
  company.textContent = ad.name_company || 'Company not specified';

  const description = document.createElement('p');
  description.className = 'job-description';
  description.textContent = ad.description_jobAD || 'Contact the company for more information';

  const detailsButton = document.createElement('button');
  detailsButton.className = 'button';
  detailsButton.type = 'button';
  detailsButton.textContent = 'View details';

  // Fetch details only when this particular button is clicked
  detailsButton.addEventListener('click', () => loadJobDetails(ad.id_jobAD));

  card.append(topRow, title, company, description, detailsButton);

  return card;
}

// Fetch full details for the selected ad and update the existing page
async function loadJobDetails(adId) {
  detailsSection.hidden = false;
  detailsContent.textContent = 'Loading details…';
  detailsSection.scrollIntoView({ behavior: 'smooth', block: 'start' });

  try {
    const response = await fetch(`${API_BASE_URL}/ads/${encodeURIComponent(adId)}`);

    if (!response.ok) {
      throw new Error(`The server returned HTTP ${response.status}`);
    }

    const ad = await response.json();

    // Build the details view with safe text nodes
    detailsContent.replaceChildren();

    const title = document.createElement('h3');
    title.textContent = ad.title;

    const company = document.createElement('p');
    company.textContent = `${ad.name_company} · ${ad.position || 'Position not specified'}`;

    const descriptionLabel = document.createElement('strong');
    descriptionLabel.textContent = 'About the role';

    const description = document.createElement('p');
    description.textContent = ad.description_jobAD || 'No description has been provided';

    const meta = document.createElement('div');
    meta.className = 'details-meta';

    const category = document.createElement('span');
    category.textContent = ad.name_category;

    const address = document.createElement('span');
    address.textContent = ad.address_company || 'Location not specified';

    const id = document.createElement('span');
    id.textContent = `Job ID: ${ad.id_jobAD}`;

    meta.append(category, address, id);
    detailsContent.append(title, company, descriptionLabel, description, meta);
  } catch (error) {
    detailsContent.textContent = `Could not load job details: ${error.message}`;
  }
}

// Hide the details panel without navigating away or reloading the page
closeDetailsButton.addEventListener('click', () => {
  detailsSection.hidden = true;
});
