import { Link } from "react-router-dom";
import Logo from "../../assets/images/logo.png";
import LocationSelector from "./LocationSelector";
import SearchBar from "./SearchBar";
import PersonalAccount from "./PersonalAccount";
import Navigation from "./Navigation";
import { FaCartShopping } from "react-icons/fa6";
import CatSelector from "./CatSelector";

function Header() {
   return (
      <>
         <div className="headerWrapper">
            <div className="top-strip bg-blue">
               <div className="container">
                  <p className="mt-0 mb-0 text-center">
                     Hi, Chào mừng bạn đến với cửa hàng cửa chúng tôi!
                  </p>
               </div>
            </div>
            <header className="header">
               <div className="container">
                  <div className="row headerRow">
                     <div className="logoWrapper d-flex align-items-center">
                        <Link to={"/"}>
                           <img className="logo" src={Logo} alt="Logo" />
                        </Link>
                     </div>
                     <div className="headerForm d-flex">
                        <LocationSelector />
                        <SearchBar />
                     </div>
                     <div className="headerUser d-flex">
                        <PersonalAccount />
                        <div className="cartIcon d-flex align-items-center">
                           <span className="amount">$0.00</span>
                           <button className="button d-flex-center">
                              <span className="quantity d-inline-flex-center">
                                 0
                              </span>
                              <FaCartShopping />
                           </button>
                        </div>
                     </div>
                  </div>
                  <div className="row headerRow">
                     <CatSelector />
                     <Navigation />
                  </div>
               </div>
               <hr className="headerLine" />
            </header>
         </div>
      </>
   );
}

export default Header;
