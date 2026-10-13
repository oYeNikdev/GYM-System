import React, { useRef, useState } from "react";

const navigation = [
  { href: "#approach", label: "Our approach" },
  { href: "#membership", label: "Membership" },
  { href: "#visit", label: "Visit" },
];

const plans = ["Monthly", "Quarterly", "Annual"];

function App() {
  const [menuOpen, setMenuOpen] = useState(false);
  const menuButton = useRef(null);

  function closeMenu() {
    setMenuOpen(false);
  }

  function handleHeaderKeyDown(event) {
    if (event.key === "Escape" && menuOpen) {
      closeMenu();
      menuButton.current?.focus();
    }
  }

  return (
    <>
      <a className="skip-link" href="#main">Skip to content</a>
      <header className="site-header" onKeyDown={handleHeaderKeyDown}>
        <div className="header-inner">
          <a className="wordmark" href="#home" onClick={closeMenu} aria-label="IronCore Fitness, home">
            <span className="wordmark-mark" aria-hidden="true">IC</span>
            <span>IRONCORE<span className="wordmark-light">FITNESS</span></span>
          </a>
          <button
            className="menu-toggle"
            type="button"
            ref={menuButton}
            aria-controls="primary-navigation"
            aria-expanded={menuOpen}
            onClick={() => setMenuOpen((open) => !open)}
          >
            <span>{menuOpen ? "Close" : "Menu"}</span>
            <span className="menu-toggle-lines" aria-hidden="true"><i /><i /></span>
          </button>
          <nav id="primary-navigation" className={menuOpen ? "primary-nav is-open" : "primary-nav"} aria-label="Main navigation">
            {navigation.map((item) => (
              <a href={item.href} key={item.href} onClick={closeMenu}>{item.label}</a>
            ))}
            <a className="nav-cta" href="#visit" onClick={closeMenu}>Details pending <span aria-hidden="true">↗</span></a>
          </nav>
        </div>
      </header>

      <main id="main">
        <section className="hero" id="home" aria-labelledby="hero-title">
          <img
            className="hero-image"
            src="https://images.unsplash.com/photo-1534438327276-14e5300c3a48?auto=format&fit=crop&w=2000&q=80"
            alt=""
            fetchPriority="high"
          />
          <div className="hero-shade" />
          <div className="hero-content">
            <p className="eyebrow"><span className="status-dot" /> Sample gym website <span className="eyebrow-divider">/</span> Details to be confirmed</p>
            <h1 id="hero-title">Make room<br />for <span>stronger.</span></h1>
            <p className="hero-description">A considered preview for a local fitness space. We’ll add real services, plans, and contact details when they’re verified.</p>
            <div className="hero-actions">
              <a className="button button-primary" href="#membership">Explore example plans <span aria-hidden="true">↓</span></a>
              <a className="text-link" href="#approach">About this preview <span aria-hidden="true">↘</span></a>
            </div>
            <p className="hero-caption">IRONCORE FITNESS <span>·</span> FICTIONAL DEMO BRAND</p>
          </div>
          <a className="hero-index" href="#approach" aria-label="Scroll to our approach">01 <span /> 04</a>
        </section>

        <section className="approach section-shell" id="approach" aria-labelledby="approach-title">
          <div className="section-label"><span>01</span> A PLACE TO BEGIN</div>
          <div className="approach-grid">
            <h2 id="approach-title">Built around<br /><span>your next rep.</span></h2>
            <div className="approach-copy">
              <p>This is a design preview, not a verified gym listing. Our story, coaching approach, and available facilities are still waiting for confirmed details.</p>
              <p className="quiet-note">No trainer credentials, service promises, or facility claims are represented here as fact.</p>
              <a className="underlined-link" href="#visit">See what’s confirmed <span aria-hidden="true">↗</span></a>
            </div>
          </div>
          <div className="detail-strip" aria-label="Gym information status">
            <div><span className="strip-number">A</span><span>Gym story<small>To be confirmed</small></span></div>
            <div><span className="strip-number">B</span><span>Facilities<small>To be confirmed</small></span></div>
            <div><span className="strip-number">C</span><span>Trainer profiles<small>To be confirmed</small></span></div>
          </div>
        </section>

        <section className="membership section-shell" id="membership" aria-labelledby="membership-title">
          <div className="membership-heading">
            <div>
              <div className="section-label"><span>02</span> FIND YOUR RHYTHM</div>
              <h2 id="membership-title">Membership, <em>eventually.</em></h2>
            </div>
            <p>These are example plan labels only. No rates, durations, inclusions, or sign-up offers have been confirmed.</p>
          </div>
          <div className="plan-grid">
            {plans.map((plan, index) => (
              <article className="plan-panel" key={plan}>
                <span className="plan-index">0{index + 1} / EXAMPLE</span>
                <h3>{plan}</h3>
                <p className="plan-price">Details to be confirmed</p>
                <div className="plan-rule" />
                <p className="plan-description">Pricing and membership terms will appear here after verification.</p>
                <span className="plan-status">NOT AVAILABLE FOR SIGN-UP</span>
              </article>
            ))}
          </div>
          <p className="membership-footnote">Membership names are illustrative placeholders, not current offers.</p>
        </section>

        <section className="visit section-shell" id="visit" aria-labelledby="visit-title">
          <div className="visit-stamp" aria-hidden="true">IC</div>
          <div className="visit-content">
            <div className="section-label"><span>03</span> BEFORE YOU VISIT</div>
            <h2 id="visit-title">Details first.<br /><span>Then the heavy lifting.</span></h2>
            <p>Address, opening hours, phone, WhatsApp, and map information are not verified yet. Contact actions are intentionally unavailable.</p>
            <div className="visit-details">
              <div><span>LOCATION</span><strong>To be confirmed</strong></div>
              <div><span>OPENING HOURS</span><strong>To be confirmed</strong></div>
              <div><span>CONTACT</span><strong>To be confirmed</strong></div>
            </div>
            <p className="demo-notice">This React preview is informational only. It has no booking form and does not collect or submit personal information.</p>
          </div>
        </section>
      </main>

      <footer className="site-footer">
        <a className="wordmark footer-wordmark" href="#home">
          <span className="wordmark-mark" aria-hidden="true">IC</span>
          <span>IRONCORE<span className="wordmark-light">FITNESS</span></span>
        </a>
        <p>Fictional demo brand <span>·</span> Business details to be confirmed</p>
        <a className="back-top" href="#home">Back to top ↑</a>
      </footer>
    </>
  );
}

export default App;
