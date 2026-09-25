import { Link } from "react-router-dom";
import Logo from "../../assets/images/logo.png";
import ProvinceDropdown from "./ProvinceDropdown";
import SearchBar from "./SearchBar";
import MiniCart from "./MiniCart";
import PersonalAccount from "./PersonalAccount";
import CatDropdown from "./CatDropdown";
import Navigation from "./Navigation";

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
                     <CatDropdown />
                     <Navigation />
                  </div>
               </div>
            </div>
         </header>
      </>
   );
}

export default Header;
