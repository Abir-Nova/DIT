
import {
  AfterViewInit,
  Component,
  ElementRef,
  OnDestroy,
  ViewChild
} from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements AfterViewInit, OnDestroy {
  @ViewChild('closingSection')
  closingSection!: ElementRef<HTMLElement>;

  currentYear = new Date().getFullYear();

  private observer?: IntersectionObserver;

  ngAfterViewInit(): void {
    // Check whether the browser supports scroll observation.
    if (typeof IntersectionObserver === 'undefined') {
      this.closingSection.nativeElement.classList.add('is-visible');
      return;
    }

    // Watch the CTA section and reveal it when it enters the viewport.
    this.observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add('is-visible');

            // Reveal once; avoid repeating the animation on every scroll.
            this.observer?.unobserve(entry.target);
          }
        });
      },
      {
        threshold: 0.15,
        rootMargin: '0px 0px -35px 0px'
      }
    );

    this.observer.observe(this.closingSection.nativeElement);
  }

  ngOnDestroy(): void {
    // Disconnect the observer when Angular destroys the page.
    this.observer?.disconnect();
  }
}
