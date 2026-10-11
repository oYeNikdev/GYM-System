const $ = (sel) => document.querySelector(sel);
const rupees = (n) => "₹" + n.toLocaleString("en-IN");

// 1. Render mock data into the page
function renderPlans() {
  $("#plan-list").innerHTML = PLANS.map((p) => `
    <article class="card plan ${p.popular ? "popular" : ""}">
      ${p.popular ? '<p class="badge">Popular choice (sample label)</p>' : ""}
      <h3>${p.name}</h3>
      <p class="price">${rupees(p.price)}</p>
      <p class="per">${p.months > 1 ? rupees(Math.round(p.price / p.months)) + " per month" : "billed monthly"}</p>
      <ul class="ticks">${p.benefits.map((b) => `<li>${b}</li>`).join("")}</ul>
      <button class="btn ${p.popular ? "btn-red" : "btn-ghost"}" data-plan="${p.id}">Enquire about ${p.name}</button>
    </article>`).join("");
  $("#plan").innerHTML = '<option value="">Select a plan</option>' +
    PLANS.map((p) => `<option value="${p.id}">${p.name} (${rupees(p.price)})</option>`).join("");
}

function renderAbout() {
  $("#facilities").innerHTML = FACILITIES.map((f) => `<li class="card">${f}</li>`).join("");
  $("#trainers").innerHTML = TRAINERS.map((t) => `
    <article class="card"><h4>${t.name}</h4><p class="role">${t.role}</p><p>${t.bio}</p></article>`).join("");
  $("#hours").innerHTML = HOURS.map((h) => `<li><span>${h.days}</span><span>${h.time}</span></li>`).join("");
}

// 2. Contact buttons only work once CONFIG is filled in
function setupContact() {
  const set = (id, url) => {
    const el = $(id);
    if (url) { el.href = url; el.target = url.startsWith("http") ? "_blank" : ""; el.rel = "noopener"; }
    else { el.setAttribute("aria-disabled", "true"); el.removeAttribute("href"); }
  };
  set("#call-btn", CONFIG.phone && "tel:+" + CONFIG.phone);
  set("#wa-btn", CONFIG.whatsapp && "https://wa.me/" + CONFIG.whatsapp);
  set("#map-btn", CONFIG.mapUrl);
  $("#contact-note").textContent = (CONFIG.phone && CONFIG.whatsapp && CONFIG.mapUrl)
    ? "" : "Contact buttons are disabled until you add details in js/data.js.";
}

// 3. Mobile menu
function setupMenu() {
  const btn = $(".menu-btn"), nav = $("#nav");
  const toggle = (open) => { nav.classList.toggle("open", open); btn.setAttribute("aria-expanded", open); };
  btn.addEventListener("click", () => toggle(btn.getAttribute("aria-expanded") !== "true"));
  nav.addEventListener("click", (e) => { if (e.target.tagName === "A") toggle(false); });
  document.addEventListener("keydown", (e) => { if (e.key === "Escape") { toggle(false); btn.focus(); } });
}

// 4. Form validation. Returns an error message, or "" if valid.
function validate(values) {
  const errors = {};
  if (values.name.trim().length < 2) errors.name = "Enter your full name (at least 2 characters).";
  const digits = values.mobile.replace(/[\s-]/g, "").replace(/^(\+91|91|0)(?=\d{10}$)/, "");
  if (!/^[6-9]\d{9}$/.test(digits)) errors.mobile = "Enter a valid 10-digit Indian mobile number starting with 6, 7, 8 or 9.";
  if (!PLANS.some((p) => p.id === values.plan)) errors.plan = "Choose a plan.";
  if (!values.date) errors.date = "Choose a visit date.";
  else if (values.date < todayISO()) errors.date = "Choose today or a future date.";
  return { errors, mobile: digits };
}

function todayISO() {
  const d = new Date();
  return new Date(d.getTime() - d.getTimezoneOffset() * 60000).toISOString().slice(0, 10);
}

function saveTestEnquiry(entry) {
  // TEMPORARY: replace with fetch("/api/enquiries", { method: "POST", ... }) when the backend exists.
  const list = JSON.parse(localStorage.getItem("ironcore_test_enquiries") || "[]");
  list.push(entry);
  localStorage.setItem("ironcore_test_enquiries", JSON.stringify(list));
}

function setupForm() {
  const form = $("#trial-form"), status = $("#form-status");
  $("#date").min = todayISO();
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const values = Object.fromEntries(new FormData(form));
    const { errors, mobile } = validate(values);
    ["name", "mobile", "plan", "date"].forEach((f) => {
      $("#" + f + "-err").textContent = errors[f] || "";
      $("#" + f).setAttribute("aria-invalid", Boolean(errors[f]));
    });
    status.className = "";
    if (Object.keys(errors).length) {
      status.textContent = "Please fix the highlighted fields and try again.";
      status.classList.add("error");
      form.querySelector("[aria-invalid='true']").focus();
      return;
    }
    try {
      saveTestEnquiry({ name: values.name.trim(), mobile, plan: values.plan, date: values.date, savedAt: new Date().toISOString() });
      status.textContent = "Test enquiry saved in this browser only. Nothing was sent to IronCore.";
      status.classList.add("success");
      form.reset();
    } catch {
      status.textContent = "Could not save in this browser (storage may be blocked). Try again.";
      status.classList.add("error");
    }
    status.focus();
  });
  // "Enquire" buttons preselect the plan and jump to the form
  $("#plan-list").addEventListener("click", (e) => {
    const id = e.target.dataset.plan;
    if (!id) return;
    $("#plan").value = id;
    const behavior = matchMedia("(prefers-reduced-motion: reduce)").matches ? "auto" : "smooth";
    $("#trial").scrollIntoView({ behavior });
    setTimeout(() => $("#name").focus({ preventScroll: true }), 400);
  });
}

renderPlans(); renderAbout(); setupContact(); setupMenu(); setupForm();
$("#year").textContent = new Date().getFullYear();
document.querySelectorAll("img").forEach((img) => img.addEventListener("error", () => img.remove()));