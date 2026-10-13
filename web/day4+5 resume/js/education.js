
/* =
   EDUCATION SCROLL ANIMATIONS
   = */

document.addEventListener("DOMContentLoaded", () => {
    const educationItems = document.querySelectorAll(
        ".education-item"
    );

    const educationIntro = document.querySelector(
        ".education-intro"
    );

    // Fallback for browsers without IntersectionObserver
    if (!("IntersectionObserver" in window)) {
        educationItems.forEach((item) => {
            item.classList.add("is-visible");
        });

        educationIntro?.classList.add("is-visible");
        return;
    }

    // Reveal the education heading
    if (educationIntro) {
        const introObserver = new IntersectionObserver(
            (entries, observer) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        educationIntro.classList.add("is-visible");
                        observer.unobserve(entry.target);
                    }
                });
            },
            { threshold: 0.15 }
        );

        introObserver.observe(educationIntro);
    }

    // Reveal each education entry separately
    const educationObserver = new IntersectionObserver(
        (entries, observer) => {
            entries.forEach((entry) => {
                if (entry.isIntersecting) {
                    const item = entry.target;
                    const index = Number(item.dataset.index || 0);

                    // Small stagger between entries
                    item.style.transitionDelay = `${index * 140}ms`;
                    item.classList.add("is-visible");

                    observer.unobserve(item);
                }
            });
        },
        {
            threshold: 0.15,
            rootMargin: "0px 0px -40px 0px"
        }
    );

    educationItems.forEach((item, index) => {
        item.dataset.index = index;
        educationObserver.observe(item);
    });
});
