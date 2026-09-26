import React, { useEffect, useState } from "react";
import {
   StarIcon,
   HeartIcon,
   ArrowLongRightIcon,
   CheckIcon,
   MinusIcon,
   PlusIcon,
   TruckIcon,
   SparklesIcon,
   CurrencyDollarIcon,
} from "@heroicons/react/24/outline";
import { StarIcon as StarSolid } from "@heroicons/react/24/solid";
import ProductImgDemo from "../../assets/images/product-image-demo.jpg";
import { REVIEWS_DEMO } from "../../data";
import StarRating from "../../components/StarRating";
import { Link } from "react-router-dom";
import Product from "../../components/Product";
import { PRODUCTS_DEMO } from "../../data";

const PRODUCT_IMAGES = [ProductImgDemo, ProductImgDemo, ProductImgDemo];

function ProductSumary() {
   const [selectedImage, setSelectedImage] = useState(PRODUCT_IMAGES[0]);
   const [quantity, setQuantity] = useState(1);

   const handleDecrease = () => {
      if (quantity > 1) setQuantity((prev) => prev - 1);
   };

   const handleIncrease = () => {
      setQuantity((prev) => prev + 1);
   };

   return (
      <section className="py-10 font-sans text-[#202435] p-[50px] bg-white rounded-md">
         {/* Header: Title & Meta */}
         <div className="mb-8">
            <h1 className="text-[26px] font-bold text-[#202435] mb-2 leading-tight">
               All Natural Italian-Style Chicken Meatballs
            </h1>
            <div className="flex flex-wrap items-center gap-4 text-[13px] text-[#71778e]">
               <div>
                  Brands:{" "}
                  <span className="font-medium text-[#202435]">Welch's</span>
               </div>
               <span className="text-gray-300">|</span>
               <div className="flex items-center gap-1">
                  <div className="flex text-amber-400">
                     <StarSolid className="w-4 h-4" />
                     <StarSolid className="w-4 h-4" />
                     <StarSolid className="w-4 h-4" />
                     <StarSolid className="w-4 h-4" />
                     <StarIcon className="w-4 h-4 text-gray-300" />
                  </div>
                  <span className="text-[12px] font-semibold text-[#71778e] ml-1">
                     1 REVIEW
                  </span>
               </div>
               <span className="text-gray-300">|</span>
               <div>
                  SKU:{" "}
                  <span className="font-medium text-[#202435]">ZU49VOR</span>
               </div>
            </div>
         </div>

         {/* Layout 3 cột */}
         <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start">
            {/* CỘT 1: Gallery Ảnh (4 cols) */}
            <div className="lg:col-span-4">
               <div className="relative rounded-lg border border-gray-100 mb-4 flex items-center justify-center bg-[#fdfdfd]">
                  {/* Badges */}
                  <div className="absolute top-4 left-4 flex flex-col gap-1.5 z-10">
                     <span className="bg-[#2bbef9] text-white text-[11px] font-bold px-2 py-0.5 rounded uppercase">
                        23%
                     </span>
                     <span className="bg-[#5a627a] text-white text-[10px] font-bold px-2 py-0.5 rounded uppercase tracking-wider">
                        RECOMMENDED
                     </span>
                  </div>

                  {/* Main Image */}
                  <img
                     src={ProductImgDemo}
                     alt="Product Preview"
                     className="w-full object-contain transition-all duration-300 drop-shadow-md"
                  />
               </div>

               {/* Thumbnails */}
               <div className="flex gap-3">
                  {PRODUCT_IMAGES.map((img, idx) => (
                     <button
                        key={idx}
                        type="button"
                        onClick={() => setSelectedImage(img)}
                        className={`w-[70px] h-[70px] rounded-md border p-1 transition-all ${
                           selectedImage === img
                              ? "border-[#233a95] ring-1 ring-[#233a95]"
                              : "border-gray-200 hover:border-gray-400"
                        }`}
                     >
                        <img
                           src={img}
                           alt={`thumb-${idx}`}
                           className="w-full h-full object-contain"
                        />
                     </button>
                  ))}
               </div>
            </div>

            {/* CỘT 2: Thông tin chi tiết & Mua hàng (5 cols) */}
            <div className="lg:col-span-5">
               {/* Giá & Trạng thái tồn kho */}
               <div className="flex items-center gap-3 mb-2">
                  <span className="text-[18px] text-gray-400 line-through font-medium">
                     $9.35
                  </span>
                  <span className="text-[26px] font-bold text-[#d51243]">
                     $7.25
                  </span>
               </div>

               <div className="mb-5">
                  <span className="bg-[#e5f8ed] text-[#15b060] text-[11px] font-bold uppercase px-2.5 py-1 rounded">
                     IN STOCK
                  </span>
               </div>

               {/* Mô tả ngắn */}
               <p className="text-[14px] text-[#71778e] leading-relaxed mb-6">
                  Vivamus adipiscing nisl ut dolor dignissim semper. Nulla
                  luctus malesuada tincidunt. Class aptent taciti sociosqu ad
                  litora torquent
               </p>

               {/* Bộ tăng giảm số lượng & Nút Add to Cart */}
               <div className="flex items-center gap-4 mb-6">
                  <div className="flex items-center border border-gray-200 rounded-full h-[46px] px-2 bg-[#f3f4f7]">
                     <button
                        type="button"
                        onClick={handleDecrease}
                        className="w-8 h-8 flex items-center justify-center rounded-full hover:bg-white text-gray-600 transition-colors"
                     >
                        <MinusIcon className="w-3.5 h-3.5" />
                     </button>
                     <span className="w-10 text-center text-sm font-semibold">
                        {quantity}
                     </span>
                     <button
                        type="button"
                        onClick={handleIncrease}
                        className="w-8 h-8 flex items-center justify-center rounded-full hover:bg-white text-gray-600 transition-colors"
                     >
                        <PlusIcon className="w-3.5 h-3.5" />
                     </button>
                  </div>

                  <button
                     type="button"
                     className="flex-1 h-[46px] rounded-full bg-[#233a95] hover:bg-[#1c2e77] text-white font-semibold text-[14px] transition-colors shadow-sm"
                  >
                     Thêm vào giỏ hàng
                  </button>
                  <button className="flex-center w-[46px] h-[46px] rounded-full border border-[e4e5ee]ư">
                     <HeartIcon className="w-[20px]" />
                  </button>
               </div>

               {/* Checklist thuộc tính */}
               <ul className="space-y-2 text-[13px] text-[#71778e] mb-6">
                  <li className="flex items-center gap-2">
                     <CheckIcon className="w-4 h-4 text-[#15b060] stroke-[3]" />
                     <span>Type: Organic</span>
                  </li>
                  <li className="flex items-center gap-2">
                     <CheckIcon className="w-4 h-4 text-[#15b060] stroke-[3]" />
                     <span>MFG: Jun 4.2021</span>
                  </li>
                  <li className="flex items-center gap-2">
                     <CheckIcon className="w-4 h-4 text-[#15b060] stroke-[3]" />
                     <span>LIFE: 30 days</span>
                  </li>
               </ul>

               <hr className="border-gray-200 mb-6" />

               {/* Metadata (Category, Tags, Brand) */}
               <div className="space-y-1.5 text-[13px] text-[#71778e] mb-6">
                  <div>
                     Category:{" "}
                     <span className="text-[#202435] font-medium hover:underline cursor-pointer">
                        Meats & Seafood
                     </span>
                  </div>
                  <div>
                     Tags:{" "}
                     <span className="text-[#202435] font-medium hover:underline cursor-pointer">
                        chicken, natural, organic
                     </span>
                  </div>
                  <div>
                     Brand:{" "}
                     <span className="text-[#202435] font-medium hover:underline cursor-pointer">
                        Welch's
                     </span>
                  </div>
               </div>

               {/* Social Share Buttons */}
               <div className="flex items-center gap-2">
                  <a
                     href="#"
                     className="w-8 h-8 rounded-full bg-[#3b5998] text-white flex items-center justify-center text-xs font-bold hover:opacity-90"
                  >
                     f
                  </a>
                  <a
                     href="#"
                     className="w-8 h-8 rounded-full bg-[#1da1f2] text-white flex items-center justify-center text-xs font-bold hover:opacity-90"
                  >
                     t
                  </a>
                  <a
                     href="#"
                     className="w-8 h-8 rounded-full bg-[#bd081c] text-white flex items-center justify-center text-xs font-bold hover:opacity-90"
                  >
                     p
                  </a>
                  <a
                     href="#"
                     className="w-8 h-8 rounded-full bg-[#0077b5] text-white flex items-center justify-center text-xs font-bold hover:opacity-90"
                  >
                     in
                  </a>
                  <a
                     href="#"
                     className="w-8 h-8 rounded-full bg-[#ff4500] text-white flex items-center justify-center text-xs font-bold hover:opacity-90"
                  >
                     r
                  </a>
                  <a
                     href="#"
                     className="w-8 h-8 rounded-full bg-[#25d366] text-white flex items-center justify-center text-xs font-bold hover:opacity-90"
                  >
                     w
                  </a>
               </div>
            </div>

            {/* CỘT 3: Sidebar Thông tin dịch vụ & Cam kết (3 cols) */}
            <div className="lg:col-span-3 space-y-4">
               {/* Banner Covid / Notice */}
               <div className="bg-[#fdedee] text-[#d51243] text-[12px] font-medium p-3.5 rounded-[6px] text-center">
                  Covid-19 Info: We keep delivering.
               </div>

               {/* Box cam kết dịch vụ */}
               <div className="bg-[#f7f8fd] border border-[#e5e8f4] rounded-[8px] p-5 space-y-5">
                  <div className="flex items-start gap-4">
                     <TruckIcon className="w-6 h-6 text-[#202435] shrink-0 mt-0.5" />
                     <p className="text-[13px] text-[#202435] leading-snug">
                        Free Shipping apply to all orders over $100
                     </p>
                  </div>

                  <div className="flex items-start gap-4">
                     <SparklesIcon className="w-6 h-6 text-[#202435] shrink-0 mt-0.5" />
                     <p className="text-[13px] text-[#202435] leading-snug">
                        Guaranteed 100% Organic from natural farms
                     </p>
                  </div>

                  <div className="flex items-start gap-4">
                     <CurrencyDollarIcon className="w-6 h-6 text-[#202435] shrink-0 mt-0.5" />
                     <p className="text-[13px] text-[#202435] leading-snug">
                        1 Day Returns if you change your mind
                     </p>
                  </div>
               </div>
            </div>
         </div>
      </section>
   );
}

function ProductTabs() {
   const [tabActive, setTabActive] = useState("desc");
   const [ratingInput, setRatingInput] = useState(5);
   const [hoverRating, setHoverRating] = useState(0);
   return (
      <section className="py-10 font-sans text-[#202435] p-[50px] bg-white rounded-md mt-20">
         <div className="flex gap-[30px] border-b border-[#e4e5ee]">
            <button
               className="mb-3 py-2 text-[20px] font-bold cursor-pointer"
               onClick={() => setTabActive("desc")}
            >
               Mô tả
            </button>
            <button
               className="mb-3 py-2 text-[20px] font-bold cursor-pointer"
               onClick={() => setTabActive("review")}
            >
               Đánh giá (100)
            </button>
         </div>
         <div className="mt-[30px]">
            {tabActive === "desc" && (
               <p>
                  Quisque varius diam vel metus mattis, id aliquam diam rhoncus.
                  Proin vitae magna in dui finibus malesuada et at nulla. Morbi
                  elit ex, viverra vitae ante vel, blandit feugiat ligula. Fusce
                  fermentum iaculis nibh, at sodales leo maximus a. Nullam
                  ultricies sodales nunc, in pellentesque lorem mattis quis.
                  Cras imperdiet est in nunc tristique lacinia. Nullam aliquam
                  mauris eu accumsan tincidunt. Suspendisse velit ex, aliquet
                  vel ornare vel, dignissim a tortor. Morbi ut sapien vitae odio
                  accumsan gravida. Morbi vitae erat auctor, eleifend nunc a,
                  lobortis neque. Praesent aliquam dignissim viverra. Maecenas
                  lacus odio, feugiat eu nunc sit amet, maximus sagittis dolor.
                  Vivamus nisi sapien, elementum sit amet eros sit amet,
                  ultricies cursus ipsum. Sed consequat luctus ligula. Curabitur
                  laoreet rhoncus blandit. Aenean vel diam ut arcu pharetra
                  dignissim ut sed leo. Vivamus faucibus, ipsum in vestibulum
                  vulputate, lorem orci convallis quam, sit amet consequat nulla
                  felis pharetra lacus. Duis semper erat mauris, sed egestas
                  purus commodo vel.
               </p>
            )}
            {tabActive === "review" && (
               <div className="bg-white rounded-[8px] font-sans text-[#202435]">
                  <div className="divide-y divide-gray-100 mb-12">
                     {REVIEWS_DEMO.map((rev) => (
                        <div key={rev.id} className="py-6 first:pt-6 last:pb-0">
                           <div className="flex items-start gap-4">
                              <img
                                 src={rev.avatar}
                                 alt={rev.author}
                                 className="w-12 h-12 rounded-full object-cover shrink-0 border border-gray-200"
                              />
                              <div className="flex-1 min-w-0">
                                 <div className="flex flex-wrap items-center justify-between gap-2 mb-1.5">
                                    <h4 className="text-sm font-bold text-[#202435]">
                                       {rev.author}
                                    </h4>
                                    <span className="text-xs text-gray-400">
                                       {rev.date}
                                    </span>
                                 </div>

                                 <div className="mb-2.5">
                                    <StarRating rating={rev.rating} />
                                 </div>

                                 <p className="text-[13.5px] leading-relaxed text-[#51586e]">
                                    {rev.comment}
                                 </p>
                              </div>
                           </div>

                           {/* Sub-reviews / Phản hồi */}
                           {rev.replies && rev.replies.length > 0 && (
                              <div className="mt-4 ml-8 pl-5 border-l-2 border-[#2bbef9]/30 space-y-4">
                                 {rev.replies.map((sub) => (
                                    <div
                                       key={sub.id}
                                       className="flex items-start gap-3 bg-[#f8fafc] p-4 rounded-[6px]"
                                    >
                                       <img
                                          src={sub.avatar}
                                          alt={sub.author}
                                          className="w-9 h-9 rounded-full object-cover shrink-0"
                                       />
                                       <div className="flex-1 min-w-0">
                                          <div className="flex items-center justify-between gap-2 mb-1">
                                             <div className="flex items-center gap-2">
                                                <span className="text-xs font-bold text-[#202435]">
                                                   {sub.author}
                                                </span>
                                                {sub.isStoreStaff && (
                                                   <span className="bg-[#2bbef9] text-white text-[9px] font-semibold px-2 py-0.5 rounded-full uppercase">
                                                      Staff
                                                   </span>
                                                )}
                                             </div>
                                             <span className="text-[11px] text-gray-400">
                                                {sub.date}
                                             </span>
                                          </div>
                                          <p className="text-xs text-[#51586e] leading-relaxed">
                                             {sub.comment}
                                          </p>
                                       </div>
                                    </div>
                                 ))}
                              </div>
                           )}
                        </div>
                     ))}
                  </div>

                  <div className="bg-[#f8fafc] rounded-xl p-6 border border-gray-100">
                     <h3 className="text-base font-bold text-[#202435] mb-2 uppercase">
                        Thêm đánh giá của bạn
                     </h3>
                     <p className="text-xs text-[#71778e] mb-5">
                        Email và tên của bạn sẽ được bảo mật. Các trường bắt
                        buộc được đánh dấu *
                     </p>

                     <form className="space-y-4">
                        {/* Chọn số sao rating */}
                        <div>
                           <label className="block text-xs font-semibold text-[#202435] uppercase mb-1.5">
                              Đánh giá của bạn *
                           </label>
                           <div className="flex items-center gap-1 cursor-pointer">
                              {[1, 2, 3, 4, 5].map((star) => {
                                 const active =
                                    (hoverRating || ratingInput) >= star;
                                 return (
                                    <button
                                       key={star}
                                       type="button"
                                       onClick={() => setRatingInput(star)}
                                       onMouseEnter={() => setHoverRating(star)}
                                       onMouseLeave={() => setHoverRating(0)}
                                       className="p-0.5 focus:outline-none"
                                    >
                                       {active ? (
                                          <StarSolid className="w-5 h-5 text-amber-400 transition-transform active:scale-125" />
                                       ) : (
                                          <StarIcon className="w-5 h-5 text-gray-300" />
                                       )}
                                    </button>
                                 );
                              })}
                              <span className="text-xs font-medium text-gray-500 ml-2">
                                 ({hoverRating || ratingInput} / 5 sao)
                              </span>
                           </div>
                        </div>

                        <div>
                           <label className="block text-xs font-semibold text-[#202435] uppercase mb-1.5">
                              Nội dung đánh giá *
                           </label>
                           <textarea
                              required
                              rows={4}
                              placeholder="Chia sẻ trải nghiệm của bạn về sản phẩm này..."
                              className="w-full p-4 text-[13.5px] rounded-[6px] border border-gray-200 bg-white focus:outline-none focus:border-[#2bbef9] focus:ring-1 focus:ring-[#2bbef9] resize-y"
                           />
                        </div>

                        {/* Nút Submit */}
                        <div>
                           <button
                              type="submit"
                              className="h-11 px-8 rounded-full bg-[#233a95] hover:bg-[#1a2d75] text-white text-xs font-bold uppercase tracking-wider transition-colors shadow-sm cursor-pointer"
                           >
                              Gửi đánh giá
                           </button>
                        </div>
                     </form>
                  </div>
               </div>
            )}
         </div>
      </section>
   );
}

function ReletedProducts() {
   return (
      <section className="w-full mt-[100px]">
         <h3 className="text-[#202435] font-dosis uppercase font-semibold mb-4">
            Sản phẩm liên quan
         </h3>

         {/* Grid Container 2 hàng x 5 cột */}
         <div className="border border-[#edeeef] rounded-[10px] overflow-hidden bg-[#edeeef]">
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 xl:grid-cols-5 gap-[1px]">
               {PRODUCTS_DEMO.slice(0, 5).map((product) => (
                  <div key={product.id} className="bg-white h-full">
                     <Product product={product} />
                  </div>
               ))}
            </div>
         </div>
      </section>
   );
}

function RecenttlyViewedProducts() {
   return (
      <section className="w-full mt-[100px]">
         <h3 className="text-[#202435] font-dosis uppercase font-semibold mb-4">
            Đã xem gần đây
         </h3>

         <div className="border border-[#edeeef] rounded-[10px] overflow-hidden bg-[#edeeef]">
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 xl:grid-cols-5 gap-[1px]">
               {PRODUCTS_DEMO.slice(0, 5).map((product) => (
                  <div key={product.id} className="bg-white h-full">
                     <Product product={product} />
                  </div>
               ))}
            </div>
         </div>
      </section>
   );
}

function ProductDetails() {
   useEffect(() => {
      document.body.classList.add("bg-[#f7f8fd]");

      return () => {
         document.body.classList.remove("bg-[#f7f8fd]");
      };
   }, []);
   return (
      <>
         <ProductSumary />
         <ProductTabs />
         <ReletedProducts />
         <RecenttlyViewedProducts />
      </>
   );
}

export default ProductDetails;
