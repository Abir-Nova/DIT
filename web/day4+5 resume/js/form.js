
/* =
   CONTACT INTERACTIONS
   = */

document.addEventListener("DOMContentLoaded", () => {
    const contact = document.querySelector("#contact");
    const form = document.querySelector("#contact-form");
    const status = document.querySelector("#form-status");

    if (!contact) return;

    // Reveal the contact section when it enters the viewport.
    const revealContact = () => {
        contact.classList.add("is-contact-visible");
    };

    if ("IntersectionObserver" in window) {
        const observer = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        revealContact();
                        observer.unobserve(entry.target);
                    }
                });
            },
            { threshold: 0.12 }
        );

        observer.observe(contact);
    } else {
        revealContact();
    }

    // Keep the status message accessible.
    if (form && status) {
        form.addEventListener("submit", () => {
            status.textContent = "Opening your email client…";
        });
    }

    // Add a small interaction to the contact links.
    contact.querySelectorAll(".contact-links a").forEach((link) => {
        link.addEventListener("pointerenter", () => {
            link.classList.add("is-hovered");
        });

        link.addEventListener("pointerleave", () => {
            link.classList.remove("is-hovered");
        });
    });
});
