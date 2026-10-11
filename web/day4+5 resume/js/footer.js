
/* =========================================
   FOOTER INTERACTIONS
   ========================================= */

document.addEventListener("DOMContentLoaded", () => {
    const footer = document.querySelector(".footer");
    const progress = document.querySelector(".footer-progress span");
    const backToTop = document.querySelector(".footer-back-top");
    const year = document.querySelector("[data-current-year]");

    if (year) {
        year.textContent = new Date().getFullYear();
    }

    // Reveal the footer when it enters the viewport.
    if (footer) {
        if ("IntersectionObserver" in window) {
            const observer = new IntersectionObserver(
                (entries) => {
                    entries.forEach((entry) => {
                        if (entry.isIntersecting) {
                            footer.classList.add("is-footer-visible");
                            observer.unobserve(footer);
                        }
                    });
                },
                { threshold: 0.08 }
            );

            observer.observe(footer);
        } else {
            footer.classList.add("is-footer-visible");
        }
    }

    // Update the thin progress indicator.
    let ticking = false;

    const updateProgress = () => {
        if (!progress) return;

        const scrollableHeight =
            document.documentElement.scrollHeight - window.innerHeight;

        const amount = scrollableHeight > 0
            ? (window.scrollY / scrollableHeight) * 100
            : 0;

        progress.style.width = `${Math.min(100, Math.max(0, amount))}%`;
        ticking = false;
    };

    window.addEventListener(
        "scroll",
        () => {
            if (!ticking) {
                window.requestAnimationFrame(updateProgress);
                ticking = true;
            }
        },
        { passive: true }
    );

    updateProgress();

    // Return to the top of the page.
    if (backToTop) {
        backToTop.addEventListener("click", () => {
            window.scrollTo({
                top: 0,
                behavior: window.matchMedia(
                    "(prefers-reduced-motion: reduce)"
                ).matches ? "auto" : "smooth"
            });
        });
    }
});
