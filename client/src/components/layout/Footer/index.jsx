import { FOOTER_FEATURES, CATEGORIES_DATA } from "./data.jsx";
import { EnvelopeIcon, PhoneIcon } from "@heroicons/react/24/outline";
import CouponImg from "../../../assets/images/coupon.png";
import { Link } from "react-router-dom";
import DownloadAppStoreImg from "../../../assets/images/download-app-store.png";
import DownloadGooglePlayImg from "../../../assets/images/download-app-google-play.png";
import { CustomIcons } from "../../common/CustomIcons";

function Newsletter() {
   return (
      <section className="newsletter bg-[#233a95]">
         <div className="container">
            <div className="flex gap-[30px] justify-between items-end">
               <div className="text-white max-w-[500px] py-[65px]">
                  <div className="mb-[5px] text-[#e4e5ee]">
                     Giảm 100.000đ cho đơn hàng đầu tiên
                  </div>
                  <h3 className="font-semibold mb-[10px]">
                     Nhận bản tin và ưu đãi mới nhất...
                  </h3>
                  <p className="text-[#e4e5ee] text-[12px] opacity-70 mb-[50px]">
                     Tham gia đăng ký email của chúng tôi ngay bây giờ để nhận
                     thông tin cập nhật về chương trình khuyến mãi và phiếu giảm
                     giá
                  </p>
                  <form className="relative">
                     <span className="icon flex-center w-[50px] h-[62px] absolute left-0 top-0 text-[#c2c2d3]">
                        <EnvelopeIcon className="w-[22px]" />
                     </span>
                     <input
                        name="newsletter"
                        type="email"
                        placeholder="Nhập email của bạn..."
                        className="w-full leading-[62px] bg-white rounded-md ps-[50px] pe-[110px] text-[#202433]"
                     />
                     <button
                        type="button"
                        className="text-white bg-[#233a95] text-[14px] font-medium px-6 rounded-md h-[54px] absolute right-[4px] top-[4px]"
                     >
                        Đăng ký
                     </button>
                  </form>
               </div>
               <div>
                  <img src={CouponImg} alt="Coupon" />
               </div>
            </div>
         </div>
      </section>
   );
}

function Features() {
   return (
      <section className="bg-[#f7f8fd]">
         <div className="container">
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 lg:gap-0 py-10 border-b border-[#edeeef]">
               {FOOTER_FEATURES.map((item, index) => (
                  <div
                     key={item.id}
                     className={`flex items-center justify-center gap-[15px] px-[15px] ${
                        index !== FOOTER_FEATURES.length - 1
                           ? "lg:border-r lg:border-[#e2e4ec]"
                           : ""
                     }`}
                  >
                     <div className="flex-shrink-0 flex-center">
                        {item.icon}
                     </div>
                     <span className="text-[13px] font-medium text-[#202435] leading-none">
                        {item.title}
                     </span>
                  </div>
               ))}
            </div>
         </div>
      </section>
   );
}

function Links() {
   return (
      <section className="bg-[#f7f8fd]">
         <div className="container">
            <div className="flex justify-between gap-x-[30px] gap-y-[60px] py-12">
               {CATEGORIES_DATA.map((category, index) => (
                  <div key={index} className="flex flex-col">
                     <h6 className="text-[16px] text-gray-800 tracking-wider uppercase mb-5">
                        {category.title}
                     </h6>

                     {/* Danh sách mục con */}
                     <ul className="space-y-3">
                        {category.items.map((item, itemIdx) => (
                           <li key={itemIdx}>
                              <a
                                 href="#"
                                 className="text-sm text-gray-500 hover:text-blue-600 transition-colors duration-150 inline-block"
                              >
                                 {item}
                              </a>
                           </li>
                        ))}
                     </ul>
                  </div>
               ))}
            </div>
         </div>
      </section>
   );
}

function Contact() {
   return (
      <section className="">
         <div className="container">
            <div className="flex items-center justify-between gap-[30px] py-[46px] border-b border-[#edeeef]">
               <div className="flex items-center gap-[20px]">
                  <span className="icon flex-center w-[42px] h-[42px] text-[#202435] border border-[#e4e5ee] rounded-full">
                     <PhoneIcon className="w-[20px] opacity-75" />
                  </span>
                  <div className="">
                     <Link
                        to={"tel:0123456789"}
                        className="mb-[5px] text-[20px] font-semibold"
                     >
                        0123 456 789
                     </Link>
                     <p className="text-[#202435] text-[12px] opacity-50">
                        Từ 8:00 - 22:00
                     </p>
                  </div>
               </div>
               <div className="flex gap-[15px]">
                  <div className="flex gap-[15px] items-center">
                     <div className="text-[#202435]">
                        <h6 className="text-[14px] font-semibold">
                           Tải ứng dụng cho Mobile:{" "}
                        </h6>
                        <p className="text-[12px] opacity-50">
                           Nhận ngay mã giảm giá 15%
                        </p>
                     </div>
                     <div className="grid grid-cols-2 gap-[5px]">
                        <Link to={"/"} className="max-w-[116px]">
                           <img
                              src={DownloadGooglePlayImg}
                              alt="Get app on Google Play"
                           />
                        </Link>
                        <Link to={"/"} className="max-w-[116px]">
                           <img
                              src={DownloadAppStoreImg}
                              alt="Get app on App Store"
                           />
                        </Link>
                     </div>
                  </div>
                  <div className="flex gap-[5px] items-center">
                     <Link
                        to={"/"}
                        className="w-[36px] h-[36px] flex-center text-[#233a95] border border-[#e4e5ee] rounded-full"
                     >
                        <CustomIcons.Facebook className="w-[18px]" />
                     </Link>
                     <Link
                        to={"/"}
                        className="w-[36px] h-[36px] flex-center text-[#233a95] border border-[#e4e5ee] rounded-full"
                     >
                        <CustomIcons.X className="w-[14px]" />
                     </Link>
                     <Link
                        to={"/"}
                        className="w-[36px] h-[36px] flex-center text-[#233a95] border border-[#e4e5ee] rounded-full"
                     >
                        <CustomIcons.Instagram className="w-[14px]" />
                     </Link>
                  </div>
               </div>
            </div>
         </div>
      </section>
   );
}

function Bottom() {
   return (
      <section className="py-[30px]">
         <div className="container">
            <div className="flex justify-between gap-[30px] text-[12px] text-[#9b9bb4]">
               <p>Copyright 2026 © Duc Hai Tran.</p>
               <div className="flex gap-[15px]">
                  <Link to={"/"}>Chính sách</Link>
                  <Link to={"/"}>Điều khoản và điều kiện</Link>
                  <Link to={"/"}>Đổi trả và hoàn hàng</Link>
                  <Link to={"/"}>Cookie</Link>
               </div>
            </div>
         </div>
      </section>
   );
}

function Footer() {
   return (
      <footer>
         <Newsletter />
         <Features />
         <Links />
         <Contact />
         <Bottom />
      </footer>
   );
}

export default Footer;
