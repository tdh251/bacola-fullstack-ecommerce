/** @type {import('tailwindcss').Config} */
export default {
   content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
   safelist: [
      "swiper-pagination",
      "swiper-pagination-bullets",
      "swiper-pagination-bullet",
      "swiper-pagination-bullet-active",
   ],
   theme: {
      extend: {
         fontFamily: {
            sans: ["Inter", "sans-serif"],
            dosis: ["Dosis", "sans-serif"],
            mulish: ["Mulish", "sans-serif"],
         },
         container: {
            center: true,
            padding: "15px",
         },
      },
   },
   plugins: [],
};
