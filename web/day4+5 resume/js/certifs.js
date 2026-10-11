
/* =========================================
   CERTIFICATIONS — SCROLL REVEAL
   ========================================= */

document.addEventListener("DOMContentLoaded", () => {
    const section = document.querySelector("#certifs");
    if (!section) return;

    const cards = section.querySelectorAll(".certif-card");

    // Respect the visitor's reduced-motion preference.
    const reduceMotion = window.matchMedia(
        "(prefers-reduced-motion: reduce)"
    ).matches;

    if (reduceMotion || !("IntersectionObserver" in window)) {
        cards.forEach((card) => card.classList.add("is-visible"));
        return;
    }

    const observer = new IntersectionObserver(
        (entries, currentObserver) => {
            entries.forEach((entry) => {
                if (!entry.isIntersecting) return;

                const card = entry.target;
                const index = Number(card.dataset.revealIndex || 0);

                // Reveal each card with a short staggered delay.
                window.setTimeout(() => {
                    card.classList.add("is-visible");
                }, index * 120);

                // Reveal only once.
                currentObserver.unobserve(card);
            });
        },
        {
            threshold: 0.12,
            rootMargin: "0px 0px -35px 0px"
        }
    );

    cards.forEach((card, index) => {
        card.dataset.revealIndex = index;
        observer.observe(card);
    });
});
