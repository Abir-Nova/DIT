
/* =
   SKILLS FALLING ANIMATION
   = */

document.addEventListener("DOMContentLoaded", () => {
    const skillsSection = document.querySelector("#skills");
    const skills = document.querySelectorAll("#skills .skill-tag");

    if (!skillsSection || skills.length === 0) {
        return;
    }

    // Prevent the animation from being started more than once.
    let animationStarted = false;

    // Reduced-motion users see the skills without the falling animation.
    const reduceMotion = window.matchMedia(
        "(prefers-reduced-motion: reduce)"
    ).matches;

    if (reduceMotion) {
        skills.forEach((skill) => {
            skill.classList.add("is-landed");
        });

        return;
    }

    // Start when the skills section becomes visible.
    const observer = new IntersectionObserver(
        (entries) => {
            entries.forEach((entry) => {
                if (entry.isIntersecting && !animationStarted) {
                    animationStarted = true;
                    observer.unobserve(skillsSection);

                    skills.forEach((skill, index) => {
                        // Each skill starts after the previous one.
                        const delay = index * 110;

                        window.setTimeout(() => {
                            skill.classList.add("is-falling");

                            // When the animation finishes, keep the skill still.
                            skill.addEventListener(
                                "animationend",
                                () => {
                                    skill.classList.remove("is-falling");
                                    skill.classList.add("is-landed");
                                },
                                { once: true }
                            );
                        }, delay);
                    });
                }
            });
        },
        {
            threshold: 0.15
        }
    );

    observer.observe(skillsSection);
});
