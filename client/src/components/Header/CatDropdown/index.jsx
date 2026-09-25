import { Bars3Icon, ChevronDownIcon } from "@heroicons/react/24/outline";

function CatDropdown() {
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

export default CatDropdown;
