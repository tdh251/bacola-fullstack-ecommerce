import { Link } from "react-router-dom";
import Logo from "../../assets/images/logo.png";
import ProvinceDropdown from "./ProvinceDropdown";

function Header() {
   return (
      <>
         <header className="w-full">
            <div className="bg-[#233a95] py-2">
               <div className="container mx-auto px-4">
                  <p className="my-0 text-center text-xs font-medium text-white">
                     Hi, Chào mừng bạn đến với cửa hàng cửa chúng tôi!
                  </p>
               </div>
            </div>
            <div className="w-full h-auto py-[15px]">
               <div className="container mx-auto">
                  <div className="">
                     <div className="max-w-[164px] shrink-0 flex items-center">
                        <Link to={"/"}>
                           <img src={Logo} alt="Logo" />
                        </Link>
                     </div>
                     <div className="headerForm flex">
                        <ProvinceDropdown />
                     </div>
                  </div>
               </div>
            </div>
         </header>
      </>
   );
}

export default Header;
