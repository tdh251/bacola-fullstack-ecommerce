import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";

import SliderImg1 from "../../../assets/images/slider-image-1.jpg";
import SliderImg2 from "../../../assets/images/slider-image-2.jpg";
import SliderImg3 from "../../../assets/images/slider-image-3.jpg";
import Button from "../../../components/common/Button";
import { ArrowLongRightIcon } from "@heroicons/react/24/outline";

function Slider() {
   return (
      <section className="slider">
         <Swiper
            modules={[Pagination]}
            pagination={{
               clickable: true,
            }}
            spaceBetween={0}
            slidesPerView={1}
            speed={750}
            loop={true}
            autoplay={{
               delay: 3000,
               disableOnInteraction: false,
            }}
            className="rounded-[10px]"
         >
            <SwiperSlide className="flex items-center">
               <div
                  className="bg-center bg-cover py-[120px] px-[60px]"
                  style={{ backgroundImage: `url(${SliderImg1})` }}
               >
                  <div className="max-w-[450px] w-full">
                     <div className="flex items-center gap-[10px] font-dosis mb-[15px]">
                        <span className="block text-[12px] font-medium uppercase">
                           Ưu đãi đọc quyền
                        </span>
                        <span className="block text-[14px] font-semibold uppercase text-[#038e42] tracking-[1px] bg-gradient-to-r from-[#00b853]/20 to-[rgba(32,55,88,0)] px-[12px] py-[5px] rounded-full">
                           Giảm -20%
                        </span>
                     </div>
                     <h2 className="mb-[8px]">
                        Các sản phẩm tươi sạch, tự nhiên
                     </h2>
                     <p className="mb-[15px]">
                        Chỉ áp dụng hết tuần này, đừng bỏ lỡ...
                     </p>
                     <div className="flex items-end gap-[8px] mb-[20px]">
                        <span className="inline-block leading-[25px]">từ</span>
                        <span className="inline-block text-[#d51243] text-[36px] font-semibold leading-none">
                           500.000đ
                        </span>
                     </div>
                     <Button className="flex-center bg-[#2bbef9] text-white gap-[10px] rounded-[30px] px-[20px] text-[14px] h-[40px]">
                        Mua sắm ngay
                        <ArrowLongRightIcon className="w-[18px]" />
                     </Button>
                  </div>
               </div>
            </SwiperSlide>
            <SwiperSlide className="relative">
               <div
                  className="bg-center bg-cover py-[120px] px-[60px]"
                  style={{ backgroundImage: `url(${SliderImg2})` }}
               >
                  <div className="max-w-[450px] w-full">
                     <div className="flex items-center gap-[10px] font-dosis mb-[15px]">
                        <span className="block text-[12px] font-medium uppercase">
                           Ưu đãi đọc quyền
                        </span>
                        <span className="block text-[14px] font-semibold uppercase text-[#038e42] tracking-[1px] bg-gradient-to-r from-[#00b853]/20 to-[rgba(32,55,88,0)] px-[12px] py-[5px] rounded-full">
                           Giảm -20%
                        </span>
                     </div>
                     <h2 className="mb-[8px]">
                        Các sản phẩm tươi sạch, tự nhiên
                     </h2>
                     <p className="mb-[15px]">
                        Chỉ áp dụng hết tuần này, đừng bỏ lỡ...
                     </p>
                     <div className="flex items-end gap-[8px] mb-[20px]">
                        <span className="inline-block leading-[25px]">từ</span>
                        <span className="inline-block text-[#d51243] text-[36px] font-semibold leading-none">
                           500.000đ
                        </span>
                     </div>
                     <Button className="flex-center bg-[#2bbef9] text-white gap-[10px] rounded-[30px] px-[20px] text-[14px] h-[40px]">
                        Mua sắm ngay
                        <ArrowLongRightIcon className="w-[18px]" />
                     </Button>
                  </div>
               </div>
            </SwiperSlide>
            <SwiperSlide className="relative">
               <div
                  className="bg-center bg-cover py-[120px] px-[60px]"
                  style={{ backgroundImage: `url(${SliderImg3})` }}
               >
                  <div className="max-w-[450px] w-full">
                     <div className="flex items-center gap-[10px] font-dosis mb-[15px]">
                        <span className="block text-[12px] font-medium uppercase">
                           Ưu đãi đọc quyền
                        </span>
                        <span className="block text-[14px] font-semibold uppercase text-[#038e42] tracking-[1px] bg-gradient-to-r from-[#00b853]/20 to-[rgba(32,55,88,0)] px-[12px] py-[5px] rounded-full">
                           Giảm -20%
                        </span>
                     </div>
                     <h2 className="mb-[8px]">
                        Các sản phẩm tươi sạch, tự nhiên
                     </h2>
                     <p className="mb-[15px]">
                        Chỉ áp dụng hết tuần này, đừng bỏ lỡ...
                     </p>
                     <div className="flex items-end gap-[8px] mb-[20px]">
                        <span className="inline-block leading-[25px]">từ</span>
                        <span className="inline-block text-[#d51243] text-[36px] font-semibold leading-none">
                           500.000đ
                        </span>
                     </div>
                     <Button className="flex-center bg-[#2bbef9] text-white gap-[10px] rounded-[30px] px-[20px] text-[14px] h-[40px]">
                        Mua sắm ngay
                        <ArrowLongRightIcon className="w-[18px]" />
                     </Button>
                  </div>
               </div>
            </SwiperSlide>
         </Swiper>
      </section>
   );
}

export default Slider;
