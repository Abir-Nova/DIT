# Abir Nassif — Online Resume

A recruiter-facing interactive online resume built with semantic HTML, separated CSS, and vanilla JavaScript.

## Structure

abir-online-resume/
    index.html
    assets/
       Abir_Nassif_CV_2026.pdf
    css/
       style.css
    js/
       main.js
    README.md


## Features

- Responsive design for desktop, tablet and mobile
- Semantic HTML5 structure
- Separate HTML / CSS / JavaScript
- Scroll reveal animations
- Animated expertise marquee
- Mobile navigation
- Interactive hover states
- Accessible skip link and reduced-motion support
- Interactive contact form using "mailto:"
- Download/view CV CTA
- Recruiter-oriented hierarchy: profile → experience → projects → skills → education → contact

## Run locally

Open "index.html" directly in a browser, 

or use a local server:

bash
npx serve 


Then open `http://localhost:3000 `.

## Contact form

The included form uses `mailto:` so it works without a backend. 
For a production deployment I'll replace with Formspree, Netlify Forms, or an API/backend endpoint.

## Deployment

This project can be hosted on GitHub Pages, Netlify or Vercel as a static site.
