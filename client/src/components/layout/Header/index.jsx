import { Link } from "react-router-dom";
import Logo from "../../../assets/images/logo.png";
import ProvinceDropdown from "./ProvinceDropdown";
import {
   Bars3Icon,
   ChevronDownIcon,
   ShoppingBagIcon,
   UserIcon,
   MagnifyingGlassIcon,
   XMarkIcon,
} from "@heroicons/react/24/outline";

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
               <a
                  href="/"
                  className="px-[18px] leading-[40px] font-dosis font-semibold uppercase cursor-pointer text-[#3e445a]"
               >
                  Sản phẩm
               </a>
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
