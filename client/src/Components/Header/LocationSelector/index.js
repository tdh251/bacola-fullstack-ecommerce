import { useState } from "react";
import { FaAngleDown, FaCloudscale, FaSearch } from "react-icons/fa";

const LOCATIONS = [
   { id: "alabama", name: "Hà Nội", minPrice: "$130" },
   { id: "alaska", name: "Bắc Ninh", minPrice: "$120" },
   { id: "arizona", name: "Thanh Hóa", minPrice: "$150" },
   { id: "california", name: "Hải Phòng", minPrice: "$110" },
   { id: "colorado", name: "Nghệ An", minPrice: "$140" },
   { id: "florida", name: "Quảng Nam", minPrice: "$160" },
   { id: "florida", name: "Hà Tĩnh", minPrice: "$160" },
   { id: "florida", name: "Quảng Ninh", minPrice: "$160" },
   { id: "florida", name: "Hưng Yên", minPrice: "$160" },
   { id: "florida", name: "Hồ Chí Minh", minPrice: "$160" },
   { id: "florida", name: "Đồng Nai", minPrice: "$160" },
];

function LocationDialog({ onClose }) {
   return (
      <>
         <div className="bodyOverlay" onClick={onClose}></div>
         <div className="dialogWrapper d-flex-center">
            <div className="dialogContainer">
               <button
                  className="btnClose"
                  aria-label="Close"
                  onClick={onClose}
               >
                  <FaCloudscale />
               </button>

               <h6 className="dialogTitle">Chọn tỉnh/thành phố</h6>
               <p className="dialogSubtitle">
                  Chọn tỉnh/thành phố của bạn để tính giá ship
               </p>
               <div className="dialogSearch">
                  <div className="searchProvince">
                     <span className="searchIcon d-inline-flex-center">
                        <FaSearch />
                     </span>
                     <input
                        type="text"
                        name="s"
                        className="searchField"
                        placeholder="Nhập tên tỉnh/thành phố"
                     />
                  </div>
               </div>

               {/* Danh sách địa điểm */}
               <ul className="dialogList">
                  {LOCATIONS.map((item) => {
                     return (
                        <li className="d-flex cursor-pointer">
                           <span className="location-name">{item.name}</span>
                           <span className="min-badge">
                              Min: {item.minPrice}
                           </span>
                        </li>
                     );
                  })}
               </ul>
            </div>
         </div>
      </>
   );
}

function LocationSelector() {
   const [isShow, setIsShow] = useState(false);
   const handleOpen = (e) => {
      e.preventDefault();
      setIsShow(true);
   };
   const handleClose = (e) => {
      e.preventDefault();
      setIsShow(false);
   };
   return (
      <>
         <button
            className="countryDropdown d-flex align-items-center"
            onClick={handleOpen}
         >
            <div className="location-info">
               <span className="d-block location-label">Your Location</span>
               <span className="d-block text-left current-location">
                  Vietnam
               </span>
            </div>
            <span className="ml-auto">
               <FaAngleDown />
            </span>
         </button>
         {isShow && <LocationDialog onClose={handleClose} />}
      </>
   );
}

export default LocationSelector;
