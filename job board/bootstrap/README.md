# HireLoop Job Board

A beginner-friendly job board built with Node.js, Express, MySQL, HTML, CSS and browser JavaScript.

## What it does

- Lists job ads from the API when the page loads
- Shows the title and short description of every ad
- Loads full details when a user clicks **View details**, without reloading the page
- Implements the four required API routes from the bootstrap
- Adds `GET /ads` so the front-end can list all ads
- Validates requests and returns useful HTTP status codes

## 1. Prerequisites

- Node.js and npm
- MySQL Server
- A MySQL client such as MySQL Workbench
- Visual Studio Code (recommended)

Check Node.js and npm in a new VS Code terminal or PowerShell window:

```powershell
node -v
npm -v
```

If either command is not recognized, install the LTS version from https://nodejs.org/en/download/ and reopen the terminal.

Check that MySQL Server is running and that you can connect to it from MySQL Workbench.

## 2. Create the database

1. Open MySQL Workbench and connect to your local MySQL server
2. Open `database.sql` from this project
3. Review the script, then execute it
4. The script creates the `hireloop` database if needed, creates missing tables, and inserts the sample records only when matching records do not already exist
5. In Workbench, refresh the Schemas panel and check the `hireloop` schema

The SQL script does not drop your database or delete existing rows.

If your MySQL account is not `root`, use your own username in `.env`.

## 3. Configure the backend

Open a terminal in the `backend` directory:

```powershell
cd backend
```

Install the project's dependencies:

```powershell
npm install
```

Create your local environment file by copying the example:

```powershell
Copy-Item .env.example .env
```

Open `backend/.env` and update `DB_USER` and `DB_PASSWORD` to match your local MySQL account.

Example for a typical local setup:

```env
PORT=3000
DB_HOST=localhost
DB_PORT=3306
DB_USER=root
DB_PASSWORD=your_actual_mysql_password
DB_NAME=hireloop
```

Replace `your_actual_mysql_password` with your real password. Do not commit `.env` or share it publicly.

## 4. Start the application

From the `backend` directory:

```powershell
npm run dev
```

You should see a message that the server is listening on port 3000 and that the database connection succeeded.

Open this page in your browser:

http://localhost:3000

The front-end and API are served by the same server.

To stop the server, press `Ctrl+C` in the terminal.

## 5. API routes

| Method | URL | Purpose | Success |
|---|---|---|---|
| GET | `/api/ads` | List all ads for the front-end | 200 |
| GET | `/api/companies/:id/ads` | List ads for one company | 200 |
| GET | `/api/ads/:id` | Get full details for one ad | 200 |
| POST | `/api/ads` | Create an ad | 201 |
| POST | `/api/ads/:id/applications` | Submit an application | 201 |

The `/api` prefix keeps API URLs separate from the front-end page.

## 6. Test the routes with PowerShell

Keep the server running in one terminal and use a second terminal for requests.

### List all ads

```powershell
Invoke-RestMethod http://localhost:3000/api/ads
```

### List ads for company 1

```powershell
Invoke-RestMethod http://localhost:3000/api/companies/1/ads
```

### Get ad 1 details

```powershell
Invoke-RestMethod http://localhost:3000/api/ads/1
```

### Create a job ad

```powershell
$body = @{
  title = "Junior PHP Developer"
  position = "Backend Developer"
  id_company = 1
  id_category = 1
  description_jobAD = "Build and maintain backend applications"
} | ConvertTo-Json

Invoke-RestMethod -Method Post `
  -Uri http://localhost:3000/api/ads `
  -ContentType "application/json" `
  -Body $body
```

A successful creation returns HTTP 201 and the new ad data.

### Submit an application

Use an applicant ID that exists in your database and an ad they have not already applied to.

```powershell
$body = @{
  id_applicant = 3
  message = "I am interested in this position"
} | ConvertTo-Json

Invoke-RestMethod -Method Post `
  -Uri http://localhost:3000/api/ads/3/applications `
  -ContentType "application/json" `
  -Body $body
```

The sample data already contains some applications. The unique database constraint prevents an applicant from applying to the same ad twice, so choose a valid combination that has not been used yet.

### Test a missing ad

```powershell
Invoke-RestMethod http://localhost:3000/api/ads/9999
```

This should return HTTP 404. PowerShell may display an error for non-2xx responses, which is expected for this test.

## 7. Folder architecture

```text
hireloop/
├── database.sql
├── API.md
├── README.md
├── .gitignore
├── frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
└── backend/
    ├── package.json
    ├── .env.example
    └── src/
        ├── server.js
        ├── db.js
        ├── middleware/
        │   └── errorHandler.js
        └── routes/
            ├── ads.js
            └── companies.js
```

## 8. Common problems

### `node` or `npm` is not recognized

Install the Node.js LTS release, close all terminal windows, reopen VS Code, and try `node -v` again.

### `ECONNREFUSED` or a database connection error

Check that MySQL Server is running, the port is correct, and `.env` contains the correct credentials.

### `Access denied for user`

The username or password in `.env` does not match your MySQL account.

### `Unknown database 'hireloop'`

Run `database.sql` in MySQL Workbench.

### Port 3000 is already in use

Change `PORT=3000` to another unused port such as `3001` in `.env`, restart the server, and use that port in the browser and API tests.

### The page opens but ads fail to load

Open the browser developer tools with `F12`, select **Console** and **Network**, and inspect the `/api/ads` request. Also check the server terminal for errors.

## 9. Before submitting

- Include the source code, `database.sql`, and this README
- Include `backend/package-lock.json` after running `npm install`
- Do not include `backend/node_modules`
- Do not include your real `.env` file or database password
- Make sure the project runs from the README instructions
