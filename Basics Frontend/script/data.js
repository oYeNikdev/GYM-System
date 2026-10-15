// // All mock data lives here. Later, a Spring Boot API will return this kind of data.

// const CONFIG = {
//   phone: "", // Verified international number digits, including country code.
//   whatsapp: "", // Verified WhatsApp number digits, including country code.
//   mapUrl: "" // Verified Google Maps share link.
// };

// const PLANS = [
//   { id: "monthly", name: "Monthly (example only)" },
//   { id: "quarterly", name: "Quarterly (example only)" },
//   { id: "annual", name: "Annual (example only)" }
// ];

// const TRAINERS = [];

// const FACILITIES = [];

// const HOURS = [];
 
// EDIT THESE: leave empty until you have real details. Buttons stay disabled while empty.
const CONFIG = {
  phone: "",        // digits with country code, e.g. "91XXXXXXXXXX"
  whatsapp: "",     // same format
  mapUrl: ""        // a Google Maps share link
};
 
const PLANS = [
  { id: "monthly",   name: "Monthly",   months: 1,  price: 1499,  popular: false,
    benefits: ["Full gym floor access", "Free fitness assessment", "Cancel any month"] },
  { id: "quarterly", name: "Quarterly", months: 3,  price: 3999,  popular: true,
    benefits: ["Everything in Monthly", "2 trainer check-ins a month", "Diet guidance sheet"] },
  { id: "annual",    name: "Annual",    months: 12, price: 11999, popular: false,
    benefits: ["Everything in Quarterly", "Lowest cost per month", "1 free freeze of up to 15 days"] }
];
 
const TRAINERS = [
  { name: "Sample Trainer A", role: "Strength & Conditioning", bio: "Fictional profile. Helps first-time lifters learn safe form." },
  { name: "Sample Trainer B", role: "Fat Loss & Cardio", bio: "Fictional profile. Builds simple, repeatable weekly routines." },
  { name: "Sample Trainer C", role: "Mobility & Recovery", bio: "Fictional profile. Focuses on posture and injury-aware training." }
];
 
const FACILITIES = ["Free weights and racks", "Strength machines", "Cardio zone", "Functional training area", "Clean changing rooms", "Drinking water and lockers"];
 
const HOURS = [
  { days: "Monday to Saturday", time: "5:30 AM to 10:00 PM" },
  { days: "Sunday", time: "7:00 AM to 12:00 PM" }
];
 