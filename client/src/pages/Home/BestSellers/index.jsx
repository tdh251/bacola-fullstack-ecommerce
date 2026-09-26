import { useRef } from "react";
import {
   ArrowLongRightIcon,
   ChevronLeftIcon,
   ChevronRightIcon,
} from "@heroicons/react/24/outline";
import { Link } from "react-router-dom";
import { Swiper, SwiperSlide } from "swiper/react";
import { Navigation } from "swiper/modules";

import "swiper/css";

import Product from "../../../components/Product";
import { PRODUCTS_DEMO } from "../../../data";

function BestSellers() {
   const swiperRef = useRef(null);

   return (
      <section className="w-full mt-[120px]">
         {/* Header section */}
         <div className="flex items-center justify-between mb-4">
            <div>
               <h3 className="text-[#202435] font-dosis uppercase font-semibold">
                  Bán chạy nhất
               </h3>
               <p className="text-[12px] text-[#9b9bb4]">
                  Đừng bỏ lỡ các ưu đãi hiện có cho đến hết tháng 10.
               </p>
            </div>

            <Link
               to="/products"
               className="flex items-center gap-2 border border-[#e2e4ec] rounded-full px-4 py-1.5 text-[12px] text-[#71778e] font-medium hover:border-[#2bbef9] hover:text-[#2bbef9] transition-colors"
            >
               Xem tất cả
               <ArrowLongRightIcon className="w-4 h-4" />
            </Link>
         </div>

         {/* Carousel Container */}
         <div className="relative">
            {/* Nút Navigation trái (Custom) */}
            <button
               type="button"
               onClick={() => swiperRef.current?.slidePrev()}
               className="absolute -left-4 top-1/2 -translate-y-1/2 z-20 w-9 h-9 rounded-full bg-white shadow-md border border-[#f0f0f0] flex items-center justify-center text-[#71778e] hover:bg-[#2bbef9] hover:text-white transition-all cursor-pointer"
            >
               <ChevronLeftIcon className="w-4 h-4 stroke-[2.5]" />
            </button>

            {/* Nút Navigation phải (Custom) */}
            <button
               type="button"
               onClick={() => swiperRef.current?.slideNext()}
               className="absolute -right-4 top-1/2 -translate-y-1/2 z-20 w-9 h-9 rounded-full bg-white shadow-md border border-[#f0f0f0] flex items-center justify-center text-[#71778e] hover:bg-[#2bbef9] hover:text-white transition-all cursor-pointer"
            >
               <ChevronRightIcon className="w-4 h-4 stroke-[2.5]" />
            </button>

            {/* Swiper Slider */}
            <Swiper
               modules={[Navigation]}
               onBeforeInit={(swiper) => {
                  swiperRef.current = swiper;
               }}
               slidesPerView={5}
               spaceBetween={0}
               speed={750}
               loop={true}
               breakpoints={{
                  320: { slidesPerView: 1 },
                  640: { slidesPerView: 2 },
                  768: { slidesPerView: 3 },
                  1024: { slidesPerView: 4 },
                  1280: { slidesPerView: 5 },
               }}
               className="w-ful overflow-hidden border border-[#edeeef] rounded-[10px] bg-white"
            >
               {PRODUCTS_DEMO.map((product) => (
                  <SwiperSlide
                     key={product.id}
                     className="h-auto border-r border-[#edeeef]"
                  >
                     <Product product={product} />
                  </SwiperSlide>
               ))}
            </Swiper>
         </div>
      </section>
   );
}

export default BestSellers;
