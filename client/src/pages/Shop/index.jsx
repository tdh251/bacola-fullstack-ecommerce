import React, { useState } from "react";

import Product from "../../components/Product";
import { PRODUCTS_DEMO, CATEGORIES, BRANDS } from "../../data";
import { PlusIcon } from "@heroicons/react/24/outline";

function FilterSidebar() {
   const [maxPrice, setMaxPrice] = useState(70);

   return (
      <>
         {/* 1. PRODUCT CATEGORIES */}
         <div className="mb-10">
            <h6 className="font-semibold tracking-[-0.05px] uppercase mb-3 text-[#202435]">
               Danh mục sản phẩm
            </h6>
            <ul className="space-y-3.5">
               {CATEGORIES.map((item, index) => (
                  <li
                     key={index}
                     className="flex items-center justify-between text-[13.5px]"
                  >
                     <label className="flex items-center gap-2.5 cursor-pointer text-[#71778e] hover:text-[#202435] transition-colors">
                        <input
                           type="checkbox"
                           className="w-[15px] h-[15px] rounded-[3px] border-[#d8dbe4] text-[#2bbef9] focus:ring-0 focus:ring-offset-0 cursor-pointer"
                        />
                        <span>{item.name}</span>
                     </label>
                     {item.hasSub && (
                        <button
                           type="button"
                           className="text-gray-400 hover:text-gray-600 transition-colors p-0.5"
                        >
                           <PlusIcon className="w-3.5 h-3.5 stroke-[2.5]" />
                        </button>
                     )}
                  </li>
               ))}
            </ul>
         </div>

         {/* 2. FILTER BY PRICE */}
         <div className="mb-10">
            <h6 className="font-semibold tracking-[-0.05px] uppercase mb-3 text-[#202435]">
               Lọc theo giá
            </h6>

            {/* Thanh trượt giá */}
            <div className="relative flex items-center mb-2">
               <input
                  type="range"
                  min="0"
                  max="100"
                  value={maxPrice}
                  onChange={(e) => setMaxPrice(e.target.value)}
                  className="w-full h-1.5 bg-[#1b223c] rounded-lg appearance-none cursor-pointer accent-[#1b223c]"
               />
            </div>

            <div className="flex items-center justify-between text-[14px]">
               <span className="text-[#71778e]">
                  Price:{" "}
                  <span className="font-bold text-[#202435] font-dosis">
                     $0 — ${maxPrice}
                  </span>
               </span>
               <button
                  type="button"
                  className="text-[12px] font-bold tracking-wider uppercase text-[#202435] hover:text-[#2bbef9] transition-colors"
               >
                  Lọc
               </button>
            </div>
         </div>

         {/* 3. PRODUCT STATUS */}
         <div className="mb-9">
            <h6 className="font-semibold tracking-[-0.05px] uppercase mb-3 text-[#202435]">
               Tình trạng
            </h6>
            <ul className="space-y-3.5">
               {["In Stock", "On Sale"].map((status, index) => (
                  <li key={index} className="flex items-center text-[13.5px]">
                     <label className="flex items-center gap-2.5 cursor-pointer text-[#71778e] hover:text-[#202435] transition-colors">
                        <input
                           type="checkbox"
                           className="w-[15px] h-[15px] rounded-[3px] border-[#d8dbe4] text-[#2bbef9] focus:ring-0 focus:ring-offset-0 cursor-pointer"
                        />
                        <span>{status}</span>
                     </label>
                  </li>
               ))}
            </ul>
         </div>

         {/* 4. BRANDS */}
         <div className="mb-9">
            <h6 className="font-semibold tracking-[-0.05px] uppercase mb-3 text-[#202435]">
               Thương hiệu
            </h6>
            <ul className="space-y-3.5">
               {BRANDS.map((brand, index) => (
                  <li
                     key={index}
                     className="flex items-center justify-between text-[13.5px]"
                  >
                     <label className="flex items-center gap-2.5 cursor-pointer text-[#71778e] hover:text-[#202435] transition-colors">
                        <input
                           type="checkbox"
                           className="w-[15px] h-[15px] rounded-[3px] border-[#d8dbe4] text-[#2bbef9] focus:ring-0 focus:ring-offset-0 cursor-pointer"
                        />
                        <span>{brand.name}</span>
                     </label>
                     <span className="text-[13px] text-[#71778e]">
                        ({brand.count})
                     </span>
                  </li>
               ))}
            </ul>
         </div>
      </>
   );
}

function ShopToolbar({ totalProducts = 24 }) {
   return (
      <div className="flex flex-wrap items-center justify-between gap-4 bg-[#f3f4f7] p-4 rounded-[7px] mb-6">
         <p className="text-xs text-[#71778e]">
            Hiển thị <span className="font-semibold text-[#202435]">1–12</span>{" "}
            trong tổng số {totalProducts} kết quả
         </p>

         <div className="flex items-center gap-3">
            <label htmlFor="sort" className="text-xs text-[#71778e]">
               Sắp xếp:
            </label>
            <select
               id="sort"
               className="text-xs border border-gray-300 rounded-[5px] bg-white px-3 py-1.5 focus:outline-none focus:border-[#2bbef9]"
            >
               <option value="default">Mặc định</option>
               <option value="price-asc">Giá: Thấp đến Cao</option>
               <option value="price-desc">Giá: Cao đến Thấp</option>
               <option value="newest">Mới nhất</option>
            </select>
         </div>
      </div>
   );
}

function Products() {
   return (
      <div>
         <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
            {PRODUCTS_DEMO.map((product) => (
               <Product key={product.id} product={product} />
            ))}
         </div>
      </div>
   );
}

function Shop() {
   return (
      <div className="flex justify-between gap-x-[30px] gap-y-[60px] items-start">
         <aside className="w-full max-w-[320px] sticky top-[15px]">
            <FilterSidebar />
         </aside>
         <div className="w-full max-w-[1120px]">
            <ShopToolbar />
            <Products />
         </div>
      </div>
   );
}

export default Shop;
