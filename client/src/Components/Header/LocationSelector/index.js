import { FaAngleDown } from "react-icons/fa";

function LocationSelector() {
   return (
      <button className="countryDropdown d-flex align-items-center">
         <div className="location-info">
            <span className="d-block location-label">Your Location</span>
            <span className="d-block text-left current-location">Vietnam</span>
         </div>
         <span className="ml-auto">
            <FaAngleDown />
         </span>
      </button>
   );
}

export default LocationSelector;
