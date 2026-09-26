import { Link } from "react-router-dom";
import Logo from "../../../assets/images/logo.png";
import {
   Bars3Icon,
   ChevronDownIcon,
   ShoppingBagIcon,
   UserIcon,
   MagnifyingGlassIcon,
   XMarkIcon,
} from "@heroicons/react/24/outline";
import { useState } from "react";

import { LOCATIONS } from "./data";

function SearchBar() {
   return (
      <form className="flex-grow relative">
         <input
            type="text"
            className="ps-[30px] pe-[60px] h-[60px] text-[15px] bg-[#f3f4f7] text-[#202435] w-[100%] rounded-[7px]"
            placeholder="Tìm kiếm sản phẩm..."
            name="q"
         />
         <button
            type="button"
            className="flex-center absolute top-0 right-0 bg-transparent text-[26px] w-[60px] h-[60px]"
         >
            <MagnifyingGlassIcon className="w-[18px] h-auto" />
         </button>
      </form>
   );
}

function CategoryDropdown() {
   return (
      <div className="flex items-center relative w-full px-[20px] gap-[15px] max-w-[215px] rounded-[50px] bg-[#2bbef9] text-white cursor-pointer">
         <Bars3Icon className="w-[16px]" />
         <span className="inline-block uppercase text-[15px] leading-[50px] font-semibold font-dosis">
            All CATEGORIES
         </span>
         <ChevronDownIcon className="w-[16px] ms-auto" />
         <span className="absolute bottom-0 left-1/2 -translate-x-1/2 translate-y-1/2 text-[10px] leading-[18px] text-[#71778e] bg-[#edeef5] border border-white rounded-[18px] px-[10px] whitespace-nowrap">
            Tổng 26 Danh Mục
         </span>
      </div>
   );
}

function Navigation() {
   return (
      <nav className="pt-[28px] pb-[20px]">
         <ul className="flex text-[18px]">
            <li>
               <a
                  href="/"
                  className="px-[18px] leading-[40px] font-dosis font-semibold uppercase cursor-pointer text-[#3e445a]"
               >
                  Trang chủ
               </a>
            </li>
            <li>
               <a
                  href="/"
                  className="px-[18px] leading-[40px] font-dosis font-semibold uppercase cursor-pointer text-[#3e445a]"
               >
                  Về chúng tôi
               </a>
            </li>
            <li>
               <Link
                  to={"/shop"}
                  className="px-[18px] leading-[40px] font-dosis font-semibold uppercase cursor-pointer text-[#3e445a]"
               >
                  Sản phẩm
               </Link>
            </li>
            <li>
               <a
                  href="/"
                  className="px-[18px] leading-[40px] font-dosis font-semibold uppercase cursor-pointer text-[#3e445a]"
               >
                  Tin tức
               </a>
            </li>
            <li>
               <a
                  href="/"
                  className="px-[18px] leading-[40px] font-dosis font-semibold uppercase cursor-pointer text-[#3e445a]"
               >
                  Liên hệ
               </a>
            </li>
         </ul>
      </nav>
   );
}

function MiniCart() {
   return (
      <div className="flex items-center gap-[15px]">
         <span className="font-dosis font-semibold">$0.00</span>
         <button className="flex-center relative w-[42px] h-[42px] rounded-full text-[#ea2b0f] border border-[#fff1ee] bg-[#fff1ee]">
            <span className="flex-center absolute top-0 right-[-4px] text-[10px] bg-[#ea2b0f] text-white min-w-[18px] h-[18px] rounded-full">
               0
            </span>
            <ShoppingBagIcon className="w-[18px] h-auto" />
         </button>
      </div>
   );
}

function PersonalAccount() {
   return (
      <div className="relative">
         <button className="flex-center w-[42px] h-[42px] bg-white border border-[#e2e4ec] rounded-full">
            <UserIcon className="w-[18px]" />
         </button>
      </div>
   );
}

function ProvinceDropdown() {
   const [isOpen, setIsOpen] = useState(false);
   const [selectedLocation, setSelectedLocation] = useState("");

   const handleOpen = () => setIsOpen(true);
   const handleClose = () => setIsOpen(false);

   const handleSelectLocation = (location) => {
      setSelectedLocation(location);
      setIsOpen(false);
   };

   const [searchTerm, setSearchTerm] = useState("");

   const filteredLocations = LOCATIONS.filter((item) =>
      item.name.toLowerCase().includes(searchTerm.toLowerCase().trim()),
   );

   return (
      <>
         <button
            type="button"
            onClick={handleOpen}
            className="flex w-full max-w-[180px] items-center gap-[10px] rounded-[7px] border border-[#d9d9e9] bg-white px-[18px] py-[12px] text-[#3e445a] shadow-[0_2px_4px_rgba(0,0,0,0.05)] text-left"
         >
            <div className="flex flex-col min-w-0 flex-1">
               <span className="block text-[10px] opacity-50 text-[#3e445a]">
                  Vị trí của bạn
               </span>
               <span className="block truncate text-[13px] font-semibold text-[#233a95]">
                  {selectedLocation
                     ? selectedLocation.name
                     : "Chọn tỉnh/thành phố"}
               </span>
            </div>
            <ChevronDownIcon className="w-[16px] h-auto ml-auto text-gray-400 text-2" />
         </button>
         {isOpen && (
            <div className="fixed inset-0 z-50 flex-center p-4">
               <div className="relative w-full max-w-[420px] rounded-[7px] bg-white p-[30px] shadow-2xl transition-all will-change-transform">
                  {/* Close Button */}
                  <button
                     type="button"
                     className="absolute right-[15px] top-[15px] p-1.5 text-gray-400 hover:text-gray-700 transition-colors"
                     aria-label="Close"
                     onClick={handleClose}
                  >
                     <XMarkIcon className="w-[26px] h-auto" />
                  </button>

                  {/* Dialog Titles */}
                  <h6 className="mb-2 text-base font-bold text-[#202435]">
                     Chọn tỉnh/thành phố
                  </h6>
                  <p className="mb-5 text-xs text-gray-500">
                     Chọn tỉnh/thành phố của bạn để xem cước vận chuyển
                  </p>

                  <div className="relative mb-5">
                     <span className="flex-center pointer-events-none absolute left-0 top-0 h-12 w-12 text-gray-400">
                        <MagnifyingGlassIcon className="w-[16px] h-auto" />
                     </span>
                     <input
                        type="text"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        placeholder="Nhập tên tỉnh/thành phố"
                        autoFocus
                        className="h-12 w-full rounded-[7px] bg-[#f3f4f7] pl-[46px] pr-[15px] font-['Inter',sans-serif] text-[15px] text-[#202435] placeholder:text-gray-400 focus:bg-white focus:outline-none focus:ring-1 focus:ring-blue-500"
                     />
                  </div>

                  <ul className="mt-5 h-[287px] list-none overflow-y-auto divide-y divide-black/5 pr-1">
                     {filteredLocations.length > 0 ? (
                        filteredLocations.map((item) => {
                           const isSelected = selectedLocation?.id === item.id;
                           return (
                              <li
                                 key={item.id}
                                 onClick={() => onSelectLocation(item)}
                                 className={`flex cursor-pointer items-center justify-between gap-[15px] py-3 text-[14px] transition-colors hover:text-blue-600 ${
                                    isSelected
                                       ? "text-blue-600 font-medium"
                                       : "text-[#3e445a]"
                                 }`}
                              >
                                 <span className="truncate">{item.name}</span>
                                 <span className="mr-[10px] shrink-0 rounded-[30px] border border-[#e2e2ec] px-[10px] py-[3px] text-[10px] font-medium text-[#9b9bb4]">
                                    Min: {item.minPrice}
                                 </span>
                              </li>
                           );
                        })
                     ) : (
                        <li className="py-6 text-center text-xs text-gray-400">
                           Không tìm thấy tỉnh/thành phố nào
                        </li>
                     )}
                  </ul>
               </div>
            </div>
         )}
      </>
   );
}

function Header() {
   return (
      <>
         <header className="w-full">
            <div className="bg-[#233a95] py-2">
               <div className="container">
                  <p className="my-0 text-center text-xs font-medium text-white">
                     Hi, Chào mừng bạn đến với cửa hàng cửa chúng tôi!
                  </p>
               </div>
            </div>
            <div className="w-full h-auto py-[15px]">
               <div className="container">
                  <div className="flex items-center gap-[15px] justify-between">
                     <Link
                        to={"/"}
                        className="max-w-[164px] shrink-0 flex items-center"
                     >
                        <img src={Logo} alt="Logo" />
                     </Link>
                     <div className="flex gap-[15px] flex-1 max-w-[790px]">
                        <ProvinceDropdown />
                        <SearchBar />
                     </div>
                     <div className="flex items-center gap-[15px]">
                        <PersonalAccount />
                        <MiniCart />
                     </div>
                  </div>
               </div>
            </div>
            <div className="w-full border-b border-[#e3e4e6]">
               <div className="container">
                  <div className="flex justify-between items-center gap-[30px]">
                     <CategoryDropdown />
                     <Navigation />
                  </div>
               </div>
            </div>
         </header>
      </>
   );
}

export default Header;
