import { useState } from "react";
import useUIContext from "../../../../context/UIContext";
import {
   ChevronDownIcon,
   MagnifyingGlassIcon,
   XMarkIcon,
} from "@heroicons/react/24/outline";

const LOCATIONS = [
   { id: "ha-noi", name: "Hà Nội", minPrice: "$130" },
   { id: "bac-ninh", name: "Bắc Ninh", minPrice: "$120" },
   { id: "thanh-hoa", name: "Thanh Hóa", minPrice: "$150" },
   { id: "hai-phong", name: "Hải Phòng", minPrice: "$110" },
   { id: "nghe-an", name: "Nghệ An", minPrice: "$140" },
   { id: "quang-nam", name: "Quảng Nam", minPrice: "$160" },
   { id: "ha-tinh", name: "Hà Tĩnh", minPrice: "$160" },
   { id: "quang-ninh", name: "Quảng Ninh", minPrice: "$160" },
   { id: "hung-yen", name: "Hưng Yên", minPrice: "$160" },
   { id: "ho-chi-minh", name: "Hồ Chí Minh", minPrice: "$160" },
   { id: "dong-nai", name: "Đồng Nai", minPrice: "$160" },
];

function Dialog({ onClose, onSelectLocation, selectedLocation }) {
   const [searchTerm, setSearchTerm] = useState("");

   const filteredLocations = LOCATIONS.filter((item) =>
      item.name.toLowerCase().includes(searchTerm.toLowerCase().trim()),
   );

   return (
      <div className="fixed inset-0 z-50 flex-center p-4">
         <div className="relative w-full max-w-[420px] rounded-[7px] bg-white p-[30px] shadow-2xl transition-all will-change-transform">
            {/* Close Button */}
            <button
               type="button"
               className="absolute right-[15px] top-[15px] p-1.5 text-gray-400 hover:text-gray-700 transition-colors"
               aria-label="Close"
               onClick={onClose}
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
   );
}

function Dropdown({ onOpen, selectedLocation }) {
   // const { handleOpen } = useUIContext();
   return (
      <button
         type="button"
         onClick={onOpen}
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
   );
}

export default function ProvinceDropdown() {
   const [isOpen, setIsOpen] = useState(false);
   const [selectedLocation, setSelectedLocation] = useState("");

   const handleOpen = () => setIsOpen(true);
   const handleClose = () => setIsOpen(false);

   const handleSelectLocation = (location) => {
      setSelectedLocation(location);
      setIsOpen(false);
   };

   return (
      <>
         <Dropdown onOpen={handleOpen} selectedLocation={selectedLocation} />
         {isOpen && (
            <Dialog
               onClose={handleClose}
               onSelectLocation={handleSelectLocation}
               selectedLocation={selectedLocation}
            />
         )}
      </>
   );
}
